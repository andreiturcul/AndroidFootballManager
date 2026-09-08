package com.example.footballmanager.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.footballmanager.ui.myteam.MyTeamScreen
import com.example.footballmanager.ui.players.PlayerDetailScreen
import com.example.footballmanager.ui.players.PlayersScreen
import com.example.footballmanager.ui.profile.ProfileScreen
import com.example.footballmanager.ui.teams.TeamsScreen
import com.example.footballmanager.ui.transfers.ProposeTransferScreen
import com.example.footballmanager.ui.transfers.TransfersScreen

private sealed class BottomTab(val route: String, val label: String, val icon: androidx.compose.ui.graphics.vector.ImageVector) {
    object Teams : BottomTab("home_teams", "Teams", Icons.Filled.Shield)
    object Players : BottomTab("home_players", "Players", Icons.Filled.Groups)
    object Transfers : BottomTab("home_transfers", "Transfers", Icons.Filled.SwapHoriz)
    object MyTeam : BottomTab("home_myteam", "My Team", Icons.Filled.SportsSoccer)
    object Profile : BottomTab("home_profile", "Profile", Icons.Filled.Person)
}

private val tabs = listOf(BottomTab.Teams, BottomTab.Players, BottomTab.Transfers, BottomTab.MyTeam, BottomTab.Profile)

@Composable
fun HomeScreen(currentUserId: Long, onLoggedOut: () -> Unit) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            val backStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = backStackEntry?.destination?.route
            NavigationBar {
                tabs.forEach { tab ->
                    NavigationBarItem(
                        selected = currentRoute == tab.route,
                        onClick = {
                            navController.navigate(tab.route) {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(tab.icon, contentDescription = tab.label) },
                        label = { Text(tab.label) }
                    )
                }
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = BottomTab.Teams.route,
            modifier = Modifier.padding(padding)
        ) {
            composable(BottomTab.Teams.route) {
                TeamsScreen(onTeamSelected = {
                    navController.navigate(BottomTab.Players.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                    }
                })
            }
            composable(BottomTab.Players.route) {
                PlayersScreen(onPlayerClick = { playerId ->
                    navController.navigate("player_detail/$playerId")
                })
            }
            composable("player_detail/{playerId}") { backStackEntry ->
                val playerId = backStackEntry.arguments?.getString("playerId")?.toLongOrNull() ?: return@composable
                PlayerDetailScreen(
                    playerId = playerId,
                    onProposeTransfer = { pid -> navController.navigate("propose_transfer/$pid") },
                    onBack = { navController.popBackStack() }
                )
            }
            composable("propose_transfer/{playerId}") { backStackEntry ->
                val playerId = backStackEntry.arguments?.getString("playerId")?.toLongOrNull() ?: return@composable
                ProposeTransferScreen(
                    playerId = playerId,
                    currentUserId = currentUserId,
                    onDone = { navController.popBackStack(BottomTab.Players.route, inclusive = false) }
                )
            }
            composable(BottomTab.Transfers.route) {
                TransfersScreen(currentUserId = currentUserId)
            }
            composable(BottomTab.MyTeam.route) {
                MyTeamScreen(currentUserId = currentUserId)
            }
            composable(BottomTab.Profile.route) {
                ProfileScreen(currentUserId = currentUserId, onLoggedOut = onLoggedOut)
            }
        }
    }
}

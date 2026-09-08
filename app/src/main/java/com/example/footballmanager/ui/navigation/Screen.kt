package com.example.footballmanager.ui.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home")               // hosts the bottom nav
    object Players : Screen("players")
    object Teams : Screen("teams")
    object Transfers : Screen("transfers")
    object ProposeTransfer : Screen("propose_transfer")
    object MyTeam : Screen("my_team")
    object Profile : Screen("profile")

    object PlayerDetail : Screen("player_detail/{playerId}") {
        fun createRoute(playerId: Long) = "player_detail/$playerId"
    }
}

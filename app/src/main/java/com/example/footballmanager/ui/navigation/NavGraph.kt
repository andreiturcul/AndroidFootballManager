package com.example.footballmanager.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.footballmanager.ui.auth.LoginScreen
import com.example.footballmanager.ui.auth.RegisterScreen
import com.example.footballmanager.ui.home.HomeScreen
import com.example.footballmanager.ui.splash.SplashScreen

/**
 * Top-level Navigation Component graph: Splash -> (Login/Register) -> Home.
 * Home itself hosts a second, nested NavHost for the bottom-nav tabs.
 */
@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route) {
            SplashScreen(
                onSessionFound = { userId ->
                    navController.navigate("home/$userId") {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                },
                onNoSession = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = { userId ->
                    navController.navigate("home/$userId") {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onGoToRegister = { navController.navigate(Screen.Register.route) }
            )
        }
        composable(Screen.Register.route) {
            RegisterScreen(
                onRegisterSuccess = { userId ->
                    navController.navigate("home/$userId") {
                        popUpTo(Screen.Register.route) { inclusive = true }
                    }
                },
                onGoToLogin = { navController.popBackStack() }
            )
        }
        composable("home/{userId}") { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")?.toLongOrNull() ?: 0L
            HomeScreen(
                currentUserId = userId,
                onLoggedOut = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }
    }
}

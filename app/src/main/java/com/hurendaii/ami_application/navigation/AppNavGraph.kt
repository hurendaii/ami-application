package com.hurendaii.ami_application.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hurendaii.ami_application.ui.*

sealed class Screen(val route: String) {
    object Main : Screen("main_screen")
    object Username : Screen("username_screen")
    object UsernameGreet : Screen("username_greet_screen/{userName}") {
        fun createRoute(userName: String) = "username_greet_screen/$userName"
    }
    object Stickman : Screen("stickman_screen")
    object FriendChosen : Screen("friend_chosen_screen/{friendName}") {
        fun createRoute(friendName: String) = "friend_chosen_screen/$friendName"
    }
    object Setup : Screen("setup_screen/{friendName}") {
        fun createRoute(friendName: String) = "setup_screen/$friendName"
    }
    object Casa : Screen("casa_screen/{friendName}") {
        fun createRoute(friendName: String) = "casa_screen/$friendName"
    }

    object Shop : Screen("shop_screen/{friendName}") {
        fun createRoute(friendName: String) = "shop_screen/$friendName"
    }
    object Profile : Screen("profile_screen/{friendName}") {
        fun createRoute(friendName: String) = "profile_screen/$friendName"
    }
    object Health : Screen("health_screen/{friendName}") {
        fun createRoute(friendName: String) = "health_screen/$friendName"
    }
    object Games : Screen("games_screen/{friendName}") {
        fun createRoute(friendName: String) = "games_screen/$friendName"
    }
    object Track : Screen("track_screen/{friendName}") {
        fun createRoute(friendName: String) = "track_screen/$friendName"
    }
    object Settings : Screen("settings_screen/{friendName}") {
        fun createRoute(friendName: String) = "settings_screen/$friendName"
    }
}

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Main.route
    ) {
        composable(Screen.Main.route) {
            MainScreen {
                navController.navigate(Screen.Username.route)
            }
        }

        composable(Screen.Username.route) {
            UsernameInputScreen { userName ->
            navController.navigate(Screen.UsernameGreet.createRoute(userName))
            }
        }

        composable(Screen.UsernameGreet.route) { backStackEntry ->
            val userName = backStackEntry.arguments?.getString("userName") ?: "User"
            GreetingScreen(userName) {
                navController.navigate(Screen.Stickman.route)
            }
        }

        composable(Screen.Stickman.route) {
            StickmanScreen { friendName ->
                navController.navigate(Screen.Setup.createRoute(friendName))
            }
        }

        composable(Screen.Setup.route) { backStackEntry ->
            val friendName = backStackEntry.arguments?.getString("friendName") ?: "Friend"
            SetupScreen(friendName) {
                navController.navigate(Screen.Casa.createRoute(it))
            }
        }

        composable(Screen.Casa.route) { backStackEntry ->
            val friendName = backStackEntry.arguments?.getString("friendName") ?: "Friend"
            CasaScreen(friendName = friendName) { route ->
                navController.navigate(route)
            }
        }

        // All blank screens with return to casa
        listOf(
            Screen.Shop,
            Screen.Profile,
            Screen.Health,
            Screen.Games,
            Screen.Settings
        ).forEach { screen ->
            composable(screen.route) { backStackEntry ->
                val friendName = backStackEntry.arguments?.getString("friendName") ?: "Friend"
                BlankScreen(
                    title = screen.route.substringBefore("_screen").capitalize(),
                    onReturnToCasa = {
                        navController.navigate(Screen.Casa.createRoute(friendName)) {
                            popUpTo(Screen.Casa.route) { inclusive = false }
                        }
                    }
                )
            }
        }

        composable(Screen.FriendChosen.route) { backStackEntry ->
            val friendName = backStackEntry.arguments?.getString("friendName") ?: "Unknown"
            FriendChosenScreen(friendName, navController)
        }
        
        composable(Screen.Track.route) { backStackEntry ->
            val friendName = backStackEntry.arguments?.getString("friendName") ?: "Friend"
            TrackScreen(friendName = friendName) {
                navController.navigate(Screen.Casa.createRoute(friendName)) {
                    popUpTo(Screen.Casa.route) { inclusive = false }
                }
            }
        }
    }
}

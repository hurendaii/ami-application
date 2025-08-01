package com.hurendaii.ami_application.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.hurendaii.ami_application.ui.*
import androidx.lifecycle.viewmodel.compose.viewModel



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
    object Tasks : Screen("tasks_screen/{friendName}") {
        fun createRoute(friendName: String) = "tasks_screen/$friendName"
    }
    object AMI : Screen("ami_screen/{friendName}") {
        fun createRoute(friendName: String) = "ami_screen/$friendName"
    }
    object Feed : Screen("feed_screen/{friendName}") {
        fun createRoute(friendName: String) = "feed_screen/$friendName"
    }
}

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController()
) { 
    val amiViewModel: AmiViewModel = viewModel()
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
            SetupScreen(
                amiViewModel = amiViewModel,
                currentFriendName = friendName,
                onNameSubmitted = {
                    val updatedName = amiViewModel.amiModel.name
                    navController.navigate(Screen.Casa.createRoute(updatedName))
                }
            )
        }

        composable(Screen.Casa.route) { backStackEntry ->
            val friendName = backStackEntry.arguments?.getString("friendName") ?: "Friend"
            CasaScreen(
                amiViewModel = amiViewModel,
                friendName = friendName,
                onNavigate = { route -> navController.navigate(route) }
            )
        }

        composable(Screen.Health.route) { backStackEntry ->
            val friendName = backStackEntry.arguments?.getString("friendName") ?: "Friend"
            HealthScreen(
                friendName = friendName,
                onTasksClick = {
                    navController.navigate(Screen.Tasks.createRoute(friendName))
                },
                onMyAmiClick = {
                    navController.navigate(Screen.AMI.createRoute(friendName))  // <--- Add this
                },
                onReturnToCasa = {
                    navController.navigate(Screen.Casa.createRoute(friendName)) {
                        popUpTo(Screen.Casa.route) { inclusive = false }
                    }
                }
            )
        }

        composable(
            route = Screen.Tasks.route,
            arguments = listOf(navArgument("friendName") { type = NavType.StringType })
        ) { backStackEntry ->
            val friendName = backStackEntry.arguments?.getString("friendName") ?: "Friend"
            TasksScreen(friendName = friendName) {
                navController.navigate(Screen.Health.createRoute(friendName)) {
                    popUpTo(Screen.Health.route) { inclusive = false }
                }
            }
        }

        listOf(
            Screen.Shop,
            Screen.Profile,
            Screen.Games,
            Screen.Settings
        ).forEach { screen ->
            composable(screen.route) { backStackEntry ->
                val friendName = backStackEntry.arguments?.getString("friendName") ?: "Friend"
                BlankScreen(
                    title = screen.route.substringBefore("_screen").replaceFirstChar { it.uppercase() },
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

        composable(
            route = Screen.AMI.route,
            arguments = listOf(navArgument("friendName") { type = NavType.StringType })
        ) { backStackEntry ->
            val friendName = backStackEntry.arguments?.getString("friendName") ?: "Friend"
            AMIScreen(
                friendName = friendName,  // <--- Pass friendName here
                onBack = {
                    navController.navigate(Screen.Health.createRoute(friendName)) {
                        popUpTo(Screen.Health.route) { inclusive = false }
                    }
                }
            )
        }
        composable(
            route = Screen.Feed.route,
            arguments = listOf(navArgument("friendName") { type = NavType.StringType })
        ) { backStackEntry ->
            val friendName = backStackEntry.arguments?.getString("friendName") ?: "Friend"
            FeedScreen(friendName = friendName) {
                navController.navigate(Screen.Casa.createRoute(friendName)) {
                    popUpTo(Screen.Casa.route) { inclusive = false }
                }
            }
        }

    }
}

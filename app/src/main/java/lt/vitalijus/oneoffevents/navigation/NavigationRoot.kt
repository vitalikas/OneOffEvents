package lt.vitalijus.oneoffevents.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import lt.vitalijus.oneoffevents.login.presentation.LoginScreenRoot
import lt.vitalijus.oneoffevents.profile.presentation.user_profile.UserProfileScreenRoot

@Composable
fun NavigationRoot(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = NavigationRoute.Login,
    ) {
        composable<NavigationRoute.Login> {
            LoginScreenRoot(
                onNavigateToScreen = {
                    navController.navigate(NavigationRoute.UserProfile)
                },
                onSystemGoBackClick = { navController.navigateUp() }
            )
        }

        composable<NavigationRoute.UserProfile> {
            UserProfileScreenRoot(
                onNavigateToLogin = {
                    navController.navigate(NavigationRoute.Login)
                }
            )
        }
    }
}

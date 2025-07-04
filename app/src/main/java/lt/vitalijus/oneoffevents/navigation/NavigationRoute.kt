package lt.vitalijus.oneoffevents.navigation

import kotlinx.serialization.Serializable

interface NavigationRoute {

    @Serializable
    data object UserProfile : NavigationRoute

    @Serializable
    data object Login : NavigationRoute
}

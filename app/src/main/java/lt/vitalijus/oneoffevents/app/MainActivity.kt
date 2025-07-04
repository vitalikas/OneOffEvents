package lt.vitalijus.oneoffevents.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import lt.vitalijus.oneoffevents.core.presentation.design_system.theme.OneOffEventsTheme
import lt.vitalijus.oneoffevents.navigation.NavigationRoot

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OneOffEventsTheme {
                NavigationRoot(
                    navController = rememberNavController()
                )
            }
        }
    }
}

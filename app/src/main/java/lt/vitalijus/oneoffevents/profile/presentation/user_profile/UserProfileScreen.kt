@file:OptIn(ExperimentalMaterial3Api::class)

package lt.vitalijus.oneoffevents.profile.presentation.user_profile

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import lt.vitalijus.oneoffevents.core.presentation.util.GenericAutoConsumingEffectHandler
import org.koin.androidx.compose.koinViewModel

@Composable
fun UserProfileScreenRoot(
    viewModel: UserProfileViewModel = koinViewModel(),
    onNavigateToLogin: () -> Unit
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    GenericAutoConsumingEffectHandler(
        viewModel = viewModel,
        onEffect = { effect ->
            when (effect) {
                UserProfileEffect.NavigateToScreen -> onNavigateToLogin()
                is UserProfileEffect.ShowError -> showError(context = context, effect = effect)
            }
        }
    )

    UserProfileScreen(
        uiState = uiState,
        onProcessEvent = viewModel::handleEvent
    )
}

@Composable
private fun UserProfileScreen(
    uiState: UserProfileUiState,
    onProcessEvent: (event: UserProfileEvent) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profile") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Text("Deleting account...")
            } else {
                Button(
                    onClick = { onProcessEvent(UserProfileEvent.DeleteAccountClicked) }
                ) {
                    Text("Delete Account")
                }
            }
        }
    }
}

private fun showError(
    context: Context,
    effect: UserProfileEffect.ShowError
) {
    Toast.makeText(
        context,
        effect.message,
        Toast.LENGTH_LONG
    ).show()
}

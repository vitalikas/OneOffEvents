package lt.vitalijus.oneoffevents.login.presentation

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import lt.vitalijus.oneoffevents.core.presentation.util.DeviceConfiguration
import lt.vitalijus.oneoffevents.core.presentation.util.GenericAutoConsumingEffectHandler
import lt.vitalijus.oneoffevents.login.presentation.components.NoteMarkButton
import lt.vitalijus.oneoffevents.login.presentation.components.NoteMarkLink
import lt.vitalijus.oneoffevents.login.presentation.components.NoteMarkTextField
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreenRoot(
    viewModel: LoginViewModel = koinViewModel(),
    onNavigateToScreen: () -> Unit,
    onSystemGoBackClick: () -> Unit
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    GenericAutoConsumingEffectHandler(
        viewModel = viewModel,
        onEffect = { effect ->
            when (effect) {
                LoginEffect.NavigateToScreen -> onNavigateToScreen()
                is LoginEffect.ShowError -> showErrorMessage(context = context, effect = effect)
            }
        }
    )

    LoginScreen(
        state = uiState,
        onEvent = viewModel::handleEvent,
        onSystemGoBackClick = onSystemGoBackClick
    )
}

@Composable
private fun LoginScreen(
    state: LoginUiState,
    onEvent: (loginEvent: LoginEvent) -> Unit,
    onSystemGoBackClick: () -> Unit
) {
    var emailText by remember { mutableStateOf("") }
    var passwordText by remember { mutableStateOf("") }


    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        contentWindowInsets = WindowInsets.statusBars
    ) { innerPadding ->

        val rootModifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .clip(
                MaterialTheme.shapes.large.copy(
                    bottomStart = CornerSize(0.dp),
                    bottomEnd = CornerSize(0.dp)
                )
            )
            .background(MaterialTheme.colorScheme.surfaceContainerLowest)
            .padding(
                horizontal = 16.dp,
                vertical = 24.dp
            )
            .consumeWindowInsets(WindowInsets.navigationBars)

        val windowsSizeClass = currentWindowAdaptiveInfo().windowSizeClass
        val deviceConfiguration = DeviceConfiguration.fromWindowSizeClass(
            windowSizeClass = windowsSizeClass
        )
        when (deviceConfiguration) {
            DeviceConfiguration.MOBILE_PORTRAIT -> {
                Column(
                    modifier = rootModifier,
                    verticalArrangement = Arrangement.spacedBy(32.dp)
                ) {
                    LoginHeaderSection(
                        modifier = Modifier
                            .fillMaxWidth()
                    )

                    LoginFormSection(
                        emailText = emailText,
                        onEmailTextChange = { emailText = it },
                        passwordText = passwordText,
                        onPasswordTextChange = { passwordText = it },
                        onLoginClick = { onEvent(LoginEvent.LoginButtonClicked) },
                        isLoading = state.isLoading,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }

            DeviceConfiguration.MOBILE_LANDSCAPE -> {
                Row(
                    modifier = rootModifier
                        .windowInsetsPadding(WindowInsets.displayCutout)
                        .padding(horizontal = 32.dp),
                    horizontalArrangement = Arrangement.spacedBy(32.dp)
                ) {
                    LoginHeaderSection(
                        modifier = Modifier
                            .weight(1f)
                    )

                    LoginFormSection(
                        emailText = emailText,
                        onEmailTextChange = { emailText = it },
                        passwordText = passwordText,
                        onPasswordTextChange = { passwordText = it },
                        onLoginClick = { onEvent(LoginEvent.LoginButtonClicked) },
                        isLoading = state.isLoading,
                        modifier = Modifier
                            .weight(1f)
                            .verticalScroll(rememberScrollState())
                    )
                }
            }

            DeviceConfiguration.TABLET_PORTRAIT,
            DeviceConfiguration.TABLET_LANDSCAPE,
            DeviceConfiguration.DESKTOP -> {
                Column(
                    modifier = rootModifier
                        .verticalScroll(rememberScrollState())
                        .padding(top = 48.dp),
                    verticalArrangement = Arrangement.spacedBy(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LoginHeaderSection(
                        modifier = Modifier
                            .widthIn(max = 540.dp),
                        alignment = Alignment.CenterHorizontally
                    )

                    LoginFormSection(
                        emailText = emailText,
                        onEmailTextChange = { emailText = it },
                        passwordText = passwordText,
                        onPasswordTextChange = { passwordText = it },
                        onLoginClick = { onEvent(LoginEvent.LoginButtonClicked) },
                        isLoading = state.isLoading,
                        modifier = Modifier
                            .widthIn(max = 540.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun LoginHeaderSection(
    modifier: Modifier = Modifier,
    alignment: Alignment.Horizontal = Alignment.Start,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = alignment
    ) {
        Text(
            text = "Log In",
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = "Capture your thoughts and ideas",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun LoginFormSection(
    emailText: String,
    onEmailTextChange: (String) -> Unit,
    passwordText: String,
    onPasswordTextChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    isLoading: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        NoteMarkTextField(
            text = emailText,
            onValueChange = onEmailTextChange,
            label = "Email",
            hint = "john.doe@example.com",
            isInputSecure = false
        )

        Spacer(modifier = Modifier.height(16.dp))

        NoteMarkTextField(
            text = passwordText,
            onValueChange = onPasswordTextChange,
            label = "Password",
            hint = "Password",
            isInputSecure = true
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
        } else {
            NoteMarkButton(
                text = "Log In",
                onClick = onLoginClick,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            NoteMarkLink(
                text = "Don't have an account? Sign Up",
                onClick = {

                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}

private fun showErrorMessage(
    context: Context,
    effect: LoginEffect.ShowError
) {
    Toast.makeText(
        context,
        effect.message,
        Toast.LENGTH_LONG
    ).show()
}

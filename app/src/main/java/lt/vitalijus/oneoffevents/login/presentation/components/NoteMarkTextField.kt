package lt.vitalijus.oneoffevents.login.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import lt.vitalijus.oneoffevents.R

@Composable
fun NoteMarkTextField(
    text: String,
    onValueChange: (String) -> Unit,
    label: String,
    hint: String,
    isInputSecure: Boolean,
    modifier: Modifier = Modifier
) {
    var isPasswordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(6.dp))

        OutlinedTextField(
            value = text,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth(),
            visualTransformation = if (isInputSecure && !isPasswordVisible) {
                PasswordVisualTransformation(mask = '*')
            } else {
                VisualTransformation.None
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                cursorColor = MaterialTheme.colorScheme.primary,
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = Color.Transparent
            ),
            placeholder = {
                Text(
                    text = hint,
                    style = MaterialTheme.typography.bodyLarge
                )
            },
            textStyle = MaterialTheme.typography.bodyLarge,
            shape = MaterialTheme.shapes.medium,
            trailingIcon = {
                if (isInputSecure) {
                    IconButton(
                        onClick = {
                            isPasswordVisible = !isPasswordVisible
                        }
                    ) {
                        when {
                            isPasswordVisible -> {
                                Icon(
                                    painter = painterResource(R.drawable.visibility_off),
                                    contentDescription = "Hide password"
                                )
                            }

                            !isPasswordVisible -> {
                                Icon(
                                    painter = painterResource(R.drawable.visibility),
                                    contentDescription = "Show password"
                                )
                            }
                        }
                    }
                }
            }
        )
    }
}

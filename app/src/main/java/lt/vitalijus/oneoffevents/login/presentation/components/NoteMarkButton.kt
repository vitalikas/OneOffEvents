package lt.vitalijus.oneoffevents.login.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NoteMarkButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String? = null,
    enabled: Boolean = true,
    content: (@Composable () -> Unit)? = null
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        contentPadding = PaddingValues(12.dp),
    ) {
        if (content != null) {
            content()
        } else if (text != null) {
            Text(
                text = text,
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}

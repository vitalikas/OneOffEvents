package lt.vitalijus.oneoffevents.core.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun <EFFECT, VM: AutoConsumableEffect<EFFECT>> GenericAutoConsumingEffectHandler(
    viewModel: VM,
    onEffect: suspend (effect: EFFECT) -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    val currentEffect by viewModel.effect.collectAsStateWithLifecycle(null)

    LaunchedEffect(currentEffect, lifecycleOwner) {
        currentEffect?.let {
            onEffect(it)
            viewModel.consumeEffect()
        }
    }
}

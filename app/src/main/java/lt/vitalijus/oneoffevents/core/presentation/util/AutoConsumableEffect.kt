package lt.vitalijus.oneoffevents.core.presentation.util

import kotlinx.coroutines.flow.StateFlow

interface AutoConsumableEffect<EFFECT> {
    val effect: StateFlow<EFFECT?>
    fun postEffect(newEffect: EFFECT)
    fun consumeEffect()
}

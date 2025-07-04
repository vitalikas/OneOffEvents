package lt.vitalijus.oneoffevents.core.presentation.util

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AutoConsumableEffectDelegate<EFFECT> : AutoConsumableEffect<EFFECT> {

    private val _effect = MutableStateFlow<EFFECT?>(null)
    override val effect: StateFlow<EFFECT?>
        get() = _effect.asStateFlow()

    override fun consumeEffect() {
        _effect.update { null }
    }

    override fun postEffect(newEffect: EFFECT) {
        _effect.update { newEffect }
    }
}

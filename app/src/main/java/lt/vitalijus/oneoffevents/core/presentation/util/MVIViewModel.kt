package lt.vitalijus.oneoffevents.core.presentation.util

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class MVIViewModel<STATE, PARTIAL_STATE, INTENT, EFFECT>(
    protected val savedStateHandle: SavedStateHandle? = null,
    protected val effectDelegate: AutoConsumableEffect<EFFECT>
) : ViewModel(), AutoConsumableEffect<EFFECT> by effectDelegate {

    protected abstract val initialState: STATE

    protected abstract val reducer: Reducer<STATE, PARTIAL_STATE>

    private val _uiState by lazy { MutableStateFlow(initialState) }
    val uiState = _uiState.asStateFlow()

    abstract fun handleIntent(intent: INTENT)

    protected fun updateState(partialState: PARTIAL_STATE) {
        _uiState.update { currentState ->
            reducer.reduce(
                state = currentState,
                partialState = partialState
            )
        }
    }
}

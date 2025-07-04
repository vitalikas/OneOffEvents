package lt.vitalijus.oneoffevents.core.presentation.util

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class MVIViewModel<STATE, EVENT, EFFECT>(
    protected val savedStateHandle: SavedStateHandle? = null,
    protected val effectDelegate: AutoConsumableEffect<EFFECT>
) : ViewModel(), AutoConsumableEffect<EFFECT> by effectDelegate {

    abstract val initialState: STATE

    private val _uiState by lazy { MutableStateFlow(initialState) }
    val uiState = _uiState.asStateFlow()

    fun updateUiState(
        updateAction: (currentState: STATE) -> STATE // or updateAction: STATE.() -> STATE as param
    ) {
        _uiState.update(updateAction)
    }

    abstract fun handleEvent(event: EVENT)
}

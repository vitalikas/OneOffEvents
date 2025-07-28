package lt.vitalijus.oneoffevents.core.presentation.util

interface Reducer<S, P> {
    fun reduce(state: S, partialState: P): S
}

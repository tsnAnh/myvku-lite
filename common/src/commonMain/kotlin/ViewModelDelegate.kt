import kotlinx.coroutines.CoroutineScope

interface ViewModelDelegate {
    fun provideCoroutineScope(scope: CoroutineScope)
}

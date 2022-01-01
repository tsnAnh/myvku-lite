package dev.tsnanh.common

import ViewModelDelegate
import dev.tsnanh.common.data.mapper.mapToDomainAbsences
import dev.tsnanh.common.data.remote.api.MyVKULiteApi
import dev.tsnanh.common.models.Absence
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

interface MyVKULiteViewModel : ViewModelDelegate {
    val absences: StateFlow<List<Absence>>
    val makeupClasses: StateFlow<List<Absence>>
    val subjects: StateFlow<List<Absence>>

    fun refreshAbsences()

    fun refreshMakeupClasses()

    fun refreshSubjects()
}

class MyVKULiteViewModelImpl : MyVKULiteViewModel {
    private lateinit var coroutineScope: CoroutineScope

    private var absencesJob: Job? = null

    private val _absences = MutableStateFlow<List<Absence>>(emptyList())
    override val absences: StateFlow<List<Absence>> = _absences.asStateFlow()

    init {
        provideCoroutineScope(CoroutineScope(Dispatchers.Main))
        refreshAbsences()
    }

    override val makeupClasses: StateFlow<List<Absence>>
        get() = TODO("Not yet implemented")

    override val subjects: StateFlow<List<Absence>>
        get() = TODO("Not yet implemented")

    override fun refreshAbsences() {
        absencesJob.cancelIfActive()
        absencesJob = coroutineScope.launch {
            val absences = MyVKULiteApi.getAbsences().mapToDomainAbsences()
            _absences.update { absences }
        }
    }

    override fun refreshMakeupClasses() {
        TODO("Not yet implemented")
    }

    override fun refreshSubjects() {
        TODO("Not yet implemented")
    }

    override fun provideCoroutineScope(scope: CoroutineScope) {
        coroutineScope = scope
    }
}

fun Job?.cancelIfActive() {
    if (this?.isActive == true) cancel()
}

package com.invade.universities.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.invade.core.network.base.Status
import com.invade.core.network.error.ErrorEntity
import com.invade.universities.data.model.University
import com.invade.universities.domain.usecases.GetLocalUniversitiesUseCase
import com.invade.universities.domain.usecases.GetUniversitiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UniversitiesViewModel @Inject constructor(
    private val getUniversitiesUseCase: GetUniversitiesUseCase,
    private val getLocalUniversitiesUseCase: GetLocalUniversitiesUseCase
) : ViewModel() {

    private var viewModelJob: Job? = null

    private val _viewStateUniversities = MutableStateFlow<List<University>>(listOf())
    val viewStateUniversities = _viewStateUniversities.asStateFlow()

    private val _viewStateLoading = MutableStateFlow<Boolean>(false)
    val viewStateLoading = _viewStateLoading.asStateFlow()

    private val _viewStateError = MutableStateFlow<ErrorEntity?>(null)
    val viewStateError = _viewStateError.asStateFlow()


    fun getUniversities(countryName: String) {
        viewModelJob = viewModelScope.launch(Dispatchers.IO) {
            getUniversitiesUseCase(countryName).collect { response ->
                _viewStateLoading.emit(response.loading)
                when (response.status) {
                    Status.ERROR -> {
                        getLocalUniversities(countryName)
                        emitError(response.errorEntity)
                    }
                    Status.SUCCESS -> {
                        val universities = response.data
                        if (universities?.isNotEmpty() == true)
                            _viewStateUniversities.emit(universities)
                        else
                            getLocalUniversities(countryName)
                    }
                    else -> {}
                }
            }
        }
    }

    private fun getLocalUniversities(countryName: String) {
        viewModelJob = viewModelScope.launch(Dispatchers.IO) {
            getLocalUniversitiesUseCase(countryName).collect { response ->
                _viewStateLoading.emit(response.loading)
                when (response.status) {
                    Status.SUCCESS -> {
                        response.data?.let {
                            _viewStateUniversities.emit(it)
                        }
                    }
                    else -> {}
                }
            }
        }
    }

    private suspend fun emitError(errorEntity: ErrorEntity?) {
        _viewStateError.emit(errorEntity)
        _viewStateError.emit(null)
    }
    override fun onCleared() {
        super.onCleared()
        viewModelJob = null
    }
}
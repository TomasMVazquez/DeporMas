package com.applications.toms.depormas.ui.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.applications.toms.depormas.data.model.Event
import com.applications.toms.depormas.data.model.Sport
import com.applications.toms.depormas.data.repository.SportRepository
import com.applications.toms.depormas.utils.Scope
import com.applications.toms.depormas.utils.Scope.ImplementJob
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class HomeViewModel(private val sportRepository: SportRepository): ViewModel(), Scope by ImplementJob() {

    val sports = sportRepository.getSports().asLiveData()

    private val allSports = Sport(-1,"","ic_all_sports",-1, false)

    private val _selectedSport = MutableLiveData<Sport>()
    val selectedSport: LiveData<Sport> get() = _selectedSport

    sealed class UiModel {
        object Loading: UiModel()
        class Content(val events: List<Event>): UiModel()
    }

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) refresh()
            return _model
        }

    init {
        initScope()
        _selectedSport.value = allSports
    }

    private fun refresh(){
        launch {
            _model.value = UiModel.Loading
            _model.value = UiModel.Content(emptyList())
        }
    }

    override fun onCleared() {
        cancelScope()
        super.onCleared()
    }

    fun onSelectSport(sportChecked: Sport?) {
        _selectedSport.value = sportChecked ?: allSports
    }

}
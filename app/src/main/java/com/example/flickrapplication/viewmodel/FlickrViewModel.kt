package com.example.flickrapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flickrapplication.model.FlickrContents
import com.example.flickrapplication.repository.FlickrRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FlickrViewModel : ViewModel() {

    var flickrRepository: FlickrRepository = FlickrRepository.getFlickrRepo()

    //state flow to display grid contents
    private val _displayGridContents = MutableStateFlow<FlickrContents?>(null)
    val displayGridContents: StateFlow<FlickrContents?> = _displayGridContents.asStateFlow()

    //state flow to Display network Error
    private val _displayNetworkError = MutableStateFlow(false)
    val displayNetworkError: StateFlow<Boolean> = _displayNetworkError

    //State flow to Display progress indicator
    private val _showProgressIndicator = MutableStateFlow(false)
    val showProgressIndicator: StateFlow<Boolean?> = _showProgressIndicator.asStateFlow()

    fun getFlickrContents(searchData : String){
        viewModelScope.launch {
            _showProgressIndicator.value = true
            val result = flickrRepository.getFlickrContent(searchData)
            _showProgressIndicator.value = false
            if( result == null){
                _displayNetworkError.value = true
            } else {
                _displayNetworkError.value = false
                _displayGridContents.value  = result
            }
        }
    }
}
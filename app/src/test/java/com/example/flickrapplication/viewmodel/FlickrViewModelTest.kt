package com.example.flickrapplication.viewmodel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.withContext
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class FlickrViewModelTest {

    private lateinit var viewModelTest: FlickrViewModel
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        viewModelTest = FlickrViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun verify_network_call_to_get_flickr_content_returns_data() {
        runTest {
            val result = withContext(Dispatchers.Main){
                viewModelTest.flickrRepository.getFlickrContent("test")
            }
             assertNotNull(result)
        }
    }
}
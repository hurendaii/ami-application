package com.hurendaii.ami_application.ui

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.hurendaii.ami_application.model.AmiModel

class AmiViewModel : ViewModel() {

    private var _amiModel by mutableStateOf(
        AmiModel(name = "Unnamed", hunger = 5, sleep = 5, boredom = 5)
    )
    val amiModel: AmiModel
        get() = _amiModel

    // Success message state
    var successMessage by mutableStateOf("")
        private set

    // Set the name and record success
    fun setName(name: String) {
        _amiModel = _amiModel.copy(name = name)
        successMessage = "Name set to $name"
    }

    // Actions
    fun feed() {
        val newHunger = (_amiModel.hunger - 1).coerceAtLeast(0)
        _amiModel = _amiModel.copy(hunger = newHunger)
        successMessage = "Fed ${_amiModel.name}!"
    }

    fun sleep() {
        val newSleep = (_amiModel.sleep - 1).coerceAtLeast(0)
        _amiModel = _amiModel.copy(sleep = newSleep)
        successMessage = "${_amiModel.name} went to sleep!"
    }

    fun play() {
        val newBoredom = (_amiModel.boredom - 1).coerceAtLeast(0)
        _amiModel = _amiModel.copy(boredom = newBoredom)
        successMessage = "Played with ${_amiModel.name}!"
    }

    // Clear after displaying
    fun clearSuccessMessage() {
        successMessage = ""
    }
}

package com.example.viewmodeldemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.math.roundToInt

class DemoViewModel : ViewModel() {

    private val _isFahrenheit = MutableLiveData(true)
    val isFahrenheit: LiveData<Boolean> = _isFahrenheit

    private val _result = MutableLiveData("")
    val result: LiveData<String> = _result

    fun convertTemp(temp: String) {
        _result.value = try {
            val tempInt = temp.toInt()

            if (_isFahrenheit.value == true) {
                ((tempInt - 32) * 0.5556).roundToInt().toString()
            } else {
                ((tempInt * 1.8) + 32).roundToInt().toString()
            }
        } catch (e: Exception) {
            "Invalid Entry"
        }
    }

    fun switchChange() {
        _isFahrenheit.value = _isFahrenheit.value != true
    }
}
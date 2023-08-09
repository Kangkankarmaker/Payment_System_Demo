package com.kk.cibltask.screens.payment

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class PaymentViewModel : ViewModel() {

    var number by mutableStateOf("")
    var name by mutableStateOf("")
    var amount by mutableStateOf("")
    var narration by mutableStateOf("")


    fun updateNumber(_number: String) {
        number = _number
    }

    fun updateName(_name: String) {
        name = _name
    }

    fun updateAmount(_amount: String) {
        amount = _amount
    }

    fun updateNarration(_narration: String) {
        narration = _narration
    }

    fun validateField(): Boolean {

        if (number.length < 10)
            return false

        if (name.isEmpty())
            return false

        if (amount.isEmpty())
            return false

        if (narration.isEmpty())
            return false

        return true
    }
}
package dev.passerby.effectivetestproject.presentation

import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputEditText
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable

class Utils {
    fun parseInput(input: String?): String {
        return input?.trim() ?: ""
    }
}

fun TextInputEditText.createObservable(): Flowable<String> {
    return Flowable.create({
        addTextChangedListener(object : SimpleTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                it.onNext(s.toString())
            }
        })
    }, BackpressureStrategy.BUFFER)
}

private abstract class SimpleTextWatcher : TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }

    override fun afterTextChanged(s: Editable?) {

    }
}
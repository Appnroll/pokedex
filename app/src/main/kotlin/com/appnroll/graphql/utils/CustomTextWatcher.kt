package com.appnroll.graphql.utils

import android.text.Editable
import android.text.TextWatcher


abstract class CustomTextWatcher: TextWatcher {

    override fun afterTextChanged(s: Editable?) {
        // do noting
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        // do noting
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        // do noting
    }
}
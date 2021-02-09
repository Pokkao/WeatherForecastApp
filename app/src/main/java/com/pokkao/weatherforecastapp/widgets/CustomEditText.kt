package com.pokkao.weatherforecastapp.widgets

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Typeface
import android.os.Build
import android.text.InputType
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.pokkao.weatherforecastapp.R
import com.pokkao.weatherforecastapp.R.styleable
import kotlinx.android.synthetic.main.layout_edit_text_with_label.view.*
import kotlinx.android.synthetic.main.layout_edit_text_with_label.view.errorText

class CustomEditText : FrameLayout {

    private lateinit var errorMessage: String

    constructor(context: Context) : super(context) {
        inflaterLayout(null)
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        inflaterLayout(attributeSet)
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyle: Int) : super(
        context,
        attributeSet,
        defStyle
    ) {
        inflaterLayout(attributeSet)
    }

    private fun inflaterLayout(attributeSet: AttributeSet?) {
        attributeSet?.let {
            val typedArray = context.obtainStyledAttributes(it, styleable.CustomEditText)
            View.inflate(context, R.layout.layout_edit_text_with_label, this)
            setTitle(typedArray)
            setInput(typedArray)
            setAttribute(typedArray)
            typedArray.recycle()
        }

    }


    private fun setTitle(typedArray: TypedArray) {
        val titleString = typedArray.getString(styleable.CustomEditText_ed_title)
        if (titleString.isNullOrEmpty()) {
            title.visibility = View.GONE
        }

        val titleBold = typedArray.getBoolean(styleable.CustomEditText_ed_title_bold, true)
        title.text = titleString
        if (titleBold) {
            title.setTypeface(title.typeface, Typeface.BOLD)
        }
    }

    private fun setInput(typedArray: TypedArray) {
        val inputString = typedArray.getString(styleable.CustomEditText_ed_input)
        val inputHint = typedArray.getString(styleable.CustomEditText_ed_input_hint)
        val inputType = typedArray.getInt(styleable.CustomEditText_ed_input_type, 0)

        editText.setText(inputString)
        setHint(inputHint)

        when (inputType) {
            1 -> {
                editText.inputType = InputType.TYPE_CLASS_NUMBER
            }
        }

    }

    private fun setAttribute(typedArray: TypedArray) {
        val errorString = typedArray.getString(styleable.CustomEditText_ed_error)
        val isRequire = typedArray.getBoolean(styleable.CustomEditText_ed_require, true)
        val hint = typedArray.getString(styleable.CustomEditText_ed_input_hint)
        val errorGone = typedArray.getBoolean(styleable.CustomEditText_ed_error_gone, false)

        editText.hint = title.text
        errorMessage = title.text.toString()
        hint?.let {
            setHint(it)
        }

        if (errorString.isNullOrEmpty()) {
            errorText.text = context.getString(R.string.txt_empty_error_text)
        } else {
            errorText.text = errorString
        }

        if (errorGone) {
            errorText.visibility = View.GONE
        }

        if (isRequire) {
            requireFiled.visibility = View.VISIBLE
        }
    }

    fun showError() {
        errorText.visibility = View.VISIBLE
        setErrorTextColor()
    }

    private fun setErrorTextColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            errorText.setTextColor(
                context.resources.getColor(
                    R.color.color_button_red,
                    context.theme
                )
            )
        } else {
            errorText.setTextColor(context.resources.getColor(R.color.color_button_red))
        }
    }

    fun hideError() {
        errorText.visibility = View.INVISIBLE
    }

    fun setHint(hint: String?) {
        editText.hint = hint
    }

    fun getText(): String {
        return editText.text.toString().trim()
    }

}

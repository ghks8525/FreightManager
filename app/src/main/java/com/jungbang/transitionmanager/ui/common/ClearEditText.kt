package com.jungbang.transitionmanager.ui.common

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import android.graphics.drawable.Drawable
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.jungbang.freightmanager.Utils.Trace
import com.jungbang.transitionmanager.R

class ClearEditText : ConstraintLayout, View.OnClickListener, TextWatcher,
    View.OnFocusChangeListener {

    constructor(context: Context) : super(context) {

    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(context, attrs)
    }

    lateinit var constraintLayout: ConstraintLayout

    lateinit var editText: EditText

    lateinit var iconImageView: ImageView

    lateinit var clearImageView: ImageView

    lateinit var drawableIcon: Drawable

    private fun init(context: Context, attrs: AttributeSet) {

        val v = inflate(context, R.layout.clear_edit_text, this)

        constraintLayout = v.findViewById(R.id.cet_cl)
        editText = v.findViewById(R.id.cet_et)
        iconImageView = v.findViewById(R.id.cet_iv_icon)
        clearImageView = v.findViewById(R.id.cet_iv_clear)

        context.theme.obtainStyledAttributes(attrs, R.styleable.ClearEditText, 0, 0).apply {
            drawableIcon = getDrawable(R.styleable.ClearEditText_drawableIcon)!!
            iconImageView.setImageDrawable(drawableIcon)
            editText.inputType = getInt(R.styleable.ClearEditText_android_inputType, 0)
            editText.hint = getString(R.styleable.ClearEditText_android_hint)
        }

        clearImageView.setOnClickListener(this)
        editText.onFocusChangeListener = this
        editText.addTextChangedListener(this)
        setClearIconVisible(false)
    }

    private fun setClearIconVisible(bool: Boolean) {
        if (bool) {
            clearImageView.visibility = View.VISIBLE
        } else {
            clearImageView.visibility = View.INVISIBLE
        }
    }

    override fun onClick(v: View?) {
        if (v?.id != R.id.cet_iv_clear)
            return

        editText.text.clear()
        setClearIconVisible(false)
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(c: CharSequence?, p1: Int, p2: Int, p3: Int) {
        if (c.isNullOrEmpty()) {
            setClearIconVisible(false)
        } else {
            setClearIconVisible(true)
        }
    }

    override fun afterTextChanged(p0: Editable?) {
    }

    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        constraintLayout.isSelected = hasFocus
        if(hasFocus && editText.text.isNotEmpty())
            setClearIconVisible(true)
        if(!hasFocus)
            setClearIconVisible(false)
    }

    fun getText(): String = editText.text.toString()
}
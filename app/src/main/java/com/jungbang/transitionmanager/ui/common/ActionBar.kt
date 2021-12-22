package com.jungbang.transitionmanager.ui.common

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import com.jungbang.transitionmanager.R

class ActionBar(var listener: onActionBarListener)
{
    companion object {
        const val ACTION_NONE = 0
        const val ACTION_BACK = 1
        const val ACTION_CREATE = 2
        const val ACTION_ADD = 3

        const val ALIGN_LEFT = 5
        const val ALIGN_CENTER = 0
        const val ALIGN_RIGHT = 6
    }

    var darkMode = false
    var title: String? = null
    var leftBtn = 0
    var rightBtn = 0
    var mCount = 0
    var mTitleAlign = ALIGN_CENTER

    private var actionBarListener: onActionBarListener? = null

    interface onActionBarListener {
        fun onLeft()
        fun onRight()
    }

    init {
        actionBarListener = listener
    }

    fun setCount(count: Int) {
        mCount = count
    }

    fun setAlign(align: Int) {
        mTitleAlign = align
    }

    fun onClickLeft(view: View?) {
        if (actionBarListener != null) {
            if (leftBtn != ACTION_NONE) {
                actionBarListener!!.onLeft()
            }
        }
    }

    fun onClickRight(view: View?) {
        if (actionBarListener != null) {
            if (rightBtn != ACTION_NONE) {
                actionBarListener!!.onRight()
            }
        }
    }

    fun setButton(left: Int, right: Int) {
        leftBtn = left
        rightBtn = right
    }

    fun getLeftVisibility(): Boolean = (leftBtn != ACTION_NONE)

    fun getRightVisibility(): Boolean = (rightBtn != ACTION_NONE)

    fun getImage(direction: Int): Int {
        return when (direction) {
            ACTION_BACK -> R.drawable.primary_arrow_left
            ACTION_CREATE -> R.drawable.secondary_create
            ACTION_ADD -> R.drawable.primary_plus
            else -> 0
        }
    }

    fun getLeftImage(): Int {
        return getImage(leftBtn)
    }

    fun getRightImage(): Int {
        return getImage(rightBtn)
    }

    fun isDarkMode(): Boolean {
        return darkMode
    }


    fun getTitleAlign() = mTitleAlign

//    fun getTitleAlign(): String {
//        return when (mTitleAlign) {
//            ALIGN_LEFT -> "viewStart"
//            ALIGN_CENTER -> "center"
//            ALIGN_RIGHT -> "viewEnd"
//            else -> "center"
//        }
//    }
}
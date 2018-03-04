package com.aldoapps.ravegroovyadventure.util

import com.aldoapps.ravegroovyadventure.R
import java.util.*

/**
 * Created by aldo on 01/03/18.
 */
object DrawableRepository {

    private fun getRandomNumber(bound: Int): Int {
        val random = Random(System.currentTimeMillis())
        return random.nextInt(bound)
    }

    @JvmStatic
    fun getWaitingDrawable(): Int =
            when (getRandomNumber(3)) {
                0 -> R.drawable.g_waiting_1
                1 -> R.drawable.g_waiting_2
                2 -> R.drawable.g_waiting_3
                else -> R.drawable.g_waiting_1
            }

    @JvmStatic
    fun getErrorDrawable(): Int =
            when (getRandomNumber(2)) {
                0 -> R.drawable.g_error_1
                1 -> R.drawable.g_error_2
                else -> R.drawable.g_error_1
            }

    @JvmStatic
    fun getDefaultDrawable(): Int =
            when (getRandomNumber(5)) {
                0 -> R.drawable.ic_default_circle
                1 -> R.drawable.ic_default_brand
                2 -> R.drawable.ic_default_dolphin
                3 -> R.drawable.ic_default_ie
                4 -> R.drawable.ic_default_internet
                5 -> R.drawable.ic_default_sad
                else -> R.drawable.ic_default_circle
            }

}
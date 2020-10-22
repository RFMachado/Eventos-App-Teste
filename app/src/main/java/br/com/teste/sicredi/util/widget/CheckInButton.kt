package br.com.teste.sicredi.util.widget

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.OvershootInterpolator
import androidx.appcompat.widget.AppCompatButton
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart
import androidx.core.view.isVisible

class CheckInButton(context: Context, attrs: AttributeSet): AppCompatButton(context, attrs) {
    private val scaleDownValue = 0.5f
    private var isScalingUp = false
    private var isScalingDown = false

    fun scaleDown() {
        if (isScalingDown || scaleX == scaleDownValue)
            return

        isScalingDown = true
        val scaleDownX = ObjectAnimator.ofFloat(this, View.SCALE_X, 1f, scaleDownValue)
        val scaleDownY = ObjectAnimator.ofFloat(this, View.SCALE_Y, 1f, scaleDownValue)

        AnimatorSet().apply {
            playTogether(scaleDownX, scaleDownY)
            duration = 300
            interpolator = AccelerateDecelerateInterpolator()
            doOnEnd {
                isVisible = false
                isScalingDown = false
            }
        }.start()
    }

    fun scaleUp() {
        if (isScalingUp || scaleX == 1f)
            return

        isScalingUp = true
        val scaleUpX = ObjectAnimator.ofFloat(this, View.SCALE_X, 1f)
        val scaleUpY = ObjectAnimator.ofFloat(this, View.SCALE_Y, 1f)

        AnimatorSet().apply {
            playTogether(scaleUpX, scaleUpY)
            duration = 300
            interpolator = OvershootInterpolator()
            doOnStart { isVisible = true }
            doOnEnd { isScalingUp = false }
        }.start()
    }

}
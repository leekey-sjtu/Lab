package com.sjtu.lab.lab3

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.sjtu.lab.R
import com.sjtu.lab.lab3.ViewAnimationFragment.Companion.animationNameList
import com.sjtu.lab.lab3.ViewAnimationFragment.Companion.now_animation_num

class PropertyAnimationFragment : Fragment() {

    private val tvAnimationName by lazy { view?.findViewById<TextView>(R.id.tv_animation_name) }
    private val imgLauncher by lazy { view?.findViewById<ImageView>(R.id.img_launcher) }
    private val btnChangeAnimation by lazy { view?.findViewById<Button>(R.id.btn_change_animation) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_property_animation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvAnimationName?.text = animationNameList[now_animation_num] // 更新当前动画名称
        btnChangeAnimation?.setOnClickListener { // 设置 button 点击监听
            tvAnimationName?.text = animationNameList[now_animation_num] // 更新当前动画名称
            showPropertyAnimation(now_animation_num)
            now_animation_num = (now_animation_num + 1) % 4 // 循环取模
        }
    }

    private fun showPropertyAnimation(nowAnimationNumber: Int) {
        when (nowAnimationNumber) {
            0 -> {
                val animatorRotation = ObjectAnimator.ofFloat(imgLauncher, "rotation", 0.0f, 1080.0f)
                animatorRotation.duration = 2000
                animatorRotation.start()
            }
            1 -> {
                val animatorAlpha = ObjectAnimator.ofFloat(imgLauncher, "alpha", 1.0f, 0.0f, 1.0f)
                animatorAlpha.duration = 2000
                animatorAlpha.start()
            }
            2 -> {
                val animatorTranslationX = ObjectAnimator.ofFloat(imgLauncher, "translationX", 0.0f, 200.0f, 0.0f, -200.0f, 0.0f)
                val animatorTranslationY = ObjectAnimator.ofFloat(imgLauncher, "translationY", 0.0f, 200.0f, 400.0f, 200.0f, 0.0f)
                val animatorSet = AnimatorSet()
                animatorSet.duration = 2000
                animatorSet.play(animatorTranslationX).with(animatorTranslationY)
                animatorSet.start()
            }
            3 -> {
                val animatorScaleX = ObjectAnimator.ofFloat(imgLauncher, "scaleX", 1.0f, 0.0f, 1.0f)
                val animatorScaleY = ObjectAnimator.ofFloat(imgLauncher, "scaleY", 1.0f, 0.0f, 1.0f)
                val animatorSet = AnimatorSet()
                animatorSet.duration = 2000
                animatorSet.play(animatorScaleX).with(animatorScaleY)
                animatorSet.start()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        tvAnimationName?.text = animationNameList[now_animation_num] // 更新当前动画名称
    }

    override fun onPause() {
        super.onPause()
        now_animation_num -= 1 // 此时 now_animation_num 指下一次动画，ViewAnimationFragment 若要延续播放，则要减1
    }
}
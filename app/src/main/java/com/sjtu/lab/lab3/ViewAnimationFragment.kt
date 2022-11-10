package com.sjtu.lab.lab3

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.*
import android.widget.ImageView
import android.widget.TextView
import com.sjtu.lab.R

class ViewAnimationFragment : Fragment() {

    private val tvAnimationName by lazy { view?.findViewById<TextView>(R.id.tv_animation_name) }
    private val imgLauncher by lazy { view?.findViewById<ImageView>(R.id.img_launcher) }
    private lateinit var rotateAnimation: RotateAnimation
    private lateinit var alphaAnimation: AlphaAnimation
    private lateinit var translateAnimation: TranslateAnimation
    private lateinit var scaleAnimation: ScaleAnimation
    private lateinit var viewAnimationList: MutableList<Animation>
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_view_animation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAnimation()
        initAnimationListener()
    }

    // 初始化 4 个视图动画
    private fun initAnimation() {
        rotateAnimation = RotateAnimation(
            0.0f, 720.0f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        )
        alphaAnimation = AlphaAnimation(
            1.0f, 0.0f
        )
        translateAnimation = TranslateAnimation(
            Animation.RELATIVE_TO_SELF, 0.0f,
            Animation.RELATIVE_TO_SELF, 1.0f,
            Animation.RELATIVE_TO_SELF, 0.0f,
            Animation.RELATIVE_TO_SELF, 1.5f
        )
        scaleAnimation = ScaleAnimation(
            0.0f,1.0f,
            0.0f,1.0f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        )
        rotateAnimation.duration = 2000
        alphaAnimation.duration = 2000
        translateAnimation.duration = 2000
        scaleAnimation.duration = 2000
        viewAnimationList = mutableListOf(rotateAnimation, alphaAnimation, translateAnimation, scaleAnimation)
    }

    // 初始化视图动画的监听
    private fun initAnimationListener() {
        rotateAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                now_animation_num = 0
                tvAnimationName?.text = animationNameList[0]
            }
            override fun onAnimationEnd(animation: Animation?) { imgLauncher?.startAnimation(alphaAnimation) } // rotate 结束  alpha开始
            override fun onAnimationRepeat(animation: Animation?) { }
        })
        alphaAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                now_animation_num = 1
                tvAnimationName?.text = animationNameList[1]
            }
            override fun onAnimationEnd(animation: Animation?) { imgLauncher?.startAnimation(translateAnimation) } //alpha 结束 translate 开始
            override fun onAnimationRepeat(animation: Animation?) { }
        })
        translateAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                now_animation_num = 2
                tvAnimationName?.text = animationNameList[2]
            }
            override fun onAnimationEnd(animation: Animation?) { imgLauncher?.startAnimation(scaleAnimation) } // translate 结束 scale 开始
            override fun onAnimationRepeat(animation: Animation?) { }
        })
        scaleAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                now_animation_num = 3
                tvAnimationName?.text = animationNameList[3]
            }
            override fun onAnimationEnd(animation: Animation?) { imgLauncher?.startAnimation(rotateAnimation) } // scale 结束 rotate 开始
            override fun onAnimationRepeat(animation: Animation?) { }
        })
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed({
            imgLauncher?.startAnimation(viewAnimationList[now_animation_num]) // 延续播放当前动画
        }, 4900)
    }

    override fun onPause() {
        super.onPause()
        imgLauncher?.clearAnimation() // 清除动画
    }

    companion object {
        var now_animation_num = 0 // 指示当前正在播放哪种动画
        val animationNameList = mutableListOf("rotate 动画", "alpha 动画", "translate 动画", "scale 动画")
    }
}
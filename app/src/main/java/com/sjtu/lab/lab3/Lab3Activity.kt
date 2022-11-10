package com.sjtu.lab.lab3

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.sjtu.lab.R

class Lab3Activity : AppCompatActivity() {

    private val tabLayout by lazy { findViewById<TabLayout>(R.id.tab_layout) }
    private val viewPager by lazy { findViewById<ViewPager2>(R.id.view_pager_2) }
    private val lottieLoading by lazy { findViewById<LottieAnimationView>(R.id.lottie_loading) }
    private val viewAnimationFragment = ViewAnimationFragment()
    private val propertyAnimationFragment = PropertyAnimationFragment()
    private val lottieAnimationFragment = LottieAnimationFragment()
    private val fragmentList = mutableListOf(viewAnimationFragment, propertyAnimationFragment, lottieAnimationFragment)
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab3)
        initViewPager()
        initTabLayout()
    }

    // 初始化 ViewPager
    private fun initViewPager() {
        viewPager.adapter = ViewPagerAdapter(this, fragmentList) // viewPager2 适配器
        TabLayoutMediator(tabLayout, viewPager) { tab: TabLayout.Tab, position: Int -> // 联立 viewPager2 和 tabLayout
            tab.setCustomView(R.layout.activity_lab3_tab_view) // 自定义 tab 的布局样式
            tab.customView?.findViewById<TextView>(R.id.text_view)?.text= tabTitle[position]
        }.attach()
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                fragmentList[position].view?.alpha = 0.0f // 隐藏 fragment 布局
                lottieLoading.alpha = 1.0f // 显示 loading 动画
                lottieLoading.scaleX = 1.0f
                lottieLoading.scaleY = 1.0f
                lottieLoading.playAnimation()
                handler.removeCallbacksAndMessages(null) // 移除之前 handler 回调
                handler.postDelayed( {
                    val animatorAlpha = ObjectAnimator.ofFloat(lottieLoading, "alpha", 1.0f, 0.0f)
                    val animatorScaleX = ObjectAnimator.ofFloat(lottieLoading, "scaleX", 1.0f, 0.0f)
                    val animatorScaleY = ObjectAnimator.ofFloat(lottieLoading, "scaleY", 1.0f, 0.0f)
                    val animatorRotation = ObjectAnimator.ofFloat(lottieLoading, "rotation", 0.0f, 360.0f)
                    val animatorSet = AnimatorSet()
                    animatorSet.duration = 500
                    animatorSet.play(animatorAlpha).with(animatorScaleX).with(animatorScaleY).with(animatorRotation)
                    animatorSet.start() // 最后 500ms 展示 loading 的消失动画
                    handler.postDelayed({
                        lottieLoading.clearAnimation()
                        fragmentTransition(position)
                    }, 500)
                }, 4500) // 5000ms 后停止 loading 动画，显示页面内容
            }
        })
    }

    // 初始化 TabLayout
    private fun initTabLayout() {
        val firstTabTextView = tabLayout.getTabAt(0)?.customView?.findViewById<TextView>(R.id.text_view) // 初始化 first tab
        firstTabTextView?.scaleX = 1.1f
        firstTabTextView?.scaleY = 1.1f
        firstTabTextView?.setTextColor(resources.getColor(R.color.teal_200))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener { // tabLayout 添加 select 监听
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val tabTextView = tab?.customView?.findViewById<TextView>(R.id.text_view)
                showTabAnimation(tabTextView, TAB_SELECT)
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val tabTextView = tab?.customView?.findViewById<TextView>(R.id.text_view)
                showTabAnimation(tabTextView, TAB_UNSELECT)
            }
            override fun onTabReselected(tab: TabLayout.Tab?) { }
        })
    }

    // 展示 tab 的切换动画
    private fun showTabAnimation(tabTextView: TextView?, tabSelectType: Int) {
        val animatorScaleX : ObjectAnimator
        val animatorScaleY : ObjectAnimator
        if (tabSelectType == TAB_SELECT) {
            tabTextView?.setTextColor(resources.getColor(R.color.teal_200))
            animatorScaleX = ObjectAnimator.ofFloat(tabTextView, "scaleX", 1.0f, 1.1f)
            animatorScaleY = ObjectAnimator.ofFloat(tabTextView, "scaleY", 1.0f, 1.1f)
        } else {
            tabTextView?.setTextColor(resources.getColor(R.color.gray))
            animatorScaleX = ObjectAnimator.ofFloat(tabTextView, "scaleX", 1.1f, 1.0f)
            animatorScaleY = ObjectAnimator.ofFloat(tabTextView, "scaleY", 1.1f, 1.0f)
        }
        val animatorSet = AnimatorSet()
        animatorSet.duration = 200
        animatorSet.play(animatorScaleX).with(animatorScaleY)
        animatorSet.start()
    }

    // 展示 fragment 切换的淡入效果
    private fun fragmentTransition(position: Int) {
        val animatorAlpha = ObjectAnimator.ofFloat(fragmentList[position].view, "alpha", 0.0f, 1.0f)
        animatorAlpha.duration = 500
        animatorAlpha.start()
    }

    companion object {
        const val TAB_SELECT = 0
        const val TAB_UNSELECT = 1
        val tabTitle = mutableListOf(
            "视图动画",
            "属性动画",
            "Lottie动画"
        )
    }
}
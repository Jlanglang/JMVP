package com.linfeng.mvp.presenter

import android.support.design.widget.TabLayout
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.baozi.mvp.view.PagerView


/**
 * Created by Administrator on 2017/8/8 0008.
 */

open class PagerPresenter(private val mView: PagerView) {
    private var mAdapter: PagerAdapter? = null

    protected open// 当要显示的图片可以进行缓存的时候，会调用这个方法进行显示图片的初始化，我们将要显示的ImageView加入到ViewGroup中，然后作为返回值返回即可
    // 例如PagerAdapter只缓存三张要显示的图片，如果滑动的图片超出了缓存的范围，就会调用这个方法，将图片销毁
    val adapter: PagerAdapter
        get() {
            if (mAdapter == null) {
                mAdapter = object : PagerAdapter() {
                    override fun instantiateItem(view: ViewGroup, position: Int): Any {
                        view.addView(mView.getPager().get(position))
                        return mView.getPager().get(position)
                    }

                    override fun getItemPosition(`object`: Any): Int {
                        return PagerAdapter.POSITION_NONE
                    }

                    override fun getCount(): Int {
                        return mView.getPager().size()
                    }

                    override fun destroyItem(view: ViewGroup, position: Int, `object`: Any) {
                        view.removeView(mView.getPager().get(position))
                    }

                    override fun isViewFromObject(view: View, `object`: Any): Boolean {
                        return view === `object`
                    }
                }
            }
            return mAdapter
        }

    open fun onCreate() {
        initViewPager()
        initTabLayout()
    }

    private fun initTabLayout() {
        val tablayout = mView.getTablayout()
        if (tablayout == null || mView.getTabString() == null) {
            return
        }
        tablayout!!.setupWithViewPager(mView.getViewPager())
        val tabImage = mView.getTabDrawables()
        val tabString = mView.getTabString()
        val tabLayoutItem = mView.getTabLayoutItem()
        for (i in tabImage.indices) {
            var tab: TabLayout.Tab? = tablayout!!.getTabAt(i)
            if (tab != null && tabLayoutItem != 0) {
                val inflate = LayoutInflater.from(mView.getContext()).inflate(tabLayoutItem, null) as ViewGroup
                val childCount = inflate.childCount
                for (j in 0 until childCount) {
                    val childAt = inflate.getChildAt(j)
                    if (childAt is ImageView) {
                        childAt.setImageResource(tabImage[i])
                    }
                    if (childAt is TextView) {
                        childAt.setText(tabString[i])
                    }
                }
                tab.customView = inflate
            } else {
                tab = tablayout!!.newTab()
                tab!!.setText(tabString[i])
                tab.setIcon(tabImage[i])
            }
        }
        mView.getViewPager().setOffscreenPageLimit(mView.getTabString().length)
        if (!mView.isAnimation()) {
            tablayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    val position = tab.position
                    mView.getViewPager().setCurrentItem(position, false)
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {

                }

                override fun onTabReselected(tab: TabLayout.Tab) {

                }
            })
        }
    }

    private fun initViewPager() {
        val viewPager = mView.getViewPager()
        viewPager.setAdapter(adapter)
    }
}

package com.linfeng.mvp.presenter

import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView


import java.util.ArrayList

/**
 * Created by Administrator on 2017/8/21 0021.
 */

class PagerFragmentPresenter(private val mView: com.linfeng.mvp.view.PagerFragmentView) : PagerPresenter(mView) {
    private var fragments: ArrayList<Fragment> = ArrayList()

    override val adapter: FragmentStatePagerAdapter
        get() = object : FragmentStatePagerAdapter(mView.fgManager) {
            override fun getItem(position: Int): Fragment {
                val fragment = getFragment(mView.fragments[position])
                getFragments()[position] = fragment!!
                return getFragments()[position]
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return mView.tabString?.get(position)
            }

            override fun getCount(): Int {
                return getFragments().size
            }
        }

    override fun onCreate() {
        initViewpager()
        initTabLayout()
    }

    private fun initTabLayout() {
        val tabLayout = mView.tablayout
        if (tabLayout == null || mView.tabDrawables == null || mView.tabString == null) {
            return
        }
        tabLayout.setupWithViewPager(mView.viewPager)

        val tabImage = mView.tabDrawables ?: IntArray(0)
        val tabString = mView.tabString
        val tabLayoutItem = mView.tabLayoutItem ?: 0
        for (i in tabImage.indices) {
            var tab: TabLayout.Tab? = tabLayout.getTabAt(i)
            if (tab != null && tabLayoutItem != 0) {
                val inflate = LayoutInflater.from(mView.mContext).inflate(tabLayoutItem, null) as ViewGroup
                val childCount = inflate.childCount
                for (j in 0 until childCount) {
                    val childAt = inflate.getChildAt(j)
                    if (childAt is ImageView) {
                        childAt.setImageResource(tabImage[i])
                    }
                    if (childAt is TextView) {
                        childAt.text = tabString!![i]
                    }
                }
                tab.customView = inflate
            } else {
                tab = tabLayout.newTab()
                tab.text = tabString!![i]
                tab.setIcon(tabImage[i])
                tabLayout!!.addTab(tab, i)
            }
        }
        mView.viewPager.offscreenPageLimit = mView.fragments.size
        if (!mView.isAnimation) {
            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    val position = tab.position
                    mView.viewPager.setCurrentItem(position, false)
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {

                }

                override fun onTabReselected(tab: TabLayout.Tab) {

                }
            })
        }
    }

    private fun initViewpager() {
        val viewPager = mView.viewPager
        viewPager.adapter = adapter
    }

    fun getFragments(): ArrayList<Fragment> {
        val fragments = mView.fragments
        for (i in fragments.indices) {
            val fragment = getFragment(fragments[i]) ?: continue
            this.fragments.add(fragment)
        }
        return this.fragments
    }

    companion object {
        fun getFragment(zClass: Class<Fragment>): Fragment? {
            try {
                return zClass.newInstance()
            } catch (e: InstantiationException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }
            return null
        }
    }
}

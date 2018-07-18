package com.linfeng.mvp.presenter

import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.baozi.mvp.view.PagerFragmentView

import java.util.ArrayList

/**
 * Created by Administrator on 2017/8/21 0021.
 */

class PagerFragmentPresenter(private val mView: PagerFragmentView) : PagerPresenter(mView) {
    private var fragments: MutableList<Fragment>? = null

    protected override val adapter: FragmentStatePagerAdapter
        get() = object : FragmentStatePagerAdapter(mView.getFgManager()) {
            override fun getItem(position: Int): Fragment {
                if (getFragments()[position] == null) {
                    val fragment = getFragment(mView.getFragments()[position])
                    getFragments().set(position, fragment)
                }
                return getFragments()[position]
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return mView.getTabString()[position]
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
        val tablayout = mView.getTablayout()
        if (tablayout == null || mView.getTabDrawables() == null || mView.getTabString() == null) {
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
                tablayout!!.addTab(tab, i)
            }
        }
        mView.getViewPager().setOffscreenPageLimit(mView.getFragments().length)
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

    private fun initViewpager() {
        val viewPager = mView.getViewPager()
        viewPager.setAdapter(adapter)
    }

    fun getFragments(): MutableList<Fragment> {
        if (fragments == null) {
            fragments = ArrayList()
            val fragments = mView.getFragments()
            for (i in fragments.indices) {
                val fragment = getFragment(fragments[i])
                this.fragments!!.add(fragment)
            }
        }
        return fragments
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

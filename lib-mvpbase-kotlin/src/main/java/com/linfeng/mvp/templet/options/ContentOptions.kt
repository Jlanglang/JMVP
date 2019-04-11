package com.linfeng.mvp.templet.options

import android.content.Context
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.view.View
import com.linfeng.mvp.templet.weight.LoadingPager
import java.util.*

/**
 * Created by baozi on 2017/10/25.
 */

class ContentOptions : Cloneable {
    @LayoutRes
    private var emptyLayout: Int = 0
    @LayoutRes
    private var errorLayout: Int = 0
    @LayoutRes
    private var loadingLayout: Int = 0

    /**
     * 是否显示
     */
    private var isOpenLoading: Boolean = false

    private var clickIds: MutableSet<Int>? = null


    private var throwables: List<Class<*>>? = null

    fun addClickId(@IdRes clickId: Int): ContentOptions {
        if (clickIds == null) {
            clickIds = TreeSet()
            clickIds!!.add(clickId)
        }
        return this
    }

    fun getClickIds(): Set<Int>? {
        return clickIds
    }

    @LayoutRes
    fun getEmptyLayout(): Int {
        return emptyLayout
    }

    fun setEmptyLayout(@LayoutRes emptyLayout: Int): ContentOptions {
        this.emptyLayout = emptyLayout
        return this
    }

    @LayoutRes
    fun getErrorLayout(): Int {
        return errorLayout
    }

    fun setErrorLayout(@LayoutRes errorLayout: Int): ContentOptions {
        this.errorLayout = errorLayout
        return this
    }

    @LayoutRes
    fun getLoadingLayout(): Int {
        return loadingLayout
    }

    fun setLoadingLayout(@LayoutRes loadingLayout: Int): ContentOptions {
        this.loadingLayout = loadingLayout
        return this
    }

    fun isOpenLoading(): Boolean {
        return isOpenLoading
    }

    fun setOpenLoading(openLoading: Boolean): ContentOptions {
        isOpenLoading = openLoading
        return this
    }


    /**
     * @param context     上下文
     * @param SuccessView 成功正文View
     * @return
     */
    fun buildLoadingPager(context: Context, SuccessView: View): LoadingPager {
        val loadingPager = LoadingPager(context)
        loadingPager.emptyLayout = emptyLayout
        loadingPager.errorLayout = errorLayout
        loadingPager.loadingLayout = loadingLayout
        loadingPager.isShowLoading = isOpenLoading
        loadingPager.successPage = SuccessView
        if (clickIds != null && clickIds!!.size > 0) {
            for (id in clickIds!!) {
                loadingPager.setRefreshClick(id)
            }
        }
        return loadingPager
    }

    public override fun clone(): ContentOptions {
        try {
            return super.clone() as ContentOptions
        } catch (e: CloneNotSupportedException) {
            throw RuntimeException(e)
        }

    }

    /**
     * 指定异常才显示错误
     *
     * @param throwable
     * @return
     */
    fun setThrowableList(vararg throwable: Class<*>): ContentOptions {
        this.throwables = Arrays.asList(*throwable)
        return this
    }

    companion object {
        fun create(): ContentOptions {
            return ContentOptions()
        }
    }
}


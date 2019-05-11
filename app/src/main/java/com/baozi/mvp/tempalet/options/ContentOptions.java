package com.baozi.mvp.tempalet.options;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.view.View;

import com.baozi.mvp.tempalet.weight.LoadingPager;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by baozi on 2017/10/25.
 */

public class ContentOptions implements Cloneable {
    @LayoutRes
    private int emptyLayout;
    @LayoutRes
    private int errorLayout;
    @LayoutRes
    private int loadingLayout;

    /**
     * 是否显示
     */
    private boolean isOpenLoading;

    private Set<Integer> clickIds;


    private List<Class> throwables;

    public static ContentOptions create() {
        return new ContentOptions();
    }

    public ContentOptions addClickId(@IdRes int clickid) {
        if (clickIds == null) {
            clickIds = new TreeSet<>();
            clickIds.add(clickid);
        }
        return this;
    }

    public Set<Integer> getClickIds() {
        return clickIds;
    }

    @LayoutRes
    public int getEmptyLayout() {
        return emptyLayout;
    }

    public ContentOptions setEmptyLayout(@LayoutRes int emptyLayout) {
        this.emptyLayout = emptyLayout;
        return this;
    }

    @LayoutRes
    public int getErrorLayout() {
        return errorLayout;
    }

    public ContentOptions setErrorLayout(@LayoutRes int errorLayout) {
        this.errorLayout = errorLayout;
        return this;
    }

    @LayoutRes
    public int getLoadingLayout() {
        return loadingLayout;
    }

    public ContentOptions setLoadingLayout(@LayoutRes int loadingLayout) {
        this.loadingLayout = loadingLayout;
        return this;
    }

    public boolean isOpenLoading() {
        return isOpenLoading;
    }

    public ContentOptions setOpenLoading(boolean openLoading) {
        isOpenLoading = openLoading;
        return this;
    }



    /**
     * @param context     上下文
     * @param SuccessView 成功正文View
     * @return
     */
    public LoadingPager buildLoadingPager(Context context, View SuccessView) {
        LoadingPager loadingPager = new LoadingPager(context);
        loadingPager.setEmptyLayout(emptyLayout);
        loadingPager.setErrorLayout(errorLayout);
        loadingPager.setLoadingLayout(loadingLayout);
        loadingPager.setShowLoading(isOpenLoading);
        loadingPager.setSuccessPage(SuccessView);
        if (clickIds != null && clickIds.size() > 0) {
            for (Integer id : clickIds) {
                loadingPager.setRefreshClick(id);
            }
        }
        return loadingPager;
    }

    public ContentOptions clone() {
        try {
            return (ContentOptions) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 指定异常才显示错误
     *
     * @param throwable
     * @return
     */
    public ContentOptions setThrowableList(Class... throwable) {
        this.throwables = Arrays.asList(throwable);
        return this;
    }

    public List<Class> getThrowables() {
        return throwables;
    }
}


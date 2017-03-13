package com.linfeng.common.adapter.recyclerview.base;


/**
 * Created by zhy on 16/6/22.
 */
public interface ItemViewDelegate<T> {

    int getItemViewLayoutId();

    /**
     * 是否显示这个item
     *
     * @param item     当前item的数据
     * @param position 当前的位置
     * @return  比如position为1,当创建这个Viewitem的时候,这里你要根据item数据,告诉adapter在什么情况下,是显示这种条目的
     */
    boolean isForViewType(T item, int position);

    void convert(ViewHolder holder, T t, int position);

}

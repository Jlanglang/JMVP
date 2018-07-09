package com.linfeng.imageloder.listener;

/**
 * Created by heyao on 2017/8/25.
 */

public interface ProgressLoadListener {

    void update(int bytesRead, int contentLength);

    void onException();

    void onResourceReady();
}

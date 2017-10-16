package com.linfeng.common.utils;


/*
 * @创建时间   2015/12/9 14:32
 * @描述	      创建普通的线程池的代理
 * @描述	      创建下载的线程池的代理
 *
 * @更新者     $Author: admin $
 * @更新时间   $Date: 2015-12-14 09:26:22 +0800 (星期一, 14 十二月 2015) $
 * @更新描述   ${TODO}
 */
public class ThreadPoolProxyFactory {

    volatile static ThreadPoolProxy mNormalThreadPoolProxy;    // 只需创建一次即可
    volatile static ThreadPoolProxy mDownloadThreadPoolProxy;    // 只需创建一次即可

    /**
     * 返回普通线程池的代理
     * 双重检查加锁,保证只有第一次实例化的时候才启用同步机制,提高效率
     *
     * @return
     */
    public static ThreadPoolProxy createNormalThreadPoolProxy() {
        if (mNormalThreadPoolProxy == null) {
            synchronized (ThreadPoolProxyFactory.class) {
                if (mNormalThreadPoolProxy == null) {
                    mNormalThreadPoolProxy = new ThreadPoolProxy(5, 5, 3000);
                }
            }
        }
        return mNormalThreadPoolProxy;
    }

    /**
     * 返回下载线程池的代理
     */
    public static ThreadPoolProxy createDownloadThreadPoolProxy() {
        if (mDownloadThreadPoolProxy == null) {
            synchronized (ThreadPoolProxyFactory.class) {
                if (mDownloadThreadPoolProxy == null) {
                    mDownloadThreadPoolProxy = new ThreadPoolProxy(3, 3, 3000);
                }
            }
        }
        return mDownloadThreadPoolProxy;
    }
}

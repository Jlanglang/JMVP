package com.baozi.mvpdemo.contract;

/**
 * @author jlanglang  2017/1/8 9:25
 * @版本 2.0
 * @Change
 */

public class SimpleWebViewFragmentContract {

    public interface View extends BaseWebViewContract.View {

        void setTitle(String str);

        void setTitle(int res);

        void setToolbarImage(int imageResource);

        void setToolbarRightText(String str);
    }

    public interface Presenter {
        void loadData();
    }

    public interface Model {
    }


}
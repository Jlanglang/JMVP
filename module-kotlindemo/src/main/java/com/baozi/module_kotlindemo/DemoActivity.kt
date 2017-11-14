package com.baozi.module_kotlindemo

import android.os.Bundle
import android.util.Log
import com.baozi.module_kotlindemo.model.DemoView
import com.baozi.module_kotlindemo.presenter.DemoPresenter
import com.baozi.mvp.base.BaseActivity

class DemoActivity : BaseActivity<DemoPresenter>(), DemoView {

    override fun initPresenter(): DemoPresenter {
        return DemoPresenter()
    }

    override fun A() {
        Log.i("log", "调用了方法A")
    }

    override fun B() {
        Log.i("log", "调用了方法B")
        mPresenter.onDetach()
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_demo
    }
}

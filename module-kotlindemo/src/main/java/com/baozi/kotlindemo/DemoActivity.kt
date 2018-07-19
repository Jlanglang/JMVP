package com.baozi.kotlindemo

import com.baozi.kotlindemo.presenter.DemoPresenter
import com.linfeng.mvp.annotation.JMvpContract
import com.linfeng.mvp.base.BaseActivity

@JMvpContract(layout = R.layout.activity_demo)
class DemoActivity : BaseActivity<DemoPresenter>() {

}

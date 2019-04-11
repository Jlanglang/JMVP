package com.baozi.kotlindemo

import android.app.Fragment
import android.os.Bundle
import com.baozi.kotlindemo.presenter.DemoPresenter
import com.linfeng.mvp.annotation.JMvpContract
import com.linfeng.mvp.base.BaseActivity

@JMvpContract(layout = R.layout.activity_demo)
class DemoActivity : BaseActivity<DemoPresenter>() {
    override fun init(savedInstanceState: Bundle?) {
        super.init(savedInstanceState)
        newInstance<Fragment>(
                Pair("1", '1'),
                Pair("1", '1'),
                Pair("1", '1'),
                Pair("1", '1'),
                Pair("1", '1')
        )
    }
}

inline fun <reified T : Fragment> newInstance(vararg a: Pair<String, Any>) {
    val newInstance = T::class.java.newInstance()
    val bundle = Bundle()
    a.forEach {
        val second = it.second
        if (second is String) {
            bundle.putString(it.first, it.second as? String)
            return
        }
    }
    newInstance.arguments = bundle
}
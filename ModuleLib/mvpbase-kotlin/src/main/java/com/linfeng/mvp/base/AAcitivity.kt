package com.linfeng.mvp.base

//import com.baozi.mvp.base.BaseActivity
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

///**
// * @author jlanglang  2017/7/8 16:24
// * @版本 2.0
// * @Change
// */
//class AAcitivity : BaseActivity<APresenter>() {
//    override fun initPresenter(): APresenter {
//        return APresenter()
//    }
//
//    override fun initView(inflater: LayoutInflater, savedInstanceState: Bundle?): View {
//        val packageName = context.packageName
//        return View(context)
//    }
//
//}


interface BaseView {
    val context: Context
}

interface ABaseView : BaseView {

}

open class BasePresenter(var V: BaseView) {

}

abstract class BaseActivity<out T : BasePresenter> : AppCompatActivity(), BaseView {
    abstract val mPreserter: T
    override val context: Context
        get() = this
}

class IActivity : BaseActivity<IPresenter>(), ABaseView {

    override val mPreserter: IPresenter by lazy {
        IPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        mPreserter.get()
    }
}

class IPresenter(var a: ABaseView) : BasePresenter(a) {
    fun get() {
        a.context
    }
}


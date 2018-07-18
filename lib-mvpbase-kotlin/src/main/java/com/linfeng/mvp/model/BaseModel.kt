package com.linfeng.mvp.model

import com.baozi.mvp.presenter.BasePresenter

/**
 * Created by baozi on 2017/10/23.
 */

class BaseModel<T : BasePresenter>(protected var mPresenter: T)

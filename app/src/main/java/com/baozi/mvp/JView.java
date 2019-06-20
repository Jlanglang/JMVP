package com.baozi.mvp;

import com.baozi.mvp.presenter.BasePresenter;
import com.baozi.mvp.presenter.EmptyPresenter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface JView {
    int layout() default 0;

    Class<? extends BasePresenter> p() default EmptyPresenter.class;
}

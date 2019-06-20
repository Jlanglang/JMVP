package com.baozi.mvp.annotation;

import com.baozi.mvp.presenter.BasePresenter;
import com.baozi.mvp.presenter.EmptyPresenter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface JView {
    /**
     * @return presenter的class
     */
    Class<? extends BasePresenter> p() default EmptyPresenter.class;

    /**
     * @return 布局id
     */
    int layout() default 0;
}

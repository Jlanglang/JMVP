package com.linfeng.rx_retrofit_network.location.model;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ParameterTypeImpl implements ParameterizedType {
    private Class raw;
    private Type[] args;

    public ParameterTypeImpl(Class raw, Type[] args) {
        this.raw = raw;
//        this.args = args != null ? new Class[]{args} : new Type[0];
        this.args = args == null ? new Type[0] : args;
    }

    @Override
    public Type[] getActualTypeArguments() {
        return args;
    }

    @Override
    public Type getRawType() {
        return raw;
    }

    @Override
    public Type getOwnerType() {
        return null;
    }
}
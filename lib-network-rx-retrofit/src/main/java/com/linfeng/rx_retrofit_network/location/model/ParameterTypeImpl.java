package com.linfeng.rx_retrofit_network.location.model;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ParameterTypeImpl implements ParameterizedType {
    private final Class raw;
    private final Type[] args;

    public ParameterTypeImpl(Class raw, Class args) {
        this.raw = raw;
        this.args = args != null ? new Class[]{args} : new Type[0];
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
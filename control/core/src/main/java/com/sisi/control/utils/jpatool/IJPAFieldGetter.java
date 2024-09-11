package com.sisi.control.utils.jpatool;

import java.io.Serializable;

@FunctionalInterface
public interface IJPAFieldGetter<T> extends Serializable {
    Object apply(T source);
}


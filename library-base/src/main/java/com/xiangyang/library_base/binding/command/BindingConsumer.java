package com.xiangyang.library_base.binding.command;

/**
 * A one-argument action.
 * 一个参数动作
 * @param <T> the first argument type
 */
public interface BindingConsumer<T> {
    void call(T t);
}

package com.oracle.lambda.expr;

/**
 * Created by smdaziz on 4/20/2017.
 */
public interface Predicate<T> {

    boolean test(T t);

}

package com.hardware.commons;

@FunctionalInterface
public interface Converter<S, R> {

    R convert(S source);
}

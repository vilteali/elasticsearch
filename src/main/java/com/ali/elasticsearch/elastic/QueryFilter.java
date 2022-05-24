package com.ali.elasticsearch.elastic;

public interface QueryFilter<T> {

    Integer getSize();

    Integer getFrom();

    T getValue();

}

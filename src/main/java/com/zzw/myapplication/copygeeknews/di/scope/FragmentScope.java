package com.zzw.myapplication.copygeeknews.di.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 自己写的注解,不懂这个范围什么意思
 */

@Scope
@Retention(RUNTIME)
public @interface FragmentScope {
}

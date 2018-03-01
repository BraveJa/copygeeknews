package com.zzw.myapplication.copygeeknews.di.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 *  * 自己写的注解,同@singleton单例的作用
 */

@Scope
@Retention(RUNTIME)
public @interface ActivityScope {
}

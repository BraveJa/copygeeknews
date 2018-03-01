package com.zzw.myapplication.copygeeknews.di.qulifier;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by zqy on 2017/10/13.
 *
 */
@Qualifier
@Documented
@Retention(RUNTIME)
public @interface MZhihuUrl {

}

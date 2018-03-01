package com.zzw.myapplication.copygeeknews.component;

import com.zzw.myapplication.copygeeknews.util.MRxUtil;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;

/**
 * Created by zqy on 2018/2/28.
 */

public class MRxBus {
	private final FlowableProcessor<Object> bus;
	private MRxBus(){
		bus = PublishProcessor.create().toSerialized();
	}
	public static MRxBus getDefault(){
		return RxBusHolder.sInstance;
	}
	private static class RxBusHolder{
		private static final MRxBus sInstance = new MRxBus();
	}
//发送事件
	public void post (Object o){
		bus.onNext(o);
	}
	//接收事件
	public <T>Flowable<T> toFlowable(Class<T> eventType){
		return bus.ofType(eventType);
	}
	// 封装默认订阅
	public <T> Disposable toDefaultFlowable(Class<T> eventType, Consumer<T> act) {
		return bus.ofType(eventType).compose(MRxUtil.<T>rxSchedulerHelper()).subscribe(act);
	}
}

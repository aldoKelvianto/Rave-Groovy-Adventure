package com.aldoapps.ravegroovyadventure.base

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

abstract class BaseInteractor<T, P> {

    private val compositeDisposable = CompositeDisposable()

    abstract fun buildInteractorObservable(param: P): Observable<T>

    fun execute(observer: DisposableObserver<T>, params: P) {
        val disposableObserver = buildInteractorObservable(params).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer)
        compositeDisposable.add(disposableObserver)
    }

    fun dispose() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}
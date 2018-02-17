package com.wardellbagby.rxcountdowntimer

import android.os.CountDownTimer
import android.os.Looper
import com.wardellbagby.rxcountdowntimer.RxCountDownTimer.Companion.create
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

/**
 * An RxJava compatible [CountDownTimer]. Use [create] functions to create a [Flowable] that will emit
 * the values a [CountDownTimer] would if given the same parameters.
 */
class RxCountDownTimer private constructor(periodInFuture: Long, countDownInterval: Long, private val timeUnit: TimeUnit) {

    private val onTick: Flowable<Long> = Flowable.create<Long>({
        if (Looper.myLooper() == null) {
            it.onError(IllegalStateException("Can't create RxCountDownTimer inside thread that has not called Looper.prepare()"))
            return@create
        }
        val timer = ConcreteCountDownTimer(it, periodInFuture, countDownInterval, timeUnit)
        timer.start()

    }, BackpressureStrategy.LATEST)

    companion object {
        /**
         * Create a [Flowable] that will be a countdown until a specified time in the future, emitting regular notifications on the way.
         *
         * @param millisInFuture The milliseconds in the future that this will countdown to.
         * @param countDownInterval The minimum amount of time between emissions.
         */
        @JvmStatic
        fun create(millisInFuture: Long, countDownInterval: Long) = RxCountDownTimer(millisInFuture, countDownInterval, TimeUnit.MILLISECONDS).onTick

        /**
         * Create a [Flowable] that will be a countdown until a specified time in the future, emitting regular notifications on the way.
         *
         * @param periodInFuture The amount of time in the future that this will countdown to.
         * @param countDownInterval The minimum amount of time between emissions.
         * @param timeUnit The unit of time that [periodInFuture] and [countDownInterval] were specified in, and that values will be emitted in.
         */
        @JvmStatic
        fun create(periodInFuture: Long, countDownInterval: Long, timeUnit: TimeUnit) = RxCountDownTimer(periodInFuture, countDownInterval, timeUnit).onTick
    }
}

package com.wardellbagby.rxcountdowntimer

import io.reactivex.FlowableEmitter
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import java.util.concurrent.TimeUnit

/**
 * @author Wardell Bagby, 2018
 */
class ConcreteCountDownTimerTest {

    @Mock
    lateinit var mockedEmitter: FlowableEmitter<Long>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testOnTick_Millis() {
        val currentTimeUnit = TimeUnit.MILLISECONDS
        val periodInFuture = currentTimeUnit.convert(1000L, TimeUnit.MILLISECONDS)
        val countDownInterval = currentTimeUnit.convert(500L, TimeUnit.MILLISECONDS)
        val concreteCountDownTimer = ConcreteCountDownTimer(mockedEmitter, periodInFuture, countDownInterval, currentTimeUnit)

        concreteCountDownTimer.onTick(5000)
        verify(mockedEmitter).onNext(5000)
        concreteCountDownTimer.onTick(4322)
        verify(mockedEmitter).onNext(4322)
        concreteCountDownTimer.onTick(1000)
        verify(mockedEmitter).onNext(1000)
        concreteCountDownTimer.onTick(10)
        verify(mockedEmitter).onNext(10)
        concreteCountDownTimer.onFinish()
        verify(mockedEmitter).onNext(0)
        verify(mockedEmitter).onComplete()
    }

    @Test
    fun testOnTick_Seconds() {
        val currentTimeUnit = TimeUnit.SECONDS
        val periodInFuture = currentTimeUnit.convert(1000L, TimeUnit.MILLISECONDS)
        val countDownInterval = currentTimeUnit.convert(500L, TimeUnit.MILLISECONDS)
        val concreteCountDownTimer = ConcreteCountDownTimer(mockedEmitter, periodInFuture, countDownInterval, currentTimeUnit)

        concreteCountDownTimer.onTick(5000)
        verify(mockedEmitter).onNext(5)
        concreteCountDownTimer.onTick(4322)
        verify(mockedEmitter).onNext(4)
        concreteCountDownTimer.onTick(1000)
        verify(mockedEmitter).onNext(1)
        concreteCountDownTimer.onTick(10)
        concreteCountDownTimer.onFinish()
        verify(mockedEmitter, times(2)).onNext(0)
        verify(mockedEmitter).onComplete()
    }

    @Test
    fun testOnTick_Micros() {
        val currentTimeUnit = TimeUnit.MICROSECONDS
        val periodInFuture = currentTimeUnit.convert(1000L, TimeUnit.MILLISECONDS)
        val countDownInterval = currentTimeUnit.convert(500L, TimeUnit.MILLISECONDS)
        val concreteCountDownTimer = ConcreteCountDownTimer(mockedEmitter, periodInFuture, countDownInterval, currentTimeUnit)

        concreteCountDownTimer.onTick(5000)
        verify(mockedEmitter).onNext(5000000)
        concreteCountDownTimer.onTick(4322)
        verify(mockedEmitter).onNext(4322000)
        concreteCountDownTimer.onTick(1000)
        verify(mockedEmitter).onNext(1000000)
        concreteCountDownTimer.onTick(10)
        verify(mockedEmitter).onNext(10000)
        concreteCountDownTimer.onFinish()
        verify(mockedEmitter).onNext(0)
        verify(mockedEmitter).onComplete()
    }

    @Test
    fun testOnTick_Hours() {
        val currentTimeUnit = TimeUnit.HOURS
        val periodInFuture = currentTimeUnit.convert(36000000L, TimeUnit.MILLISECONDS)
        val countDownInterval = currentTimeUnit.convert(1800000L, TimeUnit.MILLISECONDS)
        val concreteCountDownTimer = ConcreteCountDownTimer(mockedEmitter, periodInFuture, countDownInterval, currentTimeUnit)

        concreteCountDownTimer.onTick(18000000)
        verify(mockedEmitter).onNext(5)
        concreteCountDownTimer.onTick(12000000)
        verify(mockedEmitter).onNext(3)
        concreteCountDownTimer.onTick(9000000)
        verify(mockedEmitter).onNext(2)
        concreteCountDownTimer.onTick(6000000)
        verify(mockedEmitter).onNext(1)
        concreteCountDownTimer.onTick(3600000)
        verify(mockedEmitter, times(2)).onNext(1)
        concreteCountDownTimer.onTick(3599999)
        verify(mockedEmitter).onNext(0)
        concreteCountDownTimer.onTick(1)
        verify(mockedEmitter, times(2)).onNext(0)
        concreteCountDownTimer.onFinish()
        verify(mockedEmitter, times(3)).onNext(0)
        verify(mockedEmitter).onComplete()
    }
}
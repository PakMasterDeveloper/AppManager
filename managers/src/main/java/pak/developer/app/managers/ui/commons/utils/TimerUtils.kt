package pak.developer.app.managers.ui.commons.utils

import android.os.CountDownTimer
import java.util.concurrent.TimeUnit

object TimerUtils {
    private var countDownTimer: CountDownTimer? = null
    private var isRunning: Boolean = false
    private var isPause: Boolean = false
    private var isLeftTime: Long = 0
    fun startTimer(duration: Long, listener: TimerCallback) {
        isRunning = true
        isPause = false
        isLeftTime = 0L
        countDownTimer = object :
            CountDownTimer(TimeUnit.SECONDS.toMillis(duration), TimeUnit.SECONDS.toMillis(1)) {
            override fun onFinish() {
                stopTimer()
                listener.onTimerFinish()
            }

            override fun onTick(millisUntilFinished: Long) {
                isLeftTime = millisUntilFinished
                listener.onTimerTick(milliSecond = millisUntilFinished)
            }
        }.start()
    }

    fun pauseTimer() {
        if (isRunning) {
            isRunning = false
            isPause = true
            if (countDownTimer != null) {
                countDownTimer!!.cancel()
            }
        }
    }

    fun resumeTimer(listener: TimerCallback) {
        if (isPause) {
            startTimer(isLeftTime, listener)
        }
    }

    fun stopTimer() {
        isRunning = false
        isPause = false
        isLeftTime = 0L
        countDownTimer?.let {
            countDownTimer!!.cancel()
            countDownTimer = null
        } ?: null
    }

    interface TimerCallback {
        fun onTimerFinish()
        fun onTimerTick(milliSecond: Long)
    }
}
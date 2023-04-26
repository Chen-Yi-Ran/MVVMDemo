package com.example.mvvmdemo.lifecycle

/**
 * 管理所注册进来的接口，这个接口就ILifecycle
 * 保存当前View的生命周期状态
 */
class LifecycleProvider {

    private var currentLifeState: LifeState? = null

    private val lifecycleListener = arrayListOf<ILifecycle>()

    fun addLifeListener(listener: ILifecycle) {
        if (!lifecycleListener.contains(listener)) {
            lifecycleListener.add(listener)
        }
    }

    fun removeLifeListener(listener: ILifecycle) {
        lifecycleListener.remove(listener)
    }

    fun makeLifeState(state: LifeState) {
        currentLifeState = state
        when (state) {
            LifeState.CREATE -> {
                dispatchCreateState()
            }
            LifeState.DESTROY -> {
                dispatchDestroyState()
            }
            LifeState.RESUME -> {
                dispatchResumeState()
            }
            LifeState.START -> {
                dispatchStartState()
            }
            LifeState.STOP -> {
                dispatchStopState()
            }
            LifeState.PAUSE -> {
                dispatchPauseState()
            }

        }

    }

    private fun dispatchPauseState() {
        lifecycleListener.forEach {
            it.onPause()
        }
    }

    private fun dispatchStopState() {

        lifecycleListener.forEach {
            it.onStop()
        }
    }

    private fun dispatchStartState() {
        lifecycleListener.forEach {
            it.onStart()
        }

    }

    private fun dispatchResumeState() {
        lifecycleListener.forEach {
            it.onResume()
        }

    }

    private fun dispatchDestroyState() {
        lifecycleListener.forEach {
            it.onDestroy()
        }
        lifecycleListener.clear()
    }

    private fun dispatchCreateState() {
        lifecycleListener.forEach {
            it.onCreate()
        }
    }

}
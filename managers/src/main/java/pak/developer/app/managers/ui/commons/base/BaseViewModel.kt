package pak.developer.app.managers.ui.commons.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel<T> : ViewModel() {
    private var view: T? = null
    open fun attachView(view: T) {
        this.view = view
    }

    protected fun getView(): T {
        if (this.view == null) {
            throw NullPointerException("view is null call attach method 1st")
        }
        return this.view!!
    }

    protected fun getNullableView(): T? {
        return this.view
    }

    override fun onCleared() {
        super.onCleared()
        this.view = null
    }
}
package pak.developer.app.managers.ui.commons.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<viewBinding : ViewBinding> : AppCompatActivity() {
    protected lateinit var binding: viewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getActivityView()
        setContentView(binding.root)
        initView(savedInstanceState)
        dataObserver()
    }

    abstract fun getActivityView(): viewBinding
    abstract fun initView(savedInstanceState: Bundle?)
    open fun dataObserver() {}
}
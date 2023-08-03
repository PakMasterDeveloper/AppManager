package pak.developer.app.managers.ui.commons.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<viewBinding : ViewBinding> : Fragment() {
    protected lateinit var binding: viewBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getFragmentView(inflater, container, savedInstanceState)
        return binding.root
    }

    abstract fun getFragmentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): viewBinding

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        initView(savedInstanceState)
        dataObserver()
    }

    abstract fun initView(savedInstanceState: Bundle?)
    open fun dataObserver() {}

}
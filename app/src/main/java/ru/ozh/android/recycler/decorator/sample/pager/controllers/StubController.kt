package ru.ozh.android.recycler.decorator.sample.pager.controllers

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding
import ru.ozh.android.recycler.decorator.sample.pager.controllers.StubController.Holder
import ru.surfstudio.android.easyadapter.controller.NoDataItemController
import ru.surfstudio.android.easyadapter.holder.BaseViewHolder

class StubController<T : ViewBinding>(private val binding: (ViewGroup) -> T) : NoDataItemController<Holder>() {

    override fun viewType(): Int {
        return binding.hashCode()
    }

    override fun createViewHolder(parent: ViewGroup): Holder =
        Holder(binding(parent))

    class Holder(binding: ViewBinding) : BaseViewHolder(binding.root)
}
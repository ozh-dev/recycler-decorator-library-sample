package ru.ozh.recycler.decorator.pager.controllers

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import ru.surfstudio.android.easyadapter.controller.NoDataItemController
import ru.surfstudio.android.easyadapter.holder.BaseViewHolder

class Controller(@LayoutRes val layoutRes: Int) : NoDataItemController<Controller.Holder>() {

    override fun viewType(): Int {
        return layoutRes
    }

    override fun createViewHolder(parent: ViewGroup): Holder =
        Holder(parent, layoutRes)

    class Holder(parent: ViewGroup, layoutRes: Int) : BaseViewHolder(parent, layoutRes)
}
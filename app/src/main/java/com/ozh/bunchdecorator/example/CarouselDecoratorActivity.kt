package com.ozh.bunchdecorator.example

import android.graphics.Canvas
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.ozh.bunchdecorator.example.controllers.Controller
import com.ozh.bunchdecorator.example.controllers.LinePagerIndicatorDecoration
import kotlinx.android.synthetic.main.activity_pager.*
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList

class CarouselDecoratorActivity : AppCompatActivity() {

    private val easyAdapter = EasyAdapter()
    private val easyAdapter2 = EasyAdapter()

    private val controller = Controller(R.layout.item_controller_pager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pager)

        init()
    }

    private fun init() {
        pager_rv.apply {
            layoutManager = LinearLayoutManager(this@CarouselDecoratorActivity, RecyclerView.HORIZONTAL, false)
            adapter = easyAdapter.apply { setFirstInvisibleItemEnabled(false) }
            PagerSnapHelper().attachToRecyclerView(pager_rv)
        }

        pager2_rv.apply {
            adapter = easyAdapter2.apply { setFirstInvisibleItemEnabled(false) }
        }

        //TODO add this case in dsl decorators
        val indicatorDrawer = object : RecyclerView.ItemDecoration() {
            val drawer = LinePagerIndicatorDecoration()
            override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
                super.onDraw(c, parent, state)
                drawer.draw(c, parent, state)
            }

            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                super.getItemOffsets(outRect, view, parent, state)
                outRect.bottom = resources.getDimensionPixelOffset(R.dimen.dp32)
            }
        }

        pager_rv.addItemDecoration(indicatorDrawer)
        pager2_rv.children.forEach { view ->
            if (view is RecyclerView) {
                view.addItemDecoration(indicatorDrawer)
            }
        }

        val itemList = ItemList.create()

        (0..5).forEach {
            itemList.add("$it", controller)
        }

        easyAdapter.setItems(itemList)
        easyAdapter2.setItems(itemList)
    }
}

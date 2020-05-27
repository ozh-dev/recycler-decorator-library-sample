package ru.ozh.recycler.decorator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.ozh.bunchdecorator.example.R
import com.ozh.dd.example.controllers.Controller
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

//        pager2_rv.apply {
//            adapter = easyAdapter2.apply { setFirstInvisibleItemEnabled(false) }
//        }

//        val offsetDrawer = object : OffsetDrawer {
//            override fun getItemOffsets(outRect: Rect, childView: View, recyclerView: RecyclerView, state: RecyclerView.State) {
//                outRect.bottom = resources.getDimensionPixelOffset(R.dimen.dp32)
//            }
//        }
//
//        val decorator = decorator {
//            underlay {
//                layer {
//                    attachTo(RECYCLER_VIEW)
//                    drawBy(LinePagerIndicatorDecoration())
//                }
//            }
//
//            offsets {
//                offset {
//                    attachTo(EACH_VIEW)
//                    drawBy(offsetDrawer)
//                }
//            }
//        }

//        pager_rv.addItemDecoration(decorator)
//        pager2_rv.children.forEach { view ->
//            if (view is RecyclerView) {
//                view.addItemDecoration(decorator)
//            }
//        }

        val itemList = ItemList.create()

        (0..5).forEach {
            itemList.add("$it", controller)
        }

        easyAdapter.setItems(itemList)
        easyAdapter2.setItems(itemList)
    }
}

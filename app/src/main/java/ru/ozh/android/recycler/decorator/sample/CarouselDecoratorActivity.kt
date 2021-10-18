package ru.ozh.android.recycler.decorator.sample

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import ru.ozh.android.recycler.decorator.sample.databinding.ActivityPagerBinding
import ru.ozh.android.recycler.decorator.sample.pager.controllers.StubController
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList

class CarouselDecoratorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPagerBinding

    private val easyAdapter = EasyAdapter()
    private val easyAdapter2 = EasyAdapter()

    private val controller by lazy {
        StubController { parent ->
            ActivityPagerBinding.inflate(LayoutInflater.from(this), parent, false)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPagerBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.rootView)
        init()
    }

    private fun init() {
        binding.pagerRv.apply {
            layoutManager = LinearLayoutManager(this@CarouselDecoratorActivity, RecyclerView.HORIZONTAL, false)
            adapter = easyAdapter.apply { isFirstInvisibleItemEnabled = false }
            PagerSnapHelper().attachToRecyclerView(this)
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
            itemList.add(controller)
        }

        easyAdapter.setItems(itemList)
        easyAdapter2.setItems(itemList)
    }
}

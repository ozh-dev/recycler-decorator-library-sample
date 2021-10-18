package ru.ozh.android.recycler.decorator.sample.pager

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import ru.ozh.android.recycler.decorator.sample.pager.controllers.StubController
import ru.ozh.android.recycler.decorator.sample.list.decor.RoundDecor
import ru.ozh.android.recycler.decorator.sample.list.decor.SimpleOffsetDrawer
import ru.ozh.android.recycler.decorator.lib.Decorator
import ru.ozh.android.recycler.decorator.sample.R
import ru.ozh.android.recycler.decorator.sample.databinding.ActivityPagerBinding
import ru.ozh.android.recycler.decorator.sample.databinding.ItemControllerLongCardBinding
import ru.ozh.android.recycler.decorator.sample.databinding.ItemControllerPagerBinding
import ru.ozh.android.recycler.decorator.sample.pager.controllers.LinePagerIndicatorDecoration
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList
import ru.ozh.android.recycler.decorator.sample.pager.controllers.ScaleLinePageIndicatorDecoration
import ru.ozh.android.recycler.decorator.sample.px

class CarouselDecoratorActivityView : AppCompatActivity() {

    private lateinit var binding: ActivityPagerBinding
    private val easyAdapter = EasyAdapter()

    private val controller by lazy {
        StubController { parent ->
            ItemControllerPagerBinding.inflate(LayoutInflater.from(this), parent, false)
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
            layoutManager = LinearLayoutManager(this@CarouselDecoratorActivityView, RecyclerView.HORIZONTAL, false)
            adapter = easyAdapter.apply { isFirstInvisibleItemEnabled = false }
            PagerSnapHelper().attachToRecyclerView(this)
        }

        val roundViewHoldersGroupDrawer = RoundDecor(8.px.toFloat())

        val simpleOffsetDrawer2 = SimpleOffsetDrawer(
            left = 16.px,
            right = 16.px,
            bottom = 32.px
        )

        val decorator2 = Decorator.Builder()
            .underlay(roundViewHoldersGroupDrawer)
            .overlay(ScaleLinePageIndicatorDecoration(context = this))
            .offset(simpleOffsetDrawer2)
            .build()

        binding.pagerRv.addItemDecoration(decorator2)

        val itemList = ItemList.create()

        (0..5).forEach { _ ->
            itemList.add(controller)
        }

        easyAdapter.setItems(itemList)
    }
}

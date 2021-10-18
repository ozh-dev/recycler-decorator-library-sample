package ru.ozh.android.recycler.decorator.sample.list

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.ozh.android.recycler.decorator.sample.R
import ru.ozh.android.recycler.decorator.sample.R.layout
import ru.ozh.android.recycler.decorator.sample.databinding.ActivityRecyclerBinding
import ru.ozh.android.recycler.decorator.sample.databinding.ItemControllerShortCardBinding
import ru.ozh.android.recycler.decorator.sample.pager.controllers.StubController
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList

class SdkLinearDecoratorActivityView : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerBinding
    private val easyAdapter = EasyAdapter()

    private val shortCardController by lazy {
        StubController { parent ->
            ItemControllerShortCardBinding.inflate(LayoutInflater.from(this), parent, false)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        init()
    }

    private fun init() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@SdkLinearDecoratorActivityView)
            adapter = easyAdapter
        }

        val dividerItemDecoration = DividerItemDecoration(this, RecyclerView.VERTICAL)
        ResourcesCompat.getDrawable(resources, R.drawable.divider_drawable, null)?.let { drawable ->
            dividerItemDecoration.setDrawable(drawable)
        }
        binding.recyclerView.addItemDecoration(dividerItemDecoration)

        ItemList.create()
            .apply {
                repeat(6) {
                    add(shortCardController)
                }
            }
            .also(easyAdapter::setItems)
    }
}
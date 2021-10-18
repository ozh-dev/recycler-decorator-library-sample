package ru.ozh.android.recycler.decorator.sample.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import ru.ozh.android.recycler.decorator.sample.list.decor.Gap
import ru.ozh.android.recycler.decorator.sample.list.decor.LinearDividerDrawer
import ru.ozh.android.recycler.decorator.sample.list.decor.ParallaxDecor
import ru.ozh.android.recycler.decorator.sample.list.decor.RoundDecor
import ru.ozh.android.recycler.decorator.sample.list.decor.RoundPolitic.Group
import ru.ozh.android.recycler.decorator.sample.list.decor.Rules
import ru.ozh.android.recycler.decorator.sample.list.decor.SimpleOffsetDrawer
import ru.ozh.android.recycler.decorator.sample.pager.controllers.StubController
import ru.ozh.android.recycler.decorator.sample.px
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList
import ru.ozh.android.recycler.decorator.lib.Decorator
import ru.ozh.android.recycler.decorator.sample.R
import ru.ozh.android.recycler.decorator.sample.databinding.ActivityRecyclerBinding
import ru.ozh.android.recycler.decorator.sample.databinding.ItemControllerLongCardBinding
import ru.ozh.android.recycler.decorator.sample.databinding.ItemControllerShortCardBinding
import ru.ozh.android.recycler.decorator.sample.databinding.ItemControllerSpaceBinding

class LinearDecoratorActivityView : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerBinding
    private val easyAdapter = EasyAdapter()

    private val shortCardController by lazy {
        StubController { parent ->
            ItemControllerShortCardBinding.inflate(LayoutInflater.from(this), parent, false)
        }
    }
    private val longCardController by lazy {
        StubController { parent ->
            ItemControllerLongCardBinding.inflate(LayoutInflater.from(this), parent, false)
        }
    }
    private val spaceController by lazy {
        StubController { parent ->
            ItemControllerSpaceBinding.inflate(LayoutInflater.from(this), parent, false)
        }
    }

    private val horizontalAndVerticalOffsetDecor by lazy {
        SimpleOffsetDrawer(
            left = 16.px,
            top = 8.px,
            right = 16.px,
            bottom = 8.px
        )
    }

    private val horizontalOffsetDecor by lazy {
        SimpleOffsetDrawer(
            left = 16.px,
            right = 16.px
        )
    }

    private val dividerDrawer2Dp by lazy {
        LinearDividerDrawer(
            Gap(
                ContextCompat.getColor(this, R.color.gray_A150),
                2.px,
                paddingStart = 16.px,
                paddingEnd = 16.px,
                rule = Rules.MIDDLE
            )
        )
    }

    private val roundDecor by lazy {
        RoundDecor(12.px.toFloat(), roundPolitic = Group())
    }
    private val paralaxDecor by lazy {
        ParallaxDecor(this, R.drawable.and_q)
    }

    private val decorator by lazy {
        Decorator.Builder()
            .underlay(longCardController.viewType() to roundDecor)
            .underlay(spaceController.viewType() to paralaxDecor)
            .overlay(shortCardController.viewType() to dividerDrawer2Dp)
            .offset(longCardController.viewType() to horizontalOffsetDecor)
            .offset(shortCardController.viewType() to horizontalOffsetDecor)
            .offset(spaceController.viewType() to horizontalAndVerticalOffsetDecor)
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        init()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.linear_decor_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.disable_decorator -> {
                binding.recyclerView.removeItemDecoration(decorator)
                true
            }

            R.id.enable_decorator -> {
                if (binding.recyclerView.itemDecorationCount == 0) {
                    binding.recyclerView.addItemDecoration(decorator)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun init() {
        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(this@LinearDecoratorActivityView)
            adapter = easyAdapter
            addItemDecoration(decorator)
            setPadding(0, 16.px, 0, 16.px)
        }

        ItemList.create()
            .apply {
                repeat(3) {
                    add(longCardController)
                }
                add(spaceController)
                repeat(5) {
                    add(shortCardController)
                }
            }
            .also(easyAdapter::setItems)
    }
}

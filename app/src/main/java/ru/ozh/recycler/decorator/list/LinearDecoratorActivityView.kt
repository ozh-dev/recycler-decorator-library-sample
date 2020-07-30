package ru.ozh.recycler.decorator.list

import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ozh.bunchdecorator.example.R
import kotlinx.android.synthetic.main.activity_recycler.*
import ru.ozh.recycler.decorator.list.decor.*
import ru.ozh.recycler.decorator.pager.controllers.Controller
import ru.ozh.recycler.decorator.px
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList
import ru.surfstudio.android.recycler.decorator.Decorator

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class LinearDecoratorActivityView : AppCompatActivity() {

    private val easyAdapter = EasyAdapter()

    private val shortCardController = Controller(R.layout.item_controller_short_card)
    private val longCardController = Controller(R.layout.item_controller_long_card)
    private val spaceController = Controller(R.layout.item_controller_space)

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
                resources.getColor(R.color.gray_A150),
                2.px,
                paddingStart = 16.px,
                paddingEnd = 16.px,
                rule = Rules.MIDDLE
            )
        )
    }

    private val roundDecor by lazy {
        RoundDecor(12.px.toFloat(), roundPolitic = RoundPolitic.Group())
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
        setContentView(R.layout.activity_recycler)
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
                recycler_view.removeItemDecoration(decorator)
                true
            }

            R.id.enable_decorator -> {
                if (recycler_view.itemDecorationCount == 0) {
                    recycler_view.addItemDecoration(decorator)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun init() {
        with(recycler_view) {
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

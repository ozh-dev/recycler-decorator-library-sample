package com.ozh.bunchdecorator.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ozh.bunchdecorator.example.controllers.IntegerController
import com.ozh.bunchdecorator.example.controllers.StringController
import com.ozh.bunchdecorator.lib.decorators.layers.Decorator
import com.ozh.bunchdecorator.lib.decorators.sample.*
import kotlinx.android.synthetic.main.activity_main.*
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList

class RoundedDecoratorActivity : AppCompatActivity() {

    private val easyAdapter = EasyAdapter()

    private val intController = IntegerController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@RoundedDecoratorActivity)
            adapter = easyAdapter.apply { setFirstInvisibleItemEnabled(false) }
        }

        val decorator = Decorator {

            underlay {

                layer {
                    viewItemType = intController.viewType()
                    layerDrawer = RoundViewHoldersGroupDrawer(resources.getDimensionPixelOffset(R.dimen.dp8).toFloat())
                }
            }

            offsets {
                offset {
                    offsetDrawer = SimpleOffsetDrawer(resources.getDimensionPixelOffset(R.dimen.dp8))
                }
            }
        }

        recycler_view.addItemDecoration(decorator.build())

        val items = ItemList.create()
        (0..5).forEach {
            items.add(it, intController)
        }

        easyAdapter.setItems(items)

        recycler_view.invalidateItemDecorations()
    }
}

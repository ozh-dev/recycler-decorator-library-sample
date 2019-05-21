package com.ozh.bunchdecorator.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ozh.bunchdecorator.example.controllers.IntegerController
import com.ozh.bunchdecorator.example.controllers.StringController
import com.ozh.bunchdecorator.lib.decorators.sample.LinearOverDividerDrawer
import com.ozh.bunchdecorator.lib.decorators.sample.Rules
import com.ozh.bunchdecorator.lib.decorators.sample.Gap
import com.ozh.bunchdecorator.lib.decorators.layers.Decorator
import kotlinx.android.synthetic.main.activity_main.*
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList

class LinearDecoratorActivity : AppCompatActivity() {

    private val easyAdapter = EasyAdapter()

    private val intController = IntegerController()
    private val stringController = StringController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@LinearDecoratorActivity)
            adapter = easyAdapter
        }

        val items = ItemList.create()
        (0..5).forEach {
            items.add(it, intController)
        }

        (0..5).forEach {
            items.add("String $it", stringController)
        }
        easyAdapter.setItems(items)

        val decorator = Decorator {

            underlay {

            }

            overlay {
                layer {
                    viewItemType = intController.viewType()
                    layerDrawer = LinearOverDividerDrawer(
                        Gap(
                            resources.getColor(R.color.material_900),
                            resources.getDimensionPixelOffset(R.dimen.dp28),
                            resources.getDimensionPixelOffset(R.dimen.dp8),
                            resources.getDimensionPixelOffset(R.dimen.dp8),
                            Rules.MIDDLE
                        )
                    )
                }

                layer {
                    layerDrawer = LinearOverDividerDrawer(
                        Gap(
                            resources.getColor(R.color.material_red_A700),
                            resources.getDimensionPixelOffset(R.dimen.dp16),
                            resources.getDimensionPixelOffset(R.dimen.dp16),
                            resources.getDimensionPixelOffset(R.dimen.dp16),
                            Rules.MIDDLE or Rules.END
                        )
                    )
                }
            }
        }

        recycler_view.addItemDecoration(decorator.build())
    }
}

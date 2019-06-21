package com.ozh.bunchdecorator.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ozh.bunchdecorator.example.controllers.Controller
import com.ozh.dsl.decorators.layers.Decorator
import com.ozh.dsl.decorators.sample.*
import kotlinx.android.synthetic.main.activity_main.*
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList

class LinearDecoratorActivity : AppCompatActivity() {

    private val easyAdapter = EasyAdapter()

    private val controller56 = Controller(R.layout.item_controller56)
    private val controller80 = Controller(R.layout.item_controller80)

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

        val decorator = Decorator {

            underlay {

                layer {
                    viewItemType = controller56.viewType()
                    layerDrawer = RoundViewHoldersGroupDrawer(resources.getDimensionPixelOffset(R.dimen.dp8).toFloat())
                }

                layer {
                    viewItemType = controller56.viewType()
                    layerDrawer = LinearOverDividerDrawer(
                        Gap(
                            resources.getColor(R.color.material_red_A700),
                            resources.getDimensionPixelOffset(R.dimen.dp8),
                            resources.getDimensionPixelOffset(R.dimen.dp16),
                            resources.getDimensionPixelOffset(R.dimen.dp16),
                            Rules.MIDDLE
                        )
                    )
                }
            }

            overlay {

                layer {
                    viewItemType = controller80.viewType()
                    layerDrawer = LinearOverDividerDrawer(
                        Gap(
                            resources.getColor(R.color.material_50),
                            resources.getDimensionPixelOffset(R.dimen.dp28),
                            resources.getDimensionPixelOffset(R.dimen.dp8),
                            resources.getDimensionPixelOffset(R.dimen.dp8),
                            Rules.MIDDLE
                        )
                    )
                }

                layer {
                    viewItemType = controller80.viewType()
                    layerDrawer = LinearOverDividerDrawer(
                        Gap(
                            resources.getColor(R.color.material_501),
                            resources.getDimensionPixelOffset(R.dimen.dp2),
                            rule = Rules.MIDDLE or Rules.END
                        )
                    )
                }
            }

            offsets {

                offset {
                    viewItemType = controller80.viewType()
                    offsetDrawer = SimpleOffsetDrawer(resources.getDimensionPixelOffset(R.dimen.dp8))
                }

                offset {
                    viewItemType = controller56.viewType()
                    offsetDrawer = SimpleOffsetDrawer(resources.getDimensionPixelOffset(R.dimen.dp16))
                }
            }
        }
        recycler_view.addItemDecoration(decorator.build())

        val items = ItemList.create()
        (0..5).forEach {
            items.add("Round $it", controller56)
        }
        (0..10).forEach {
            items.add("String $it", controller80)
        }
        easyAdapter.setItems(items)
    }
}

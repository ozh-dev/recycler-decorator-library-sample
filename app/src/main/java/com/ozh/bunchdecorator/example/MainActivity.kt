package com.ozh.bunchdecorator.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ozh.bunchdecorator.example.controllers.IntegerController
import com.ozh.bunchdecorator.example.controllers.MyOffsetDrawer
import com.ozh.bunchdecorator.example.controllers.StringController
import com.ozh.bunchdecorator.example.controllers.MyUnderlayDrawer
import com.ozh.bunchdecorator.lib.decorators.layers.Decorator
import com.ozh.bunchdecorator.lib.decorators.deprecated.LinearItemsDecoration
import kotlinx.android.synthetic.main.activity_main.*
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList

class MainActivity : AppCompatActivity() {

    private val easyAdapter = EasyAdapter()

    private val intController = IntegerController()
    private val stringController = StringController()
    private val linearItemsDecoration = LinearItemsDecoration()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = easyAdapter
            addItemDecoration(linearItemsDecoration)
        }

        val items = ItemList.create()
        (0..5).forEach {
            items.add(it, intController)
        }

        (0..5).forEach {
            items.add("String $it", stringController)
        }
        easyAdapter.setItems(items)

        change_draw_btn.setOnClickListener {
            linearItemsDecoration.drawOver = linearItemsDecoration.drawOver.not()
            recycler_view.invalidateItemDecorations()
        }


        val decorator = Decorator {

            underlay {
                layer {
                    viewItemType = intController.viewType()
                    layerDrawer = MyUnderlayDrawer()
                }

                layer {

                }
            }

            overlay {
                layer {

                }

                layer {

                }
            }

            offsets {
                offset {
                    viewItemType = intController.viewType()
                    offsetDrawer = MyOffsetDrawer()
                }
            }
        }

        recycler_view.addItemDecoration(decorator.build())
    }
}

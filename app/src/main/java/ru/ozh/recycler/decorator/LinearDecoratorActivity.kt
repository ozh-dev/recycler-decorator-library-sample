package ru.ozh.recycler.decorator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ozh.bunchdecorator.example.R
import com.ozh.dd.example.controllers.Controller
import kotlinx.android.synthetic.main.activity_main.*
import ru.surfstudio.android.easyadapter.EasyAdapter

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

//        val decorator = decorator {
//
//            underlay {
//
//                layer {
//                    attachTo(controller56.viewType())
//                    drawBy(
//                        RoundViewHoldersGroupDrawer(
//                            resources.getDimensionPixelOffset(
//                                R.dimen.dp8
//                            ).toFloat()
//                        )
//                    )
//                }
//
//                layer {
//                    attachTo(controller56.viewType())
//                    drawBy(
//                        LinearOverDividerDrawer(
//                            Gap(
//                                resources.getColor(R.color.material_red_A700),
//                                resources.getDimensionPixelOffset(R.dimen.dp8),
//                                resources.getDimensionPixelOffset(R.dimen.dp16),
//                                resources.getDimensionPixelOffset(R.dimen.dp16),
//                                Rules.MIDDLE
//                            )
//                        )
//                    )
//                }
//            }
//
//            overlay {
//
//                layer {
//                    attachTo(controller80.viewType())
//                    drawBy(
//                        LinearOverDividerDrawer(
//                            Gap(
//                                resources.getColor(R.color.material_50),
//                                resources.getDimensionPixelOffset(R.dimen.dp28),
//                                resources.getDimensionPixelOffset(R.dimen.dp8),
//                                resources.getDimensionPixelOffset(R.dimen.dp8),
//                                Rules.MIDDLE
//                            )
//                        )
//                    )
//                }
//
//                layer {
//                    attachTo(controller80.viewType())
//                    drawBy(
//                        LinearOverDividerDrawer(
//                            Gap(
//                                resources.getColor(R.color.material_501),
//                                resources.getDimensionPixelOffset(R.dimen.dp2),
//                                rule = Rules.MIDDLE or Rules.END
//                            )
//                        )
//                    )
//                }
//            }
//
//            offsets {
//
//                offset {
//                    attachTo(controller80.viewType())
//                    drawBy(
//                        SimpleOffsetDrawer(
//                            resources.getDimensionPixelOffset(
//                                R.dimen.dp8
//                            )
//                        )
//                    )
//                }
//
//                offset {
//                    attachTo(controller56.viewType())
//                    drawBy(
//                        SimpleOffsetDrawer(
//                            resources.getDimensionPixelOffset(
//                                R.dimen.dp16
//                            )
//                        )
//                    )
//                }
//            }
//        }

//        recycler_view.addItemDecoration(decorator)

//        val items = ItemList.create()
//        (0..5).forEach {
//            items.add("Round $it", controller56)
//        }
//        (0..10).forEach {
//            items.add("String $it", controller80)
//        }
//        easyAdapter.setItems(items)
    }
}

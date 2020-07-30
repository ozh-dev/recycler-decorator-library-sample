package ru.ozh.recycler.decorator.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ozh.bunchdecorator.example.R
import kotlinx.android.synthetic.main.activity_recycler.*
import ru.ozh.recycler.decorator.pager.controllers.Controller
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList

class SdkLinearDecoratorActivityView : AppCompatActivity() {

    private val easyAdapter = EasyAdapter()

    private val shortCardController = Controller(R.layout.item_controller_short_card)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)
        init()
    }

    private fun init() {
        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@SdkLinearDecoratorActivityView)
            adapter = easyAdapter
        }

        val dividerItemDecoration = DividerItemDecoration(this, RecyclerView.VERTICAL)
        dividerItemDecoration.setDrawable(resources.getDrawable(R.drawable.divider_drawable))
        recycler_view.addItemDecoration(dividerItemDecoration)

        ItemList.create()
            .apply {
                repeat(6) {
                    add(shortCardController)
                }
            }
            .also(easyAdapter::setItems)
    }
}

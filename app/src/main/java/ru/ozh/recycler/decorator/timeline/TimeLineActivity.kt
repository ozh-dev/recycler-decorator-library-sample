package ru.ozh.recycler.decorator.timeline

import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ozh.bunchdecorator.example.R
import kotlinx.android.synthetic.main.activity_recycler.*
import ru.ozh.recycler.decorator.Colors
import ru.ozh.recycler.decorator.timeline.decor.EvenTimelineDecor
import ru.ozh.recycler.decorator.timeline.decor.EventOffsetDecor
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList
import ru.surfstudio.android.recycler.decorator.Decorator

class TimeLineActivity : AppCompatActivity() {

    private val easyAdapter = EasyAdapter()

    private val events = listOf(
        Event(eventName = "Prospect", status = Status.DONE, gravity = Gravity.END, icon = R.drawable.ic_partner),
        Event(eventName = "Tour", status = Status.DONE, gravity = Gravity.START, icon = R.drawable.ic_tour),
        Event(eventName = "Offer", status = Status.PROCESS, gravity = Gravity.END, icon = R.drawable.ic_money),
        Event(eventName = "Contract", status = Status.UNDONE, gravity = Gravity.START, icon = R.drawable.ic_contract),
        Event(eventName = "Settled", status = Status.UNDONE, gravity = Gravity.END, icon = R.drawable.ic_house)
    )

    private val eventController = EventController()

    private val decorator by lazy {
        Decorator.Builder()
            .underlay(EvenTimelineDecor(this))
            .offset(EventOffsetDecor())
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)
        init()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.decorator_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.dark_mode -> {
                Colors.currentTheme = Colors.Theme.DARK
                recycler_view.invalidateItemDecorations()
                easyAdapter.notifyDataSetChanged()
                true
            }
            R.id.light_mode -> {
                Colors.currentTheme = Colors.Theme.LIGHT
                recycler_view.invalidateItemDecorations()
                easyAdapter.notifyDataSetChanged()
                true
            }

            R.id.disable_decorator -> {
                recycler_view.removeItemDecorationAt(0)
                true
            }

            R.id.enable_decorator -> {
                if(recycler_view.itemDecorationCount == 0) {
                    recycler_view.addItemDecoration(decorator)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun init() {

        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@TimeLineActivity)
            adapter = easyAdapter
            addItemDecoration(decorator)
        }

        ItemList.create()
            .addAll(events, eventController)
            .also(easyAdapter::setItems)
    }
}
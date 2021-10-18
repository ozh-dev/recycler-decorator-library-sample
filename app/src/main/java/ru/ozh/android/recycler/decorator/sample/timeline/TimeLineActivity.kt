package ru.ozh.android.recycler.decorator.sample.timeline

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ru.ozh.android.recycler.decorator.sample.R
import ru.ozh.android.recycler.decorator.lib.Decorator
import ru.ozh.android.recycler.decorator.sample.timeline.Status.DONE
import ru.ozh.android.recycler.decorator.sample.timeline.Status.PROCESS
import ru.ozh.android.recycler.decorator.sample.timeline.Status.UNDONE
import ru.ozh.android.recycler.decorator.sample.Colors
import ru.ozh.android.recycler.decorator.sample.databinding.ActivityRecyclerBinding
import ru.ozh.android.recycler.decorator.sample.timeline.decor.EvenTimelineDecor
import ru.ozh.android.recycler.decorator.sample.timeline.decor.EventOffsetDecor
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList

class TimeLineActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerBinding

    private val easyAdapter = EasyAdapter()

    private val events = listOf(
        Event(eventName = "Prospect", status = DONE, gravity = Gravity.END, icon = R.drawable.ic_partner),
        Event(eventName = "Tour", status = DONE, gravity = Gravity.START, icon = R.drawable.ic_tour),
        Event(eventName = "Offer", status = PROCESS, gravity = Gravity.END, icon = R.drawable.ic_money),
        Event(eventName = "Contract", status = UNDONE, gravity = Gravity.START, icon = R.drawable.ic_contract),
        Event(eventName = "Settled", status = UNDONE, gravity = Gravity.END, icon = R.drawable.ic_house)
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
        binding = ActivityRecyclerBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        init()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.decorator_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        val recyclerView = binding.recyclerView
        return when (item.itemId) {
            R.id.dark_mode -> {
                Colors.currentTheme = Colors.Theme.DARK
                recyclerView.invalidateItemDecorations()
                easyAdapter.notifyDataSetChanged()
                true
            }
            R.id.light_mode -> {
                Colors.currentTheme = Colors.Theme.LIGHT
                recyclerView.invalidateItemDecorations()
                easyAdapter.notifyDataSetChanged()
                true
            }

            R.id.disable_decorator -> {
                recyclerView.removeItemDecorationAt(0)
                true
            }

            R.id.enable_decorator -> {
                if(recyclerView.itemDecorationCount == 0) {
                    recyclerView.addItemDecoration(decorator)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun init() {

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@TimeLineActivity)
            adapter = easyAdapter
            addItemDecoration(decorator)
        }

        ItemList.create()
            .addAll(events, eventController)
            .also(easyAdapter::setItems)
    }
}
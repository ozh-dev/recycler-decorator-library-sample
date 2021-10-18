package ru.ozh.android.recycler.decorator.sample.chat

import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.ozh.android.recycler.decorator.lib.Decorator
import ru.ozh.android.recycler.decorator.sample.R
import ru.ozh.android.recycler.decorator.sample.chat.decor.ChatDecorOffset
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList
import ru.ozh.android.recycler.decorator.sample.chat.controller.ChatMessageController
import ru.ozh.android.recycler.decorator.sample.chat.decor.ChatMessageDecor
import ru.ozh.android.recycler.decorator.sample.chat.decor.StickyHeaderDecor
import ru.ozh.android.recycler.decorator.sample.chat.controller.MessageTimeController
import ru.ozh.android.recycler.decorator.sample.chat.decor.CircleBarDecor
import ru.ozh.android.recycler.decorator.sample.chat.decor.ScrollBarDecor
import ru.ozh.android.recycler.decorator.sample.databinding.ActivityRecyclerBinding

class ChatActivityView : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerBinding
    private val easyAdapter = EasyAdapter()
            .apply {
                isFirstInvisibleItemEnabled = true
            }

    private val chatController = ChatMessageController()
    private val messageTimeController = MessageTimeController()

    val decorator by lazy<RecyclerView.ItemDecoration> {
        Decorator.Builder()
            .underlay(CircleBarDecor())
            .overlay(StickyHeaderDecor())
            .underlay(chatController.viewType() to ChatMessageDecor(this))
            .overlay(ScrollBarDecor())
            .offset(chatController.viewType() to ChatDecorOffset())
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
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@ChatActivityView)
            adapter = easyAdapter
        }

        binding.recyclerView.addItemDecoration(decorator)

        val items = ItemList.create()

        repeat(100) { number ->
            if(number.rem(4) == 0) {
                val date = DateFormat.format("dd MMMM yyyy HH:mm:ss", System.currentTimeMillis())
                items.add(date.toString(), messageTimeController)
            }
            val direction = ChatMessageDirection.values()[(0..1).random()]
            items.add(ChatObject(number, direction), chatController)
        }

        easyAdapter.setItems(items)
    }
}
package ru.ozh.recycler.decorator.chat

import android.os.Bundle
import android.text.format.DateFormat
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ozh.bunchdecorator.example.R
import kotlinx.android.synthetic.main.activity_recycler.*
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList
import ru.surfstudio.android.recycler.decorator.Decorator
import ru.ozh.recycler.decorator.chat.controller.ChatMessageController
import ru.ozh.recycler.decorator.chat.controller.MessageTimeController
import ru.ozh.recycler.decorator.chat.decor.*
import ru.surfstudio.android.recycler.decorator.sample.easyadapter.chat.ChatObject

class ChatActivityView : AppCompatActivity() {

    private val easyAdapter = EasyAdapter()
            .apply {
                setFirstInvisibleItemEnabled(true)
            }

    private val chatController = ChatMessageController()
    private val messageTimeController = MessageTimeController()

    val decorator by lazy {
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
        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@ChatActivityView)
            adapter = easyAdapter
        }

        recycler_view.addItemDecoration(decorator)

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
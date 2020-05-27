package ru.ozh.recycler.decorator.chat

import android.os.Bundle
import android.text.format.DateFormat
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)
        init()
    }

    private fun init() {
        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@ChatActivityView)
            adapter = easyAdapter
        }

        val decorator = Decorator.Builder()
                .underlay(CircleBarDecor())
                .underlay(messageTimeController.viewType() to MessageTimeDecor(this))
                .underlay(chatController.viewType() to ChatMessageDecor(this))
                .overlay(ScrollBarDecor())
                .offset(chatController.viewType() to ChatDecorOffset())
                .build()

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
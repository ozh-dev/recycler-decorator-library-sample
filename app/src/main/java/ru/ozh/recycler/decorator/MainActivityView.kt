package ru.ozh.recycler.decorator

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ozh.bunchdecorator.example.R
import kotlinx.android.synthetic.main.activity_main.*
import ru.ozh.recycler.decorator.chat.ChatActivityView
import ru.ozh.recycler.decorator.list.LinearDecoratorActivityView
import ru.ozh.recycler.decorator.list.SdkLinearDecoratorActivityView
import ru.ozh.recycler.decorator.pager.CarouselDecoratorActivityView
import ru.ozh.recycler.decorator.timeline.TimeLineActivity

class MainActivityView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linear_decor_btn.setOnClickListener {
            startActivity(Intent(this, LinearDecoratorActivityView::class.java))
        }

        sdk_linear_decor_btn.setOnClickListener {
            startActivity(Intent(this, SdkLinearDecoratorActivityView::class.java))
        }

        pager_decor_btn.setOnClickListener {
            startActivity(Intent(this, CarouselDecoratorActivityView::class.java))
        }

        chat_btn.setOnClickListener {
            startActivity(Intent(this, ChatActivityView::class.java))
        }

        timeline_btn.setOnClickListener {
            startActivity(Intent(this, TimeLineActivity::class.java))
        }
    }
}
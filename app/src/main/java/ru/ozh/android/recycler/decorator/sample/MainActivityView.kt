package ru.ozh.android.recycler.decorator.sample

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import ru.ozh.android.recycler.decorator.sample.chat.ChatActivityView
import ru.ozh.android.recycler.decorator.sample.databinding.ActivityMainBinding
import ru.ozh.android.recycler.decorator.sample.list.LinearDecoratorActivityView
import ru.ozh.android.recycler.decorator.sample.list.SdkLinearDecoratorActivityView
import ru.ozh.android.recycler.decorator.sample.pager.CarouselDecoratorActivityView
import ru.ozh.android.recycler.decorator.sample.timeline.TimeLineActivity

class MainActivityView : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        binding.linearDecorBtn.setOnClickListener {
            startActivity(Intent(this, LinearDecoratorActivityView::class.java))
        }

        binding.sdkLinearDecorBtn.setOnClickListener {
            startActivity(Intent(this, SdkLinearDecoratorActivityView::class.java))
        }

        binding.pagerDecorBtn.setOnClickListener {
            startActivity(Intent(this, CarouselDecoratorActivityView::class.java))
        }

        binding.chatBtn.setOnClickListener {
            startActivity(Intent(this, ChatActivityView::class.java))
        }

        binding.timelineBtn.setOnClickListener {
            startActivity(Intent(this, TimeLineActivity::class.java))
        }
    }
}
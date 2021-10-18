package ru.ozh.android.recycler.decorator.sample.chat.decor

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ru.ozh.android.recycler.decorator.lib.Decorator
import ru.ozh.android.recycler.decorator.sample.R
import ru.ozh.android.recycler.decorator.sample.px

class MessageTimeDecor(private val context: Context) : Decorator.ViewHolderDecor {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
            .apply {
                color = ContextCompat.getColor(context, android.R.color.darker_gray)
            }

    override fun draw(canvas: Canvas, view: View, recyclerView: RecyclerView, state: RecyclerView.State) {

        val timeView = view.findViewById<TextView>(R.id.time_tv)

        val textRectAreaOrigin = Rect()

        timeView.paint.getTextBounds(timeView.text.toString(), 0, timeView.text.length, textRectAreaOrigin)

        val textWith = textRectAreaOrigin.width()
        val textHeight = textRectAreaOrigin.height()

        val viewCy = (timeView.y + timeView.measuredHeight / 2).toInt()
        val viewCx = timeView.measuredWidth / 2

        //map text coordinates to canvas area
        val textRectAreaMapped = RectF(
                (viewCx - textWith / 2).toFloat(),
                (viewCy - textHeight / 2).toFloat(),
                (viewCx + textWith / 2).toFloat(),
                (viewCy + textHeight / 2).toFloat()
        )

        val scaleValue = textHeight * 0.45F

        textRectAreaMapped.inset(-scaleValue, -scaleValue)

        canvas.drawRoundRect(textRectAreaMapped, 10.px.toFloat(), 10.px.toFloat(), paint)
    }
}
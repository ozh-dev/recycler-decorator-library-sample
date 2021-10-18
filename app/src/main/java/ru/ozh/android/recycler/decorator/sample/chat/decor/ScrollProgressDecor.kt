package ru.ozh.android.recycler.decorator.sample.chat.decor

import android.graphics.*
import androidx.annotation.FloatRange
import androidx.recyclerview.widget.RecyclerView
import ru.ozh.android.recycler.decorator.lib.Decorator

abstract class ScrollProgressDecor : Decorator.RecyclerViewDecor {

    abstract fun draw(canvas: Canvas, recyclerView: RecyclerView, state: RecyclerView.State, @FloatRange(from = .0, to = 1.0) progress: Float)

    override fun draw(canvas: Canvas, recyclerView: RecyclerView, state: RecyclerView.State) {

        val currentOffset = recyclerView.computeVerticalScrollOffset()
        val maxOffset = recyclerView.computeVerticalScrollRange() - recyclerView.computeVerticalScrollExtent()
        val scrollProgress = currentOffset.toFloat() / maxOffset
        draw(canvas, recyclerView, state, scrollProgress)
    }
}
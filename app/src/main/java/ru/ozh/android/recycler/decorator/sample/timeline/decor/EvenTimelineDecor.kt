package ru.ozh.android.recycler.decorator.sample.timeline.decor

import android.content.Context
import android.graphics.*
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import ru.ozh.android.recycler.decorator.sample.R
import ru.ozh.android.recycler.decorator.sample.Colors
import ru.ozh.android.recycler.decorator.sample.timeline.Event
import ru.ozh.android.recycler.decorator.sample.timeline.EventController
import ru.ozh.android.recycler.decorator.sample.timeline.Status
import ru.ozh.android.recycler.decorator.lib.Decorator
import ru.ozh.android.recycler.decorator.sample.px

class EvenTimelineDecor(private val context: Context) : Decorator.RecyclerViewDecor {

    private val bigDotRadius = 16.px.toFloat()
    private val smallDotRadius = 8.px.toFloat()
    private val tinyDotRadius = 4.px.toFloat()

    private val icCheck = AppCompatResources.getDrawable(context, R.drawable.ic_check)?.toBitmap(16.px, 16.px)!!
    private val icClock = AppCompatResources.getDrawable(context, R.drawable.ic_clock)?.toBitmap(16.px, 16.px)!!

    private val points = mutableListOf<Pair<Event, PointF>>()
    private val path = Path()

    private val paint = Paint()
        .apply {
            isAntiAlias = true
            strokeWidth = 4.px.toFloat()
        }

    private val statusIconPaint = Paint()
        .apply {
            isAntiAlias = true
        }

    private val eventIconPaint = Paint()
        .apply {
            isAntiAlias = true
        }

    override fun draw(
        canvas: Canvas,
        recyclerView: RecyclerView,
        state: RecyclerView.State
    ) {

        canvas.drawColor(Colors.bgColor)

        points.clear()
        recyclerView.children.forEach { view ->
            val vh = recyclerView.getChildViewHolder(view) as EventController.Holder
            val y = view.bottom - (view.height / 2)
            val x = view.left - 32.px
            val point = PointF(x.toFloat(), y.toFloat())
            val event = vh.data!!
            points.add(event to point)
        }

        drawLine(canvas)
        drawCircle(canvas)
    }

    private fun drawCircle(canvas: Canvas) {
        points.forEach { (event, point) ->

            val (x, y) = point.x to point.y
            val status = event.status

            when (status) {
                Status.DONE -> {
                    eventIconPaint.colorFilter = PorterDuffColorFilter(Colors.doneColor, PorterDuff.Mode.SRC_ATOP)
                    paint.color = Colors.doneColor
                    canvas.drawCircle(x, y, bigDotRadius, paint)

                    statusIconPaint.colorFilter = PorterDuffColorFilter(Colors.bgColor, PorterDuff.Mode.SRC_ATOP)
                    val left = x - (icCheck.width / 2)
                    val top = y - (icCheck.height / 2)
                    canvas.drawBitmap(icCheck, left, top, statusIconPaint)
                }
                Status.PROCESS -> {
                    eventIconPaint.colorFilter = PorterDuffColorFilter(Colors.processColor, PorterDuff.Mode.SRC_ATOP)
                    paint.color = Colors.processColor
                    canvas.drawCircle(x, y, bigDotRadius, paint)

                    statusIconPaint.colorFilter = PorterDuffColorFilter(Colors.doneColor, PorterDuff.Mode.SRC_ATOP)
                    val left = x - (icCheck.width / 2)
                    val top = y - (icCheck.height / 2)
                    canvas.drawBitmap(icClock, left, top, statusIconPaint)
                }
                Status.UNDONE -> {
                    eventIconPaint.colorFilter = PorterDuffColorFilter(Colors.undoneEventColor, PorterDuff.Mode.SRC_ATOP)
                    statusIconPaint.colorFilter = PorterDuffColorFilter(Colors.undoneEventColor, PorterDuff.Mode.SRC_ATOP)
                    paint.color = Colors.undoneTimeLineColor
                    canvas.drawCircle(x, y, smallDotRadius, paint)
                    paint.color = Colors.bgColor
                    canvas.drawCircle(x, y, tinyDotRadius, paint)
                }
                Status.UNDEFINE -> {
                    paint.color = Color.WHITE
                }
            }

            val iconEvent = AppCompatResources.getDrawable(context, event.icon)?.toBitmap(40.px, 40.px)!!

            val iconTop = y - (iconEvent.height / 2)
            val iconLeft = (x / 2) - (iconEvent.width / 2)
            canvas.drawBitmap(iconEvent, iconLeft, iconTop, eventIconPaint)
        }
    }

    private fun drawLine(canvas: Canvas) {
        points.zipWithNext { point1, point2 ->

            val (x1, y1) = point1.second.x to point1.second.y
            val (x2, y2) = point2.second.x to point2.second.y
            val (status1, status2) = point1.first.status to point2.first.status

            if (status1 == Status.DONE && status2 == Status.PROCESS) {
                paint.color = Colors.doneColor
                paint.shader =
                    LinearGradient(x1, y1, x2, y2, Colors.doneColor, Colors.processColor, Shader.TileMode.CLAMP)
            } else if (status1 == Status.DONE && status2 == Status.DONE) {
                paint.color = Colors.doneColor
                paint.shader = null
            } else if (status1 == Status.PROCESS || status1 == Status.UNDONE) {
                paint.color = Colors.undoneTimeLineColor
                paint.shader = null
            }

            canvas.drawLine(x1, y1, x2, y2, paint)
        }
    }

}
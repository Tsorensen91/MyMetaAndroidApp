package chester.ac.uk.nextgenappandroid.components

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import java.time.YearMonth
import java.util.*

class MyMetaCalendarView(context: Context, attrs: AttributeSet): View(context, attrs) {

    private val calendar = Calendar.getInstance()
    private var weeksInMonth = 0

    private var cells = mutableListOf<RectF>()

    private val rectPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        style = Paint.Style.STROKE
        strokeWidth = 1.5f
    }

    init {
        calendar.set(2019, Calendar.MAY, 1)
        weeksInMonth = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()


    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val cellWidth = width.toFloat() / 7.0f
        val cellHeight = height.toFloat() / weeksInMonth.toFloat()

        for(x in 0 until 7){
            for(y in 0 until weeksInMonth){
                val rectX = x * cellWidth
                val rectY = y * cellHeight
                val rectWidth = rectX + cellWidth
                val rectHeight = rectY + cellHeight
                cells.add(RectF(rectX, rectY, rectWidth,rectHeight))
            }
        }

        canvas?.apply {
            for(i in 0 until cells.size) {
                canvas.drawRect(cells[i], rectPaint)
            }
        }

        cells.clear()
    }

}
package chester.ac.uk.nextgenappandroid.calendar

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import java.util.*

class MyMetaCalendarView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val calendar = Calendar.getInstance()
    private var weeksInMonth = 0

    private var cells = Array(35) { RectF(0f, 0f, 0f, 0f) }

    private val rectPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        style = Paint.Style.STROKE
        strokeWidth = 1.5f
    }

    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        style = Paint.Style.FILL
        textSize = 25f
    }


    init {
        calendar.set(2019, Calendar.MAY, 1)
        weeksInMonth = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));

        val cellWidth = measuredWidth.toFloat() / 7.0f
        val cellHeight = measuredHeight.toFloat() / weeksInMonth.toFloat()

        for (y in 0 until weeksInMonth) {
            for (x in 0 until 7) {

                val index = x + (y * 7)
                val rectX = x * cellWidth
                val rectY = y * cellHeight
                val rectWidth = rectX + cellWidth
                val rectHeight = rectY + cellHeight

                cells[index].left = rectX
                cells[index].top = rectY
                cells[index].right = rectWidth
                cells[index].bottom = rectHeight
            }
        }

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.apply {
            for (i in 0 until cells.size) {
                //Render cells
                canvas.drawRect(cells[i], rectPaint)

                //Render day number
                canvas.drawText((i + 1).toString(), cells[i].left + 20, cells[i].top + 30, textPaint)
            }
        }
    }


}
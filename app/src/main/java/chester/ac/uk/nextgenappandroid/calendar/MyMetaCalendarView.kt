package chester.ac.uk.nextgenappandroid.calendar

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.text.format.DateFormat
import android.util.AttributeSet
import android.view.View
import java.util.*

class CalRect(var x: Float, var y: Float, var width: Float, var height: Float) {

    var date: Date? = null

    fun getRectF(): RectF {
        return RectF(x, y, x + width, y + height);
    }
}

class MyMetaCalendarView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val calendar = GregorianCalendar.getInstance()
    private var currentDate: Date = calendar.time
    lateinit var calendarDate: Date
    private lateinit var startDateOfMonth: Date
    private var weeksInMonth = 0
    private var weekDayOfFirstDay = 0

    private lateinit var cells: Array<CalRect>

    private val rectPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        style = Paint.Style.STROKE
        strokeWidth = 1.5f
    }

    private val rectFillPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.GRAY
        style = Paint.Style.FILL_AND_STROKE
        strokeWidth = 1.5f
    }

    private val rectFillCurrentDatePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLUE
        style = Paint.Style.FILL
        strokeWidth = 1.5f
    }

    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        style = Paint.Style.FILL
        textSize = 25f
    }


    init {
        updateCalendar()
    }

    //Lots of plus and minus 1's and 2' (Just go with it)
    private fun updateCalendar() {
        calendarDate = calendar.time

        weekDayOfFirstDay = calendar.get(Calendar.DAY_OF_WEEK) - 2 // -2 because the week starts on a Sunday
        val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

        weeksInMonth = 6
        cells = Array(weeksInMonth * 7) { CalRect(0f, 0f, 0f, 0f) }

        calendar.set(Calendar.DAY_OF_MONTH, 1)
        startDateOfMonth = calendar.time

        for (i in 0 until cells.size) {

            if (i in weekDayOfFirstDay..(daysInMonth + 1)) {
                cells[i].date = startDateOfMonth
                calendar.add(Calendar.DATE, 1)
                startDateOfMonth = calendar.time
            }
        }
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

                cells[index].x = rectX
                cells[index].y = rectY
                cells[index].width = cellWidth
                cells[index].height = cellHeight
            }
        }

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.apply {
            for (i in 0 until cells.size) {

                //Render cells
                if (cells[i].date != null) {
                    canvas.drawRect(cells[i].getRectF(), rectPaint)

                    val day = (DateFormat.format("d", cells[i].date) as String)
                    canvas.drawText(day, cells[i].x + 20, cells[i].y + 30, textPaint)

                    if (cells[i].date == currentDate) {
                        canvas.drawRect(cells[i].getRectF(), rectFillCurrentDatePaint)
                    }
                } else {
                    canvas.drawRect(cells[i].getRectF(), rectFillPaint)
                    canvas.drawRect(cells[i].getRectF(), rectPaint)
                }
            }
        }
    }

    fun incrementMonth() {
        calendar.add(Calendar.MONTH, 1)
        updateCalendar()

        requestLayout()
        invalidate()
    }

    fun decrementMonth() {
        calendar.add(Calendar.MONTH, -1)
        updateCalendar()

        requestLayout()
        invalidate()
    }
}
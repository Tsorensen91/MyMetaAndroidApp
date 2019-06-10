package chester.ac.uk.nextgenappandroid.calendar

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import java.util.*
import android.R.attr.label
import android.graphics.*
import chester.ac.uk.nextgenappandroid.R.drawable.canvas


class CalRect(var x: Float, var y: Float, var width: Float, var height: Float) {

    lateinit var date: Date

    fun getRectF(): RectF {
        return RectF(x, y, x + width, y + height);
    }
}

class MyMetaCalendarView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    val calendar = GregorianCalendar()
    val currentDate = GregorianCalendar()

    private val daysCount = 42

    var cells = arrayOfNulls<CalRect>(daysCount)

    private val rectOutline = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        style = Paint.Style.STROKE
        strokeWidth = 1.5f
    }

    private val rectFillWhite = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.WHITE
        style = Paint.Style.FILL
        strokeWidth = 1.5f
    }

    private val rectFillGrey = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#ebebeb")
        style = Paint.Style.FILL_AND_STROKE
        strokeWidth = 1.5f
    }

    private val rectFillTeal = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#abe5e4")
        style = Paint.Style.FILL
        strokeWidth = 1.5f
    }

    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        style = Paint.Style.FILL
        textSize = 25f
    }

    private val circleTextPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.WHITE
        style = Paint.Style.FILL
        textSize = 25f
    }

    private val circleAppointmentPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#eca610")
        style = Paint.Style.FILL
    }

    private val circleNutritionPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#8dc63f")
        style = Paint.Style.FILL
    }

    private val circleTransportPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#6dcff6")
        style = Paint.Style.FILL
    }

    private val circleWorkPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#f06eaa")
        style = Paint.Style.FILL
    }

    private val circlePlusPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.GRAY
        style = Paint.Style.FILL
    }

    init {
        updateCalendar()
    }

    private fun updateCalendar() {
        val calendar = currentDate.clone() as Calendar

        // determine the cell for current month's beginning
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        var monthBeginningCell = calendar.get(Calendar.DAY_OF_WEEK) - 2

        if (monthBeginningCell == -1) //Stop wrapping of the cells out of the grid
            monthBeginningCell = 6

        // move calendar backwards to the beginning of the week
        calendar.add(Calendar.DAY_OF_MONTH, -monthBeginningCell)

        // fill cells
        for (i in 0 until cells.size) {
            cells[i] = CalRect(0f, 0f, 0f, 0f)
            cells[i]?.date = calendar.time
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));

        val cellWidth = measuredWidth.toFloat() / 7.0f
        val cellHeight = measuredHeight.toFloat() / 6.0f

        for (y in 0 until 6) {
            for (x in 0 until 7) {

                val index = x + (y * 7)
                val rectX = x * cellWidth
                val rectY = y * cellHeight

                cells[index]?.x = rectX
                cells[index]?.y = rectY
                cells[index]?.width = cellWidth
                cells[index]?.height = cellHeight
            }
        }

    }

    private val today = Date()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.apply {
            for (i in 0 until cells.size) {

                if (cells[i] != null) {
                    val cell = cells[i]
                    val date = cell!!.date
                    val day = date.date
                    val month = date.month
                    val year = date.year

                    //Render cells
                    if (month != currentDate.time.month || year != currentDate.time.year) { // Gray out days that aren't in the current month

                        canvas.drawRect(cell.getRectF(), rectFillGrey)
                    } else if (day == today.date && month == today.month && year == today.year) { //Highlight the current day

                        canvas.drawRect(cell.getRectF(), rectFillTeal)
                        canvas.drawText(day.toString(), cell.x + 20, cell.y + 30, textPaint)
                    } else { //Fill in other cells as white

                        canvas.drawRect(cell.getRectF(), rectFillWhite)
                        canvas.drawText(day.toString(), cell.x + 20, cell.y + 30, textPaint)
                    }

                    //Draw notification bubbles
                    for (entry in CalendarData.calendarEntries) {
                        if (entry.date.date == cell.date.date && entry.date.month == cell.date.month && entry.date.year == cell.date.year) {


                            when {
                                entry.events.size == 1 -> {
                                    val event = entry.events[0]

                                    when (event.type) {
                                        EventType.APPOINTMENT -> drawTextInCircle(canvas, cell.getRectF().left + (cell.getRectF().width() / 2), cell.getRectF().bottom - 25f, "Ap", circleAppointmentPaint, circleTextPaint)
                                        EventType.NUTRITION -> drawTextInCircle(canvas, cell.getRectF().left + (cell.getRectF().width() / 2), cell.getRectF().bottom - 25f, "Nu", circleNutritionPaint, circleTextPaint)
                                        EventType.TRANSPORT -> drawTextInCircle(canvas, cell.getRectF().left + (cell.getRectF().width() / 2), cell.getRectF().bottom - 25f, "Tr", circleTransportPaint, circleTextPaint)
                                        EventType.WORK -> drawTextInCircle(canvas, cell.getRectF().left + (cell.getRectF().width() / 2), cell.getRectF().bottom - 25f, "Wo", circleWorkPaint, circleTextPaint)

                                    }

                                }
                                entry.events.size == 2 -> {
                                    var event = entry.events[0]

                                    when (event.type) {
                                        EventType.APPOINTMENT -> drawTextInCircle(canvas, cell.getRectF().left + (cell.getRectF().width() / 2) - 25f, cell.getRectF().bottom - 25f, "Ap", circleAppointmentPaint, circleTextPaint)
                                        EventType.NUTRITION -> drawTextInCircle(canvas, cell.getRectF().left + (cell.getRectF().width() / 2) - 25f, cell.getRectF().bottom - 25f, "Nu", circleNutritionPaint, circleTextPaint)
                                        EventType.TRANSPORT -> drawTextInCircle(canvas, cell.getRectF().left + (cell.getRectF().width() / 2) - 25f, cell.getRectF().bottom - 25f, "Tr", circleTransportPaint, circleTextPaint)
                                        EventType.WORK -> drawTextInCircle(canvas, cell.getRectF().left + (cell.getRectF().width() / 2) - 25f, cell.getRectF().bottom - 25f, "Wo", circleWorkPaint, circleTextPaint)

                                    }

                                    event = entry.events[1]
                                    when (event.type) {
                                        EventType.APPOINTMENT -> drawTextInCircle(canvas, cell.getRectF().left + (cell.getRectF().width() / 2) + 25f, cell.getRectF().bottom - 25f, "Ap", circleAppointmentPaint, circleTextPaint)
                                        EventType.NUTRITION -> drawTextInCircle(canvas, cell.getRectF().left + (cell.getRectF().width() / 2) + 25f, cell.getRectF().bottom - 25f, "Nu", circleNutritionPaint, circleTextPaint)
                                        EventType.TRANSPORT -> drawTextInCircle(canvas, cell.getRectF().left + (cell.getRectF().width() / 2) + 25f, cell.getRectF().bottom - 25f, "Tr", circleTransportPaint, circleTextPaint)
                                        EventType.WORK -> drawTextInCircle(canvas, cell.getRectF().left + (cell.getRectF().width() / 2) + 25f, cell.getRectF().bottom - 25f, "Wo", circleWorkPaint, circleTextPaint)

                                    }
                                }
                                entry.events.size == 3 -> {

                                    var event = entry.events[0]

                                    when (event.type) {
                                        EventType.APPOINTMENT -> drawTextInCircle(canvas, cell.getRectF().left + (cell.getRectF().width() / 2) - 45f, cell.getRectF().bottom - 25f, "Ap", circleAppointmentPaint, circleTextPaint)
                                        EventType.NUTRITION -> drawTextInCircle(canvas, cell.getRectF().left + (cell.getRectF().width() / 2) - 45f, cell.getRectF().bottom - 25f, "Nu", circleNutritionPaint, circleTextPaint)
                                        EventType.TRANSPORT -> drawTextInCircle(canvas, cell.getRectF().left + (cell.getRectF().width() / 2) - 45f, cell.getRectF().bottom - 25f, "Tr", circleTransportPaint, circleTextPaint)
                                        EventType.WORK -> drawTextInCircle(canvas, cell.getRectF().left + (cell.getRectF().width() / 2) - 45f, cell.getRectF().bottom - 25f, "Wo", circleWorkPaint, circleTextPaint)

                                    }

                                    event = entry.events[1]
                                    when (event.type) {
                                        EventType.APPOINTMENT -> drawTextInCircle(canvas, cell.getRectF().left + (cell.getRectF().width() / 2), cell.getRectF().bottom - 25f, "Ap", circleAppointmentPaint, circleTextPaint)
                                        EventType.NUTRITION -> drawTextInCircle(canvas, cell.getRectF().left + (cell.getRectF().width() / 2), cell.getRectF().bottom - 25f, "Nu", circleNutritionPaint, circleTextPaint)
                                        EventType.TRANSPORT -> drawTextInCircle(canvas, cell.getRectF().left + (cell.getRectF().width() / 2), cell.getRectF().bottom - 25f, "Tr", circleTransportPaint, circleTextPaint)
                                        EventType.WORK -> drawTextInCircle(canvas, cell.getRectF().left + (cell.getRectF().width() / 2), cell.getRectF().bottom - 25f, "Wo", circleWorkPaint, circleTextPaint)

                                    }

                                    event = entry.events[2]
                                    when (event.type) {
                                        EventType.APPOINTMENT -> drawTextInCircle(canvas, cell.getRectF().left + (cell.getRectF().width() / 2) + 45f, cell.getRectF().bottom - 25f, "Ap", circleAppointmentPaint, circleTextPaint)
                                        EventType.NUTRITION -> drawTextInCircle(canvas, cell.getRectF().left + (cell.getRectF().width() / 2) + 45f, cell.getRectF().bottom - 25f, "Nu", circleNutritionPaint, circleTextPaint)
                                        EventType.TRANSPORT -> drawTextInCircle(canvas, cell.getRectF().left + (cell.getRectF().width() / 2) + 45f, cell.getRectF().bottom - 25f, "Tr", circleTransportPaint, circleTextPaint)
                                        EventType.WORK -> drawTextInCircle(canvas, cell.getRectF().left + (cell.getRectF().width() / 2) + 45f, cell.getRectF().bottom - 25f, "Wo", circleWorkPaint, circleTextPaint)

                                    }
                                }
                                entry.events.size > 3 -> {
                                    var event = entry.events[0]

                                    when (event.type) {
                                        EventType.APPOINTMENT -> drawTextInCircle(canvas, cell.getRectF().left + (cell.getRectF().width() / 2) - 45f, cell.getRectF().bottom - 25f, "Ap", circleAppointmentPaint, circleTextPaint)
                                        EventType.NUTRITION -> drawTextInCircle(canvas, cell.getRectF().left + (cell.getRectF().width() / 2) - 45f, cell.getRectF().bottom - 25f, "Nu", circleNutritionPaint, circleTextPaint)
                                        EventType.TRANSPORT -> drawTextInCircle(canvas, cell.getRectF().left + (cell.getRectF().width() / 2) - 45f, cell.getRectF().bottom - 25f, "Tr", circleTransportPaint, circleTextPaint)
                                        EventType.WORK -> drawTextInCircle(canvas, cell.getRectF().left + (cell.getRectF().width() / 2) - 45f, cell.getRectF().bottom - 25f, "Wo", circleWorkPaint, circleTextPaint)

                                    }

                                    event = entry.events[1]
                                    when (event.type) {
                                        EventType.APPOINTMENT -> drawTextInCircle(canvas, cell.getRectF().left + (cell.getRectF().width() / 2), cell.getRectF().bottom - 25f, "Ap", circleAppointmentPaint, circleTextPaint)
                                        EventType.NUTRITION -> drawTextInCircle(canvas, cell.getRectF().left + (cell.getRectF().width() / 2), cell.getRectF().bottom - 25f, "Nu", circleNutritionPaint, circleTextPaint)
                                        EventType.TRANSPORT -> drawTextInCircle(canvas, cell.getRectF().left + (cell.getRectF().width() / 2), cell.getRectF().bottom - 25f, "Tr", circleTransportPaint, circleTextPaint)
                                        EventType.WORK -> drawTextInCircle(canvas, cell.getRectF().left + (cell.getRectF().width() / 2), cell.getRectF().bottom - 25f, "Wo", circleWorkPaint, circleTextPaint)

                                    }

                                    drawTextInCircle(canvas, cell.getRectF().left + (cell.getRectF().width() / 2) + 45f, cell.getRectF().bottom - 25f, "+${entry.events.size - 2}", circlePlusPaint, circleTextPaint)
                                }
                            }
                        }
                    }

                    canvas.drawRect(cell.getRectF(), rectOutline) //Draw rect outline

                }
            }
        }
    }

    private fun drawTextInCircle(canvas: Canvas, xPos: Float, yPos: Float, text: String, circlePaint: Paint, textPaint: Paint) {
        val textRect = Rect()
        textPaint.getTextBounds(text, 0, text.length, textRect)

        canvas.drawCircle(xPos, yPos, 20f, circlePaint)
        if (text == "Ap")
            canvas.drawText(text, xPos - (textRect.width() / 2f), yPos + (textRect.height() / 4f) + 4f, textPaint) //MAGIC NUMBERS
        else
            canvas.drawText(text, xPos - (textRect.width() / 2f), yPos + (textRect.height() / 2f), textPaint)
    }

    fun incrementMonth() {
        currentDate.add(Calendar.MONTH, 1)
        updateCalendar()

        requestLayout()
        invalidate()
    }

    fun decrementMonth() {
        currentDate.add(Calendar.MONTH, -1)
        updateCalendar()

        requestLayout()
        invalidate()
    }
}
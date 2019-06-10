package chester.ac.uk.nextgenappandroid.calendar

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View

class NotificationBubbleView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

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

    var event = EventType.APPOINTMENT

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)

        canvas?.apply {

            when (event) {
                EventType.APPOINTMENT -> drawTextInCircle(canvas, width / 2f, height / 2f, "Ap", circleAppointmentPaint, circleTextPaint)
                EventType.NUTRITION -> drawTextInCircle(canvas, width / 2f, height / 2f, "Nu", circleNutritionPaint, circleTextPaint)
                EventType.TRANSPORT -> drawTextInCircle(canvas, width / 2f, height / 2f, "Tr", circleTransportPaint, circleTextPaint)
                EventType.WORK -> drawTextInCircle(canvas, width / 2f, height / 2f, "Wo", circleWorkPaint, circleTextPaint)

            }
        }
    }

    private fun drawTextInCircle(canvas: Canvas, xPos: Float, yPos: Float, text: String, circlePaint: Paint, textPaint: Paint) {
        val textRect = Rect()
        textPaint.getTextBounds(text, 0, text.length, textRect)

        canvas.drawCircle(xPos, yPos, 22f, circlePaint)
        if (text == "Ap")
            canvas.drawText(text, xPos - (textRect.width() / 2f), yPos + (textRect.height() / 4f) + 4f, textPaint) //MAGIC NUMBERS
        else
            canvas.drawText(text, xPos - (textRect.width() / 2f), yPos + (textRect.height() / 2f), textPaint)
    }

}
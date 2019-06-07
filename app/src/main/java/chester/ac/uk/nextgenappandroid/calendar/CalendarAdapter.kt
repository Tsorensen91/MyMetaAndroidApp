package chester.ac.uk.nextgenappandroid.calendar

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import chester.ac.uk.nextgenappandroid.R
import kotlinx.android.synthetic.main.calendar_expand_card_layout.view.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.SimpleFormatter

class CalendarAdapter(val list: MutableList<CalendarEvent>): RecyclerView.Adapter<CalendarAdapter.ViewHolder>() {


    val formatter = SimpleDateFormat("HH:mm", Locale.ENGLISH)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.calendar_expand_card_layout, viewGroup, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val cardView = viewHolder.itemView

        cardView.tvStartTime.text = "$position:00"
        cardView.tvTitle.text = list[position].title
        cardView.tvStartTime.text = formatter.format(list[position].startTime)
        cardView.tvEndTime.text = formatter.format(list[position].endTime)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}



package chester.ac.uk.nextgenappandroid.calendar

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import chester.ac.uk.nextgenappandroid.R
import kotlinx.android.synthetic.main.calendar_expand_card_layout.view.*

class CalendarAdapter: RecyclerView.Adapter<CalendarAdapter.ViewHolder>() {


    val timeList = listOf<String>("1:00", "2:00")

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_layout, viewGroup, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return timeList.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.tvHourTimeSlot.text = position.toString()
    }


    inner class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview)
}



package chester.ac.uk.nextgenappandroid.calendar

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import chester.ac.uk.nextgenappandroid.R
import kotlinx.android.synthetic.main.calendar_expand_card_layout.view.*

class CalendarAdapter: RecyclerView.Adapter<CalendarAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.calendar_expand_card_layout, viewGroup, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 24
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val cardView = viewHolder.itemView
        cardView.tvHourTimeSlot.text = "$position:00"
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}



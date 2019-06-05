package chester.ac.uk.nextgenappandroid.calendar

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import chester.ac.uk.nextgenappandroid.R
import kotlinx.android.synthetic.main.calendar_expand_card_layout.view.*

class CalendarAapter: RecyclerView.Adapter<CalendarAapter.ViewHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_layout, viewGroup, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {

    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.tvHourTimeSlot.text = position.toString()
    }


    inner class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview)
}



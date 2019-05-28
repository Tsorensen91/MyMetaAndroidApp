package chester.ac.uk.nextgenappandroid.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import java.util.*


class CalendarAdapter(private val context: Context?, private val dates: Array<Date>) : BaseAdapter() {

    override fun getCount(): Int {
        return dates.size
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getView(position: Int, convertView: View, parent: ViewGroup): View {

        (convertView as TextView).text = position.toString()

        return convertView
    }

}
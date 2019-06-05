package chester.ac.uk.nextgenappandroid.calendar


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import chester.ac.uk.nextgenappandroid.R


class CalendarExpand : Fragment() {

    private lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        layoutManager = LinearLayoutManager(context)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar_expand, container, false)
    }


}

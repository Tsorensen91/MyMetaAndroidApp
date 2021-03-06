package chester.ac.uk.nextgenappandroid.transition


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import chester.ac.uk.nextgenappandroid.FragmentType
import chester.ac.uk.nextgenappandroid.MainActivity
import chester.ac.uk.nextgenappandroid.R
import kotlinx.android.synthetic.main.fragment_transition_tracker_add.*
import kotlinx.android.synthetic.main.transition_tracker_add_card_layout.*
import kotlinx.android.synthetic.main.transition_tracker_add_card_layout.view.*
import java.text.SimpleDateFormat
import java.util.*


class TransitionTrackerAddFragment : Fragment() {

    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var adapter: TransitionAddRecyclerAdapter
    private lateinit var spinnerAdapter: ArrayAdapter<CharSequence>
    var transitionTime = ""
    private val months = arrayOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")
    private val formatter = SimpleDateFormat("dd/MM/yyy", Locale.ENGLISH)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_transition_tracker_add, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val transitionList = arrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1)

        layoutManager = LinearLayoutManager(context)
        rvTransitionProcess.layoutManager = layoutManager
        adapter = TransitionAddRecyclerAdapter(transitionList)
        rvTransitionProcess.adapter = adapter

        setupArrayAdapters()
        setupDateAdapters()

        transitionTrackerSubmit.setOnClickListener {
            //class TransitionItem (var time: String, date : String, var notes: String, var transitionList : List<TransitionPillarStep>)

            val month = when (monthSpinner.selectedItem) {
                "Jan" -> 1
                "Feb" -> 2
                "Mar" -> 3
                "Apr" -> 4
                "May" -> 5
                "Jun" -> 6
                "Jul" -> 7
                "Aug" -> 8
                "Sep" -> 9
                "Oct" -> 10
                "Nov" -> 11
                "Dec" -> 12
                else -> 1 //Should not reach this
            }

            val stepDate = "${daySpinner.selectedItem}/$month/${yearSpinner.selectedItem}"

            val newStep = TransitionItem(transitionTime, formatter.parse(stepDate), etAddNotes.text.toString(), transitionList)

            TransitionTrackerData.list.add(newStep)
            TransitionTrackerData.list.sortByDescending { it.date }
            (activity as MainActivity).showFragment(FragmentType.TRANSITION_TRACKER, false)
        }

    }

    private fun setupArrayAdapters() {
        spinnerAdapter = ArrayAdapter.createFromResource(activity!!, R.array.time_meal_spinner, android.R.layout.simple_spinner_item)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        hourSpinner.adapter = spinnerAdapter
        hourSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = hourSpinner.getItemAtPosition(position).toString()
                transitionTime = selectedItem
            }
        }
    }

    private fun setupDateAdapters() {

        val calendar = Calendar.getInstance()!!
        val currentYear = calendar.get(Calendar.YEAR)

        val daySpinnerAdapter = ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, android.R.id.text1)
        daySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        daySpinner.adapter = daySpinnerAdapter

        val yearSpinnerAdapter = ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, android.R.id.text1)
        yearSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        yearSpinner.adapter = yearSpinnerAdapter

        val yearStringArray = arrayListOf<String>()
        for (i in (currentYear - 10)..(currentYear + 10))
            yearStringArray.add(i.toString())

        yearSpinnerAdapter.addAll(yearStringArray.reversed())
        yearSpinnerAdapter.notifyDataSetChanged()
        yearSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = yearSpinner.getItemAtPosition(position)

                calendar.set(Calendar.YEAR, selectedItem.toString().toInt())
                val daysForMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

                daySpinnerAdapter.clear()
                for (i in 0 until daysForMonth) {
                    daySpinnerAdapter.add((i + 1).toString())
                }
                daySpinnerAdapter.notifyDataSetChanged()

            }
        }
        yearSpinner.setSelection(10)

        val monthSpinnerAdapter = ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, android.R.id.text1)
        monthSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        monthSpinner.adapter = monthSpinnerAdapter

        for (i in 0 until months.size)
            monthSpinnerAdapter.add(months[i])

        monthSpinnerAdapter.notifyDataSetChanged()
        monthSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {

                calendar.set(Calendar.MONTH, position)
                val daysForMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

                daySpinnerAdapter.clear()
                for (i in 0 until daysForMonth) {
                    daySpinnerAdapter.add((i + 1).toString())
                }
                daySpinnerAdapter.notifyDataSetChanged()

            }
        }
    }

}

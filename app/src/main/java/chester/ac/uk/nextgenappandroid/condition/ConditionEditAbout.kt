package chester.ac.uk.nextgenappandroid.condition


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import chester.ac.uk.nextgenappandroid.MainActivity
import chester.ac.uk.nextgenappandroid.R
import kotlinx.android.synthetic.main.fragment_condition_edit_about.*
import java.util.*


class ConditionEditAbout : Fragment() {

    private lateinit var adapter: ArrayAdapter<CharSequence>
    val aboutMeArray : Array<String> = arrayOf("name", "gender", "condition", "hospital", "ethnicity", "DOBday", "DOBmonth", "DOByear")
    private val months = arrayOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_condition_edit_about, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupDateAdapters()
        setupArrayAdapters()

        submitButton.setOnClickListener{
            var aboutString = ""
            aboutMeArray[0] = etName.text.toString()
            aboutMeArray[2] = etCondition.text.toString()
            aboutMeArray[3] = etHospital.text.toString()
            for (x in 0 until aboutMeArray.size){
                aboutString += aboutMeArray[x]
                aboutString += " | "
            }
            (activity as MainActivity).fragmentSwap(getString(R.string.condition), aboutString)
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
        for(i in (currentYear - 100)..currentYear)
            yearStringArray.add(i.toString())

        yearSpinnerAdapter.addAll(yearStringArray.reversed())
        yearSpinnerAdapter.notifyDataSetChanged()
        yearSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = yearSpinner.getItemAtPosition(position)

                calendar.set(Calendar.YEAR, selectedItem.toString().toInt())
                val daysForMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

                daySpinnerAdapter.clear()
                for(i in 0 until  daysForMonth){
                    daySpinnerAdapter.add((i + 1).toString())
                }
                daySpinnerAdapter.notifyDataSetChanged()

            }
        }

        val monthSpinnerAdapter = ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, android.R.id.text1)
        monthSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        monthSpinner.adapter = monthSpinnerAdapter

        for(i in 0 until months.size)
            monthSpinnerAdapter.add(months[i])

        monthSpinnerAdapter.notifyDataSetChanged()
        monthSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {

                calendar.set(Calendar.MONTH, position)
                val daysForMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

                daySpinnerAdapter.clear()
                for(i in 0 until  daysForMonth){
                    daySpinnerAdapter.add((i + 1).toString())
                }
                daySpinnerAdapter.notifyDataSetChanged()

            }
        }

    }

    private fun setupArrayAdapters() {
        adapter = ArrayAdapter.createFromResource(activity!!, R.array.gender_array, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        genderSpinner.adapter = adapter
        genderSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = genderSpinner.getItemAtPosition(position).toString()
                aboutMeArray[1] = selectedItem
            }
        }

        adapter = ArrayAdapter.createFromResource(activity!!, R.array.ethnicity_spinner, android.R.layout.simple_spinner_item)
        ethnicitySpinner.adapter = adapter
        ethnicitySpinner.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = ethnicitySpinner.getItemAtPosition(position).toString()
                aboutMeArray[4] = selectedItem
            }
        }
    }

}

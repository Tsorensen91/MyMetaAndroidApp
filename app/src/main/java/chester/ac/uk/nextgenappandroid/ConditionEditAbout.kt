package chester.ac.uk.nextgenappandroid


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import kotlinx.android.synthetic.main.fragment_condition.*
import kotlinx.android.synthetic.main.fragment_condition_edit_about.*


class ConditionEditAbout : Fragment() {

    private lateinit var adapter: ArrayAdapter<CharSequence>
    val aboutMeArray : Array<String> = arrayOf("name", "gender", "condition", "hospital", "ethnicity", "DOBday", "DOBmonth", "DOByear")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_condition_edit_about, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val genderSpinner : Spinner = activity!!.findViewById(R.id.genderSpinner)
        val ethnicitySpinner : Spinner = activity!!.findViewById(R.id.ethnicitySpinner)
        val daySpinner : Spinner = activity!!.findViewById(R.id.daySpinner)
        val monthSpinner : Spinner = activity!!.findViewById(R.id.monthSpinner)
        val yearSpinner : Spinner = activity!!.findViewById(R.id.yearSpinner)

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



}

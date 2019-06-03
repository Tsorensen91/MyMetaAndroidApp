package chester.ac.uk.nextgenappandroid.condition


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import chester.ac.uk.nextgenappandroid.MainActivity
import chester.ac.uk.nextgenappandroid.R
import kotlinx.android.synthetic.main.fragment_condition_edit_about.*


class ConditionEditAbout : Fragment() {

    private lateinit var adapter: ArrayAdapter<CharSequence>
    var gender = ""
    var ethnicity = ""

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
                gender = selectedItem
            }
        }

        adapter = ArrayAdapter.createFromResource(activity!!, R.array.ethnicity_spinner, android.R.layout.simple_spinner_item)
        ethnicitySpinner.adapter = adapter
        ethnicitySpinner.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = ethnicitySpinner.getItemAtPosition(position).toString()
                ethnicity = selectedItem
            }
        }

        submitButton.setOnClickListener{
            val builder = StringBuilder()

            builder.append("Name: ").append(etName.text.toString()).appendln()
            builder.append("Gender: ").append(gender).appendln()
            builder.append("Condition: ").append(etCondition.text.toString()).appendln()
            builder.append("Hospital: ").append(etHospital.text.toString()).appendln()
            builder.append("Ethnicity: ").append(ethnicity).appendln()


            (activity as MainActivity).fragmentSwap(getString(R.string.condition), builder.toString())
        }
    }



}

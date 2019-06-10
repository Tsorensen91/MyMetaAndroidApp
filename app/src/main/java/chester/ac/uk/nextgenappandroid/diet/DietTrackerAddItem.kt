package chester.ac.uk.nextgenappandroid.diet


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import chester.ac.uk.nextgenappandroid.FragmentType
import chester.ac.uk.nextgenappandroid.MainActivity
import chester.ac.uk.nextgenappandroid.R
import kotlinx.android.synthetic.main.fragment_diet_tracker_add_item.*


//submit button doesnt hide dietadditem

class DietTrackerAddItem : Fragment() {

    private lateinit var adapter: ArrayAdapter<CharSequence>
    var time = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
            // Inflate the layout for this fragment
        return inflater.inflate(chester.ac.uk.nextgenappandroid.R.layout.fragment_diet_tracker_add_item, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupArrayAdapters()

        submitButton.setOnClickListener {
//            (activity as MainActivity).fragmentSwap(getString(R.string.diettracker), "food, drink")

//            val mealName = etMealName.text
//            (activity as MainActivity).fragmentSwap(getString(R.string.diettracker), ""+mealName+", 10:02pm, 200, 500")

            val bundle = Bundle()
            val builder = StringBuilder()

            builder.append("Protein:  ").append(etProtein.text).appendln()
            builder.append("Carbohydrates:  ").append(etCarbohydrate.text).appendln()
            builder.append("Fat:  ").append(etFat.text).appendln()


            bundle.putString("dietTrackerMeal", "" + etMealName.text + "," + time + "," + builder.toString())
            (activity as MainActivity).showFragment(FragmentType.DIET_TRACKER, bundle, FragmentType.DIET_TRACKER_ADD_ITEM, false)
        }
    }

    private fun setupArrayAdapters() {
        adapter = ArrayAdapter.createFromResource(activity!!, R.array.time_meal_spinner, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        timeSpinner.adapter = adapter
        timeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = timeSpinner.getItemAtPosition(position).toString()
                time = selectedItem
            }
        }
    }

}


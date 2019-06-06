package chester.ac.uk.nextgenappandroid.diet


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import chester.ac.uk.nextgenappandroid.FragmentType
import chester.ac.uk.nextgenappandroid.MainActivity
import chester.ac.uk.nextgenappandroid.OnShowFragment
import chester.ac.uk.nextgenappandroid.R
import kotlinx.android.synthetic.main.fragment_diet_tracker.*
import java.util.*


class DietTrackerFragment : Fragment(), OnShowFragment {


    private lateinit var dietLayoutManager: RecyclerView.LayoutManager
    private var dietAdapter = DietTrackerAdapter(context!!)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_diet_tracker, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dietLayoutManager = LinearLayoutManager(context)

        rvDietTracker.layoutManager = dietLayoutManager

        rvDietTracker.adapter = dietAdapter

        addDietButton.setOnClickListener {
            (activity as MainActivity).showFragment(FragmentType.DIET_TRACKER_ADD_ITEM, true)
        }

    }


    override fun onShow(bundle: Bundle) {
        val bString = bundle.getString("from")
        if(bString == FragmentType.DIET_TRACKER_ADD_ITEM.desc) {

            val info = bundle.getString("dietTrackerMeal")
            val mealInfo = info.split(",").map { it.trim() }
            var nutrition = ""
            var dayExists = false

            for (x in 2 until mealInfo.size) {
                nutrition += mealInfo[x]
            }
            val meal = Meals(mealInfo[0], mealInfo[1], nutrition)

            for (x in 0 until DietTrackerData.dayList.size) {
                //if (DietTrackerData.dayList[x].mealDate.date == Calendar.getInstance().time.date && DietTrackerData.dayList[x].mealDate.month == Calendar.getInstance().time.month && DietTrackerData.dayList[x].mealDate.year == Calendar.getInstance().time.year ){
                    DietTrackerData.dayList[x].mealList.add(meal)
                    dayExists = true
                //}
            }
            if (!dayExists) {
                val day = DietTrackerDay(Calendar.getInstance().time)
                day.mealList.add(meal)
                DietTrackerData.dayList.add(day)
            }
        }
    }

}

package chester.ac.uk.nextgenappandroid.diet

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import chester.ac.uk.nextgenappandroid.MainActivity
import chester.ac.uk.nextgenappandroid.R
import kotlinx.android.synthetic.main.diet_tracker_card_layout.view.*
import java.text.SimpleDateFormat
import java.util.*

class DietTrackerAdapter: RecyclerView.Adapter<DietTrackerAdapter.ViewHolder>() {

    var list = mutableListOf<DietTrackerDay>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.diet_tracker_card_layout, viewGroup,false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val cardView = viewHolder.itemView
        var day = list[position].mealDate.date
        var month =  list[position].mealDate.month+1 //month is 0-11
        var year =  list[position].mealDate.year+1900 //year takes away 1900.
        cardView.tvDietDate.text = "$day/$month/$year"

        val mealListForDay = list[position].mealList
        for (meal in mealListForDay){
            cardView.tvMealName.text = meal.name
            cardView.tvMealTime.text = meal.time
            cardView.tvNutrition.text = meal.nutrition
        }

        var stringBuilder = StringBuilder()


    }

    inner class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview)

}
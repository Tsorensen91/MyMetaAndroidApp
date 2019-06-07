package chester.ac.uk.nextgenappandroid.diet

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import chester.ac.uk.nextgenappandroid.R
import kotlinx.android.synthetic.main.diet_tracker_card_rv.view.*

class MealTrackerAdapter(val pos: Int, val mealCount: Int) : RecyclerView.Adapter<MealTrackerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): MealTrackerAdapter.ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.diet_tracker_card_rv, viewGroup, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mealCount
    }

    override fun onBindViewHolder(viewHolder: MealTrackerAdapter.ViewHolder, position: Int) {
        val cardView = viewHolder.itemView

        val meal = DietTrackerData.dayList[pos].mealList[position]
        cardView.tvMealName.text = meal.name
        cardView.tvMealTime.text = meal.time
        cardView.tvNutrition.text = meal.nutrition

    }

    inner class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview)
}
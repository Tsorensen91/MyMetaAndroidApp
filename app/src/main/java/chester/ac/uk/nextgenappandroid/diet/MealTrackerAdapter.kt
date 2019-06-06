package chester.ac.uk.nextgenappandroid.diet

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import chester.ac.uk.nextgenappandroid.R

//class MealTrackerAdapter : RecyclerView.Adapter<MealTrackerAdapter.ViewHolder>() {
//
//    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): MealTrackerAdapter.ViewHolder {
//        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.diet_tracker_card_rv, viewGroup,false)
//
//
//
//        return MealTrackerAdapter.ViewHolder(view)
//    }
//
//    override fun getItemCount(): Int {
//        return DietTrackerData.dayList.size
//    }
//
//    override fun onBindViewHolder(viewHolder: MealTrackerAdapter.ViewHolder, position: Int) {
//        val cardView = viewHolder.itemView
//
//        val mealListForDay = DietTrackerData.dayList[position].mealList
//        for (meal in mealListForDay){
////            cardView.tvMealName.text = meal.name
////            cardView.tvMealTime.text = meal.time
////            cardView.tvNutrition.text = meal.nutrition
//        }
//
//        var stringBuilder = StringBuilder()
//    }
//
//    inner class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview)
//}
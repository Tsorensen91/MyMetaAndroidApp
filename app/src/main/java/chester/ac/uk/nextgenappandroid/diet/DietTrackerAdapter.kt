package chester.ac.uk.nextgenappandroid.diet

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import chester.ac.uk.nextgenappandroid.R
import kotlinx.android.synthetic.main.diet_tracket_card_layout.view.*
import java.text.SimpleDateFormat
import java.util.*

class DietTrackerAdapter : RecyclerView.Adapter<DietTrackerAdapter.ViewHolder>() {

    var date_n = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(Date())

    var list = mutableListOf<NutritionList>()



    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.diet_tracket_card_layout, viewGroup,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size + 1
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
       val cardView = viewHolder.itemView
        if (position == 0) {
            cardView.tvDatePicker.text = "Add new item"
            cardView.tvNutrition.text = ""

        } else {
            val item = list[position]
            cardView.tvDatePicker.text = date_n.toString()
            cardView.tvNutrition.text = item.toString()
        }
    }


    fun addItem(item: NutritionItem) {
        //list.add(NutritionList(List(list.size)))
        notifyItemInserted(list.size)
    }


    inner class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview)

}
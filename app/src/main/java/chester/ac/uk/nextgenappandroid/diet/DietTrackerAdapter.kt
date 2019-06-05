package chester.ac.uk.nextgenappandroid.diet

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import chester.ac.uk.nextgenappandroid.R
import kotlinx.android.synthetic.main.diet_tracker_card_layout.view.*
import java.text.SimpleDateFormat
import java.util.*

class DietTrackerAdapter: RecyclerView.Adapter<DietTrackerAdapter.ViewHolder>() {

    var date_n = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(Date())

    var list = mutableListOf<NutritionList>()



    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.diet_tracker_card_layout, viewGroup,false)


        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        //return fragments.size
        return 20
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
       val cardView = viewHolder.itemView
        //val item = fragments[position]
        cardView.tvDatePicker.text = "20/20/2020"
                //date_n.toString()
        //cardView.tvNutrition.text = "protein 200000g   something else 2000000g"
                //item.toString()
    }

    fun addItem(item: NutritionItem) {
        //fragments.add(NutritionList(List(fragments.size)))
        notifyItemInserted(list.size)
    }


    inner class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview)

}
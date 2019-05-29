package chester.ac.uk.nextgenappandroid

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import chester.ac.uk.nextgenappandroid.components.MailItem
import kotlinx.android.synthetic.main.card_layout.view.*
import kotlinx.android.synthetic.main.mailcard_layout.view.*

class MailRecyclerAdapter : RecyclerView.Adapter<MailRecyclerAdapter.ViewHolder>() {


    var list = mutableListOf<MailItem>()

    init{
        list.add(MailItem("",1))
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MailRecyclerAdapter.ViewHolder {

        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.mailcard_layout, viewGroup, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 20
    }

    override fun onBindViewHolder(viewHolder: MailRecyclerAdapter.ViewHolder, position: Int) {
        if (position > 0 ){
            viewHolder.itemView.tvFrom.text = ""
            viewHolder.itemView.tvAppointment.text = ""
        }
        if (position == itemCount -1){
            viewHolder.itemView.transitionBarBottom.visibility = View.INVISIBLE
        }


    }


    inner class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){

        }


}
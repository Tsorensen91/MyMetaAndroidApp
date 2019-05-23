package chester.ac.uk.nextgenappandroid

import android.opengl.Visibility
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.card_layout.view.*

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        if (position == 0) return 1 else return 2;
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        var view: View
        if (viewType == 1) {
            view = LayoutInflater.from(viewGroup.context).inflate(R.layout.firstcard_layout, viewGroup, false)
        } else {
            view = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_layout, viewGroup, false)
        }
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 20
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        if (position > 0) {
            viewHolder.itemView.transitionDate.text = "20/20/2020"
            viewHolder.itemView.transitionItemNotes.text = "placeholder many words on an appointment placeholder many words on an appointment placeholder many words on an appointment placeholder many words on an appointment placeholder many words on an appointment"
        }
        if (position == itemCount-1) {
            viewHolder.itemView.transitionBarBottom.visibility = View.INVISIBLE
        }
    }

    inner class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    }


}
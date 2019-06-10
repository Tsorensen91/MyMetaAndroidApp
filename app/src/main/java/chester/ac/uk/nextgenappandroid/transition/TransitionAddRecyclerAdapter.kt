package chester.ac.uk.nextgenappandroid.transition

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import chester.ac.uk.nextgenappandroid.R
import kotlinx.android.synthetic.main.transition_tracker_add_card_layout.view.*

class TransitionAddRecyclerAdapter(var transitionList : Array<Int>): RecyclerView.Adapter<TransitionAddRecyclerAdapter.ViewHolder>() {


    val steps = listOf("I understand the transition process and have been given a clear plan for my own transition going forward ",
            "I have all the information and resources I need to support my transition, and in a format that suits me",
            "I understand and can explain my condition including what it is and how it works, what my symptoms are and what causes them, the medication and treatments I follow and why (including diet management)",
            "I feel that care is being taken to understand my individual circumstances and needs and to make sure these are met in my future care",
            "I feel that I am being treated as an individual and that I’m being consulted and listened to on the decisions affecting me and my care",
            "I have time and opportunity to reflect on information I’m given and to raise any questions or concerns, and feel comfortable to do so ",
            "I have opportunity to talk to members of my care team on my own and feel confident to ask",
            "I have been given enough opportunity to meet and get to know my new adult care team",
            "I feel confident about my transition to adult services and feel I’m being well prepared and supported")


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.transition_tracker_add_card_layout, viewGroup,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return steps.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val cardView = viewHolder.itemView
        cardView.tvPillarQuestion.text = steps[position]

        cardView.bubbleSeekBar.setOnTouchListener { v, event ->
            if (event != null) {
                if (event.action == MotionEvent.ACTION_UP){
                    transitionList[position] = cardView.bubbleSeekBar.progress
                }
            }


            false
        }

    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    }


}
package chester.ac.uk.nextgenappandroid.transition

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import chester.ac.uk.nextgenappandroid.FragmentType
import chester.ac.uk.nextgenappandroid.MainActivity
import chester.ac.uk.nextgenappandroid.R
import chester.ac.uk.nextgenappandroid.R.id.transitionBarBottom
import kotlinx.android.synthetic.main.card_layout.view.*
import kotlinx.android.synthetic.main.firstcard_layout.view.*

class TransitionRecyclerAdapter(val activity: MainActivity) : RecyclerView.Adapter<TransitionRecyclerAdapter.ViewHolder>() {

    val dayList = TransitionTrackerData.list

    override fun getItemViewType(position: Int): Int {
        if (position == 0) return 1 else return 2;
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        var view: View
        if (viewType == 1) {
            view = LayoutInflater.from(viewGroup.context).inflate(R.layout.firstcard_layout, viewGroup, false)
            view.addStepIcon.setOnClickListener {

                activity.showFragment(FragmentType.TRANSITION_TRACKER_ADD, true)
            }
        } else {
            view = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_layout, viewGroup, false)
        }
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dayList.size+1 //add 1 for firstcard layout
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val cardView = viewHolder.itemView

        if (position > 0) {
            val currentDay = dayList[position-1] //take firstcard layout number away.

            cardView.transitionDate.text = currentDay.date
            cardView.tvTransitionTimeDisplay.text = currentDay.time
            cardView.tvTransitionAppointmentNotesDisplay.text = currentDay.notes
            cardView.transitionProcess.text = currentDay.transitionList[0].toString()
            cardView.infoAndResources.text = currentDay.transitionList[1].toString()
            cardView.myCondition.text = currentDay.transitionList[2].toString()
            cardView.individualCare.text = currentDay.transitionList[3].toString()
            cardView.consulting.text = currentDay.transitionList[4].toString()
            cardView.reflection.text = currentDay.transitionList[5].toString()
            cardView.communicating.text = currentDay.transitionList[6].toString()
            cardView.meeting.text = currentDay.transitionList[7].toString()
            cardView.confidence.text = currentDay.transitionList[8].toString()

        }
        if (position == itemCount-1) {
            if (cardView.transitionBarBottom != null) {
                cardView.transitionBarBottom.visibility = View.INVISIBLE
            } else {
                cardView.transitionBarAddBottom.visibility = View.INVISIBLE
            }

        }


    }

    inner class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    }


}
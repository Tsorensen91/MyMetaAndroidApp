package chester.ac.uk.nextgenappandroid.diet


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import chester.ac.uk.nextgenappandroid.MainActivity




class DietTrackerAddItem : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {



            // Inflate the layout for this fragment
        return inflater.inflate(chester.ac.uk.nextgenappandroid.R.layout.fragment_diet_tracker_add_item, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        (activity as MainActivity).fragmentSwap(getString(chester.ac.uk.nextgenappandroid.R.string.diettracker), "")
    }
    private fun onButtonClicked(button: Button) {
        if (button.id == chester.ac.uk.nextgenappandroid.R.id.submitButton) {

            val toast = Toast.makeText(context, "Submitted", Toast.LENGTH_SHORT)
            toast.setMargin(50f, 50f)
            toast.show()
        }

//    //Set button onclicklistener
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//
//        submitButton.setOnClickListener {
//            val args = Bundle()
//          //  args.putSerializable(/*something here*/)
//            val fragment = DietTrackerFragment()
//            fragment.arguments = args
//        }
//    }

    }
}

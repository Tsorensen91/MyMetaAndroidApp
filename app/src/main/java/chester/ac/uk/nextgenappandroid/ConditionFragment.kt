package chester.ac.uk.nextgenappandroid


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class ConditionFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        //var button_editaboutme: Button = view!!.findViewById(R.id.editAboutMe)
        //button_editaboutme.setOnClickListener(View.OnClickListener {
        //    activity!!.supportFragmentManager.beginTransaction().hide(ConditionFragment()).show(ConditionEditAbout()).commit()
        //})


        return inflater.inflate(R.layout.fragment_condition, container, false)
    }




}

package chester.ac.uk.nextgenappandroid.condition



import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import chester.ac.uk.nextgenappandroid.FragmentType
import chester.ac.uk.nextgenappandroid.MainActivity
import chester.ac.uk.nextgenappandroid.R
import kotlinx.android.synthetic.main.fragment_condition.*


class ConditionFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        return inflater.inflate(R.layout.fragment_condition, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        editAboutMe.setOnClickListener(){
            onButtonClicked(editAboutMe)
        }
        editMySymptoms.setOnClickListener(){
            onButtonClicked(editMySymptoms)
        }
        editMyMedication.setOnClickListener(){
            onButtonClicked(editMyMedication)
        }
        editProfilePicture.setOnClickListener(){
            onButtonClicked(editProfilePicture)
        }
    }


    private fun onButtonClicked(button: ImageButton) {
        if (button.id == R.id.editAboutMe) {
            (activity as MainActivity).showFragment(FragmentType.CONDITION_HUB_EDIT_ABOUT)
        } else if (button.id == R.id.editMySymptoms) {
            (activity as MainActivity).showFragment(FragmentType.CONDITION_HUB_MY_CONDITION)
        } else if (button.id == R.id.editMyMedication) {
            (activity as MainActivity).showFragment(FragmentType.CONDITION_HUB_MY_MEDICATION)
        } else if (button.id == R.id.editProfilePicture) {
            (activity as MainActivity).showFragment(FragmentType.CONDITION_HUB_MY_PICTURE)
        }
    }




}

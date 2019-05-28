package chester.ac.uk.nextgenappandroid



import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import kotlinx.android.synthetic.main.fragment_condition.*
import kotlinx.android.synthetic.main.fragment_condition_edit_about.*


class ConditionFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_condition, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (arguments != null) {
            var info = arguments!!["key"].toString()
            tvAbout.text = info
        }


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
            (activity as MainActivity).fragmentSwap(getString(R.string.editaboutme), "")
        } else if (button.id == R.id.editMySymptoms) {
            (activity as MainActivity).fragmentSwap(getString(R.string.editmycondition), "")
        } else if (button.id ==R.id.editMyMedication) {
            (activity as MainActivity).fragmentSwap(getString(R.string.editmymedication), "")
        } else if (button.id == R.id.editProfilePicture) {
            (activity as MainActivity).fragmentSwap(getString(R.string.editprofilepicture), "")
        }
    }




}

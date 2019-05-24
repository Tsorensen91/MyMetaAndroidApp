package chester.ac.uk.nextgenappandroid


import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import kotlinx.android.synthetic.main.fragment_condition.*
import kotlinx.android.synthetic.main.fragment_my_picture_edit.*


class ConditionFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_condition, container, false)
    }

    override fun onResume() {
        super.onResume()
        editAboutMe.setOnClickListener(){
            onButtonClicked(editAboutMe)
        }
    }


    private fun onButtonClicked(button: ImageButton) {
        if (button.id == R.id.editAboutMe) {
            (activity as MainActivity).fragmentSwap(getString(R.string.editaboutme))
        } else if (button.id == R.id.editMySymptoms) {
            (activity as MainActivity).fragmentSwap(getString(R.string.editmycondition))
        } else if (button.id ==R.id.editMyMedication) {
            (activity as MainActivity).fragmentSwap(getString(R.string.editmymedication))
        } else if (button.id == R.id.editProfilePicture) {
            (activity as MainActivity).fragmentSwap(getString(R.string.editprofilepicture))
        }
    }


}

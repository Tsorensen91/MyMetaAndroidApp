package chester.ac.uk.nextgenappandroid.condition



import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import chester.ac.uk.nextgenappandroid.FragmentType
import chester.ac.uk.nextgenappandroid.MainActivity
import chester.ac.uk.nextgenappandroid.OnShowFragment
import chester.ac.uk.nextgenappandroid.R
import kotlinx.android.synthetic.main.fragment_condition.*
import kotlinx.android.synthetic.main.fragment_my_condition.*
import java.io.FileDescriptor


class ConditionFragment : Fragment(), OnShowFragment {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        return inflater.inflate(R.layout.fragment_condition, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvAbout.text = ConditionHubData.about
        tvSymptoms.text = ConditionHubData.condition
        tvMedication.text = ConditionHubData.medication

        if (ConditionHubData.pictureUri != ""){
            val info = ConditionHubData.pictureUri
            val parcelFileDescriptor: ParcelFileDescriptor = activity!!.contentResolver.openFileDescriptor(Uri.parse(info), "r")
            val fileDescriptor: FileDescriptor = parcelFileDescriptor.fileDescriptor
            val image: Bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor)
            parcelFileDescriptor.close()

            profilePicture.setImageBitmap(image)
        }
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
        when {
            button.id == R.id.editAboutMe -> (activity as MainActivity).showFragment(FragmentType.CONDITION_HUB_EDIT_ABOUT, true)
            button.id == R.id.editMySymptoms -> (activity as MainActivity).showFragment(FragmentType.CONDITION_HUB_MY_CONDITION, true)
            button.id == R.id.editMyMedication -> (activity as MainActivity).showFragment(FragmentType.CONDITION_HUB_MY_MEDICATION, true)
            button.id == R.id.editProfilePicture -> (activity as MainActivity).showFragment(FragmentType.CONDITION_HUB_MY_PICTURE, true)
        }
    }

    override fun onShow(bundle: Bundle) {

        when (bundle.getString("from")) {
            FragmentType.CONDITION_HUB_EDIT_ABOUT.desc -> {
                ConditionHubData.about = bundle.getString("conditionEditAbout")
            }
            FragmentType.CONDITION_HUB_MY_CONDITION.desc -> {
                ConditionHubData.condition = bundle.getString("myConditionText")
            }
            FragmentType.CONDITION_HUB_MY_MEDICATION.desc -> {
                ConditionHubData.medication = bundle.getString("myMedicationText")
            }
            FragmentType.CONDITION_HUB_MY_PICTURE.desc -> {
                ConditionHubData.pictureUri = bundle.getString("myPictureUri")
            }
        }

    }


}

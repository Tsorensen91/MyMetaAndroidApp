package chester.ac.uk.nextgenappandroid.condition


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Camera
import android.net.Uri
import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import chester.ac.uk.nextgenappandroid.FragmentType
import chester.ac.uk.nextgenappandroid.MainActivity
import chester.ac.uk.nextgenappandroid.R
import kotlinx.android.synthetic.main.fragment_my_medication.*
import kotlinx.android.synthetic.main.fragment_my_picture_edit.*
import java.io.FileDescriptor
import java.io.IOException

class MyPictureEditFragment : Fragment() {

    private val READ_REQUEST_CODE: Int = 42
    var imageUri = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_picture_edit, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        pictureFromCameraButton.visibility = View.INVISIBLE

        pictureFromFileButton.setOnClickListener {
            // ACTION_OPEN_DOCUMENT is the intent to choose a file via the system's file
            // browser.
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                // Filter to only show results that can be "opened", such as a
                // file (as opposed to a fragments of contacts or timezones)
                addCategory(Intent.CATEGORY_OPENABLE)

                // Filter to show only images, using the image MIME data type.
                // If one wanted to search for ogg vorbis files, the type would be "audio/ogg".
                // To search for all documents available via installed storage providers,
                // it would be "*/*".
                type = "image/*"
            }

            startActivityForResult(intent, READ_REQUEST_CODE)
        }

        submitButton.setOnClickListener {
//            (activity as MainActivity).fragmentSwap(getString(R.string.condition), imageUri)
            val bundle = Bundle()
            bundle.putString("myPictureUri", imageUri)
            (activity as MainActivity).showFragment(FragmentType.CONDITION_HUB, bundle, FragmentType.CONDITION_HUB_MY_PICTURE, false)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {

        // The ACTION_OPEN_DOCUMENT intent was sent with the request code
        // READ_REQUEST_CODE. If the request code seen here doesn't match, it's the
        // response to some other intent, and the code below shouldn't run at all.

        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // The document selected by the user won't be returned in the intent.
            // Instead, a URI to that document will be contained in the return intent
            // provided to this method as a parameter.
            // Pull that URI using resultData.getData().
            resultData?.data?.also { uri ->
                val image = getBitmapFromUri(uri)
                imageView.setImageBitmap(image)

                imageUri = uri.toString()
            }
        }
    }

    @Throws(IOException::class)
    private fun getBitmapFromUri(uri: Uri): Bitmap {
        val parcelFileDescriptor: ParcelFileDescriptor = activity!!.contentResolver.openFileDescriptor(uri, "r")
        val fileDescriptor: FileDescriptor = parcelFileDescriptor.fileDescriptor
        val image: Bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor)
        parcelFileDescriptor.close()
        return image
    }



}

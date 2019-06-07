package chester.ac.uk.nextgenappandroid.mail

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.ParcelFileDescriptor
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import chester.ac.uk.nextgenappandroid.R
import kotlinx.android.synthetic.main.mailcard_layout.view.*
import java.io.FileDescriptor

class MailRecyclerAdapter (val activity : FragmentActivity): RecyclerView.Adapter<MailRecyclerAdapter.ViewHolder>() {

    var list = MailTrackerData.mailList

    init{

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.mailcard_layout, viewGroup, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val cardView = viewHolder.itemView
        val mailItem = list[position]

        cardView.tvDate.text = mailItem.date
        cardView.tvSubject.text = mailItem.subject
        cardView.tvFrom.text = mailItem.from
        cardView.tvNotes.text = mailItem.notes

        if (mailItem.photoUri != ""){
            val info = mailItem.photoUri
            val parcelFileDescriptor: ParcelFileDescriptor = activity!!.contentResolver.openFileDescriptor(Uri.parse(info), "r")
            val fileDescriptor: FileDescriptor = parcelFileDescriptor.fileDescriptor
            val image: Bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor)
            parcelFileDescriptor.close()

            cardView.ivMailPicture.setImageBitmap(image)
        }

    }


    inner class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView)
}
package chester.ac.uk.nextgenappandroid.mail


import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import chester.ac.uk.nextgenappandroid.FragmentType
import chester.ac.uk.nextgenappandroid.MainActivity
import chester.ac.uk.nextgenappandroid.OnShowFragment
import chester.ac.uk.nextgenappandroid.R
import chester.ac.uk.nextgenappandroid.condition.ConditionHubData
import kotlinx.android.synthetic.main.fragment_mail_tracker.*

class MailTrackerFragment : Fragment(), OnShowFragment{

    private lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var adapter: MailRecyclerAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_mail_tracker, container, false)
    }

    override fun onShow(bundle: Bundle) {
        when (bundle.getString("from")) {
            FragmentType.MAIL_TRACKER_ADD_ITEM.desc -> {
                val info = bundle.getString("mailTrackerAdd")
                val mailInfo = info.split(",").map { it.trim() }
                var newMail = MailItem(mailInfo[0], mailInfo[1], mailInfo[2], mailInfo[3], mailInfo[4])
                MailTrackerData.mailList.add(newMail)

                }
            }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        layoutManager = LinearLayoutManager(activity)
        rvMailTracker.layoutManager = layoutManager
        adapter = MailRecyclerAdapter(activity!!)
        rvMailTracker.adapter = adapter

        imAdd.setOnClickListener {
            (activity as MainActivity).showFragment(FragmentType.MAIL_TRACKER_ADD_ITEM, true )
        }
    }



}

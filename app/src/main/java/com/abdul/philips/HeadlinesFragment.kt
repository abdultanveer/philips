package com.abdul.philips

import android.os.Bundle
import androidx.fragment.app.Fragment //androidx is a compatibiilty library
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HeadlinesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HeadlinesFragment : Fragment(), AdapterView.OnItemClickListener {

    lateinit var lvNations: ListView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var screen:View =  inflater.inflate(R.layout.fragment_headlines, container, false)
        lvNations = screen.findViewById(R.id.lvNations)
        lvNations.setOnItemClickListener(this)
        return screen
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HeadlinesFragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HeadlinesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onItemClick(arrayAdapter: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        var itemClicked = arrayAdapter?.getItemAtPosition(position).toString()
        Toast.makeText(context,itemClicked,Toast.LENGTH_SHORT).show()
    }
}
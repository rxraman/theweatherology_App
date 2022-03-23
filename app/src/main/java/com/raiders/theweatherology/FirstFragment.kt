package com.raiders.theweatherology

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.android.synthetic.main.fragment_first.*

class FirstFragment : Fragment() {

    private lateinit var mfusedLocation: FusedLocationProviderClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Button1
        view.findViewById<Button>(R.id.button).setOnClickListener {
            Navigation.findNavController(it).navigate(
                R.id.action_FirstFragment_to_SecondFragment,
                bundleOf("message" to editCity.text.toString())
            )
        }//End On Click Button1

        //Button2
        view.findViewById<Button>(R.id.button2).setOnClickListener {
//            mfusedLocation= LocationServices.getFusedLocationProviderClient(this.requireContext())
//
//            getLastLocation()
//            Navigation.findNavController(it).navigate(
//                R.id.action_FirstFragment_to_SecondFragment,
//                bundleOf("lat" to editLat.text.toString(), "long" to editLong.text.toString())
//            )
        }//END On Click Button2
    }//END On VIEW CREATED



}//End Class

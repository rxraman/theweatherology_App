package com.raiders.theweatherology

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.fragment_second.*

class SecondFragment : Fragment() {

    companion object {
        fun newInstance() = SecondFragment()
    }

    private lateinit var viewModel: SecondViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SecondViewModel::class.java)

        val tempObserver = Observer<Double>{temp-> textTemp.text = ("%.0f"+ "\u2103").format(temp)}
        viewModel.getTemp().observe(viewLifecycleOwner,tempObserver)

        val dateObserver = Observer<String> {date-> textDate.text = date.toString()}
        viewModel.getDate().observe(viewLifecycleOwner,dateObserver)

        val windObserver = Observer<Double> {wind-> TextWind.text = ("%.2f"+ " m/s").format(wind)}
        viewModel.getWind().observe(viewLifecycleOwner,windObserver)

        val humidityObserver = Observer<Int>
        {humidity-> textHumidity.text =  humidity.toString()+" %"}
        viewModel.getHumidity().observe(viewLifecycleOwner,humidityObserver)

        val feelsLikeObserver = Observer<Double>
        {feelsLike-> textFeelsLike.text = ("%.0f"+ "\u2103").format(feelsLike)}
        viewModel.getFeelsLike().observe(viewLifecycleOwner,feelsLikeObserver)

//        val minTempObserver = Observer<Double>
//        {minTemp-> textMinTemp.text = ("%.0f"+ "\u2103").format(minTemp)}
//        viewModel.getMinTemp().observe(viewLifecycleOwner,minTempObserver)
//
//        val maxTempObserver = Observer<Double>
//        {maxTemp-> textMaxTemp.text = ("%.0f"+ "\u2103").format(maxTemp)}
//        viewModel.getMaxTemp().observe(viewLifecycleOwner,maxTempObserver)

        val mainDescriptionObserver = Observer<String>
        {mainDescription-> textMainDecription.text = mainDescription.toString()}
        viewModel.getMainDescription().observe(viewLifecycleOwner,mainDescriptionObserver)


        textCity.text = arguments?.getString("message")
        val queue = Volley.newRequestQueue(context)
        arguments?.getString("message")?.let { viewModel.details(it, queue) }

    }

}
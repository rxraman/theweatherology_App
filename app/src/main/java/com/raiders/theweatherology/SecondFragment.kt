package com.raiders.theweatherology

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.fragment_second.*

class SecondFragment : Fragment() {

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

        //5day Forecast Observers
        val tempObserver =
            Observer<Double> { temp -> textTemp.text = ("%.0f" + "\u00B0").format(temp) }
        viewModel.getTemp().observe(viewLifecycleOwner, tempObserver)

        val tempObserver1 = Observer<Double> { temp1 -> textTempOne.text = ("%.0f" + "\u00B0").format(temp1) }
        viewModel.getTemp1().observe(viewLifecycleOwner, tempObserver1)

        val tempObserver2 = Observer<Double> { temp2 -> textTempTwo.text = ("%.0f" + "\u00B0").format(temp2) }
        viewModel.getTemp2().observe(viewLifecycleOwner, tempObserver2)

        val tempObserver3 =
            Observer<Double> { temp3 -> textTempThree.text = ("%.0f" + "\u00B0").format(temp3) }
        viewModel.getTemp3().observe(viewLifecycleOwner, tempObserver3)

        val tempObserver4 =
            Observer<Double> { temp4 -> textTempFour.text = ("%.0f" + "\u00B0").format(temp4) }
        viewModel.getTemp4().observe(viewLifecycleOwner, tempObserver4)

        val tempObserver5 =
            Observer<Double> { temp5 -> textTempFive.text = ("%.0f" + "\u00B0").format(temp5) }
        viewModel.getTemp5().observe(viewLifecycleOwner, tempObserver5)


//        val dateObserver = Observer<String> { date -> textDate.text = date.toString() }
//        viewModel.getDate().observe(viewLifecycleOwner, dateObserver)

        val dateObserver1 = Observer<String> { date1 -> dayOne.text = date1.toString() }
        viewModel.getDate1().observe(viewLifecycleOwner, dateObserver1)

        val dateObserver2 = Observer<String> { date2 -> dayTwo.text = date2.toString() }
        viewModel.getDate2().observe(viewLifecycleOwner, dateObserver2)

        val dateObserver3 = Observer<String> { date3 -> dayThree.text = date3.toString() }
        viewModel.getDate3().observe(viewLifecycleOwner, dateObserver3)

        val dateObserver4 = Observer<String> { date4 -> dayFour.text = date4.toString() }
        viewModel.getDate4().observe(viewLifecycleOwner, dateObserver4)

        val dateObserver5 = Observer<String> { date5 -> dayFive.text = date5.toString() }
        viewModel.getDate5().observe(viewLifecycleOwner, dateObserver5)

//One Day Forecast Observers
        val windObserver =
            Observer<Double> { wind -> TextWind.text = ("%.2f" + " m/s").format(wind) }
        viewModel.getWind().observe(viewLifecycleOwner, windObserver)

        val humidityObserver = Observer<Int>
        { humidity -> textHumidity.text = humidity.toString() + " %" }
        viewModel.getHumidity().observe(viewLifecycleOwner, humidityObserver)

        val feelsLikeObserver = Observer<Double>
        { feelsLike -> textFeelsLike.text = ("%.0f" + "\u00B0").format(feelsLike) }
        viewModel.getFeelsLike().observe(viewLifecycleOwner, feelsLikeObserver)

        val minTempObserver = Observer<Double>
        { minTemp -> minTem.text = ("%.0f"+"\u00B0").format(minTemp) }
        viewModel.getMinTemp().observe(viewLifecycleOwner, minTempObserver)

        val maxTempObserver = Observer<Double>
        { maxTemp -> maxTem.text = ("%.0f" + "\u00B0").format(maxTemp) }
        viewModel.getMaxTemp().observe(viewLifecycleOwner, maxTempObserver)

        val mainDescriptionObserver = Observer<String>
        { mainDescription -> textMainDecription.text = mainDescription.toString() }
        viewModel.getMainDescription().observe(viewLifecycleOwner, mainDescriptionObserver)

    //Text City
        textCity.text = arguments?.getString("message")
        val queue = Volley.newRequestQueue(context)
        arguments?.getString("message")?.let { viewModel.oneDayForecast(it, queue) }
        val queue2 = Volley.newRequestQueue(context)
        arguments?.getString("message")?.let { viewModel.fiveDayForecast(it, queue2) }

    }

}
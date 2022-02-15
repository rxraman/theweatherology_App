package com.raiders.theweatherology

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class SecondViewModel : ViewModel() {
    //Variables
    private val API: String = "574767b600e526d124c0cdaa69212b76"
    private var temp: MutableLiveData<Double> = MutableLiveData()
    private var date: MutableLiveData<String> = MutableLiveData()
    private var wind: MutableLiveData<Double> = MutableLiveData()
    private var humidity:MutableLiveData<Int> = MutableLiveData()
    private var feelsLike:MutableLiveData<Double> = MutableLiveData()
    //    private var minTemp:MutableLiveData<Double> = MutableLiveData()
//    private var maxTemp:MutableLiveData<Double> = MutableLiveData()
    private var mainDescription:MutableLiveData<String> = MutableLiveData()

    //Getters
    fun getTemp():MutableLiveData<Double>{
        return temp
    }

    fun getDate():MutableLiveData<String>{
        return date
    }

    fun getWind():MutableLiveData<Double>{
        return wind
    }

    fun getHumidity():MutableLiveData<Int>{
        return humidity
    }

    fun getFeelsLike():MutableLiveData<Double>{
        return feelsLike
    }

//    fun getMinTemp():MutableLiveData<Double>{
//        return minTemp
//    }
//
//    fun getMaxTemp():MutableLiveData<Double>{
//        return maxTemp
//    }

    fun getMainDescription():MutableLiveData<String>{
        return mainDescription
    }

    //Code to pull from API
    fun details(city:String, queue: RequestQueue){
        var url = "https://api.openweathermap.org/data/2.5/weather?q=$city&units=metric&appid=$API"
        val stringReq = StringRequest(Request.Method.GET, url, Response.Listener<String>{
                response ->
            val obj = JSONObject(response)
            val main = obj.getJSONObject("main") //Temperature
            val today = obj.getLong("dt") //date and hour
            val windSpeed = obj.getJSONObject("wind")
            val weather = obj.getJSONArray("weather")
            val description = weather.getJSONObject(0)




            date.setValue(SimpleDateFormat("EEE,MMMM dd hh:mm a", Locale.ENGLISH).format(Date(today*1000)))
            temp.setValue(main.getDouble("temp"))
            wind.setValue(windSpeed.getDouble("speed"))
            humidity.setValue(main.getInt("humidity"))
            feelsLike.setValue(main.getDouble("feels_like"))
//            minTemp.setValue(main.getDouble("temp_min"))
//            maxTemp.setValue(main.getDouble("temp_max"))
            mainDescription.setValue(description.getString("main"))



        },Response.ErrorListener { temp.setValue(0.0) }
        )
        queue.add(stringReq)
    }

}
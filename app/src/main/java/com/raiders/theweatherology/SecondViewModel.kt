package com.raiders.theweatherology

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class SecondViewModel : ViewModel() {
    //Variables


    private val API: String = BuildConfig.KEY
    private var temp: MutableLiveData<Double> = MutableLiveData()
    private var date: MutableLiveData<String> = MutableLiveData()
    private var wind: MutableLiveData<Double> = MutableLiveData()
    private var humidity: MutableLiveData<Int> = MutableLiveData()
    private var feelsLike: MutableLiveData<Double> = MutableLiveData()
    private var minTemp: MutableLiveData<Double> = MutableLiveData()
    private var maxTemp: MutableLiveData<Double> = MutableLiveData()
    private var mainDescription: MutableLiveData<String> = MutableLiveData()
    private var temp1: MutableLiveData<Double> = MutableLiveData()
    private var temp2: MutableLiveData<Double> = MutableLiveData()
    private var temp3: MutableLiveData<Double> = MutableLiveData()
    private var temp4: MutableLiveData<Double> = MutableLiveData()
    private var temp5: MutableLiveData<Double> = MutableLiveData()
    private var date1: MutableLiveData<String> = MutableLiveData()
    private var date2: MutableLiveData<String> = MutableLiveData()
    private var date3: MutableLiveData<String> = MutableLiveData()
    private var date4: MutableLiveData<String> = MutableLiveData()
    private var date5: MutableLiveData<String> = MutableLiveData()


    //Getters
    fun getTemp(): MutableLiveData<Double> {
        return temp
    }

    fun getTemp1(): MutableLiveData<Double> {
        return temp1
    }

    fun getTemp2(): MutableLiveData<Double> {
        return temp2
    }

    fun getTemp3(): MutableLiveData<Double> {
        return temp3
    }

    fun getTemp4(): MutableLiveData<Double> {
        return temp4
    }

    fun getTemp5(): MutableLiveData<Double> {
        return temp5
    }


    fun getDate1(): MutableLiveData<String> {
        return date1
    }

    fun getDate2(): MutableLiveData<String> {
        return date2
    }

    fun getDate3(): MutableLiveData<String> {
        return date3
    }

    fun getDate4(): MutableLiveData<String> {
        return date4
    }

    fun getDate5(): MutableLiveData<String> {
        return date5
    }

    fun getDate(): MutableLiveData<String> {
        return date
    }

    fun getWind(): MutableLiveData<Double> {
        return wind
    }

    fun getHumidity(): MutableLiveData<Int> {
        return humidity
    }

    fun getFeelsLike(): MutableLiveData<Double> {
        return feelsLike
    }

    fun getMinTemp(): MutableLiveData<Double> {
        return minTemp
    }

    fun getMaxTemp(): MutableLiveData<Double> {
        return maxTemp
    }

    fun getMainDescription(): MutableLiveData<String> {
        return mainDescription
    }

    //Code to pull from API
    fun details(city: String, queue: RequestQueue) {
        val url = "https://api.openweathermap.org/data/2.5/weather?q=$city&units=metric&appid=$API"
        val stringReq =
            StringRequest(Request.Method.GET, url, { response ->
                val obj = JSONObject(response)
                val main = obj.getJSONObject("main") //Temperature
                val today = obj.getLong("dt") //date and hour
                val windSpeed = obj.getJSONObject("wind")
                val weather = obj.getJSONArray("weather")
                val description = weather.getJSONObject(0)




                date.value = SimpleDateFormat("EEE,MMMM dd hh:mm a", Locale.ENGLISH).format(
                    Date(
                        today * 1000
                    )
                )
                temp.value = main.getDouble("temp")
                wind.value = windSpeed.getDouble("speed")
                humidity.value = main.getInt("humidity")
                feelsLike.value = main.getDouble("feels_like")
                minTemp.value = main.getDouble("temp_min")
                maxTemp.value = main.getDouble("temp_max")
                mainDescription.setValue(description.getString("main"))


            }, { temp.setValue(0.0) }
            )
        queue.add(stringReq)
    }

    fun forecast(city: String, queue: RequestQueue) {
        val url =
            "https://api.openweathermap.org/data/2.5/forecast?q=$city&units=imperial&appid=$API"

        val stringReq2 =
            StringRequest(Request.Method.GET, url, { response ->

                val obj = JSONObject(response)
                val myList0 = obj.getJSONArray("list").getJSONObject(0)
                val main0 = myList0.getJSONObject("main")
                temp1.value = main0.getDouble("temp")
                val toDate0 = myList0.getLong("dt")
                date1.value = SimpleDateFormat(
                    "EEEE, MMMM dd",
                    Locale.ENGLISH
                ).format(Date(toDate0 * 1000))


                val myList8 = obj.getJSONArray("list").getJSONObject(8)
                val main8 = myList8.getJSONObject("main")
                temp2.value = main8.getDouble("temp")
                val toDate8 = myList8.getLong("dt")
                date2.value = SimpleDateFormat(
                    "EEEE, MMMM dd",
                    Locale.ENGLISH
                ).format(Date(toDate8 * 1000))

                val myList16 = obj.getJSONArray("list").getJSONObject(16)
                val main16 = myList16.getJSONObject("main")
                temp3.value = main16.getDouble("temp")
                val toDate16 = myList16.getLong("dt")
                date3.value = SimpleDateFormat("EEEE, MMMM dd", Locale.ENGLISH).format(
                    Date(
                        toDate16 * 1000
                    )
                )

                val myList24 = obj.getJSONArray("list").getJSONObject(24)
                val main24 = myList24.getJSONObject("main")
                temp4.value = main24.getDouble("temp")
                val toDate24 = myList24.getLong("dt")
                date4.value = SimpleDateFormat("EEEE, MMMM dd", Locale.ENGLISH).format(
                    Date(
                        toDate24 * 1000
                    )
                )

                val myList32 = obj.getJSONArray("list").getJSONObject(32)
                val main32 = myList32.getJSONObject("main")
                temp5.value = main32.getDouble("temp")
                val toDate32 = myList32.getLong("dt")
                date5.setValue(
                    SimpleDateFormat("EEEE, MMMM dd", Locale.ENGLISH).format(
                        Date(
                            toDate32 * 1000
                        )
                    )
                )

            },
                { temp.setValue(0.0) }
            )

        queue.add(stringReq2)
    }

}
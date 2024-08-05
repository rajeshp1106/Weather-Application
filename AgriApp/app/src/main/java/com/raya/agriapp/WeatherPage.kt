package com.raya.agriapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import android.os.AsyncTask
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import org.json.JSONObject
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class WeatherPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_page)
//        intent.getStringExtra("Latitude")
//        intent.getStringExtra("Longitude")
//
//        val latitude = intent.getStringExtra("Latitude")
//        val longitude = intent.getStringExtra("Longitude")
//
//        val place = "Bangalore"
//        Toast.makeText(this,latitude+" "+longitude,Toast.LENGTH_LONG).show()
//        Toast.makeText(this,place,Toast.LENGTH_LONG).show()
//        getJsonData()
//    }
//
//    @Suppress("DEPRECATION")
//    private fun getJsonData() {
//        "8584f39b9fc7b4dfe6861f054aea2c4a"
//        val queue = Volley.newRequestQueue(this)
//        val lat = "37.42"
//        val lon = "127.08"
////        val url = "http://api.weatherapi.com/v1/current.json?key=${API_Key}&q=${Place}&aqi=no"
////        val url = "http://api.weatherapi.com/v1/current.json?key=041f9eb0e27549dcaf8111533231601&q=Bangalore&aqi=no"
//        val url = "https://api.openweathermap.org/data/2.5/weather?lat=12.9716&lon=77.5946&appid=95a6c853529dc8b7ffe506bd910f92ed"
////        val url = "https://api.openweathermap.org/data/2.5/weather?lat=37.42&lon=127.08&appid=8584f39b9fc7b4dfe6861f054aea2c4a"
//
//        val jsonObjectRequest = JsonObjectRequest(
//            Request.Method.GET,url,null,
//            { response ->
//                Toast.makeText(this,response.toString(),Toast.LENGTH_LONG).show()
//            },
//            {Toast.makeText(this,"ERROR!!",Toast.LENGTH_LONG).show()})
//
//
//        queue.add(jsonObjectRequest)
//
//
//    }
//}


//        val lat = intent.getStringExtra("Latitude")
//        val lon = intent.getStringExtra("Longitude")
//
////        val place = "Bangalore"
////        Toast.makeText(this,lat+" "+lon, Toast.LENGTH_LONG).show()
////        Toast.makeText(this,place, Toast.LENGTH_LONG).show()
//        window.statusBarColor= Color.parseColor("#1383C3")
//        getJsonData(lat,lon)
//    }
//    private fun getJsonData(lat:String?,lon:String?) {
//        val apiKey = "8584f39b9fc7b4dfe6861f054aea2c4a"
//        val queue = Volley.newRequestQueue(this)
//        val url = "https://api.openweathermap.org/data/2.5/weather?lat=12.9716&lon=77.5946&appid=95a6c853529dc8b7ffe506bd910f92ed"
////        val url = "http://api.weatherapi.com/v1/current.json?key=${API_Key}&q=${Place}&aqi=no"
////        val url = "http://api.weatherapi.com/v1/current.json?key=041f9eb0e27549dcaf8111533231601&q=Bangalore&aqi=no"
//
////        val url = "https://api.openweathermap.org/data/2.5/weather?lat=37.42&lon=127.08&appid=8584f39b9fc7b4dfe6861f054aea2c4a"
//
//        val jsonObjectRequest = JsonObjectRequest(
//            Request.Method.GET,url,null,
//            Response.Listner{ response ->
//                setValues(response)
//            },
//            Response.ErrorListener{ Toast.makeText(this,"ERROR!!", Toast.LENGTH_LONG).show()})
//        queue.add(jsonObjectRequest)
//    }
//    private fun setValues(response:JSONObject){
//        city.text=response.getString("name")
//        var lat = response.getJSONObject("lat")
//        var lon = response.getJSONObject("lon")
//        lat.text="${lat}"
//        lon.text="${lon}"
//        weather.text = response.getJSONArray("weather").getJSONObject(0).getString("main")
//        var temp = response.getJSONObject("main").getString("name")
//        temp=(((temp).toFloat()273.15).toInt()).toString
//        var mintemp = response.getJSONObject("main").getString("temp min")
//        mintemp=(((mintemp).toFloat()273.15).toInt()).toString
//
//
//    }
//}



        weatherTask().execute()
    }
        inner class weatherTask() : AsyncTask<String, Void, String>() {
            override fun onPreExecute() {
                super.onPreExecute()
                /* Showing the ProgressBar, Making the main design GONE */
                findViewById<ProgressBar>(R.id.loader).visibility = View.VISIBLE
                findViewById<RelativeLayout>(R.id.mainContainer).visibility = View.GONE
                findViewById<TextView>(R.id.errorText).visibility = View.GONE
            }
            override fun doInBackground(vararg params: String?): String? {
                val CITY: String = "bengaluru"
                val API: String = "8118ed6ee68db2debfaaa5a44c832918"
                var response:String?
                try{
                    response = URL("https://api.openweathermap.org/data/2.5/weather?q=$CITY&units=metric&appid=$API").readText(
                        Charsets.UTF_8
                    )
                }catch (e: Exception){
                    response = null
                }
                return response
            }
            override fun onPostExecute(result: String?) {
                super.onPostExecute(result)
                try {
                    /* Extracting JSON returns from the API */
                    val jsonObj = JSONObject(result)
                    val main = jsonObj.getJSONObject("main")
                    val sys = jsonObj.getJSONObject("sys")
                    val wind = jsonObj.getJSONObject("wind")
                    val weather = jsonObj.getJSONArray("weather").getJSONObject(0)
                    val updatedAt:Long = jsonObj.getLong("dt")
                    val updatedAtText = "Updated at: "+ SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(Date(updatedAt*1000))
                    val temp = main.getString("temp")+"°C"
                    val tempMin = "Min Temp: " + main.getString("temp_min")+"°C"
                    val tempMax = "Max Temp: " + main.getString("temp_max")+"°C"
                    val pressure = main.getString("pressure")
                    val humidity = main.getString("humidity")
                    val sunrise:Long = sys.getLong("sunrise")
                    val sunset:Long = sys.getLong("sunset")
                    val windSpeed = wind.getString("speed")
                    val weatherDescription = weather.getString("description")
                    val address = jsonObj.getString("name")+", "+sys.getString("country")
                    /* Populating extracted data into our views */
                    findViewById<TextView>(R.id.address).text = address
                    findViewById<TextView>(R.id.updated_at).text =  updatedAtText
                    findViewById<TextView>(R.id.status).text = weatherDescription.capitalize()
                    findViewById<TextView>(R.id.temp).text = temp
                    findViewById<TextView>(R.id.temp_min).text = tempMin
                    findViewById<TextView>(R.id.temp_max).text = tempMax
                    findViewById<TextView>(R.id.sunrise).text = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunrise*1000))
                    findViewById<TextView>(R.id.sunset).text = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunset*1000))
                    findViewById<TextView>(R.id.wind).text = windSpeed
                    findViewById<TextView>(R.id.pressure).text = pressure
                    findViewById<TextView>(R.id.humidity).text = humidity
                    /* Views populated, Hiding the loader, Showing the main design */
                    findViewById<ProgressBar>(R.id.loader).visibility = View.GONE
                    findViewById<RelativeLayout>(R.id.mainContainer).visibility = View.VISIBLE
                } catch (e: Exception) {
                    findViewById<ProgressBar>(R.id.loader).visibility = View.GONE
                    findViewById<TextView>(R.id.errorText).visibility = View.VISIBLE
                }
            }
        }
    }

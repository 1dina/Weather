package com.example.weather

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ret = Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val services = ret.create(WeatherApis::class.java)
        services.getWeatherInfo("Egypt", "9c31f07a0a0792c4f95a1f59839287bd")
                .enqueue(object : Callback<MainActivity.WeatherInfoData> {
                    override fun onResponse(
                            call: Call<MainActivity.WeatherInfoData>,
                            response: Response<MainActivity.WeatherInfoData>
                    ) {
                        val Text3 = findViewById<TextView>(R.id.descrip)
                        Text3.setText(response.body()?.weather?.first()?.description)
                        val icon = response.body()?.weather?.first()?.icon
                        val link = "https://openweathermap.org/img/wn/${icon}@2x.png"
                        val Image = findViewById<ImageView>(R.id.MyImage)
                        Glide.with(this@MainActivity)
                                .load(link)
                                .into(Image)

                        val Text0 = findViewById<TextView>(R.id.TodayTemp)
                        val result = response.body()?.main?.temp?.minus(272.15)?.toInt()
                        Text0.setText(result.toString())
                        val Text1 = findViewById<TextView>(R.id.tempMin)
                        val result2 = response.body()?.main?.temp_min?.minus(272.15)?.toInt()
                        Text1.setText(result2.toString())
                        val Text2 = findViewById<TextView>(R.id.tempMax)
                        val result3 = response.body()?.main?.temp_max?.minus(272.15)?.toInt()
                        Text2.setText(result3.toString())


                    }

                    override fun onFailure(call: Call<MainActivity.WeatherInfoData>, t: Throwable) {
                        println("error")
                    }
                })


    }

    //description


    data class WeatherInfo(val description: String, val icon: String)

    data class WeatherInfoData(val weather: List<WeatherInfo>, val main: WeatherInfo2)
    data class WeatherInfo2(val temp: Double, val temp_min: Double, val temp_max: Double)


}




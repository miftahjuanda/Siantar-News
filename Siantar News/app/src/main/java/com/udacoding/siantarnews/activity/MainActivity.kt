package com.udacoding.siantarnews.activity

import android.app.Dialog
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.udacoding.siantarnews.R
import com.udacoding.siantarnews.adapter.NewsAdapter
import com.udacoding.siantarnews.model.News
import com.udacoding.siantarnews.model.ResponseServer
import com.udacoding.siantarnews.network.ConfigNetwork
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nointernet.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar.setTitle(R.string.app_name)

        if (isConnect()) {
            ConfigNetwork.getRetrofit().getDataNews().enqueue(object : Callback<ResponseServer> {
                override fun onResponse(
                    call: Call<ResponseServer>,
                    response: Response<ResponseServer>
                ) {

                    if (response.isSuccessful) {
                        val status = response.body()?.status

                        if (status == "ok") {
                            val articel = response.body()?.articles
                            pb_main.visibility = View.GONE

                            showData(articel)
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseServer>, t: Throwable) {
                    Log.d("error server", t.message)
                    pb_main.visibility = View.INVISIBLE
                }

            })
        } else {
            pb_main.visibility = View.GONE
            showCustomDialog()
        }
    }

    private fun showData(articel: ArrayList<News>?) {
        rv_news.setHasFixedSize(true)
        rv_news.layoutManager = LinearLayoutManager(this)

        rv_news.adapter = NewsAdapter(articel)
    }



    fun isConnect(): Boolean {
        val connect: ConnectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return connect.activeNetworkInfo != null && connect.activeNetworkInfo.isConnected
    }

    private fun showCustomDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.nointernet)
        dialog.setCancelable(true)
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window?.attributes)
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT

        dialog.btn_close.setOnClickListener {
            finishAffinity()
            finish()
            dialog.dismiss()
        }
        dialog.show()
        dialog.window?.attributes = lp
    }

    override fun onBackPressed() {
        finishAffinity()
        finish()
    }
}
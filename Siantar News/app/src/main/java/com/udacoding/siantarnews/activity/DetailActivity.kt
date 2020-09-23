package com.udacoding.siantarnews.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.udacoding.siantarnews.R
import com.udacoding.siantarnews.model.News
import kotlinx.android.synthetic.main.activity_detail.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val name = intent.getStringExtra("name")

        setSupportActionBar(toolbar_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(name)

        pb_detail.visibility = View.GONE

        initData()

    }

    private fun initData() {
        val image = intent.getStringExtra("img")
        val title = intent.getStringExtra("title")
        val author = intent.getStringExtra("author")
        val content = intent.getStringExtra("content")
        var date = intent.getStringExtra("date")
        val desc = intent.getStringExtra("desc")
        val url = intent.getStringExtra("url")

        var spf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
        val newDate:Date = spf.parse(date)
        spf = SimpleDateFormat("dd MMM yyyy HH:mm")
        date = spf.format(newDate)

        date_detail.text = date
        tv_author_detail.text = author
        tv_content_detail.text = content
        tv_desc_detail.text = desc
        tv_title_detail.text = title

        val requestOptions = RequestOptions()
            .placeholder(R.drawable.image).centerCrop()
        Glide.with(this)
            .load(image)
            .transition(DrawableTransitionOptions.withCrossFade())
            .thumbnail(
                Glide.with(this)
                    .load(image)
                    .apply(requestOptions)
            )
            .apply(requestOptions)
            .into(image_detail)

        btn_url_detail.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        } else {
            Toast.makeText(applicationContext, item.title, Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }
}
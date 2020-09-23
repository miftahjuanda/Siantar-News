package com.udacoding.siantarnews.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.udacoding.siantarnews.R
import com.udacoding.siantarnews.activity.DetailActivity
import com.udacoding.siantarnews.model.News
import kotlinx.android.synthetic.main.item_list.view.*
import java.text.SimpleDateFormat
import java.util.*


class NewsAdapter(var articel: ArrayList<News>?) : RecyclerView.Adapter<NewsAdapter.NewsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)

        val holder = NewsHolder(view)

        return holder
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        holder.title.text = articel?.get(position)?.title
        holder.author.text = articel?.get(position)?.author
        holder.nama.text = articel?.get(position)?.source?.name

        var spf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
        val newDate: Date = spf.parse(articel?.get(position)?.publishedAt)
        spf = SimpleDateFormat("dd MMM yyyy HH:mm")
        holder.tanggal.text = spf.format(newDate)

        val requestOptions = RequestOptions()
            .placeholder(R.drawable.image).centerCrop()
        Glide.with(holder.itemView.context)
            .load(articel?.get(position)?.urlToImage)
            .transition(DrawableTransitionOptions.withCrossFade())
            .thumbnail(
                Glide.with(holder.itemView.context)
                    .load(articel?.get(position)?.urlToImage)
                    .apply(requestOptions)
            )
            .apply(requestOptions)
            .into(holder.img)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.apply {
                putExtra("img", articel?.get(position)?.urlToImage)
                putExtra("name", articel?.get(position)?.source?.name)
                putExtra("title", articel?.get(position)?.title)
                putExtra("author", articel?.get(position)?.author)
                putExtra("content", articel?.get(position)?.content)
                putExtra("date", articel?.get(position)?.publishedAt)
                putExtra("desc", articel?.get(position)?.description)
                putExtra("url", articel?.get(position)?.url)
            }
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int = articel?.size ?: 0

    class NewsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img = itemView.iv_image
        val title = itemView.tv_title
        val author = itemView.tv_author
        val nama = itemView.tv_nama
        var tanggal = itemView.tv_tanggal

    }
}
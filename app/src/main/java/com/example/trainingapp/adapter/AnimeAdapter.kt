package com.example.trainingapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.trainingapp.R
import com.example.trainingapp.model.Anime

class AnimeAdapter(private val animeList: List<Anime>): RecyclerView.Adapter<AnimeAdapter.AnimeVH>() {

    class AnimeVH(private val view: View):RecyclerView.ViewHolder(view){
        val animeTitle = view.findViewById<TextView>(R.id.anime_card_title)
        val animeImage = view.findViewById<ImageView>(R.id.anime_card_image)
        fun bind(anime: Anime){
            animeTitle.text = anime.title
            Glide.with(view.context).load(anime.images?.jpg?.imageUrl).into(animeImage)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.anime_card_layout, parent, false)
        return AnimeVH(view);
    }

    override fun onBindViewHolder(holder: AnimeVH, position: Int) {
        holder.bind(animeList[position]);
    }

    override fun getItemCount(): Int {
        return animeList.size
    }
}
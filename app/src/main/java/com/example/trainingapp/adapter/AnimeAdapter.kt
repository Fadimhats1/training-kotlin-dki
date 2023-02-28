package com.example.trainingapp.adapter

import android.animation.LayoutTransition
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.bumptech.glide.Glide
import com.example.trainingapp.R
import com.example.trainingapp.model.Anime

class AnimeAdapter(private val animeList: List<Anime>) :
    RecyclerView.Adapter<AnimeAdapter.AnimeVH>() {

    class AnimeVH(private val view: View) : RecyclerView.ViewHolder(view) {
        val animeCard = view.findViewById<CardView>(R.id.anime_card)
        val detailCardContainer = view.findViewById<LinearLayout>(R.id.detail_card_container)
        val animeDescription = view.findViewById<TextView>(R.id.anime_card_description)
        val animeTitle = view.findViewById<TextView>(R.id.anime_card_title)
        val animeImage = view.findViewById<ImageView>(R.id.anime_card_image)
        val textDescription = view.findViewById<TextView>(R.id.text_description)
        val animateContainer =  view.findViewById<LinearLayout>(R.id.animate_container)

        fun bind(anime: Anime) {
            animeTitle.text = anime.title
            Glide.with(view.context).load(anime.images?.jpg?.imageUrl).into(animeImage)
            animeDescription.text = anime.synopsis
            animateContainer.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
            animeCard.setOnClickListener {
                var v: Int? = null
                if (detailCardContainer.visibility == View.GONE) {
                    v = View.VISIBLE
                    textDescription.visibility = v
                } else {
                    v = View.GONE
                    textDescription.visibility = v
                }
                TransitionManager.beginDelayedTransition(animeCard, AutoTransition())
                detailCardContainer.visibility = v
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeVH {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.anime_card_layout, parent, false)
        return AnimeVH(view);
    }

    override fun onBindViewHolder(holder: AnimeVH, position: Int) {
        holder.bind(animeList[position]);
    }

    override fun getItemCount(): Int {
        return animeList.size
    }
}
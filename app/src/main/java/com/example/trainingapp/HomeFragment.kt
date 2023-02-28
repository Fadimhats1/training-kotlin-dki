package com.example.trainingapp

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingapp.adapter.AnimeAdapter
import com.example.trainingapp.model.Anime
import com.example.trainingapp.services.api.MalApiSingleton
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {
    private val malApi = MalApiSingleton.api()
    private var animes: ArrayList<Anime> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val animeRv = view.findViewById<RecyclerView>(R.id.anime_rv)
        animeRv.adapter = AnimeAdapter(animes)
        CoroutineScope(Dispatchers.IO).launch {
            val res = malApi.getAnimes()
            withContext(Dispatchers.Main) {
                if (res.isSuccessful) {
                    val data = res.body()?.data
                    animes.clear()
                    animes.addAll(data as ArrayList<Anime>)
                    if(animes.isNotEmpty()){
                        (animeRv.adapter)?.notifyDataSetChanged()
                        view.findViewById<ProgressBar>(R.id.anime_rv_loading).visibility = View.INVISIBLE
                        animeRv.visibility = View.VISIBLE
                    }
                }
            }
        }

        return view
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}

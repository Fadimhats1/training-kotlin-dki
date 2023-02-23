package com.example.trainingapp


import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Base64
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.example.trainingapp.model.AnimesResponse
import com.example.trainingapp.services.api.MalApiSingleton
import com.example.trainingapp.services.api.IMalApiService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException


class MainActivity() : AppCompatActivity() {
    val cameraLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val bitmap = BitmapFactory.decodeFile(currentPhotoPath)
                image_camera.setImageBitmap(bitmap)


            }
        }
    private lateinit var currentPhotoPath: String
    private var PERMISSION_REQUEST_CODE = 200
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fileName = "photo"
        val storageDirectory: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)


        responseCall().enqueue(object : Callback<AnimesResponse> {
            override fun onResponse(
                call: Call<AnimesResponse>,
                response: Response<AnimesResponse>
            ) {

//                anime_list_rv.adapter = AnimeAdapter(response?.body()?.data as List<Anime>)
            }

            override fun onFailure(call: Call<AnimesResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })


        to_camera.setOnClickListener {
            try {
                val imageFile = File.createTempFile(fileName, ".jpg", storageDirectory)
                currentPhotoPath = imageFile.absolutePath
                val imageUri = FileProvider.getUriForFile(
                    this,
                    "com.example.trainingapp.fileprovider",
                    imageFile
                )
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).putExtra(
                    MediaStore.EXTRA_OUTPUT,
                    imageUri
                )
                cameraLauncher.launch(intent)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun responseCall(): Call<AnimesResponse> {
        return MalApiSingleton.getInstance().create<IMalApiService>(IMalApiService::class.java)
            .getAnimes()
    }

    private fun encodeImage(image: Bitmap): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }
}
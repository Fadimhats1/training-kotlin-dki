package com.example.trainingapp

import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Camera
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import kotlinx.android.synthetic.main.fragment_camera.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class CameraFragment : Fragment() {

    private val cameraLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val bitmap = toPotrait(BitmapFactory.decodeFile(currentPhotoPath))
                image_camera.setImageBitmap(bitmap)
            }
        }
    private val cameraPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                openCamera(requireActivity() as MainActivity)
            } else {
                Toast.makeText(
                    requireContext(),
                    "Permission denied to use camera",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

    private lateinit var currentPhotoPath: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }
    private fun checkCameraPermission(mainActivity: MainActivity) {
        val permission = android.Manifest.permission.CAMERA
        if (ContextCompat.checkSelfPermission(mainActivity, permission) != PackageManager.PERMISSION_GRANTED) {
            requestCameraPermission(mainActivity)
        } else {
           openCamera(mainActivity)
        }
    }
    private fun requestCameraPermission(mainActivity: MainActivity) {
        val permission = android.Manifest.permission.CAMERA
        if (ActivityCompat.shouldShowRequestPermissionRationale(mainActivity, permission)) {
            Toast.makeText(mainActivity, "Camera permission is needed to use the camera", Toast.LENGTH_SHORT).show()
        }
        cameraPermissionLauncher.launch(permission)
    }
    private fun toPotrait(bitmap: Bitmap): Bitmap {
        val width = bitmap.width
        val height = bitmap.height
        val matrix = Matrix()
        var bitmapRes: Bitmap = bitmap
        if (width > height) {
            matrix.setRotate(90f)
            bitmapRes = Bitmap.createBitmap(bitmapRes, 0, 0, width, height, matrix, true)
        }
        return bitmapRes
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_camera, container, false)
        val cameraPermission = android.Manifest.permission.CAMERA
        checkCameraPermission(requireActivity() as MainActivity)

        view.findViewById<Button>(R.id.open_camera).setOnClickListener {
            checkCameraPermission(requireActivity() as MainActivity)
        }
        return view
    }

    private fun openCamera(mainActivity: MainActivity) {
        val imageFile = createImageFile(mainActivity)
        currentPhotoPath = imageFile.absolutePath
        val imageUri = FileProvider.getUriForFile(
            mainActivity,
            "com.example.trainingapp.fileprovider",
            imageFile
        )
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).putExtra(
            MediaStore.EXTRA_OUTPUT,
            imageUri
        )
        cameraLauncher.launch(intent)
    }

    private fun createImageFile(mainActivity: Context): File {
        return File.createTempFile(
            "photo",
            ".jpg",
            mainActivity.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        )
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if(resultCode != RESULT_CANCELED){
//            if (requestCode == 1 && resultCode == RESULT_OK) {
//                val exif = ExifInterface(currentPhotoPath)
//                val orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED)
//                Log.e(TAG, "fixOrientation: $currentPhotoPath", )
//                Log.e(TAG, "fixOrientation: $orientation", )
//                val bitmap = data?.extras?.get("data") as Bitmap
//                image_camera.setImageBitmap(bitmap)
//            }
//        }
//    }

    companion object {

        @JvmStatic
        fun newInstance() =
            CameraFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }


}
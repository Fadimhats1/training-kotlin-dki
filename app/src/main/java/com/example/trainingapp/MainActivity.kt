package com.example.trainingapp


import android.content.ContentValues.TAG
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.pm.PackageInfoCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.example.trainingapp.databinding.ActivityMainBinding
import com.example.trainingapp.model.Anime
import com.example.trainingapp.services.api.DkiApiSingleton
import com.example.trainingapp.services.api.MalApiSingleton
import com.example.trainingapp.ui.login.LoginActivity
import com.example.trainingapp.utils.AppPreferences
import com.example.trainingapp.utils.Utils
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.util.ArrayList


class MainActivity : AppCompatActivity() {
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // FCM SDK (and your app) can post notifications.
        } else {
            // TODO: Inform user that that your app will not show notifications.
        }
    }
    private val fragmentManager = supportFragmentManager
    private var currentFragment: Fragment? = null
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        FirebaseMessaging.getInstance().subscribeToTopic("Notification").addOnCompleteListener(OnCompleteListener { task ->
//            if (!task.isSuccessful) {
//                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
//                return@OnCompleteListener
//            }
//        })
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }
            val token = task.result

            Log.e(TAG, "onCreate: $token", )
        })
        val homeFragment = HomeFragment.newInstance()
        val cameraFragment = CameraFragment.newInstance()
        val profileFragment = ProfileFragment.newInstance()
//        var animes: Bundle? = null

        if (savedInstanceState == null) {
//            homeFragment.arguments = animes
            fragmentChange(homeFragment, "HomeFragment")
        }
//        CoroutineScope(Dispatchers.IO).launch {
//            val res = malApi.getAnimes()
//            withContext(Dispatchers.Main) {
//                if (res.isSuccessful) {
//                    val data = res.body()?.data
//                    animes = Bundle().apply {
//                        putParcelableArrayList("animes", data as ArrayList<out Parcelable>)
//                    }
////                    updateFragmentData(animes, "HomeFragment")
//                }
//            }
//        }

        bottom_navigation_view.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> {
                    fragmentChange(homeFragment, "HomeFragment")

                    true
                }
                R.id.navigation_camera -> {
                    fragmentChange(cameraFragment, "CameraFragment")
                    true
                }
                R.id.navigation_profile -> {
                    fragmentChange(profileFragment, "ProfileFragment")
                    true
                }

                else -> false
            }
        }
    }

    private fun fragmentChange(fragment: Fragment, tag: String) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragmentSearch = fragmentManager.findFragmentByTag(tag)

        if (currentFragment != null) {
            fragmentTransaction.hide(currentFragment!!)
            fragmentTransaction.setMaxLifecycle(currentFragment!!, Lifecycle.State.STARTED)
        }

        if (fragmentSearch != null) {
            fragmentTransaction.show(fragmentSearch)
            fragmentTransaction.setMaxLifecycle(fragmentSearch, Lifecycle.State.RESUMED)
        } else {
            fragmentTransaction.add(R.id.fragment_container, fragment, tag)
        }

        currentFragment = fragment
        fragmentTransaction.commit()
    }
    private fun askNotificationPermission() {
        // This is only necessary for API level >= 33 (TIRAMISU)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_GRANTED
            ) {
                // FCM SDK (and your app) can post notifications.
            } else if (shouldShowRequestPermissionRationale(android.Manifest.permission.POST_NOTIFICATIONS)) {
                // TODO: display an educational UI explaining to the user the features that will be enabled
                //       by them granting the POST_NOTIFICATION permission. This UI should provide the user
                //       "OK" and "No thanks" buttons. If the user selects "OK," directly request the permission.
                //       If the user selects "No thanks," allow the user to continue without notifications.
            } else {
                // Directly ask for the permission
                requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }
    // TODO: Nanti mau tanyain ke kakak mentor gimana caranya update data suatu fragment menggunakan
//    private fun updateFragmentData(bundle: Bundle? = null, tag: String) {
//        val fragmentTransaction = fragmentManager.beginTransaction()
//        val fragment = fragmentManager.findFragmentByTag(tag)
//        fragment?.arguments = bundle
//        fragmentTransaction.commit()
//    }
    override fun onStart() {
        super.onStart()
        val api = DkiApiSingleton.api()
        val appPreferences = AppPreferences(this)

        CoroutineScope(Dispatchers.IO).launch {
            val response = api.startApi(
                Utils.TOKEN,
                appPreferences.cif,
                android.os.Build.MODEL,
                "",
                "Android",
                "null",
                appPreferences.tokenlogin,
                getVersionName(),
                ""
            )
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    delay(2000)
                    if (!response.body()?.statuscode.equals("1")) {
                        startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                        finish()
                    }
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Something went wrong with the server",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun getVersionName(): String {
        val packageManager = applicationContext.packageManager
        val packageName = packageName

        @Suppress("DEPRECATION")
        val packageInfo = packageManager.getPackageInfo(packageName, 0)
        return PackageInfoCompat.getLongVersionCode(packageInfo).toString()
    }
}
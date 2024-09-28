package com.nextbigthing.thepunchline.ui

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.nextbigthing.thepunchline.navigation.Navigation
import com.nextbigthing.thepunchline.ui.theme.ThePunchlineTheme
import com.nextbigthing.thepunchline.util.JokesPreferenceHelper
import com.nextbigthing.thepunchline.viewModel.JokesViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var jokesPreferenceHelper: JokesPreferenceHelper
    @Inject lateinit var databaseReference: DatabaseReference
    private val viewModel:JokesViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        jokesPreferenceHelper.clearPreferences()
        val packageManager = packageManager.getPackageInfo(packageName, 0)
        val versionCode = packageManager.versionCode
        jokesPreferenceHelper.saveInt("versionCode", versionCode)
        setContent {
            fetchAppId()
            ThePunchlineTheme {
                Navigation(viewModel = viewModel, jokesPreferenceHelper = jokesPreferenceHelper)
            }
        }
    }

    private fun fetchAppId() {
        databaseReference.child("AppId").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    // Fetch the AppId value
                    val versionCode = jokesPreferenceHelper.getInt("versionCode", 0)
                    val appId = snapshot.getValue(Int::class.java)
                    if (appId != null) {
                        if (appId>versionCode){
                            Toast.makeText(this@MainActivity, "Update Available", Toast.LENGTH_SHORT).show()
                        }
                    }
                    Log.d("Firebase", "AppId: $appId")
                    // Display or use the AppId value as needed
                } else {
                    Log.e("Firebase", "AppId not found")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Database error: ${error.message}")
            }
        })
    }
}
package com.nextbigthing.thepunchline.ui

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.nextbigthing.thepunchline.navigation.Navigation
import com.nextbigthing.thepunchline.ui.component.UpdateAppDialog
import com.nextbigthing.thepunchline.ui.theme.ThePunchlineTheme
import com.nextbigthing.thepunchline.util.JokesPreferenceHelper
import com.nextbigthing.thepunchline.viewModel.JokesViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var jokesPreferenceHelper: JokesPreferenceHelper
    @Inject
    lateinit var databaseReference: DatabaseReference
    private val viewModel: JokesViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        jokesPreferenceHelper.clearPreferences()

        // Get the version code of the app
        val packageManager = packageManager.getPackageInfo(packageName, 0)
        val versionCode = packageManager.versionCode
        jokesPreferenceHelper.saveInt("versionCode", versionCode)

        // MutableState to control the dialog visibility
        val showUpdateDialog = mutableStateOf(false)

        setContent {
            ThePunchlineTheme {
                // Fetch the AppId when the activity starts
                fetchAppId(showUpdateDialog)

                // Display your navigation
                Navigation(
                    viewModel = viewModel,
                    jokesPreferenceHelper = jokesPreferenceHelper,
                    context = this@MainActivity
                )

                // Show the Update Dialog if necessary
                if (showUpdateDialog.value) {
                    UpdateAppDialog(
                        onUpdateClick = {
                            Toast.makeText(this@MainActivity, "Updating...", Toast.LENGTH_SHORT)
                                .show()
                            // Perform update action here
                            showUpdateDialog.value = false
                        },
                        onDismiss = {
                            showUpdateDialog.value = false
                        }
                    )
                }
            }
        }
    }

    private fun fetchAppId(showUpdateDialog: MutableState<Boolean>) {
        databaseReference.child("AppId")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        // Fetch the AppId value
                        val versionCode = jokesPreferenceHelper.getInt("versionCode", 0)
                        val appId = snapshot.getValue(Int::class.java)
                        if (appId != null && appId > versionCode) {
                            // Trigger the dialog if AppId is greater than the version code
                            showUpdateDialog.value = true
                        }
                        Log.d("Firebase", "AppId: $appId")
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

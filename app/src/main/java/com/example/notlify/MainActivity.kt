package com.example.notlify

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.notlify.ui.theme.NotlifyTheme


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideSystemUI()
        window.decorView.apply {
            // Hide both the navigation bar and the status bar.
            // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
            // a general rule, you should design your app to hide the status bar whenever you
            // hide the navigation bar.
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        }
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
// Remember that you should never show the action bar if the
// status bar is hidden, so hide that too if necessary.
        actionBar?.hide()
        setContent {
            NotlifyTheme {
                val navController = rememberNavController()
                val destination = navController.currentBackStackEntryAsState().value?.destination?.route
                var searchState by remember {mutableStateOf("")}
                val navigationState = rememberDrawerState(initialValue = DrawerValue.Closed   )
                ModalNavigationDrawer(drawerContent = {
                                                      
                    ModalDrawerSheet() {
                                                          Text(text = "hello")
                                                      }
                }, drawerState = navigationState) {
                    Scaffold() {

                        Column(modifier = Modifier.padding(it)) {
                            NavHost(navController = navController, startDestination = "onboarding" ) {
                                composable("onboarding") {
                                    OnboardingScreen(navController)
                                }
                                composable("home") {
                                    HomeScreen(navController, drawerState = navigationState   )
                                }
                                composable("settings") {
                                    SettingsScreen(navController)
                                }
                                composable("note") {
                                    NoteScreen(navController)
                                }
                                composable("gallery") {
                                    GalleryScreen(navController, drawerState = navigationState)
                                }
                            }
                        }

                    }
                }
         

            }
        }
    }

    fun hideSystemUI() {

        getWindow().getDecorView().setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_IMMERSIVE
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
        )
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NotlifyTheme {
        Greeting("Android")
    }
}
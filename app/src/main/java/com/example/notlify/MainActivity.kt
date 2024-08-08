package com.example.notlify

import android.hardware.lights.Light
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.notlify.ui.theme.Black
import com.example.notlify.ui.theme.DarkGray
import com.example.notlify.ui.theme.DrawerGray
import com.example.notlify.ui.theme.LightGray
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
        val controller = WindowInsetsControllerCompat(
            window, window.decorView
        )

        controller.hide(WindowInsetsCompat.Type.systemBars())
        controller.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        setContent {
            NotlifyTheme {
                val navController = rememberNavController()
                val destination = navController.currentBackStackEntryAsState().value?.destination?.route
                var searchState by remember {mutableStateOf("")}
                val navigationState = rememberDrawerState(initialValue = DrawerValue.Closed   )
                ModalNavigationDrawer(gesturesEnabled = false, drawerContent = {
                    if(destination in listOf("home", "gallery")) {

                        ModalDrawerSheet(modifier = Modifier.fillMaxWidth(0.8f), drawerContainerColor = DrawerGray) {
                            AppColumn {
                                IconButton(onClick = { navController.navigate("settings") }) {
                                    Icon(Icons.Default.Settings, contentDescription = "Settings", tint = Color.White )
                                }
                                OutlinedTextField(leadingIcon = {
                                    Icon(Icons.Default.Search, contentDescription = "Search", tint = Color.LightGray )
                                },trailingIcon ={
                                    IconButton(onClick = { /*TODO*/ }) {
                                        Icon(
                                            Icons.Default.Clear,
                                            contentDescription = "clear",
                                            tint = Color.LightGray
                                        )
                                    }
                                },value = searchState, modifier = Modifier
                                    .fillMaxWidth()
                                    .height(36.dp)
                                    .padding(vertical = 10.dp),onValueChange = {searchState = it}, colors = TextFieldDefaults.outlinedTextFieldColors(containerColor = Black,))
                                Spacer(modifier = Modifier.height(20.dp))
                                NavigationDrawerItem(modifier = Modifier.height(40.dp),shape = RoundedCornerShape(10.dp   ), colors = NavigationDrawerItemDefaults.colors(selectedContainerColor = Color(0xFF404856), unselectedContainerColor = Color.Transparent, unselectedTextColor = LightGray),
                                    label = {   Text(text = "Notes", fontWeight = FontWeight.Bold, color = Color.White)},
                                    selected = destination == "home",
                                    onClick = { navController.navigate("home")},
                                    badge = {         Text(text = "2", fontWeight = FontWeight.Bold, color = Color.White)}
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                                NavigationDrawerItem(modifier = Modifier.height(40.dp),shape = RoundedCornerShape(10.dp   ), colors = NavigationDrawerItemDefaults.colors(selectedContainerColor = Color(0xFF404856), unselectedContainerColor = Color.Transparent, unselectedTextColor = LightGray),
                                    label = {   Text(text = "Gallery", fontWeight = FontWeight.Bold, color = Color.White)},
                                    selected = destination == "gallery",
                                    onClick = { navController.navigate("gallery")},
                                    badge = {  }
                                )

                                Spacer(modifier = Modifier.height(20.dp))
                                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                    Text(text = "Subjects", fontWeight = FontWeight.Bold, color = LightGray)
                                    IconButton(onClick = { /*TODO*/ }) {
                                        Icon(Icons.Default.Add, contentDescription = "Add" , tint = Color.White    )
                                        Row() {

                                        }
                                    }
                                }


                            }



                        }
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
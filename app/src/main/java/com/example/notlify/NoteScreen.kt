package com.example.notlify

import android.hardware.lights.Light
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.draw.shadow
import com.example.notlify.ui.theme.Black
import com.example.notlify.ui.theme.DarkGray
import com.example.notlify.ui.theme.DrawerGray
import com.example.notlify.ui.theme.LightBlue
import com.example.notlify.ui.theme.LightGray
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteScreen(navController: NavController) {
    var notTypedYet by remember { mutableStateOf(true )}
    Scaffold(bottomBar = {
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp), contentAlignment = Alignment.Center) {
            Row(modifier = Modifier
                .background(Black, shape = RoundedCornerShape(10.dp))
                .shadow(4.dp)) {
                repeat(5) {
                    when(it) {
                        0 -> {
                            IconButton(onClick = { /*TODO*/ }) {

                                Icon(painterResource(id = R.drawable.baseline_font_download_24), contentDescription =  "Add", tint = Color.White)


                            }
                        }
                        1-> {
                            IconButton(onClick = { /*TODO*/ }) {

                                Icon(painterResource(id = R.drawable.baseline_image_24), contentDescription =  "Add", tint = Color.White)


                            }
                        }
                        2 -> {
                            IconButton(onClick = { /*TODO*/ }) {

                                Icon(painterResource(id = R.drawable.baseline_format_list_bulleted_24), contentDescription =  "Add", tint = Color.White)


                            }
                        }
                        3 -> {
                            IconButton(onClick = { /*TODO*/ }) {

                                Icon(painterResource(id = R.drawable.baseline_format_list_bulleted_24), contentDescription =  "Add", tint = Color.White)


                            }
                        }

                    }

                }
            }
        }

    }) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(it)) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .background(DrawerGray)) {
                AppColumn {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement =Arrangement.SpaceBetween) {
                        IconButton(onClick = { navController.navigateUp() },  modifier = Modifier
                            .border(
                                width = 2.dp,
                                color = LightGray,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .background(
                                Black, shape = RoundedCornerShape(10.dp)
                            )) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "back", tint = Color.White, modifier = Modifier.size(24.dp))
                        }
                        Row(
                            modifier = Modifier
                                .background(Black, RoundedCornerShape(10.dp))
                                .border(
                                    width = 2.dp,
                                    color = LightGray,
                                    shape = RoundedCornerShape(10.dp)
                                )
                        ) {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(painterResource(id = R.drawable.baseline_undo_24), contentDescription =  "Undo", tint = LightGray)

                            }
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(painterResource(id = R.drawable.baseline_more_horiz_24), contentDescription =  "Undo", tint = LightGray)
                            }
                        }
                       /* IconButton(onClick = { navController.navigateUp() },  modifier = Modifier
                            .border(width = 2.dp, color = LightGray, shape = RoundedCornerShape(10.dp   ))
                            .background(
                                Black, shape = RoundedCornerShape(10.dp)
                            )) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "back", tint = Color.White)
                        }*/
                    }
                    Text(text = "Default 08-10-05", fontSize = 24.sp, modifier = Modifier.padding(vertical = 10.dp  ), color = Color.White)

                }
            }
            val listOfItems = remember { listOf("Import", "Scan", "Templates")}
            Column(modifier = Modifier
                .fillMaxSize()
                .background(Color.White)) {
                if(notTypedYet) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Row(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                           listOfItems.forEach {
                                Row(horizontalArrangement = Arrangement.spacedBy(5.dp   ), verticalAlignment = Alignment.CenterVertically) {
                                    when(it) {
                                        "Import" ->
                                        Icon(painter = painterResource(id = R.drawable.baseline_insert_drive_file_24), contentDescription = "", tint = DarkGray, modifier = Modifier.size(30.dp ) )
                                        "Scan" -> {
                                            Icon(painter = painterResource(id = R.drawable.baseline_qr_code_scanner_24), contentDescription = "", tint = DarkGray, modifier = Modifier.size(30.dp ) )
                                        }
                                        "Templates" -> {
                                            Icon(painter = painterResource(id = R.drawable.baseline_newspaper_24), contentDescription = "", tint = DarkGray, modifier = Modifier.size(30.dp ) )
                                        }
                                    }

                                    Text(text = "$it", fontWeight = FontWeight.SemiBold, color = DarkGray)
                                }

                            }

                        }
                    }
                }

            }
        }
    }


}
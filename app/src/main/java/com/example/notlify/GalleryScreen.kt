package com.example.notlify

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Tab
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notlify.ui.theme.Black
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextDirection.Companion.Content
import androidx.compose.ui.unit.sp
import com.example.notlify.ui.theme.Blue
import com.example.notlify.ui.theme.LightBlue
import com.example.notlify.ui.theme.LightGray
import kotlinx.coroutines.launch
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun GalleryScreen(navController: NavController, drawerState: DrawerState) {
    val state = rememberPagerState()
    var search by remember { mutableStateOf("")}
    val scope = rememberCoroutineScope()
    val tabs = remember { listOf("Discover", "Popular")}
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Black)) {
        AppColumn() {
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.SpaceBetween) {
                IconButton(onClick = {
                    scope.launch {
                        drawerState.open()
                    }
                },) {
                    Icon(painter = painterResource(id = R.drawable.baseline_menu_24), contentDescription = "Menu", tint = LightGray )

                }

                Button(contentPadding = PaddingValues(horizontal = 14.dp    ),onClick = { navController.navigate("note") }, colors = ButtonDefaults.buttonColors(backgroundColor = LightBlue), shape = RoundedCornerShape(10.dp    )) {
                    Text(text = "Login", color = Color.White, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 10.dp ))

                }


            }

           androidx.compose.material3.OutlinedTextField(colors = TextFieldDefaults.outlinedTextFieldColors(), value = search, onValueChange =  {search = it}, shape = RoundedCornerShape(20.dp  ), label = { Text(
                text = "Search Gallery", color = Color.DarkGray
            )}, modifier = Modifier
               .fillMaxWidth().height(34.dp )
               .padding(vertical = 10.dp), leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = "Search" , tint = LightGray)
            })
            androidx.compose.material.ScrollableTabRow(edgePadding = 0.dp ,modifier = Modifier
                .padding(0.dp)
                .offset(x = -5.dp),selectedTabIndex = state.currentPage, backgroundColor = Color.Transparent, indicator = {
                TabRowDefaults.Divider(
                    Modifier
                        .requiredWidth(74.dp)
                        .tabIndicatorOffset(it[state.currentPage])
                        .offset(-50.dp), thickness = 3.dp , color = LightBlue
                )
            }) {
                tabs.forEach {
                    Tab(selectedContentColor = Color.White, unselectedContentColor = Color.DarkGray,modifier = Modifier.padding(0.dp    ),selected =  state.currentPage == tabs.indexOf(it), onClick = {
                        scope.launch {
                            state.animateScrollToPage(tabs.indexOf(it))
                        }
                    }) {

                        Text(text = it, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(vertical = 6.dp    ), fontSize = 18.sp )

                    }


                }

            }
            Spacer(modifier = Modifier.height(20.dp))
            HorizontalPager(pageCount = tabs.size, state = state) {
                when(it) {
                    0 -> {
                        Column() {
                            Text(
                                text = "Back-to-School Essentials",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(vertical = 10.dp),
                                fontSize = 22.sp
                            )
                            LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp  )) {
                                items(5){
                                    Card(border = BorderStroke(width = 1.dp, color = LightGray), modifier = Modifier.width(170.dp   ), colors = CardDefaults.cardColors(containerColor = Black)) {
                                        Image(painter = painterResource(id = R.drawable.planner), contentDescription = "Poan", modifier = Modifier
                                            .fillMaxWidth()
                                            .clip(
                                                RoundedCornerShape(10.dp)
                                            ) , contentScale = ContentScale.Crop)
                                        Column(modifier = Modifier.padding(10.dp  ), verticalArrangement = Arrangement.spacedBy(10.dp )) {
                                            Text(text = "Assignment Plan..", fontWeight = FontWeight.Bold, color = Color.White)
                                            Text(text = "Notability", color = LightGray)
                                            Row(horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally   ), modifier = Modifier.fillMaxWidth()) {
                                                repeat(3) {
                                                    Box(contentAlignment = Alignment.Center,modifier = Modifier
                                                        .background(
                                                            LightBlue, shape = CircleShape
                                                        )
                                                        .size(40.dp)) {

                                                    }
                                                }
                                            }

                                        }


                                    }
                                }

                            }
                        }


                    }

                    1 -> {

                    }
                }
            }



        }



    }


}
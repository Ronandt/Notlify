package com.example.notlify

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.notlify.ui.theme.Black
import com.example.notlify.ui.theme.DarkGray
import com.example.notlify.ui.theme.DarkLightPurple
import com.example.notlify.ui.theme.DarkPurple
import com.example.notlify.ui.theme.LightBlue
import com.example.notlify.ui.theme.LightGray
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(navController: NavController, drawerState: DrawerState) {
    var state = rememberPagerState()
    val scope = rememberCoroutineScope()
    var tabs = remember {
        listOf("All Notes", "Recents", "Favorites", "Unfiled")
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Black)) {
        AppColumn {
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.SpaceBetween) {
                IconButton(onClick = {
                    scope.launch {
                        drawerState.open()
                    }
                     },) {
                    Icon(painter = painterResource(id = R.drawable.baseline_menu_24), contentDescription = "Menu", tint = LightGray )

                }
                Row(modifier = Modifier, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(painter = painterResource(R.drawable.baseline_more_horiz_24), contentDescription =  "More", tint = LightGray)


                    }
                    Button(contentPadding = PaddingValues(horizontal = 14.dp    ),onClick = { navController.navigate("note") }, colors = ButtonDefaults.buttonColors(backgroundColor = LightBlue), shape = RoundedCornerShape(10.dp    )) {
                        Text(text = "+ New", color = Black, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 10.dp ))

                    }
                }

            }

            Spacer(modifier = Modifier.height(30.dp ))
            Text(text = "All Notes", fontSize = 32.sp, color = Color.White, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(10.dp))
            androidx.compose.material.ScrollableTabRow(edgePadding = 0.dp ,modifier = Modifier
                .padding(0.dp)
                .offset(x = -5.dp),selectedTabIndex = state.currentPage, backgroundColor = Color.Transparent, indicator = {
                TabRowDefaults.Divider(
                    Modifier
                        .requiredWidth(74.dp)
                        .tabIndicatorOffset(it[state.currentPage])
                        .offset(-136.dp), thickness = 3.dp , color = LightBlue
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
            Spacer(modifier = Modifier.height(10.dp))
            HorizontalPager(pageCount = tabs.size, state = state) {
                when(it) {
                    0-> {
                        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp )) {
                            items(3) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(IntrinsicSize.Min),
                                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Image(painter = painterResource(id = R.drawable.sample), contentDescription = "Sample", modifier = Modifier
                                        .size(80.dp)
                                        .clip(
                                            RoundedCornerShape(10.dp)
                                        ))
                                    Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween) {
                                        Text(text = "Getting Started", color = Color.White)
                                        Text(text = "5 Aug 2024", color = DarkGray)
                                        Box(modifier = Modifier
                                            .background(
                                                DarkPurple,
                                                shape = RoundedCornerShape(20.dp)
                                            )
                                            .border(
                                                width = 3.dp, color =
                                                DarkLightPurple, shape = RoundedCornerShape(20.dp)
                                            )) {
                                            Text(modifier = Modifier.padding(horizontal = 8.dp    ), fontSize = 12.sp   ,text = "Welcome", color = Color.White, fontWeight = FontWeight.SemiBold)
                                        }
                                        Spacer(modifier = Modifier.height(2.dp))
                                        Divider(modifier = Modifier.fillMaxWidth(), color = DarkGray, thickness = 0.6.dp)

                                    }
                                }
                            }
                        }

                    }
                    1-> {}
                    2-> {

                    }
                    3-> {

                    }

                }

            }
        }
    }
}
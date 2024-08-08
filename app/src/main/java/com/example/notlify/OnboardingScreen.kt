package com.example.notlify

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.notlify.ui.theme.Beige
import com.example.notlify.ui.theme.Blue
import com.example.notlify.ui.theme.LightBlue
import com.example.notlify.ui.theme.LightGray
import com.example.notlify.ui.theme.White
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(navController: NavController) {
    val pagerState = rememberPagerState(0)
    val scope = rememberCoroutineScope()
    AppColumn() {
        HorizontalPager(pageCount = 3, state = pagerState) {
            when(it) {
                0 -> {
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .background(color = Beige), horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(painter = painterResource(id = R.drawable.img_2), contentDescription =  "Img2", modifier = Modifier
                            .size(400.dp)
                            .align(Alignment.End), contentScale = ContentScale.Crop)
                        Spacer(modifier = Modifier.height(50.dp))

                            Text(text = "Record audio that\nsyncs to your notes", fontSize = 30.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                        Text(text = "Next", color = LightGray, modifier = Modifier
                            .padding(vertical = 60.dp)
                            .clickable {
                                scope.launch {
                                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                }

                            })

                        Row(modifier = Modifier, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            repeat(3) {
                                if(it == 0) {
                                    Spacer(modifier = Modifier
                                        .size(8.dp)
                                        .background(color = LightBlue, CircleShape))
                                } else {
                                    Spacer(modifier = Modifier
                                        .size(8.dp)
                                        .background(color = LightGray, CircleShape))
                                }


                            }

                        }

                    }
                }
                1  -> {
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .background(color = Blue), horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(painter = painterResource(id = R.drawable.img_1), contentDescription =  "Img2", modifier = Modifier
                            .size(400.dp)
                            .align(Alignment.End), contentScale = ContentScale.Crop)
                        Spacer(modifier = Modifier.height(50.dp))

                        Text(text = "Review and create\nnotes on the go", fontSize = 30.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                        Text(text = "Next", color = LightGray, modifier = Modifier
                            .padding(vertical = 60.dp)
                            .clickable {
                                scope.launch {
                                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                }

                            })

                        Row(modifier = Modifier, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            repeat(3) {
                                if(it == 1) {
                                    Spacer(modifier = Modifier
                                        .size(8.dp)
                                        .background(color = LightBlue, CircleShape))
                                } else {
                                    Spacer(modifier = Modifier
                                        .size(8.dp)
                                        .background(color = LightGray, CircleShape))
                                }


                            }

                        }

                    }
                }
                2 -> {
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .background(color = White), horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(painter = painterResource(id = R.drawable.img_3), contentDescription =  "Img2", modifier = Modifier
                            .size(400.dp)
                            .align(Alignment.End), contentScale = ContentScale.Crop)
                        Spacer(modifier = Modifier.height(50.dp))

                        Text(text = "Import and share\nnotes quickly", fontSize = 30.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                     androidx.compose.material3.Button(onClick = {
                            navController.navigate("home")
                        }, colors = androidx.compose.material3.ButtonDefaults.buttonColors(containerColor = LightBlue), shape = RoundedCornerShape(10.dp    ), modifier = Modifier
                         .padding(vertical = 50.dp)
                         .width(220.dp)) {
                            Text(text = "Get started", color = Color.White, fontWeight = FontWeight.SemiBold)
                        }

                        Row(modifier = Modifier, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            repeat(3) {
                                if(it == 2) {
                                    Spacer(modifier = Modifier
                                        .size(8.dp)
                                        .background(color = LightBlue, CircleShape))
                                } else {
                                    Spacer(modifier = Modifier
                                        .size(8.dp)
                                        .background(color = LightGray, CircleShape))
                                }


                            }

                        }

                    }
                }
            }
        }


    }


}


package com.example.notlify

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AppColumn(modifier: Modifier = Modifier, content: @Composable () -> Unit  ) {
    Column(modifier = modifier.then(Modifier.padding(10.dp))) {
        content()
    }
}
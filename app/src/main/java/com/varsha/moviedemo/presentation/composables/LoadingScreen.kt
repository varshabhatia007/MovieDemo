package com.varsha.moviedemo.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.varsha.moviedemo.R
import com.varsha.moviedemo.ui.theme.Purple40
import com.varsha.moviedemo.ui.theme.spacing

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize(),
    ) {
        CircularProgressIndicator(
            color = Purple40
        )
        Spacer(modifier = Modifier.height(spacing.normal))
        Text(text = stringResource(R.string.label_loading), color = Purple40)
    }
}


@Preview
@Composable
fun LoadingScreenPrev() {
    LoadingScreen()
}



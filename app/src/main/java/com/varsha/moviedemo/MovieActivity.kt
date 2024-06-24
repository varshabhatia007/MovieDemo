package com.varsha.moviedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.varsha.moviedemo.presentation.navigation.RootNavigationGraph
import com.varsha.moviedemo.ui.theme.MovieDemoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieDemoTheme {
                RootNavigationGraph()
            }
        }
    }
}
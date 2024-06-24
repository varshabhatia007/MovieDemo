package com.varsha.moviedemo.presentation.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.varsha.moviedemo.R

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
){
    EmptyStateScreen(
        modifier = modifier,
        title = stringResource(id = R.string.no_internet_connection_available),
        description = stringResource(id = R.string.empty_screen_description_no_internet),
        image = R.drawable.ic_no_internet
    )
}

//no internet
@Composable
fun NoInternetConnectionScreen(
    modifier: Modifier = Modifier,
){
    EmptyStateScreen(
        modifier = modifier,
        title = stringResource(id = R.string.no_internet_connection_available),
        description = stringResource(id = R.string.empty_screen_description_no_internet),
        image = R.drawable.ic_no_internet
    )
}

//no search found
@Composable
fun EmptySearchScreen(
    modifier: Modifier = Modifier,
    title: String = stringResource(id = R.string.empty_screen_title_not_found_results),
    description: String = stringResource(id = R.string.empty_screen_description_no_results, "search")
){
    EmptyStateScreen(
        modifier = modifier,
        title = title,
        description = description,
        image = R.drawable.ic_no_search_data_available
    )
}

@Preview
@Composable
fun ErrorScreen2Prev() {
    ErrorScreen()
}



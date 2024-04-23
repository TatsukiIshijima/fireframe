package com.tatsuki.fireframe.feature.mediaselector.ui

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerScope
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun MediaSelectorTabPager(
    tabNames: List<String>,
    modifier: Modifier = Modifier,
    pagerState: PagerState = rememberPagerState { tabNames.size },
    pageContent: @Composable PagerScope.(page: Int) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        MediaSelectorTab(
            tabNames = tabNames,
            selectedTabIndex = pagerState.currentPage,
            modifier = Modifier.fillMaxWidth(),
            onTabClick = { index ->
                Log.d("MediaSelectorScreen", "onTabClick: $index")
                coroutineScope.launch {
                    pagerState.animateScrollToPage(index)
                }
            },
        )
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f),
        ) { pageIndex ->
            pageContent(pageIndex)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
private fun MediaSelectorTabPagerPreview() {
    MediaSelectorTabPager(
        tabNames = listOf("Tab1", "Tab2", "Tab3"),
        pageContent = { page ->
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = "Page $page",
                    modifier = Modifier.align(Alignment.Center),
                )
            }
        },
    )
}

package com.tatsuki.fireframe.feature.mediaselector.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun MediaSelectorTab(
    tabNames: List<String>,
    pagerState: PagerState,
    modifier: Modifier = Modifier,
) {
    val coroutineScope = rememberCoroutineScope()
    ScrollableTabRow(
        selectedTabIndex = pagerState.currentPage,
        modifier = modifier,
        divider = {},
    ) {
        tabNames.forEachIndexed { index, name ->
            Tab(
                text = {
                    Text(name)
                },
                selected = pagerState.currentPage == index,
                onClick = {
                    coroutineScope.launch {
                        // animateScrollToPage call on a parent component causes recomposition.
                        // Therefore, by calling animateScrollToPage on the Tab component, other components will not be recomposition.
                        pagerState.animateScrollToPage(index)
                    }
                },
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
private fun MediaSelectorTabPreview() {
    val tabs = listOf("Tab1", "Tab2", "Tab3")
    MediaSelectorTab(
        tabNames = tabs,
        pagerState = rememberPagerState { tabs.size },
    )
}

package com.tatsuki.fireframe.core.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerScope
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalAutoLoopPager(
    pageCount: Int,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    pageSize: PageSize = PageSize.Fill,
    beyondBoundsPageCount: Int = PagerDefaults.BeyondBoundsPageCount,
    pageSpacing: Dp = 0.dp,
    userScrollEnabled: Boolean = true,
    delayMills: Long = 1000,
    pageContent: @Composable PagerScope.(page: Int) -> Unit,
) {
    val pagerState = rememberPagerState(
        pageCount = { pageCount },
    )
    val coroutineScope = rememberCoroutineScope()
    val animateScrollToPage = { state: PagerState, page: Int ->
        coroutineScope.launch {
            if (state.isScrollInProgress) {
                return@launch
            }
            state.animateScrollToPage(page)
        }
    }

    LaunchedEffect(key1 = pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            delay(delayMills)
            animateScrollToPage(pagerState, page + 1)

            if (page == pagerState.pageCount - 1) {
                pagerState.animateScrollToPage(0)
            }
        }
    }

    HorizontalPager(
        state = pagerState,
        modifier = modifier,
        contentPadding = contentPadding,
        pageSize = pageSize,
        beyondBoundsPageCount = beyondBoundsPageCount,
        pageSpacing = pageSpacing,
        userScrollEnabled = userScrollEnabled,
    ) { pagerScope ->
        pageContent(pagerScope)
    }
}
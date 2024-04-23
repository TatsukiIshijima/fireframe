package com.tatsuki.fireframe.feature.mediaselector.ui

import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
internal fun MediaSelectorTab(
    tabNames: List<String>,
    selectedTabIndex: Int,
    modifier: Modifier = Modifier,
    onTabClick: (Int) -> Unit = {},
) {
    TabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = modifier,
    ) {
        tabNames.forEachIndexed { index, name ->
            Tab(
                text = {
                    Text(name)
                },
                selected = selectedTabIndex == index,
                onClick = {
                    onTabClick(index)
                },
            )
        }
    }
}

@Preview
@Composable
private fun MediaSelectorTabPreview() {
    MediaSelectorTab(
        tabNames = listOf("Tab1", "Tab2", "Tab3"),
        selectedTabIndex = 0,
    )
}

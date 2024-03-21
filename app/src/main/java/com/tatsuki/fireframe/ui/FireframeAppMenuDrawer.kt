package com.tatsuki.fireframe.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.tatsuki.fireframe.R

@Composable
fun FireframeAppMenuDrawer(
    onClickMenuItem: (menuItem: MenuItem) -> Unit,
) {
    ModalDrawerSheet {
        Box(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = stringResource(id = R.string.drawer_title))
        }
        MenuItem.entries.forEach { menuItem ->
            HorizontalDivider()
            NavigationDrawerItem(
                label = {
                    Text(text = stringResource(id = menuItem.titleResId))
                },
                selected = false,
                onClick = {
                    onClickMenuItem(menuItem)
                },
            )
        }
    }
}

enum class MenuItem(
    val index: Int,
    @StringRes val titleResId: Int,
) {
    MENU1(0, R.string.drawer_menu_item1),
    MENU2(1, R.string.drawer_menu_item2),
    ;
}
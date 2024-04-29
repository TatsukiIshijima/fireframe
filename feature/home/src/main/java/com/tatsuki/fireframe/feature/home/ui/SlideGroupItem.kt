package com.tatsuki.fireframe.feature.home.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tatsuki.fireframe.core.model.SlideGroup

@Composable
internal fun SlideGroupItem(
    slideGroup: SlideGroup,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onSelectGroup: (SlideGroup) -> Unit = {},
    onOpenGroup: (SlideGroup) -> Unit = {},
    onDeleteGroup: (SlideGroup) -> Unit = {},
) {
    Card(
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onSelectGroup(slideGroup) }
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (isSelected) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Check",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(
                text = slideGroup.groupName,
                modifier = Modifier.weight(1f),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "確認",
                modifier = Modifier
                    .clickable { onOpenGroup(slideGroup) }
                    .padding(4.dp),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
            )
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(
                onClick = { onDeleteGroup(slideGroup) },
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                )
            }
        }
    }
}

@Preview
@Composable
private fun SlideGroupItemSelectedPreview() {
    SlideGroupItem(
        slideGroup = SlideGroup.fake(),
        isSelected = true,
    )
}

@Preview
@Composable
private fun SlideGroupItemUnselectedPreview() {
    SlideGroupItem(
        slideGroup = SlideGroup.fake(),
        isSelected = false,
    )
}

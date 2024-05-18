package com.tatsuki.fireframe.feature.home.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tatsuki.fireframe.feature.home.model.SourceType

@Composable
internal fun SourceTypeItem(
    sourceType: SourceType,
    contentDescription: String,
    enable: Boolean,
    modifier: Modifier = Modifier,
    onClick: (SourceType) -> Unit = { _ -> },
) {
    Card(
        modifier = modifier,
    ) {
        val clickableModifier = if (enable) {
            Modifier
                .fillMaxWidth()
                .clickable { onClick(sourceType) }
        } else {
            Modifier.fillMaxWidth()
        }
        Row(
            modifier = clickableModifier
                .padding(
                    horizontal = 12.dp,
                    vertical = 20.dp,
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(id = sourceType.iconResourceId),
                contentDescription = contentDescription,
                tint = if (enable) {
                    MaterialTheme.colorScheme.onPrimaryContainer
                } else {
                    MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
                },
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = stringResource(id = sourceType.nameResourceId),
                color = if (enable) {
                    MaterialTheme.colorScheme.onPrimaryContainer
                } else {
                    MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
                },
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@Preview
@Composable
fun SourceTypeItemEnablePreview() {
    SourceTypeItem(
        sourceType = SourceType.LocalStorage(),
        contentDescription = "LocalStorage",
        enable = true,
    )
}

@Preview
@Composable
fun SourceTypeItemDisablePreview() {
    SourceTypeItem(
        sourceType = SourceType.LocalStorage(),
        contentDescription = "LocalStorage",
        enable = false,
    )
}

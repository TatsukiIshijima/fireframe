package com.tatsuki.fireframe.core.designsystem.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.tatsuki.fireframe.core.designsystem.model.FakeSelectableItem
import com.tatsuki.fireframe.core.designsystem.model.SelectableItem

@Composable
fun RadioButtonsDialog(
    title: String,
    items: List<SelectableItem>,
    selectedItem: SelectableItem,
    onSelectItem: (SelectableItem) -> Unit,
    cancelButtonText: String,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
    ) {
        RadioButtonsDialogContent(
            title = title,
            items = items,
            selectedItem = selectedItem,
            onSelectItem = onSelectItem,
            cancelButtonText = cancelButtonText,
            onDismissRequest = onDismissRequest,
            modifier = modifier,
        )
    }
}

@Composable
private fun RadioButtonsDialogContent(
    title: String,
    items: List<SelectableItem>,
    selectedItem: SelectableItem,
    onSelectItem: (SelectableItem) -> Unit,
    cancelButtonText: String,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    DialogBackground(
        content = {
            Column(
                modifier = modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.height(16.dp))
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    items(items.size) {
                        val item = items[it]
                        RadioButtonRow(
                            selectableItem = item,
                            selected = selectedItem == item,
                            onClick = onSelectItem,
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        text = cancelButtonText,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .clickable {
                                onDismissRequest()
                            }
                            .padding(8.dp),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }
        },
    )
}

@Composable
private fun RadioButtonRow(
    selectableItem: SelectableItem,
    selected: Boolean,
    onClick: (SelectableItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RadioButton(
            selected = selected,
            onClick = {
                onClick(selectableItem)
            },
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = stringResource(id = selectableItem.nameResource),
            modifier = Modifier
                .clickable {
                    onClick(selectableItem)
                }
                .padding(4.dp),
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Preview
@Composable
private fun RadioButtonsDialogContentPreview() {
    val items = (0..10).map {
        FakeSelectableItem(
            nameResource = android.R.string.ok,
        )
    }
    RadioButtonsDialogContent(
        title = "タイトル",
        items = items,
        selectedItem = items.first(),
        onSelectItem = {},
        cancelButtonText = "キャンセル",
        onDismissRequest = {},
    )
}

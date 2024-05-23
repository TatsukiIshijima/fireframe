package com.tatsuki.fireframe.core.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    positiveButtonText: String,
    negativeButtonText: String,
    onDismissRequest: () -> Unit,
    onSelectItem: (SelectableItem) -> Unit,
    onDone: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
    ) {
        RadioButtonsDialogContent(
            title = title,
            items = items,
            positiveButtonText = positiveButtonText,
            negativeButtonText = negativeButtonText,
            onDismissRequest = onDismissRequest,
            onSelectItem = onSelectItem,
            onDone = onDone,
            modifier = modifier,
        )
    }
}

@Composable
private fun RadioButtonsDialogContent(
    title: String,
    items: List<SelectableItem>,
    positiveButtonText: String,
    negativeButtonText: String,
    onDismissRequest: () -> Unit,
    onSelectItem: (SelectableItem) -> Unit,
    onDone: () -> Unit,
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
                    modifier = modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    items(items.size) {
                        val item = items[it]
                        RadioButtonRow(
                            selectableItem = item,
                            selected = false,
                            onClick = onSelectItem,
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = onDismissRequest,
                    ) {
                        Text(
                            text = negativeButtonText,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                    Spacer(modifier = Modifier.widthIn(16.dp))
                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = onDone,
                    ) {
                        Text(
                            text = positiveButtonText,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
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
        Box(
            modifier = Modifier.weight(1f),
        ) {
            RadioButton(
                selected = selected,
                onClick = {
                    onClick(selectableItem)
                },
                modifier = Modifier.align(Alignment.BottomEnd),
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Box(
            modifier = Modifier.weight(1f),
        ) {
            Text(
                text = selectableItem.name,
                modifier = Modifier.align(Alignment.CenterStart),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@Preview
@Composable
private fun RadioButtonsDialogContentPreview() {
    RadioButtonsDialogContent(
        title = "タイトル",
        items = listOf(
            FakeSelectableItem("項目1"),
            FakeSelectableItem("項目2"),
            FakeSelectableItem("項目3"),
            FakeSelectableItem("項目4"),
            FakeSelectableItem("項目5"),
            FakeSelectableItem("項目6"),
            FakeSelectableItem("項目7"),
            FakeSelectableItem("項目8"),
            FakeSelectableItem("項目9"),
            FakeSelectableItem("項目10"),
            FakeSelectableItem("項目11"),
            FakeSelectableItem("項目12"),
            FakeSelectableItem("項目13"),
            FakeSelectableItem("項目14"),
            FakeSelectableItem("項目15"),
            FakeSelectableItem("項目16"),
            FakeSelectableItem("項目17"),
            FakeSelectableItem("項目18"),
            FakeSelectableItem("項目19"),
            FakeSelectableItem("項目20"),
        ),
        positiveButtonText = "決定",
        negativeButtonText = "キャンセル",
        onDismissRequest = {},
        onSelectItem = {},
        onDone = {},
    )
}

package com.tatsuki.fireframe.core.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun ConfirmDialog(
    title: String,
    positiveButtonText: String,
    negativeButtonText: String,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
    ) {
        ConfirmDialogContent(
            title = title,
            positiveButtonText = positiveButtonText,
            negativeButtonText = negativeButtonText,
            onDismissRequest = onDismissRequest,
            onConfirmation = onConfirmation,
            modifier = modifier,
        )
    }
}

@Composable
private fun ConfirmDialogContent(
    title: String,
    positiveButtonText: String,
    negativeButtonText: String,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
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
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = onDismissRequest,
                    ) {
                        Text(text = negativeButtonText)
                    }
                    Spacer(modifier = Modifier.widthIn(16.dp))
                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = onConfirmation,
                    ) {
                        Text(text = positiveButtonText)
                    }
                }
            }
        },
    )
}

@Preview
@Composable
private fun ConfirmDialogContentPreview() {
    ConfirmDialogContent(
        title = "削除しますか？",
        positiveButtonText = "OK",
        negativeButtonText = "キャンセル",
        onDismissRequest = {},
        onConfirmation = {},
    )
}

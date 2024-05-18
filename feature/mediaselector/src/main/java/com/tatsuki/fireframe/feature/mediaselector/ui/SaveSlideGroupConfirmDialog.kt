package com.tatsuki.fireframe.feature.mediaselector.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text2.BasicTextField2
import androidx.compose.foundation.text2.input.TextFieldLineLimits
import androidx.compose.foundation.text2.input.delete
import androidx.compose.foundation.text2.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.tatsuki.fireframe.core.designsystem.component.DialogBackground
import com.tatsuki.fireframe.feature.mediaselector.R

@Composable
fun SaveSlideGroupConfirmDialog(
    onDismissRequest: () -> Unit,
    onSaveRequest: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        SaveSlideGroupConfirmDialogContent(
            onDismissRequest = onDismissRequest,
            onSaveRequest = onSaveRequest,
            modifier = modifier,
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun SaveSlideGroupConfirmDialogContent(
    onDismissRequest: () -> Unit,
    onSaveRequest: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val textFieldState = rememberTextFieldState()
    DialogBackground(
        content = {
            Column(
                modifier = modifier
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(id = R.string.save_slide_group_confirm_dialog_message),
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = MaterialTheme.colorScheme.surface,
                            shape = RoundedCornerShape(4.dp),
                        )
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.surface,
                            shape = RoundedCornerShape(4.dp),
                        ),
                ) {
                    BasicTextField2(
                        state = textFieldState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        inputTransformation = { originalValue, valueWithChanges ->
                            if (valueWithChanges.length > MAX_GROUP_NAME_LENGTH) {
                                valueWithChanges.delete(MAX_GROUP_NAME_LENGTH, valueWithChanges.length)
                            }
                        },
                        textStyle = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onSurface,
                        ),
                        lineLimits = TextFieldLineLimits.SingleLine,
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier,
                ) {
                    Button(
                        onClick = onDismissRequest,
                        modifier = Modifier
                            .weight(1f),
                    ) {
                        Text(
                            text = stringResource(id = R.string.cancel_button),
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                    Spacer(modifier = Modifier.widthIn(16.dp))
                    Button(
                        onClick = {
                            onSaveRequest(textFieldState.text.toString())
                            onDismissRequest()
                        },
                        modifier = Modifier.weight(1f),
                        enabled = textFieldState.text.isNotEmpty(),
                    ) {
                        Text(
                            text = stringResource(id = R.string.save_button),
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                }
            }
        },
    )
}

private const val MAX_GROUP_NAME_LENGTH = 15

@Preview
@Composable
private fun SaveSlideGroupConfirmDialogContentPreview() {
    SaveSlideGroupConfirmDialogContent(
        onDismissRequest = { },
        onSaveRequest = { },
    )
}

@file:OptIn(ExperimentalFoundationApi::class)

package com.cvelez.mvi.presentation.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.AlertDialog
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.cvelez.mvi.base.TimeCapsule
import com.cvelez.mvi.utils.debugInputPointer
import com.cvelez.mvi.R
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    viewModel: MainViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            Toolbar(viewModel.timeMachine)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.changeAddDialogState(true)},
                modifier = Modifier
                    .padding(2.dp)
            ) {
                Icon(imageVector = Icons.Default.AddCircle, contentDescription = null)
            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        drawerGesturesEnabled = false,
        bottomBar = {
            BottomAppBar(
                cutoutShape = MaterialTheme.shapes.small.copy(
                    CornerSize(percent = 50)
                )
            ) {
            }
        }
    ) {
        Column {
            when {
                state.isLoading -> ContentWithProgress()
                state.data.isNotEmpty() -> MainScreenContent(
                    state.data,
                    state.isShowAddDialog,
                    onItemCheckedChanged = { index, isChecked ->
                        viewModel.onItemCheckedChanged(index = index, isChecked = isChecked)
                    },
                    onDeleteButtonClick = { viewModel.deleteItem(index = it) },
                    onDialogDismissClick = { viewModel.changeAddDialogState(false) },
                    onDialogOkClick = { text -> viewModel.addNewItem(text) },
                )
            }
        }
    }
}

@Composable
private fun Toolbar(timeMachine: TimeCapsule<MainScreenState>) {
    Row(
        modifier = Modifier
            .height(56.dp)
            .background(color = Color.Blue)
            .fillMaxWidth()
            .debugInputPointer(LocalContext.current, timeMachine),
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterVertically)
                .padding(start = 16.dp),
            text = stringResource(id = R.string.main_screen_title),
            color = Color.White,
            fontSize = 18.sp,
            style = TextStyle(textAlign = TextAlign.Center, fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Start,
        )
    }
}

@Composable
private fun MainScreenContent(
    todos: List<MainScreenItem>,
    isShowAddDialog: Boolean,
    onItemCheckedChanged: (Int, Boolean) -> Unit,
    onDeleteButtonClick: (Int) -> Unit,
    onDialogDismissClick: () -> Unit,
    onDialogOkClick: (String) -> Unit,
) {
    val pending = todos.filterIsInstance<MainScreenItem.MainScreenTodoItem>().filter { !it.isChecked }
    val completed = todos.filterIsInstance<MainScreenItem.MainScreenTodoItem>().filter { it.isChecked }

    Box {
        LazyColumn {
            if (pending.isNotEmpty()) {
                Log.i("pendingtest" ,"$onItemCheckedChanged $pending")
                section("Pending Tasks") {
                    itemsIndexed(pending) { index, item ->
                        TodoListItem(item, onItemCheckedChanged, index, onDeleteButtonClick)
                    }
                }
            }

            if (completed.isNotEmpty()) {
                Log.i("completedtest" ,"$onItemCheckedChanged $completed")
                section("Completed Tasks") {
                    itemsIndexed(completed) { index, item ->
                        TodoListItem(item, onItemCheckedChanged, index, onDeleteButtonClick)
                    }
                }
            }

            item {
                if (isShowAddDialog) {
                    AddNewItemDialog(onDialogDismissClick, onDialogOkClick)
                }
            }
        }
    }
}

fun LazyListScope.section(header: String, content: LazyListScope.() -> Unit) {
    item {
        Text(
            text = header,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray)
                .padding(16.dp),
            color = Color.White
        )
    }
    content()
}

@Composable
private fun TodoListItem(
    item: MainScreenItem.MainScreenTodoItem,
    onItemCheckedChanged: (Int, Boolean) -> Unit,
    index: Int,
    onDeleteButtonClick: (Int) -> Unit
) {
    val checked = remember { mutableStateOf(item.isChecked) }
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Checkbox(
                colors = CheckboxDefaults.colors(Color.Green),
                checked = checked.value,
                onCheckedChange = {
                    onItemCheckedChanged(index, !checked.value)
                }
            )
            Text(
                text = item.text,
                modifier = Modifier.padding(start = 16.dp),
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 14.sp
                )
            )
        }
        IconButton(
            onClick = { onDeleteButtonClick(index) }
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(R.drawable.delete_svgrepo_com)
                    .build(),
                modifier = Modifier.size(40.dp),
                contentScale = ContentScale.Fit,
                contentDescription = "Btn Delete"
            )
        }
    }
}

@Composable
private fun AddNewItemDialog(
    onDialogDismissClick: () -> Unit,
    onDialogOkClick: (String) -> Unit,
) {
    var text by remember { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = onDialogDismissClick,
        text = {
            TextField(
                value = text,
                onValueChange = { newText ->
                    text = newText
                },
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Blue,
                    disabledIndicatorColor = Color.Blue,
                    unfocusedIndicatorColor = Color.Blue,
                    backgroundColor = Color.LightGray,
                )
            )
        },
        confirmButton = {
            Button(
                onClick = { onDialogOkClick(text) },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue)
            ) {
                Text(text = "Ok", style = TextStyle(color = Color.White, fontSize = 12.sp))
            }
        },
        dismissButton = {
            Button(
                onClick = onDialogDismissClick,
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue)
            ) {
                Text(text = "Cancel", style = TextStyle(color = Color.White, fontSize = 12.sp))
            }
        }
    )
}

@Composable
private fun ContentWithProgress() {
    Surface(color = Color.LightGray) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}

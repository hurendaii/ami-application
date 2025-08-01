package com.hurendaii.ami_application.ui

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*
import androidx.compose.material.icons.filled.ArrowBack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksScreen(
    friendName: String,
    onReturnToHealth: () -> Unit
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val taskList = remember { mutableStateListOf<TaskItem>() }

    var showForm by remember { mutableStateOf(false) }
    var taskText by remember { mutableStateOf("") }
    var recurring by remember { mutableStateOf(false) }
    var recurrence by remember { mutableStateOf("Daily") }
    var showRecurrenceDropdown by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()
    val removalMap = remember { mutableStateMapOf<Long, Job>() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tasks") },
                navigationIcon = {
                    IconButton(onClick = onReturnToHealth) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { showForm = !showForm }) {
                        Icon(Icons.Default.Add, contentDescription = "Add Task")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            if (showForm) {
                TaskForm(
                    taskText = taskText,
                    onTaskTextChange = { if (it.length <= 60) taskText = it },
                    recurring = recurring,
                    onRecurringChange = {
                        recurring = it
                        if (!it) showRecurrenceDropdown = false
                    },
                    showRecurrenceDropdown = showRecurrenceDropdown,
                    onRecurrenceDropdownToggle = { showRecurrenceDropdown = it },
                    recurrence = recurrence,
                    onRecurrenceSelected = { recurrence = it },
                    onSubmit = {
                        if (taskText.isNotBlank()) {
                            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                            taskList.add(
                                TaskItem(
                                    task = taskText.trim(),
                                    date = sdf.format(Date()),
                                    recurring = recurring,
                                    recurrence = recurrence
                                )
                            )
                            taskText = ""
                            recurring = false
                            showForm = false
                            showRecurrenceDropdown = false
                            Toast.makeText(context, "Task Added!", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "Task cannot be empty", Toast.LENGTH_SHORT).show()
                        }
                    }
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                taskList.forEach { task ->
                    TaskCard(
                        task = task,
                        onCheckedChange = { checked ->
                            task.isCompleted = checked
                            if (checked) {
                                val job = coroutineScope.launch {
                                    delay(2000)
                                    taskList.remove(task)
                                    removalMap.remove(task.id)
                                }
                                removalMap[task.id] = job
                            } else {
                                removalMap[task.id]?.cancel()
                                removalMap.remove(task.id)
                            }
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun TaskForm(
    taskText: String,
    onTaskTextChange: (String) -> Unit,
    recurring: Boolean,
    onRecurringChange: (Boolean) -> Unit,
    showRecurrenceDropdown: Boolean,
    onRecurrenceDropdownToggle: (Boolean) -> Unit,
    recurrence: String,
    onRecurrenceSelected: (String) -> Unit,
    onSubmit: () -> Unit
) {
    Column(modifier = Modifier.padding(bottom = 16.dp)) {
        OutlinedTextField(
            value = taskText,
            onValueChange = onTaskTextChange,
            label = { Text("Task (max 60 chars)") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Checkbox(checked = recurring, onCheckedChange = onRecurringChange)
            Text("Recurring Task")
        }

        if (recurring) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Button(onClick = { onRecurrenceDropdownToggle(!showRecurrenceDropdown) }) {
                    Text(recurrence)
                }
                DropdownMenu(
                    expanded = showRecurrenceDropdown,
                    onDismissRequest = { onRecurrenceDropdownToggle(false) }
                ) {
                    listOf("Twice a day", "Daily", "Weekly", "Monthly").forEach {
                        DropdownMenuItem(
                            text = { Text(it) },
                            onClick = {
                                onRecurrenceSelected(it)
                                onRecurrenceDropdownToggle(false)
                            }
                        )
                    }
                }
            }
        }

        Button(
            onClick = onSubmit,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Submit Task")
        }
    }
}

@Composable
fun TaskCard(task: TaskItem, onCheckedChange: (Boolean) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text("📅 ${task.date}")
                Text("📝 ${task.task}")
            }
            Checkbox(
                checked = task.isCompleted,
                onCheckedChange = onCheckedChange
            )
        }
    }
}

data class TaskItem(
    val id: Long = System.currentTimeMillis(),
    val task: String,
    val date: String,
    val recurring: Boolean,
    val recurrence: String,
    var isCompleted: Boolean = false
)

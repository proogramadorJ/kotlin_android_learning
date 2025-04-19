package com.pedrodev.inmemorytodolist

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pedrodev.inmemorytodolist.model.ItemTodo
import com.pedrodev.inmemorytodolist.ui.theme.InMemoryTodoListTheme
import com.pedrodev.inmemorytodolist.util.DateUtil
import java.time.LocalDate

class MainActivity : ComponentActivity() {


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()

        var items = listOf(
            ItemTodo(1, "Item 1", "Item 1 Detail ", LocalDate.now(), false),
            ItemTodo(2, "Item 2", "Item 2 Detail ", LocalDate.now(), false),
            ItemTodo(3, "Item 3", "Item 3 Detail ", LocalDate.now(), true),
            ItemTodo(4, "Item 4", "Item 4 Detail ", LocalDate.now(), true),
            ItemTodo(5, "Item 5", "Item 5 Detail ", LocalDate.now(), false)
        )

        setContent {
            InMemoryTodoListTheme {
                // Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                MainContent(items)
                // }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainContent(items: List<ItemTodo>) {
    Column(
        modifier = Modifier.padding(all = 12.dp)
    ) {
        Text("Todo List", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold))
        Spacer(modifier = Modifier.height(15.dp))
        MainListComposable(items)
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainListComposable(notes: List<ItemTodo>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(notes) { note ->
            ItemComposable(note) { println("Clicked on " + note.title) }
            HorizontalDivider()
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ItemComposable(item: ItemTodo, clickOnItem: (note: ItemTodo) -> Unit) {

    val formatedDate = DateUtil.formatDate(item.createdAt)
    val backGroundButtonColor = if (item.isDone) Color(0xFF28C76F) else Color(0xFFFF9800)
    val textButton = if (item.isDone) "Completed" else "Pedding"

    Box(
        modifier = Modifier.clickable { clickOnItem(item) }
    ) {
        Row {
            Column {
                Text(item.title, style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold))
                Text(formatedDate, style = TextStyle(fontSize = 10.sp))
            }

            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = backGroundButtonColor
                )
            ) {
                Text(textButton)
            }
        }
    }
}

@Composable
fun ShowItemDialog() {
    Text("Hello")
}

@Preview
@Composable
fun ShowItemDialogPreview() {
    ShowItemDialog()
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

    var items by remember {
        mutableStateOf(
            listOf(
                ItemTodo(1, "Item 1", "Item 1 Detail ", LocalDate.now(), false),
                ItemTodo(2, "Item 2", "Item 2 Detail ", LocalDate.now(), false),
                ItemTodo(3, "Item 3", "Item 3 Detail ", LocalDate.now(), true),
                ItemTodo(4, "Item 4", "Item 4 Detail ", LocalDate.now(), true),
                ItemTodo(5, "Item 5", "Item 5 Detail ", LocalDate.now(), false)
            )
        )
    }

    InMemoryTodoListTheme {
        MainContent(items)
    }
}
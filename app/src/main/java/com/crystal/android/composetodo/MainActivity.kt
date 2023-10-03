package com.crystal.android.composetodo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.crystal.android.composetodo.ui.theme.ComposeTodoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTodoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TopLevel()
                }
            }
        }
    }
}


@Composable
fun TopLevel() {
    val (text, setText) = remember { mutableStateOf("") }
    val toDoList = remember { mutableStateListOf<ToDoData>() }

    // 단계 4: `onSubmit`, `onEdit`, `onToggle`, `onDelete`를
    // 만들어 `ToDo`에 연결합니다.

    Scaffold {
        Column {
            ToDoInput(text, setText, {})
            // 단계 3: `LazyColumn`으로 `toDoList`를 표시합시다.
            // `key`를 `toDoData`의 `key`를 사용합니다.
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeTodoTheme {
        TopLevel()
    }
}


@Composable
fun ToDoInput(
    text: String,
    onTextChange: (String) -> Unit,
    onSubmit: (String) -> Unit
) {
    Row(modifier = Modifier.padding(8.dp)) {
        OutlinedTextField(
            value = text,
            onValueChange = onTextChange,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.size(8.dp))
        Button(onClick = {
            onSubmit(text)
        }) {
            Text("입력")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ToDoInputPreview() {
    ComposeTodoTheme {
        ToDoInput("테스트", {}, {})
    }
}

@Composable
fun ToDo(
    toDoData: ToDoData,
    onEdit: (key: Int, text: String) -> Unit = { _, _ -> },
    onToggle: (key: Int, checked: Boolean) -> Unit = { _, _ -> },
    onDelete: (key: Int) -> Unit = {}
) {
    var isEditing by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier.padding(4.dp),
        elevation = 8.dp
    ) {

        // 단계 1: `Row`를 만들고 `toDoData.text`를 출력하고
        // 완료를 체크하는 체크박스, 수정 버튼, 삭제 버튼을 만드세요.

        // 단계 2: `Crossfade`를 통해 `isEditing`을 따라 다른
        // UI를 보여줍니다. `OutlinedTextField`와 `Button을
        // 넣어봅시다.
    }
}

@Preview(showBackground = true)
@Composable
fun ToDoPreview() {
    ComposeTodoTheme {
        ToDo(ToDoData(1, "nice", true))
    }
}

data class ToDoData(
    val key: Int,
    val text: String,
    val done: Boolean = false
)
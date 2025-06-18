package compose.performance.benchmark

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat.RECEIVER_NOT_EXPORTED
import androidx.core.content.ContextCompat.registerReceiver
import coil.compose.AsyncImage
import coil.request.ImageRequest
import compose.performance.benchmark.demo.AccountSettings
import compose.performance.benchmark.demo.AdvancedSettings
import compose.performance.benchmark.demo.DataUsage
import compose.performance.benchmark.demo.FooItem
import compose.performance.benchmark.demo.FooView
import compose.performance.benchmark.demo.MessageBubble
import compose.performance.benchmark.demo.NotificationSettings
import compose.performance.benchmark.demo.PrivacySettings
import compose.performance.benchmark.demo.ThemeSettings
import compose.performance.benchmark.demo.getFooList
import compose.performance.benchmark.demo.getMessages
import java.net.URL
import java.time.LocalDateTime

/**
 * @author i.bekmansurov
 */
@Composable
internal fun DemoScreen() {
    PI1_Dynamic()
    PI2()
    PI3()
    PI3_Dynamic()
    PI4()
    PI5()
    PI5_Dynamic()
    PI6_Dynamic()
    PI7()
}

@Composable
fun PI1_Dynamic() {
    val result = remember { mutableStateOf<String?>(null) }
    val isLoading = remember { mutableStateOf<Boolean?>(null) }
    Column {
        Button(
            onClick = {
                isLoading.value = true
                result.value = URL("https://api/messenger/sendMessage?dialogId=123").readText()
                isLoading.value = false
            }
        ) {
            if (isLoading.value == true) {
                CircularProgressIndicator()
            } else {
                Text("Send message")
            }
        }
    }
}

@Composable
fun PI2() {
    AsyncImage(
        contentDescription = null,
        model = ImageRequest.Builder(LocalContext.current)
            .data("https://photographylife.com/wp-content/uploads/2023/05/Nikon-Z8-Official-Samples-00002.jpg")
            .build(),
    )
}

lateinit var topLevelObject: List<Int>

@Composable
fun PI3() {
    val isLoading = remember { mutableStateOf(true) }
    Box {
        val buttonTitle = "Click Me!"
        Button(onClick = { isLoading.value = false }) {
            Text(buttonTitle)
        }
        if (isLoading.value) {
            CircularProgressIndicator()
        } else {
            topLevelObject = listOf(1, 2, 3)
            Column {
                Text(topLevelObject[0].toString())
                Text(topLevelObject[1].toString())
                Text(topLevelObject[2].toString())
            }
        }
    }
}

@Composable
fun PI3_Dynamic() {
    val isOnline = remember { mutableStateOf(true) }
    val context = LocalContext.current
    DisposableEffect(Unit) {
        val receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                isOnline.value = intent.getBooleanExtra("is_online", true)
            }
        }
        registerReceiver(context, receiver, IntentFilter("NETWORK_STATUS_CHANGED"), RECEIVER_NOT_EXPORTED)
        onDispose {}
    }
    if (!isOnline.value) {
        Box {
            Text("No internet connection")
        }
    }
}

data class PI4State(
    var title: String = "",
    val dates: List<Date> = listOf(),
    val onDateClicked: (Date) -> Unit = {},
) {
    class Date(
        var day: Int = 23,
        var month: String = "May",
    )
}

@Composable
fun PI4(
    state: PI4State = PI4State(),
    items: List<String> = emptyList(),
) {
    Column {
        Text(state.title)
        state.dates.forEach { date ->
            Text(
                text = buildString { append(date.day, date.month) },
                modifier = Modifier.clickable { state.onDateClicked(date) },
            )
        }
        items.forEach { item ->
            Text(item)
        }
    }
}

@Composable
fun PI5() {
    LazyColumn {
        items(getFooList(), key = { it.id }, contentType = { "FooItem" }) { item ->
            val localDateTime = LocalDateTime.parse("18.06.2025")
            FooItem(foo = item, localDateTime = localDateTime)
        }
    }
}

@Composable
fun PI5_Dynamic() {
    Column {
        getMessages().forEach { message ->
            MessageBubble(message)
        }
    }
}

@Composable
fun PI6_Dynamic() {
    val isLoading = remember { mutableStateOf(true) }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        if (isLoading.value) {
            CircularProgressIndicator()
        } else {
            Column {
                AccountSettings()
                Column {
                    NotificationSettings()
                    Column {
                        PrivacySettings()
                        Row {
                            ThemeSettings()
                            Row {
                                DataUsage()
                                Row {
                                    AdvancedSettings()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PI7() {
    LazyColumn {
        items(100, key = { it }, contentType = { "FooViewItem" }) { index ->
            AndroidView(
                modifier = Modifier.fillMaxSize(),
                factory = { context -> FooView(context) },
                update = { view -> view.selectedItem = index },
                onReset = { view -> view.clear() },
            )
        }
    }
}

package compose.performance.benchmark.demo

import androidx.compose.runtime.Composable

/**
 * @author i.bekmansurov
 */
internal fun getMessages() = List(1000) { Message() }

internal data class Message(
    val id: Int = 1,
    val content: String = "",
    val sender: String = "",
    val receiver: String = "",
    val timestamp: String = "",
)

@Composable
internal fun MessageBubble(message: Message) {}

package compose.performance.benchmark.demo

import androidx.compose.runtime.Composable
import java.time.LocalDateTime

/**
 * @author i.bekmansurov
 */
internal fun getFooList() = List(100) { Foo(id = it, title = "Title #$it") }

internal data class Foo(
    val id: Int,
    val title: String,
)

@Composable
internal fun FooItem(
    foo: Foo,
    localDateTime: LocalDateTime,
) {}

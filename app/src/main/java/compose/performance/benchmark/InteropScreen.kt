package compose.performance.benchmark

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.util.trace
import androidx.compose.ui.viewinterop.AndroidViewBinding
import compose.performance.benchmark.databinding.FifthViewBinding
import compose.performance.benchmark.databinding.FirstViewBinding
import compose.performance.benchmark.databinding.FourthViewBinding
import compose.performance.benchmark.databinding.SecondViewBinding
import compose.performance.benchmark.databinding.ThirdViewBinding

/**
 * @author i.bekmansurov
 */
@Composable
internal fun InteropScreen() {
    Column {
        `№1_Compose`()
        `№1_View`()
        `№2_Compose`()
        `№2_View`()
        `№3_Compose`()
        `№3_View`()
        `№4_Compose`()
        `№4_View`()
        `№5_Compose`()
        `№5_View`()
    }
}

@Preview
@Composable
internal fun `№1_Compose`() = trace("№1_Compose") {
    Text("Hello, World!")
}

@Preview
@Composable
internal fun `№1_View`() = trace("№1_View") {
    AndroidViewBinding(FirstViewBinding::inflate)
}

@Preview
@Composable
internal fun `№2_Compose`() = trace("№2_Compose") {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Hello, World!")
        Image(painterResource(R.drawable.compose_logo), null)
    }
}

@Preview
@Composable
internal fun `№2_View`() = trace("№2_View") {
    AndroidViewBinding(SecondViewBinding::inflate)
}

@Preview
@Composable
internal fun `№3_Compose`() = trace("№3_Compose") {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Hello, World!")
        Image(painterResource(R.drawable.compose_logo), null)
        Button({}) { Text("Click Me!") }
    }
}

@Preview
@Composable
internal fun `№3_View`() = trace("№3_View") {
    AndroidViewBinding(ThirdViewBinding::inflate)
}

@Preview
@Composable
internal fun `№4_Compose`() = trace("№4_Compose") {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Hello, World!")
        Row {
            Image(painterResource(R.drawable.compose_logo), null)
            Image(painterResource(R.drawable.compose_logo), null)
        }
        Button({}) { Text("Click Me!") }
    }
}

@Preview
@Composable
internal fun `№4_View`() = trace("№4_View") {
    AndroidViewBinding(FourthViewBinding::inflate)
}

@Preview
@Composable
internal fun `№5_Compose`() = trace("№5_Compose") {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Hello, World!")
        Row {
            Image(painterResource(R.drawable.compose_logo), null)
            Image(painterResource(R.drawable.compose_logo), null)
        }
        Row {
            Button({}) { Text("Click Me 1!") }
            Button({}) { Text("Click Me 2!") }
        }
    }
}

@Preview
@Composable
internal fun `№5_View`() = trace("№5_View") {
    AndroidViewBinding(FifthViewBinding::inflate)
}

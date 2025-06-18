package compose.performance.benchmark

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import compose.performance.benchmark.ui.theme.ComposePerformanceBenchmarkTheme

/**
 * @author i.bekmansurov
 */
class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposePerformanceBenchmarkTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    /**
                     * Use only one (necessary) of the functions below at a time
                     */
                    InteropScreen()
                    PerformanceScreen()
                    DemoScreen()
                }
            }
        }
    }
}

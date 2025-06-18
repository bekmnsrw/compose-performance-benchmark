package compose.performance.benchmark

import androidx.benchmark.macro.CompilationMode
import androidx.benchmark.macro.MacrobenchmarkScope
import androidx.benchmark.macro.Metric
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import org.junit.Rule

/**
 * @author i.bekmansurov
 */
abstract class AbstractBenchmark(
    protected val startupMode: StartupMode = StartupMode.HOT,
    protected val iterations: Int = 100,
) {
    @get:Rule
    val rule = MacrobenchmarkRule()

    abstract val metrics: List<Metric>

    open fun MacrobenchmarkScope.setupBlock() {}

    abstract fun MacrobenchmarkScope.measureBlock()

    fun benchmark(compilationMode: CompilationMode) {
        rule.measureRepeated(
            packageName = "compose.performance",
            metrics = metrics,
            compilationMode = compilationMode,
            startupMode = startupMode,
            iterations = iterations,
            setupBlock = { setupBlock() },
            measureBlock = { measureBlock() }
        )
    }
}

fun MacrobenchmarkScope.startActivity() = startActivityAndWait()

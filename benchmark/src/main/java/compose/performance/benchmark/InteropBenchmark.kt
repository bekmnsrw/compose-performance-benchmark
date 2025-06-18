package compose.performance.benchmark

import androidx.benchmark.macro.CompilationMode
import androidx.benchmark.macro.ExperimentalMetricApi
import androidx.benchmark.macro.MacrobenchmarkScope
import androidx.benchmark.macro.Metric
import androidx.benchmark.macro.TraceSectionMetric
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author i.bekmansurov
 */
@LargeTest
@RunWith(AndroidJUnit4::class)
@OptIn(ExperimentalMetricApi::class)
class InteropBenchmark : AbstractBenchmark() {

    @Test
    fun measureInteropPerformance() = benchmark(CompilationMode.Full())

    override val metrics: List<Metric> = listOf(
        TraceSectionMetric("№1_Compose", TraceSectionMetric.Mode.Sum),
        TraceSectionMetric("№1_View", TraceSectionMetric.Mode.Sum),
        TraceSectionMetric("№2_Compose", TraceSectionMetric.Mode.Sum),
        TraceSectionMetric("№2_View", TraceSectionMetric.Mode.Sum),
        TraceSectionMetric("№3_Compose", TraceSectionMetric.Mode.Sum),
        TraceSectionMetric("№3_View", TraceSectionMetric.Mode.Sum),
        TraceSectionMetric("№4_Compose", TraceSectionMetric.Mode.Sum),
        TraceSectionMetric("№4_View", TraceSectionMetric.Mode.Sum),
        TraceSectionMetric("№5_Compose", TraceSectionMetric.Mode.Sum),
        TraceSectionMetric("№5_View", TraceSectionMetric.Mode.Sum),
    )

    override fun MacrobenchmarkScope.measureBlock() {
        pressHome(delayDurationMs = 1_000)
        startActivity()
    }
}

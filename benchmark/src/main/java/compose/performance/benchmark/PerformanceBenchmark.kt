package compose.performance.benchmark

import androidx.benchmark.macro.CompilationMode
import androidx.benchmark.macro.ExperimentalMetricApi
import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.MacrobenchmarkScope
import androidx.benchmark.macro.Metric
import androidx.benchmark.macro.StartupTimingMetric
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
class PerformanceBenchmark : AbstractBenchmark() {

    @Test
    fun measurePluginPerformance() = benchmark(CompilationMode.Full())

    override val metrics: List<Metric> = listOf(
        TraceSectionMetric("PI1_Dynamic_Benchmark", TraceSectionMetric.Mode.Sum),
        TraceSectionMetric("PI2_Benchmark", TraceSectionMetric.Mode.Sum),
        TraceSectionMetric("PI3_Benchmark", TraceSectionMetric.Mode.Sum),
        TraceSectionMetric("PI3_Dynamic_Benchmark", TraceSectionMetric.Mode.Sum),
        TraceSectionMetric("PI4_Benchmark", TraceSectionMetric.Mode.Sum),
        TraceSectionMetric("PI5_Benchmark", TraceSectionMetric.Mode.Sum),
        TraceSectionMetric("PI5_Dynamic_Benchmark", TraceSectionMetric.Mode.Sum),
        TraceSectionMetric("PI6_Dynamic_Benchmark", TraceSectionMetric.Mode.Sum),
        TraceSectionMetric("PI7_Benchmark", TraceSectionMetric.Mode.Sum),
        StartupTimingMetric(),
        FrameTimingMetric(),
    )

    override fun MacrobenchmarkScope.measureBlock() {
        pressHome(delayDurationMs = 1_000)
        startActivity()
    }
}

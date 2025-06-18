package compose.performance.benchmark.demo

import android.content.Context
import android.view.View

/**
 * @author i.bekmansurov
 */
internal class FooView(context: Context) : View(context) {

    var selectedItem: Int? = null

    fun clear() {}
}

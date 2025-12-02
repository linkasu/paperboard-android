package su.linka.linkapaperboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import java.util.Locale

class GridItemController(
    context: Context,
    private val layoutResourceId: Int,
    private val data: Array<String>,
    private val cellSizeProvider: () -> Float,
    private val uppercaseProvider: () -> Boolean
) : ArrayAdapter<String>(context, layoutResourceId, data) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val row = convertView ?: LayoutInflater.from(context).inflate(layoutResourceId, parent, false)
        val textView = row as TextView
        val size = cellSizeProvider()
        textView.textSize = size / context.resources.displayMetrics.scaledDensity

        var symbol = data[position]
        if (uppercaseProvider()) {
            symbol = symbol.uppercase(Locale.getDefault())
        }
        textView.text = symbol
        return row
    }
}

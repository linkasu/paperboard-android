package su.linka.linkapaperboard

import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.GridView
import kotlin.math.min

class GridController(
    private val contextView: View,
    private val preferences: Cookie,
    private val uppercaseProvider: () -> Boolean,
    private val onKey: (String) -> Unit
) : AdapterView.OnItemClickListener, View.OnClickListener {

    private val grid: GridView = contextView.findViewById(R.id.main_grid)
    private var page = 0
    private var text: String = "␣" + contextView.resources.getString(R.string.alphabet)
    private var gridSize: Int = preferences.gridSize
    private val slideButtonsController = SlideButtonsController(contextView)

    init {
        grid.onItemClickListener = this
        contextView.findViewById<View>(R.id.left_btn).setOnClickListener(this)
        contextView.findViewById<View>(R.id.right_btn).setOnClickListener(this)
        draw()
    }

    fun draw() {
        val size = gridSize * gridSize
        val cellSizeProvider = { gridHeight / (gridSize + 2) }
        grid.adapter = GridItemController(
            contextView.context,
            R.layout.grid_button,
            getPageArray(page),
            cellSizeProvider,
            uppercaseProvider
        )
        slideButtonsController.setTextForLeftBtn(
            TextUtils.join(", ", getPageArray(if (page == 0) text.length / size else page - 1))
        )
        slideButtonsController.setTextForRightBtn(
            TextUtils.join(", ", getPageArray(if (page == text.length / size) 0 else page + 1))
        )
    }

    private fun getPageArray(page: Int): Array<String> {
        var size = gridSize
        grid.numColumns = size
        size *= size

        val start = page * size
        val end = min(text.length, size * page + size)
        val localText = text.substring(start, end)
        return localText.map { it.toString() }.toTypedArray()
    }

    fun previosPage() {
        page = if (page == 0) {
            text.length / (gridSize * gridSize)
        } else {
            page - 1
        }
        draw()
    }

    fun nextPage() {
        val size = gridSize * gridSize
        page = if (text.length < size * page + size) {
            0
        } else {
            page + 1
        }
        draw()
    }

    override fun onItemClick(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val index = gridSize * gridSize * page + position
        onKey(text[index].toString().replace("␣", " "))
    }

    val size: Int
        get() = gridSize

    val gridHeight: Float
        get() = grid.measuredHeight.toFloat()

    fun setGridSize(gridSize: Int) {
        this.gridSize = gridSize
        preferences.setGridSize(gridSize)
        page = 0
        draw()
    }

    fun setText(text: String) {
        this.text = text
        draw()
    }

    fun refreshUppercase() {
        draw()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.left_btn -> previosPage()
            R.id.right_btn -> nextPage()
        }
    }
}

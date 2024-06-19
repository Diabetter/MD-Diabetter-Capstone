package com.example.diabetter.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridSpacingItemDecoration(private val spanCount: Int, private val spacing: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view) // Get item position
        val column = position % spanCount // Calculate column index

        if (position < spanCount) {
            outRect.top = spacing // Top margin for the first row
        }
        outRect.left = column * spacing / spanCount // Left margin based on column
        outRect.right = spacing - (column + 1) * spacing / spanCount // Right margin based on column
        outRect.bottom = spacing // Bottom margin for all items
    }
}
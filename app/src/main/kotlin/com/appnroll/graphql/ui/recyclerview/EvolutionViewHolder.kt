package com.appnroll.graphql.ui.recyclerview

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.appnroll.graphql.R
import com.appnroll.graphql.model.Evolution
import com.appnroll.graphql.utils.inflateItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_evolution.view.*


class EvolutionViewHolder(
    parent: ViewGroup,
    override val containerView: View = parent.inflateItem(R.layout.item_evolution)
): RecyclerView.ViewHolder(containerView), LayoutContainer {

    @SuppressLint("SetTextI18n")
    fun bind(item: Evolution) {
        with(containerView) {
            nameText.text = item.name
            numberText.text = item.number
            weightText.text = "Weight: ${item.weight.minimum} - ${item.weight.maximum}"
        }
    }
}
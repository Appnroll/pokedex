package com.appnroll.graphql.ui.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.appnroll.graphql.model.Evolution


class EvolutionAdapter: RecyclerView.Adapter<EvolutionViewHolder>() {

    private var evolutions = emptyList<Evolution>()

    fun updateList(newEvolutions: List<Evolution>) {
        this.evolutions = newEvolutions
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EvolutionViewHolder {
        return EvolutionViewHolder(parent)
    }

    override fun getItemCount() = evolutions.size

    override fun onBindViewHolder(holder: EvolutionViewHolder, position: Int) {
        holder.bind(evolutions[position])
    }
}

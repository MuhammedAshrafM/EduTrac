package com.invade.universities.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.invade.universities.data.model.University
import com.invade.universities.databinding.ItemUniversityBinding

class UniversitiesAdapter(
    private val onItemClick: (University) -> Unit
) :
    ListAdapter<University, UniversitiesAdapter.UniversitiesViewHolder>(Companion) {

    companion object : DiffUtil.ItemCallback<University>() {
        override fun areItemsTheSame(
            oldItem: University,
            newItem: University
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldOrder: University,
            newOrder: University
        ): Boolean {
            return oldOrder == newOrder
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UniversitiesViewHolder {
        val binding =
            ItemUniversityBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return UniversitiesViewHolder(binding)
    }


    override fun onBindViewHolder(holder: UniversitiesViewHolder, position: Int) {
        val item = currentList[position]
        item?.let {
            holder.bind(it)
        }
    }

    inner class UniversitiesViewHolder(
        private val itemBinding: ItemUniversityBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: University) {
            with(item){
                itemBinding.tvName.text = name
                itemBinding.tvName.text = stateProvince
            }

            itemBinding.itemContainer.setOnClickListener {
                onItemClick(item)
            }
        }
     }

}
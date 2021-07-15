package com.alvarengadev.selectitemrcy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.RecyclerView
import com.alvarengadev.selectitemrcy.databinding.ItemBinding

class ItemAdapter : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    private val list = arrayListOf(
        "Java",
        "Kotlin",
        "JavaScript",
        "Ruby"
    )
    private var tracker: SelectionTracker<Long>? = null

    init {
        setHasStableIds(true)
    }

    fun setTracker(tracker: SelectionTracker<Long>) {
        this.tracker = tracker
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemAdapter.ViewHolder {
        val binding = ItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ItemAdapter.ViewHolder,
        position: Int
    ) {
        tracker?.let {
            holder.bind(list[position], it.isSelected(position.toLong()))
        }
    }

    override fun getItemCount(): Int = list.size

    override fun getItemId(position: Int): Long = position.toLong()

    inner class ViewHolder(
        private val binding: ItemBinding
    ) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(
            title: String,
            isActivated: Boolean = false
        ) {
            binding.textView.text = title
            itemView.isActivated = isActivated
        }

        fun getItemDetails(): ItemDetailsLookup.ItemDetails<Long> =
            object : ItemDetailsLookup.ItemDetails<Long>() {
                override fun getPosition(): Int = adapterPosition
                override fun getSelectionKey(): Long = itemId
            }
    }
}
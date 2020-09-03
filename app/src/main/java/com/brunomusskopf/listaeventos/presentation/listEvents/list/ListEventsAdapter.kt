package com.brunomusskopf.listaeventos.presentation.listEvents.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.brunomusskopf.listaeventos.databinding.ListEventsItemBinding
import com.brunomusskopf.listaeventos.presentation.listEvents.model.EventView


class ListEventsAdapter : RecyclerView.Adapter<ListEventsAdapter.Holder>(),
    View.OnClickListener {

    val items: MutableList<EventView> = mutableListOf()
    var onClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ListEventsItemBinding.inflate(layoutInflater, parent, false)
        itemBinding.root.setOnClickListener(this)
        return Holder(itemBinding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun onClick(v: View?) {
        val eventId = v?.tag as Int
        onClickListener?.onItemClick(eventId)
    }

    class Holder(private val binding: ListEventsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(event: EventView) {
            binding.event = event
            binding.executePendingBindings()
            binding.root.tag = event.id
        }
    }

    interface OnItemClickListener {
        fun onItemClick(eventId: Int?)
    }
}
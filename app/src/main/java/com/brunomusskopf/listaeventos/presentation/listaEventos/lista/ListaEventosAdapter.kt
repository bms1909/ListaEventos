package com.brunomusskopf.listaeventos.presentation.listaEventos.lista

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.brunomusskopf.listaeventos.databinding.ItemListaEventosBinding
import com.brunomusskopf.listaeventos.presentation.listaEventos.model.EventoView


class ListaEventosAdapter : RecyclerView.Adapter<ListaEventosAdapter.Holder>(),
    View.OnClickListener {

    val itens: MutableList<EventoView> = mutableListOf()
    var onClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemListaEventosBinding.inflate(layoutInflater, parent, false)
        itemBinding.root.setOnClickListener(this)
        return Holder(itemBinding)
    }

    override fun getItemCount(): Int = itens.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = itens[position]
        holder.bind(item)
    }

    override fun onClick(v: View?) {
        val idEvento = v?.tag as Int
        onClickListener?.onItemClick(idEvento)
    }

    inner class Holder(private val binding: ItemListaEventosBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(evento: EventoView) {
            binding.evento = evento
            binding.executePendingBindings()
            binding.root.tag = evento.id
        }
    }

    interface OnItemClickListener {
        fun onItemClick(idEvento: Int?)
    }
}
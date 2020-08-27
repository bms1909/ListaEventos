package com.brunomusskopf.listaeventos.presentation.listaEventos.lista

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.brunomusskopf.listaeventos.databinding.ItemListaEventosBinding
import com.brunomusskopf.listaeventos.domain.listaEventos.model.Evento


class ListaEventosAdapter : RecyclerView.Adapter<ListaEventosAdapter.Holder>() {

    val itens : MutableList<Evento> = mutableListOf()

    inner class Holder(private val binding: ItemListaEventosBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(evento: Evento?) {
            binding.evento = evento
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding =  ItemListaEventosBinding.inflate(layoutInflater, parent, false)
        return Holder(itemBinding)
    }

    override fun getItemCount(): Int = itens.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = itens[position]
        holder.bind(item)
    }
}
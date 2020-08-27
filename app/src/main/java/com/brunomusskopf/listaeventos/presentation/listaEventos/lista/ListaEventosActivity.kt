package com.brunomusskopf.listaeventos.presentation.listaEventos.lista

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.brunomusskopf.listaeventos.R
import com.brunomusskopf.listaeventos.databinding.ActivityListaEventosBinding
import com.brunomusskopf.listaeventos.domain.listaEventos.model.Evento
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListaEventosActivity : AppCompatActivity() {

    private val viewModel : ListaEventosViewModel by viewModel()

    private val adapter = ListaEventosAdapter()
    private var binding : ActivityListaEventosBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_lista_eventos)
        binding!!.listaEventos.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        viewModel.liveData.observe(this, Observer {
            adapter.itens.clear()
            if (it != null) {
                adapter.itens.addAll(it)
            }
            adapter.notifyDataSetChanged()
        })
    }
}
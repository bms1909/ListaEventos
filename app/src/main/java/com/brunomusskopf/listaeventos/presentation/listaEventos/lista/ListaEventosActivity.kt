package com.brunomusskopf.listaeventos.presentation.listaEventos.lista

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.brunomusskopf.listaeventos.R
import com.brunomusskopf.listaeventos.databinding.ActivityListaEventosBinding
import com.brunomusskopf.listaeventos.presentation.listaEventos.detalhes.DetalhesEventoActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListaEventosActivity : AppCompatActivity(), ListaEventosAdapter.OnItemClickListener {

    private val viewModel: ListaEventosViewModel by viewModel()

    private val adapter = ListaEventosAdapter()
    private var binding: ActivityListaEventosBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_lista_eventos)

        binding!!.listaEventos.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@ListaEventosActivity)
            adapter = this@ListaEventosActivity.adapter
        }
        adapter.onClickListener = this
    }

    override fun onStart() {
        super.onStart()
        viewModel.liveData.observe(this, Observer {
            adapter.itens.clear()
            if (it != null ) {
                adapter.itens.addAll(it)
            }
            //TODO verificar retorno nunca nulo
            processaExistenciaLista(it == null)
            adapter.notifyDataSetChanged()
        })
        viewModel.liveDataProgress.observe(this, Observer {
            processaStatusCarregamento(it)
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.iniciaBuscaEventos()
    }

    private fun processaExistenciaLista(emErro: Boolean) {
        binding!!.apply {
            vsEmptyView.displayedChild = if (adapter.itens.isEmpty() || emErro) 1 else 0
            tvEmptyView.text = if (emErro) "Erro ao carregar" else "Nenhum evento encontrado"
        }
    }

    private fun processaStatusCarregamento(progressAtivo: Boolean) {
        binding!!.apply {
            if (progressAtivo) {
                tvEmptyView.text = "Carregando..."
                progressBar.show()
            } else {
                progressBar.hide()
            }
        }
    }

    override fun onItemClick(idEvento: Int?) {
        val intent = Intent(this, DetalhesEventoActivity::class.java)
        intent.putExtra(DetalhesEventoActivity.EXTRA_ID_EVENTO, idEvento)
        startActivity(intent)
    }
}
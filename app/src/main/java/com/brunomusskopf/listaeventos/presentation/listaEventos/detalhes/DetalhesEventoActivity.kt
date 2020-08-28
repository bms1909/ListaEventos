package com.brunomusskopf.listaeventos.presentation.listaEventos.detalhes

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.brunomusskopf.listaeventos.R
import com.brunomusskopf.listaeventos.databinding.ActivityDetalhesEventoBinding
import com.brunomusskopf.listaeventos.presentation.checkInEvento.CheckInEventoActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetalhesEventoActivity : AppCompatActivity() {

    companion object {
        val EXTRA_ID_EVENTO = "EXTRA_ID_EVENTO"
    }

    private val viewModel: DetalhesEventoViewModel by viewModel()

    private var idEvento: Int? = null

    private var binding: ActivityDetalhesEventoBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detalhes_evento)
        binding!!.btnCheckIn.setOnClickListener { onClickCheckIn() }

        idEvento = intent.extras?.getInt(EXTRA_ID_EVENTO, -1)
        if (idEvento == -1) {
            throw Exception("Parâmetro EXTRA_ID_EVENTO não reconhecido")
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.liveData.observe(this, Observer {
            binding!!.evento = it
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.iniciaBuscaEvento(idEvento!!)
    }

    private fun onClickCheckIn() {
        val intent = Intent(this, CheckInEventoActivity::class.java)
        intent.putExtra(CheckInEventoActivity.EXTRA_ID_EVENTO, idEvento)
        startActivity(intent)
    }
}
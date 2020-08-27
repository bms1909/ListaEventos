package com.brunomusskopf.listaeventos.presentation.listaEventos.detalhes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.brunomusskopf.listaeventos.R
import com.brunomusskopf.listaeventos.databinding.ActivityDetalhesEventoBinding
import com.brunomusskopf.listaeventos.databinding.ActivityListaEventosBinding

class DetalhesEventoActivity : AppCompatActivity() {

    companion object {
        val EXTRA_ID_EVENTO = "EXTRA_ID_EVENTO"
    }

    private var binding: ActivityDetalhesEventoBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detalhes_evento)

        val idEvento = intent.extras?.getInt(EXTRA_ID_EVENTO, -1)
        if (idEvento == 0) {
            //TODO
        }

        binding!!.tvId.text = idEvento.toString()

    }

}
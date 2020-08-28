package com.brunomusskopf.listaeventos.presentation.checkInEvento

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.brunomusskopf.listaeventos.R
import com.brunomusskopf.listaeventos.databinding.ActivityCheckInEventoBinding
import com.brunomusskopf.listaeventos.domain.checkInEvento.model.CheckInEventoRequest
import com.brunomusskopf.listaeventos.domain.checkInEvento.model.CheckInEventoValidation
import org.koin.androidx.viewmodel.ext.android.viewModel

class CheckInEventoActivity : AppCompatActivity() {

    companion object {
        val EXTRA_ID_EVENTO = "EXTRA_ID_EVENTO"
    }

    //não utilizado lateinit em objetos construídos no ciclo de vida pois caso ocorra manutenção
    //em um destes métodos, pode induzir o desenvolvedor a confiar na variável. Com a propriedade
    //sendo nullable, é necessária uma decisão caso a caso
    private var binding: ActivityCheckInEventoBinding? = null
    private val viewModel: CheckInEventoViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_check_in_evento)

        //por exemplo, neste caso é impossível ser nulo, então é forçado o not null
        binding!!.apply {
            viewModel = this@CheckInEventoActivity.viewModel
            lifecycleOwner = this@CheckInEventoActivity
        }

        viewModel.liveData.value = CheckInEventoRequest()
    }

    override fun onStart() {
        super.onStart()
        viewModel.liveDataErroStatus.observe(this, Observer{
            processaRetornoCheckIn(it)
        })
    }

    private fun processaRetornoCheckIn(errosValidacao: CheckInEventoValidation?) {
        if (errosValidacao == null) {
            finish()
        }
        else if (errosValidacao.errorMessage!= null) {
            AlertDialog.Builder(this)
                .setTitle("Falha de comunicação")
                .setMessage(errosValidacao.errorMessage)
                .setPositiveButton("OK", null)
                .show()
        }
        //demais erros são notificados diretamente a tela
    }
}
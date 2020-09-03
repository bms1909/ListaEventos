package com.brunomusskopf.listaeventos.presentation.checkInEvent

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.brunomusskopf.listaeventos.R
import com.brunomusskopf.listaeventos.databinding.ActivityCheckInEventBinding
import com.brunomusskopf.listaeventos.domain.checkInEvent.model.CheckInEventRequest
import com.brunomusskopf.listaeventos.domain.checkInEvent.model.CheckInEventValidation
import org.koin.androidx.viewmodel.ext.android.viewModel

class CheckInEventActivity : AppCompatActivity() {

    companion object {
        val EXTRA_EVENT_ID = "EXTRA_EVENT_ID"
    }

    //não utilizado lateinit em objetos construídos no ciclo de vida pois caso ocorra manutenção
    //em um destes métodos, pode induzir o desenvolvedor a confiar na variável. Com a propriedade
    //sendo nullable, é necessária uma decisão caso a caso
    private var binding: ActivityCheckInEventBinding? = null
    private val viewModel: CheckInEventViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_check_in_event)

        //por exemplo, neste caso é impossível ser nulo, então é forçado o not null
        binding!!.apply {
            viewModel = this@CheckInEventActivity.viewModel
            lifecycleOwner = this@CheckInEventActivity
        }

        viewModel.liveData.value = CheckInEventRequest()
    }

    override fun onStart() {
        super.onStart()
        viewModel.liveDataErrorStatus.observe(this) {
            processCheckInResult(it)
        }
        viewModel.liveDataProgress.observe(this) {
            processLoadingStatus(it)
        }
    }

    private fun processLoadingStatus(showProgress: Boolean) {
        binding!!.apply {
            vsProgressButton.displayedChild = if (showProgress) 1 else 0
            etName.isEnabled = !showProgress
            etEmail.isEnabled = !showProgress
        }
    }

    private fun processCheckInResult(validationErrors: CheckInEventValidation?) {
        if (validationErrors == null) {
            finish()
        }
        else if (validationErrors.errorMessage!= null) {
            AlertDialog.Builder(this)
                .setTitle("Falha no Check-In")
                .setMessage(validationErrors.errorMessage)
                .setPositiveButton("OK", null)
                .show()
        }
        //demais erros são notificados diretamente a tela
    }
}
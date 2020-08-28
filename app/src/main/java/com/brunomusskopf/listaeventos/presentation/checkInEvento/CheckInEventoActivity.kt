package com.brunomusskopf.listaeventos.presentation.checkInEvento

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.brunomusskopf.listaeventos.R
import com.brunomusskopf.listaeventos.databinding.ActivityCheckInEventoBinding
import com.brunomusskopf.listaeventos.domain.checkInEvento.model.CheckInEventoRequest
import org.koin.androidx.viewmodel.ext.android.viewModel

class CheckInEventoActivity : AppCompatActivity() {

    companion object {
        val EXTRA_ID_EVENTO = "EXTRA_ID_EVENTO"
    }

    private var binding: ActivityCheckInEventoBinding? = null
    private val viewModel: CheckInEventoViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_check_in_evento)

        binding!!.apply {
            viewModel = this@CheckInEventoActivity.viewModel
            lifecycleOwner = this@CheckInEventoActivity
        }

        viewModel.liveData.value = CheckInEventoRequest()
    }
}
package com.brunomusskopf.listaeventos.presentation.listaEventos.detalhes

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.brunomusskopf.listaeventos.R
import com.brunomusskopf.listaeventos.databinding.ActivityDetalhesEventoBinding
import com.brunomusskopf.listaeventos.domain.listaEventos.model.Cupom
import com.brunomusskopf.listaeventos.domain.listaEventos.model.Pessoa
import com.brunomusskopf.listaeventos.presentation.checkInEvento.CheckInEventoActivity
import com.brunomusskopf.listaeventos.presentation.listaEventos.model.EventoView
import com.bumptech.glide.Glide
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
        binding!!.btnIrMapa.setOnClickListener { onClickMapa() }

        binding!!.apply {
            viewModel = this@DetalhesEventoActivity.viewModel
            lifecycleOwner = this@DetalhesEventoActivity
        }

        idEvento = intent.extras!!.getInt(EXTRA_ID_EVENTO, -1)
        if (idEvento == -1) {
            throw Exception("Parâmetro EXTRA_ID_EVENTO não reconhecido")
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.liveData.observe(this, Observer {
            processaLayoutStatusErro(it == null)
            if (it != null) {
                processaEventoLiveData(it)
            }
            binding!!.btnCheckIn.visibility = if (it?.latitude == null || it.longitude == null) {
                View.GONE
            } else {
                View.VISIBLE
            }
        })
        viewModel.liveDataProgress.observe(this, Observer {
            processaStatusCarregamento(it)
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.iniciaBuscaEvento(idEvento!!)
    }

    private fun processaLayoutStatusErro(emErro: Boolean) {
        binding!!.apply {
            vsEmptyView.displayedChild = if (emErro) 1 else 0
            if (emErro) {
                tvEmptyView.text = "Erro ao carregar"
            }
        }
    }

    private fun processaStatusCarregamento(progressAtivo: Boolean) {
        binding!!.apply {
            if (progressAtivo) {
                tvEmptyView.text = "Carregando..."
                progressBar.show()
                vsEmptyView.displayedChild = 1
            } else {
                progressBar.hide()
            }
        }
    }

    private fun onClickCheckIn() {
        val intent = Intent(this, CheckInEventoActivity::class.java)
        intent.putExtra(CheckInEventoActivity.EXTRA_ID_EVENTO, idEvento)
        startActivity(intent)
    }

    private fun onClickMapa() {
        val lat = viewModel.liveData.value!!.latitude
        val lon = viewModel.liveData.value!!.longitude

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo: $lat,$lon"))
        startActivity(intent)
    }

    private fun processaEventoLiveData(evento: EventoView) {

        val validBinding = binding!!

        val imageView = validBinding.ivImage
        Glide.with(this)
            .load(evento.image)
            .override(imageView.width, imageView.height)
            .into(imageView)

        refazViewArrayItens(
            validBinding.llPeople,
            validBinding.tvPeople,
            evento.people,
            ::criaViewPeople
        )

        refazViewArrayItens(
            validBinding.llCupons,
            validBinding.tvCupons,
            evento.cupons,
            ::criaViewCupom
        )
    }

    fun <T> refazViewArrayItens(linear : LinearLayout, titulo : TextView, lista : List<T>?, functionCreate : (T) -> View ) {
        linear.removeAllViews()
        if (lista.isNullOrEmpty()) {
            titulo.visibility = View.GONE
            return
        }
        titulo.visibility = View.VISIBLE

        for (item in lista) {
            val itemView = functionCreate.invoke(item)
            linear.addView(itemView)
        }
    }

    private fun criaViewPeople(pessoa : Pessoa) : View {
        val tv = TextView(this)
        tv.text = pessoa.name
        return tv
    }

    private fun criaViewCupom(cupom: Cupom) : View {
        val tv = TextView(this)
        tv.text = getString(R.string.texto_desconto, cupom.discount)
        return tv
    }
}
package com.brunomusskopf.listaeventos.presentation.listaEventos.detalhes

import android.content.Intent
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
import com.brunomusskopf.listaeventos.domain.listaEventos.model.Evento
import com.brunomusskopf.listaeventos.domain.listaEventos.model.Pessoa
import com.brunomusskopf.listaeventos.presentation.checkInEvento.CheckInEventoActivity
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
            if (it == null) {
                processaErroCarregamento()
            } else {
                processaEventoLiveData(it)
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

    private fun processaErroCarregamento() {
        //TODO
    }

    private fun processaStatusCarregamento(progressAtivo: Boolean) {
        binding!!.apply {
            if (progressAtivo) {
                btnCheckIn.visibility = View.GONE
                progressBar.show()
            } else {
                btnCheckIn.visibility = View.VISIBLE
                progressBar.hide()
            }
        }
    }

    private fun onClickCheckIn() {
        val intent = Intent(this, CheckInEventoActivity::class.java)
        intent.putExtra(CheckInEventoActivity.EXTRA_ID_EVENTO, idEvento)
        startActivity(intent)
    }


    private fun processaEventoLiveData(evento: Evento) {

        val binding = binding!!

        val imageView = binding.ivImage
        Glide.with(this)
            .load(evento?.image)
            .override(imageView.width, imageView.height)
            //TODO .placeholder(R.drawable.)
            .into(imageView)


        refazViewArrayItens(binding.llPeople, binding.tvPeople, evento.people, ::criaViewPeople )

        refazViewArrayItens(binding.llCupons, binding.tvCupons, evento.cupons, ::criaViewCupom )
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
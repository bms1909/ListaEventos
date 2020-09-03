package com.brunomusskopf.listaeventos.presentation.listEvents.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.brunomusskopf.listaeventos.R
import com.brunomusskopf.listaeventos.databinding.ActivityEventDetailBinding
import com.brunomusskopf.listaeventos.domain.listEvents.model.Coupon
import com.brunomusskopf.listaeventos.domain.listEvents.model.Person
import com.brunomusskopf.listaeventos.presentation.checkInEvent.CheckInEventActivity
import com.brunomusskopf.listaeventos.presentation.listEvents.model.EventView
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventDetailActivity : AppCompatActivity() {

    companion object {
        val EXTRA_EVENT_ID = "EXTRA_EVENT_ID"
    }

    private val viewModel: EventDetailViewModel by viewModel()

    private var eventId: Int? = null

    private var binding: ActivityEventDetailBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_event_detail)
        binding!!.btnCheckIn.setOnClickListener { onClickCheckIn() }
        binding!!.btnIrMapa.setOnClickListener { onClickMap() }

        binding!!.apply {
            viewModel = this@EventDetailActivity.viewModel
            lifecycleOwner = this@EventDetailActivity
        }

        eventId = intent.extras!!.getInt(EXTRA_EVENT_ID, -1)
        if (eventId == -1) {
            throw Exception("Parâmetro EXTRA_ID_EVENTO não reconhecido")
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.liveData.observe(this) {
            processStatusErrorLayout(it == null)
            if (it != null) {
                processLiveDataEvent(it)
            }
            binding!!.btnCheckIn.visibility = if (it?.latitude == null || it.longitude == null) {
                View.GONE
            } else {
                View.VISIBLE
            }
        }
        viewModel.liveDataProgress.observe(this) {
            processLoadingStatus(it)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.startEventSearch(eventId!!)
    }

    private fun processStatusErrorLayout(isError: Boolean) {
        binding!!.apply {
            vsEmptyView.displayedChild = if (isError) 1 else 0
            if (isError) {
                tvEmptyView.text = "Erro ao carregar"
            }
        }
    }

    private fun processLoadingStatus(showProgress: Boolean) {
        binding!!.apply {
            if (showProgress) {
                tvEmptyView.text = "Carregando..."
                progressBar.show()
                vsEmptyView.displayedChild = 1
            } else {
                progressBar.hide()
            }
        }
    }

    private fun onClickCheckIn() {
        val intent = Intent(this, CheckInEventActivity::class.java)
        intent.putExtra(CheckInEventActivity.EXTRA_EVENT_ID, eventId)
        startActivity(intent)
    }

    private fun onClickMap() {
        val lat = viewModel.liveData.value!!.latitude
        val lon = viewModel.liveData.value!!.longitude

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo: $lat,$lon"))
        startActivity(intent)
    }

    private fun processLiveDataEvent(event: EventView) {

        val validBinding = binding!!

        val imageView = validBinding.ivImage
        Glide.with(this)
            .load(event.image)
            .override(imageView.width, imageView.height)
            .into(imageView)

        refazViewArrayItens(
            validBinding.llPeople,
            validBinding.tvPeople,
            event.people,
            ::criaViewPeople
        )

        refazViewArrayItens(
            validBinding.llCupons,
            validBinding.tvCupons,
            event.cupons,
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

    private fun criaViewPeople(person : Person) : View {
        val tv = TextView(this)
        tv.text = person.name
        return tv
    }

    private fun criaViewCupom(coupon: Coupon) : View {
        val tv = TextView(this)
        tv.text = getString(R.string.texto_desconto, coupon.discount)
        return tv
    }
}
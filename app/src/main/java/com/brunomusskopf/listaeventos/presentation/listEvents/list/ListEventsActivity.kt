package com.brunomusskopf.listaeventos.presentation.listEvents.list

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.brunomusskopf.listaeventos.R
import com.brunomusskopf.listaeventos.databinding.ActivityListEventsBinding
import com.brunomusskopf.listaeventos.presentation.listEvents.details.EventDetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListEventsActivity : AppCompatActivity(), ListEventsAdapter.OnItemClickListener {

    private val viewModel: ListEventsViewModel by viewModel()

    private val adapter = ListEventsAdapter()
    private var binding: ActivityListEventsBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list_events)

        binding!!.rvEvents.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@ListEventsActivity)
            adapter = this@ListEventsActivity.adapter
        }
        adapter.onClickListener = this
    }

    override fun onStart() {
        super.onStart()
        viewModel.liveData.observe(this) {
            adapter.items.clear()
            if (it != null ) {
                adapter.items.addAll(it)
            }
            processaEmptyList(it == null)
            adapter.notifyDataSetChanged()
        }
        viewModel.liveDataProgress.observe(this) {
            processLoadingStatus(it)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.startEventsSearch()
    }

    private fun processaEmptyList(isError: Boolean) {
        binding!!.apply {
            vsEmptyView.displayedChild = if (adapter.items.isEmpty() || isError) 1 else 0
            tvEmptyView.text = if (isError) "Erro ao carregar" else "Nenhum evento encontrado"
        }
    }

    private fun processLoadingStatus(showProgress: Boolean) {
        binding!!.apply {
            if (showProgress) {
                tvEmptyView.text = "Carregando..."
                vsEmptyView.displayedChild = 1
                progressBar.show()
            } else {
                progressBar.hide()
            }
        }
    }

    override fun onItemClick(eventId: Int?) {
        val intent = Intent(this, EventDetailActivity::class.java)
        intent.putExtra(EventDetailActivity.EXTRA_EVENT_ID, eventId)
        startActivity(intent)
    }
}
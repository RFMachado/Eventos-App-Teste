package br.com.teste.sicredi.feature.events.ui

import androidx.appcompat.app.AppCompatActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import br.com.teste.sicredi.R
import br.com.teste.sicredi.feature.common.ViewState
import br.com.teste.sicredi.feature.detail.ui.EventDetailActivity
import br.com.teste.sicredi.feature.events.domain.entity.EventData
import kotlinx.android.synthetic.main.activity_events.*
import timber.log.Timber

class EventsActivity : AppCompatActivity() {

    private val viewModel: EventsViewModel by viewModel()
    private val items = ArrayList<EventData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)

        viewModel.fetchEvents()

        bindListener()
        bindObserver()
        setupRecycler()
    }

    private fun setupRecycler() = with(recycler) {
        adapter = EventsAdapter(items) { eventID ->
            startActivity(EventDetailActivity.launchIntent(context, eventID))
        }
    }

    private fun bindListener() {
        btnTryAgain.setOnClickListener {
            viewModel.fetchEvents()
            layoutError.isVisible = false
        }
    }

    private fun bindObserver() {
        viewModel.getState().observe(this, Observer { state ->
            when (state) {
                is ViewState.Loading -> {
                    showLoading()
                }
                is ViewState.Success -> {
                    hideLoading()
                    showItems(state.data)
                }
                is ViewState.Failed -> {
                    hideLoading()
                    showError(state.throwable)
                }
            }
        })
    }

    private fun showItems(events: List<EventData>) {
        items.clear()
        items.addAll(events)
        recycler.adapter?.notifyDataSetChanged()
    }

    private fun showLoading() {
        progressBar.isVisible = true
    }

    private fun hideLoading() {
        progressBar.isVisible = false
    }

    private fun showError(throwable: Throwable) {
        Timber.e(throwable)
        layoutError.isVisible = true
    }

}
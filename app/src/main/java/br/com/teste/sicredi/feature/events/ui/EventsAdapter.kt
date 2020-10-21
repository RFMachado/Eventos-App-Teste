package br.com.teste.sicredi.feature.events.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.teste.sicredi.R
import br.com.teste.sicredi.feature.events.domain.entity.EventsData
import br.com.teste.sicredi.util.extension.inflate
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kotlinx.android.synthetic.main.item_events.view.*

class EventsAdapter(
    private val items: List<EventsData>,
    private val listener: (Int) -> Unit
) : RecyclerView.Adapter<EventsAdapter.ViewHolder>() {

    inner class ViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(parent.inflate(R.layout.item_events)) {

        fun bind(event: EventsData) = with(itemView) {
            txtTitle.text = event.title
            txtDate.text = event.dateString

            Glide.with(context)
                .load(event.image)
                .error(R.drawable.placeholder)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imgEvent)

            setOnClickListener {
                listener.invoke(event.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent)
    }

    override fun getItemCount() = items.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(items[position])
}
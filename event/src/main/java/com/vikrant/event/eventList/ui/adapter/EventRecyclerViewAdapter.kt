package com.vikrant.event.eventList.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vikrant.core.model.EventDetail
import com.vikrant.event.R
import kotlinx.android.synthetic.main.fragment_event_list_item.view.*

class EventRecyclerViewAdapter(
    private val eventListInteraction: EventListInteraction? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val callBack = object : DiffUtil.ItemCallback<EventDetail>() {

        override fun areItemsTheSame(oldItem: EventDetail, newItem: EventDetail): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: EventDetail, newItem: EventDetail): Boolean {
            return oldItem == newItem
        }

    }
    private val comparator = AsyncListDiffer(this, callBack)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return EventViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.fragment_event_list_item,
                parent,
                false
            ),
            eventListInteraction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is EventViewHolder -> {
                holder.bind(comparator.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int = comparator.currentList.size

    class EventViewHolder constructor(
        itemView: View,
        private val interaction: EventListInteraction?) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: EventDetail) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onItemClicked(bindingAdapterPosition, item)
            }
            tv_short_title.text = item.shortTitle
            Glide.with(itemView)
                .load(item.performers?.get(0)?.image)
                .into(iv_event)
            tv_date_and_time.text = item.dateTime
        }
    }

    fun updateEventsList(events: List<EventDetail>) {
        comparator.submitList(events)
    }
}

interface EventListInteraction {
    fun onItemClicked(position: Int, item: EventDetail)
}
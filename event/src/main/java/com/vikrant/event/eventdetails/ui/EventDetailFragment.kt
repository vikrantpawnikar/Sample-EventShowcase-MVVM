package com.vikrant.event.eventdetails.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.vikrant.core.model.EventDetail
import com.vikrant.event.databinding.FragmentEventDetailsBinding

class EventDetailFragment constructor(private val eventDetail: EventDetail) : Fragment() {

    private lateinit var binding: FragmentEventDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setEventDetails()
    }

    private fun setEventDetails() {
        Glide.with(this@EventDetailFragment)
            .load(eventDetail.performers?.get(0)?.image)
            .into(binding.ivEventDetail)
        binding.tvEventType.text = eventDetail.type
        binding.tvEventDescription.text = eventDetail.shortTitle
    }
}
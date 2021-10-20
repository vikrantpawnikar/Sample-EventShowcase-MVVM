package com.vikrant.event.eventdetails.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.vikrant.event.databinding.FragmentEventDetailsBinding

class EventDetailFragment(): Fragment() {

    private lateinit var binding: FragmentEventDetailsBinding
    private val detailsArgs: EventDetailFragmentArgs by navArgs()
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
            .load(detailsArgs.eventDetails.performers?.get(0)?.image)
            .into(binding.ivEventDetail)
        binding.tvEventType.text = detailsArgs.eventDetails.type
        binding.tvEventDescription.text = detailsArgs.eventDetails.shortTitle
    }
}
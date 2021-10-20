package com.vikrant.event.eventList.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.vikrant.core.model.EventDetail
import com.vikrant.core.model.Events
import com.vikrant.core.retrofitclient.ResultState
import com.vikrant.event.databinding.FragmentEventListBinding
import com.vikrant.event.eventList.ui.adapter.EventListInteraction
import com.vikrant.event.eventList.ui.adapter.EventRecyclerViewAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class EventListFragment : Fragment(), EventListInteraction {

    private val viewModel: EventListingViewModel by viewModel()
    private lateinit var listAdapter: EventRecyclerViewAdapter
    private lateinit var binding: FragmentEventListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initEventRecyclerView()
        intiViewModel()
        lifecycleScope.launchWhenCreated { viewModel.getEventData() }
    }

    private fun initEventRecyclerView() {
        binding.eventRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            listAdapter = EventRecyclerViewAdapter(this@EventListFragment)
            adapter = listAdapter
        }
    }

    private fun intiViewModel() {
        viewModel.uiLiveData.observe(viewLifecycleOwner, { populateUi(it) })
    }

    private fun populateUi(result: ResultState<Events>?) {
        when (result) {
            ResultState.Progress<Int>() -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            is ResultState.Success -> {
                updateEventData(result)
            }
            is ResultState.Failure -> {
                Toast.makeText(requireActivity(), "Failure getting data", Toast.LENGTH_SHORT)
                    .show()
            }
            else -> {
                Toast.makeText(requireActivity(), "Failure getting data", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun updateEventData(it: ResultState.Success<Events>) {
        binding.progressBar.visibility = View.GONE
        binding.eventRecyclerView.visibility = View.VISIBLE
        it.data.events?.let { value -> listAdapter.updateEventsList(value) }
    }

    override fun onItemClicked(position: Int, item: EventDetail) {
        activity?.run {
            val direction = EventListFragmentDirections.navigateToEventDetailFragment(item)
            findNavController().navigate(direction)
        }
    }
}
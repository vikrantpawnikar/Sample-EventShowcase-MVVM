package com.vikrant.event.eventList.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.vikrant.core.model.EventDetail
import com.vikrant.core.model.Events
import com.vikrant.core.retrofitclient.ResultState
import com.vikrant.event.R
import com.vikrant.event.databinding.FragmentEventListBinding
import com.vikrant.event.eventList.ui.adapter.EventListInteraction
import com.vikrant.event.eventList.ui.adapter.EventRecyclerViewAdapter
import com.vikrant.event.eventdetails.ui.EventDetailFragment
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

    override fun onResume() {
        super.onResume()
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

    private fun populateUi(it: ResultState<Events>?) {
        when (it) {
            ResultState.Progress<Int>() -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            is ResultState.Success -> {
                updateEventData(it)
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
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, EventDetailFragment(item), null)
                .addToBackStack(null)
                .commit()
        }
    }
}
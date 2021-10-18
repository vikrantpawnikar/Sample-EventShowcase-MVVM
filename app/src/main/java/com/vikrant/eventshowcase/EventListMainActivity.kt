package com.vikrant.eventshowcase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vikrant.event.R
import com.vikrant.event.eventList.ui.EventListFragment

class EventListMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.event_list_activity)
        initUi()
    }

    private fun initUi() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, EventListFragment::class.java, null)
            .commit()
    }
}
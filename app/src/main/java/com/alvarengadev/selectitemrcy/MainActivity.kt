package com.alvarengadev.selectitemrcy

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StableIdKeyProvider
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.LinearLayoutManager
import com.alvarengadev.selectitemrcy.adapter.ItemAdapter
import com.alvarengadev.selectitemrcy.adapter.MyItemDetailsLookup
import com.alvarengadev.selectitemrcy.adapter.ObserverItemAdapter
import com.alvarengadev.selectitemrcy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initComponents()
    }

    private fun initComponents() = binding.apply {
        val itemAdapter = ItemAdapter()

        rcy.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = itemAdapter
        }

        val tracker = SelectionTracker.Builder<Long>(
            "mySelection",
            rcy,
            StableIdKeyProvider(rcy),
            MyItemDetailsLookup(rcy),
            StorageStrategy.createLongStorage()
        ).withSelectionPredicate(
            SelectionPredicates.createSelectSingleAnything()
        ).build()

        options.apply {
            setOnClickInIbClose {
                tracker.clearSelection()
                this.visibility = View.GONE
            }
            setOnClickInIbTrash {
                Toast.makeText(this@MainActivity, "Trash", Toast.LENGTH_SHORT).show()
            }
        }

        itemAdapter.setTracker(tracker)
        itemAdapter.setObserverItemAdapter(object : ObserverItemAdapter {
            override fun setVisibility() {
                options.visibility = View.VISIBLE
            }
        })
    }
}
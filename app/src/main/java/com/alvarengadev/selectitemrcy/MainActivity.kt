package com.alvarengadev.selectitemrcy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StableIdKeyProvider
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.LinearLayoutManager
import com.alvarengadev.selectitemrcy.adapter.ItemAdapter
import com.alvarengadev.selectitemrcy.adapter.MyItemDetailsLookup
import com.alvarengadev.selectitemrcy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initComponents()
    }

    private fun initComponents() = binding.rcy.apply {
        val itemAdapter = ItemAdapter()

        layoutManager = LinearLayoutManager(this@MainActivity)
        adapter = itemAdapter

        val tracker = SelectionTracker.Builder<Long>(
            "mySelection",
            this,
            StableIdKeyProvider(this),
            MyItemDetailsLookup(this),
            StorageStrategy.createLongStorage()
        ).withSelectionPredicate(
            SelectionPredicates.createSelectAnything()
        ).build()

        itemAdapter.setTracker(tracker)
    }
}
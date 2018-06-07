package com.wizeline.kotlincontest

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.Snackbar.LENGTH_INDEFINITE
import android.support.v7.app.AppCompatActivity
import android.view.View.GONE
import android.view.View.VISIBLE
import com.wizeline.kotlincontest.adapter.TopAlbumsAdapter
import com.wizeline.kotlincontest.providers.RetrofitManager
import com.wizeline.kotlincontest.providers.RetrofitProvider
import kotlinx.android.synthetic.main.activity_main.*

class TopAlbumsActivity : AppCompatActivity() {

    private val retrofitProvider: RetrofitProvider by lazy { RetrofitManager() }
    private val topAlbumsActivity by lazy { TopAlbumsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        recyclerView.adapter = topAlbumsActivity
        progressBar.visibility = VISIBLE
        retrofitProvider.getList().subscribe({
            progressBar.visibility = GONE
            topAlbumsActivity.setResponse(it.albums.album)
        }, {
            progressBar.visibility = GONE
            Snackbar.make(frame, "Internet not available", LENGTH_INDEFINITE)
                    .setAction("Retry", { setupRecyclerView() }).show()
        })
    }
}
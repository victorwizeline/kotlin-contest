package com.wizeline.kotlincontest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.FrameLayout
import android.widget.ProgressBar
import com.wizeline.kotlincontest.adapter.TopAlbumsAdapter
import com.wizeline.kotlincontest.models.Response
import com.wizeline.kotlincontest.providers.RetrofitManager
import com.wizeline.kotlincontest.providers.RetrofitProvider
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class TopAlbumsActivity : AppCompatActivity(), Observer<Response> {

    private val retrofitProvider: RetrofitProvider by lazy { RetrofitManager() }
    private val recyclerView by lazy { findViewById(R.id.recycler_view) as RecyclerView }
    private val progressBar by lazy { findViewById(R.id.progress_bar) as ProgressBar }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        retrofitProvider.getList().subscribe(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onSubscribe(d: Disposable) {
        progressBar.visibility = VISIBLE
    }

    override fun onNext(response: Response) {
        recyclerView.adapter = TopAlbumsAdapter(response)
    }

    override fun onComplete() {
        progressBar.visibility = GONE
    }

    override fun onError(e: Throwable) {
        progressBar.visibility = GONE
        Snackbar.make(findViewById(R.id.frame) as FrameLayout , "No internet available", Snackbar.LENGTH_INDEFINITE).setAction("Retry", {
            retrofitProvider.getList().subscribe(this)
        }).show()
    }
}
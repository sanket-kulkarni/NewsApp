package com.example.newsapp.fragments

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import com.example.newsapp.DetailsActivity
import com.example.newsapp.R
import com.example.newsapp.adapter.NewsListAdapter
import com.example.newsapp.model.Article

class ListFragment : Fragment() {

    companion object {
        fun newInstance() = ListFragment()
    }
    private lateinit var binding:com.example.newsapp.databinding.ListFragmentBinding
    private lateinit var viewModel: ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.list_fragment, container, false)
        val adapter =NewsListAdapter(this::onItemClicked)
        binding.adapter = adapter
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.data.observe(this.activity!!, Observer {
            Log.e("ListActivity", it.toString())
            it.let(adapter::submitList)
        })
        return binding.root
    }

    private fun onItemClicked(article: Article, imageView: ImageView) {
        val intent = Intent(this.activity, DetailsActivity::class.java)
        intent.putExtra("article", article)
 //   startActivity(intent)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this.activity!!,
            imageView,
           "photo"
        )

        startActivity(intent, options.toBundle())
    }

}
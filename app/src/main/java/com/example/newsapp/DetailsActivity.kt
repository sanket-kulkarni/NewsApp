package com.example.newsapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.newsapp.databinding.ActivityDetailsBinding
import com.example.newsapp.model.Article
import io.realm.Realm
import io.realm.RealmConfiguration


private lateinit var article: Article
class DetailsActivity : AppCompatActivity() {
    val viewModel: DetailActivityViewModel by lazy {
        ViewModelProviders.of(this).get(DetailActivityViewModel::class.java)
    }
    lateinit var binding:ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)
        viewModel.article= intent.getParcelableExtra<Article>("article")!!
        binding.model=viewModel.article
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        if(viewModel.read()!=null)
        {
            menu!!.getItem(0).setIcon(ContextCompat.getDrawable(this, R.drawable.ic_baseline_star_24))
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                supportFinishAfterTransition()
                return true
            }

            R.id.action_bookmark -> {
                if(viewModel.read()!=null)
                {
                    viewModel.delete()
                    item.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_baseline_star_outline_24))
                }
                else
                {
                    viewModel.insert()
                    item.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_baseline_star_24))
                }
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
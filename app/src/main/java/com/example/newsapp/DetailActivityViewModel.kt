package com.example.newsapp

import androidx.lifecycle.ViewModel
import com.example.newsapp.model.Article
import io.realm.Realm


class DetailActivityViewModel : ViewModel(){
    lateinit var article: Article

    fun insert()
    {
        val realm: Realm = Realm.getDefaultInstance()
        realm.executeTransaction(object : Realm.Transaction {
            override fun execute(realm: Realm) {
                realm.insertOrUpdate(article)
            }
        })
    }

    fun read():Article?{
        val realm: Realm = Realm.getDefaultInstance()
      return realm.where(Article::class.java).equalTo("url", article.url).findFirst()
    }

    fun delete(){
        val realm: Realm = Realm.getDefaultInstance()
        realm.executeTransaction(object : Realm.Transaction {
            override fun execute(realm: Realm) {
                realm.where(Article::class.java).equalTo("url", article.url).findFirst()?.deleteFromRealm()
            }
        })
    }
}
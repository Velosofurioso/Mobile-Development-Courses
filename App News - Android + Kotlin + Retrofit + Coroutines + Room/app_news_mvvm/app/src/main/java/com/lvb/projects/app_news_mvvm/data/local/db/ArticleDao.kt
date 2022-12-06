package com.lvb.projects.app_news_mvvm.data.local.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lvb.projects.app_news_mvvm.data.local.model.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateInsert(article: Article): Long

    @Query("SELECT * FROM articles")
    fun getAll(): LiveData<List<Article>>

    @Delete
    suspend fun delete(article: Article)
}
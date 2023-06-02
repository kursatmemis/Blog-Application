package com.kursatmemis.blog_application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import com.kursatmemis.blog_application.adapters.MainListViewItemAdapter
import com.kursatmemis.blog_application.models.MainListViewItem
import com.shashank.sony.fancytoastlib.FancyToast

class MainActivity : AppCompatActivity() {
    private val mainListViewItems = mutableListOf<MainListViewItem>()
    private lateinit var mainListView: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainListView = findViewById(R.id.mainListView)
        createDataSource()

        val adapter = MainListViewItemAdapter(this, mainListViewItems)
        mainListView.adapter = adapter
    }

    private fun createDataSource() {
        val aboutMe = MainListViewItem(
            R.drawable.about_me_image,
            getString(R.string.about_me_title_text),
            getString(R.string.about_me_subtitle),
            "about_me"
        )

        val communication = MainListViewItem(
            R.drawable.communication_image,
            getString(R.string.communication_title),
            getString(R.string.communication_subtitle),
            "communication"
        )

        val gallery = MainListViewItem(
            R.drawable.gallery_image,
            getString(R.string.gallery_title),
            getString(R.string.gallery_subtitle),
            "gallery"
        )

        val blog = MainListViewItem(
            R.drawable.blog_image,
            getString(R.string.blog_title),
            getString(R.string.blog_subtitle),
            "blog"
        )

        mainListViewItems.add(aboutMe)
        mainListViewItems.add(communication)
        mainListViewItems.add(gallery)
        mainListViewItems.add(blog)
    }

    fun onClick(view: View?) {
        val intent = when (view?.tag) {
            "about_me" -> Intent(this, AboutMeActivity::class.java)
            "communication" -> Intent(this, CommunicationActivity::class.java)
            "gallery" -> Intent(this, GalleryActivity::class.java)
            "blog" -> Intent(this, BlogActivity::class.java)
            else -> {
                FancyToast.makeText(
                    this@MainActivity,
                    "Hedef sayfa bulunamadı!",
                    FancyToast.LENGTH_SHORT,
                    FancyToast.ERROR,
                    true
                ).show()
                return
            }
        }
        startActivity(intent)
    }
}
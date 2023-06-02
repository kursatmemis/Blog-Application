package com.kursatmemis.blog_application.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.kursatmemis.blog_application.R
import com.kursatmemis.blog_application.models.MainListViewItem

class MainListViewItemAdapter(
    val context: AppCompatActivity,
    val listViewItems: MutableList<MainListViewItem>
) : ArrayAdapter<MainListViewItem>(context, R.layout.custom_design, listViewItems) {

    @SuppressLint("InflateParams", "ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = context.layoutInflater
        val customDesign = layoutInflater.inflate(R.layout.custom_design, null)

        val bitmojiImageView = customDesign.findViewById<ImageView>(R.id.bitmojiImageView)
        val headerTextView = customDesign.findViewById<TextView>(R.id.headerTextView)
        val explanationTextView = customDesign.findViewById<TextView>(R.id.explanationTextView)
        val button = customDesign.findViewById<Button>(R.id.goToPageButton)


        val listViewItem = listViewItems[position]

        bitmojiImageView.setImageResource(listViewItem.imageViewResource)
        headerTextView.text = listViewItem.header
        explanationTextView.text = listViewItem.explanation
        button.tag = listViewItem.buttonTag

        return customDesign
    }

}
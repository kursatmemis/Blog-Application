package com.kursatmemis.blog_application.adapters

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.kursatmemis.blog_application.R
import com.kursatmemis.blog_application.models.CommunicationListViewItem

class CommunicationListViewItemAdapter(
    val context: AppCompatActivity,
    val communicationItems: MutableList<CommunicationListViewItem>
) : ArrayAdapter<CommunicationListViewItem>(context, R.layout.custom_communication_item, communicationItems) {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater = context.layoutInflater
        val customCommunicationItemView =
            layoutInflater.inflate(R.layout.custom_communication_item, null)

        val communicationActionButton =
            customCommunicationItemView.findViewById<Button>(R.id.communicationActionButton)
        val siteLogoImageView =
            customCommunicationItemView.findViewById<ImageView>(R.id.siteLogoImageView)
        val siteNameTextView =
            customCommunicationItemView.findViewById<TextView>(R.id.siteNameTextView)

        val customCommunicationItem = communicationItems[position]
        communicationActionButton.tag = customCommunicationItem.buttonTag
        communicationActionButton.setText(customCommunicationItem.buttonText)
        siteNameTextView.text = customCommunicationItem.siteInfo
        Glide.with(context).load(customCommunicationItem.imageViewResource).into(siteLogoImageView);
        return customCommunicationItemView
    }
}
package com.gno.taskthepictures.card

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.gno.taskthepictures.R
import com.squareup.picasso.Picasso

class CardFragment : Fragment(R.layout.fragment_card) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var pictureImageView: ImageView = view.findViewById(R.id.card_item_picture)
        val url = arguments?.getString("url")
        Picasso.get().load(url).into(pictureImageView)
    }
}



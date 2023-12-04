package com.example.hotelbookings

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter

class SlideAdapter (private val context: Context, private val imageResources: List<Int>) : PagerAdapter() {

    override fun getCount(): Int {
        return imageResources.size
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(context)
        val layout = inflater.inflate(R.layout.item_slide, container, false)

        val imageView = layout.findViewById<ImageView>(R.id.imageViewSlide)
        imageView.setImageResource(imageResources[position])

        container.addView(layout)
        return layout
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }

}
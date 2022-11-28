package com.ianpedraza.superherocards.utils

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.ianpedraza.superherocards.R

class ViewExtensions {

    companion object {
        fun ImageView.fromUrl(
            urlImage: String,
            centerCrop: Boolean = true,
            onLoad: ((Drawable?) -> Unit)? = null
        ) = Glide.with(this)
            .load(urlImage)
            .run {
                if (centerCrop) {
                    centerCrop()
                } else {
                    centerInside()
                }
            }
            .placeholder(R.drawable.ic_image_placeholder)
            .error(R.drawable.ic_image_broken)
            .addListener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ) = false

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    onLoad?.let { onLoad(resource) }
                    return false
                }
            })
            .into(this)

        fun Bitmap.getDarkColor(callback: (color: Int) -> Unit) =
            Palette.Builder(this).generate { palette ->
                val dominantColor = palette?.getDarkVibrantColor(Color.BLACK)

                dominantColor?.let {
                    val isColorDark = ColorsManager.isColorDark(dominantColor)

                    val color = if (isColorDark) {
                        ColorsManager.lightenColor(dominantColor, 0.25f)
                    } else {
                        dominantColor
                    }

                    callback(color)
                }
            }
    }
}

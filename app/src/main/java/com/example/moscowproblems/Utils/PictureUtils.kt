package com.example.moscowproblems.Utils

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Point

fun getScaledBitmap(path: String, desWidth: Int, desHeight: Int): Bitmap{
    val options: BitmapFactory.Options = BitmapFactory.Options()

    options.inJustDecodeBounds = true

    BitmapFactory.decodeFile(path, options)

    val srcWidth: Float = options.outWidth.toFloat()
    val srcHeight: Float = options.outHeight.toFloat()

    var helpSizeForScale = 1

    if (srcHeight > desHeight || srcWidth > desWidth) {
        val heightScale = srcHeight / desHeight
        val widthScale = srcWidth / desWidth

        val biggerScale = if (heightScale > widthScale) {
            heightScale
        } else {
            widthScale
        }

        helpSizeForScale = Math.round(biggerScale)
    }

    val newOptions: BitmapFactory.Options = BitmapFactory.Options()
    newOptions.inSampleSize = helpSizeForScale

    val newBitmapFile = BitmapFactory.decodeFile(path, newOptions)

    return newBitmapFile
}

fun getScaledBitMapWithSizeForActivity(path: String, activity: Activity): Bitmap{
    val size = Point()
    activity.windowManager.defaultDisplay.getSize(size)

    val desWidth = size.x
    val desHeight = size.y

    val newScaledBitmap = getScaledBitmap(path, desWidth, desHeight)

    return newScaledBitmap
}
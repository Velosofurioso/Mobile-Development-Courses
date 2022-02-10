package com.lvb.courses.kfeedreader

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.widget.ImageView
import java.net.URL

class DownloadImageTask(val imageView: ImageView): AsyncTask<String, Void, Bitmap>() {

    override fun doInBackground(vararg params: String?): Bitmap {
        val url = params[0]

        val stream = URL(url).openStream()
        return BitmapFactory.decodeStream(stream)
    }

    override fun onPostExecute(result: Bitmap?) {
        imageView.setImageBitmap(result)
    }
}
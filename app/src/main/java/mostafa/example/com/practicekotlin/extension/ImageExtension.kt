package mostafa.example.com.practicekotlin.extension

import android.graphics.drawable.BitmapDrawable
import android.support.v7.graphics.Palette
import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import java.util.*

fun ImageView.loadUrl(uri:String){
    Picasso.with(this.context).load(uri).into(this)
}


inline fun ImageView.loadUrl(url: String, callback: KCallback.() -> Unit) {
    Picasso.with(this.context).load(url).intoWithCallback(this, callback)
}

inline fun RequestCreator.intoWithCallback(target: ImageView, callback: KCallback.() -> Unit) {
    this.into(target, KCallback().apply(callback))
}

fun ImageView.generatePlatte(listener: (Palette)->Unit) {
    Palette.from((this.drawable as BitmapDrawable).bitmap).generate(listener)
}

class KCallback : Callback {

    private var onSuccess: (() -> Unit)? = null
    private var onError: (() -> Unit)? = null

    override fun onSuccess() {
        onSuccess?.invoke()
    }

    override fun onError() {
        onError?.invoke()
    }

    fun onSuccess(function: () -> Unit) {
        this.onSuccess = function
    }

    fun onError(function: () -> Unit) {
        this.onError = function
    }
}
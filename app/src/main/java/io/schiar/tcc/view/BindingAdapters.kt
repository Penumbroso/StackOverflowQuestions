package io.schiar.tcc.view

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import io.schiar.tcc.utilities.BitmapLoader

/**
 * Utilizado para tratamento de dados do ViewModel para serem apresentados na View através de data binding.
 */
object BindingAdapters {

    /**
     * Carrega uma imagem em um componente de imagem.
     * @param imageView componente de imagem.
     * @param bitmapLoader carregador da imagem.
     */
    @BindingAdapter("bitmapLoader")
    @JvmStatic
    fun setBitmap(imageView: ImageView, bitmapLoader: BitmapLoader?) {
        bitmapLoader?.loadInto(imageView)
    }
}
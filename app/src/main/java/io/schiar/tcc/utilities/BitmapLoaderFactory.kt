package io.schiar.tcc.utilities

object BitmapLoaderFactory : BitmapLoaderFactoryInterface {
    override fun bitmapLoader(urlString: String): BitmapLoader = BitmapLoader(urlString)
}
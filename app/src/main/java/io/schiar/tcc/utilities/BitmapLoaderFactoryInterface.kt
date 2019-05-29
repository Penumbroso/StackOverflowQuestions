package io.schiar.tcc.utilities

interface BitmapLoaderFactoryInterface {
    fun bitmapLoader(urlString: String): BitmapLoader
}
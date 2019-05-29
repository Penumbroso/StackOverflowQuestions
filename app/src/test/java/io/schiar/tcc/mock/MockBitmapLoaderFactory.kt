package io.schiar.tcc.mock

import io.schiar.tcc.mock.GenericMockFactory.Companion.mock
import io.schiar.tcc.utilities.BitmapLoader
import io.schiar.tcc.utilities.BitmapLoaderFactoryInterface

object MockBitmapLoaderFactory : BitmapLoaderFactoryInterface {
    override fun bitmapLoader(urlString: String): BitmapLoader = mock()
}
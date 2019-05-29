package io.schiar.tcc.mock

import org.mockito.Mockito

class GenericMockFactory {
    companion object {
        inline fun <reified T> any(): T {
            return Mockito.any(T::class.java)
        }

        inline fun <reified T> mock(): T {
            return Mockito.mock(T::class.java)
        }
    }
}
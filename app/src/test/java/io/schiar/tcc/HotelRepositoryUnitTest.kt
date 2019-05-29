package io.schiar.tcc

import io.schiar.tcc.mock.GenericMockFactory.Companion.any
import io.schiar.tcc.mock.GenericMockFactory.Companion.mock
import io.schiar.tcc.model.Hotel
import io.schiar.tcc.model.repository.HotelRepository
import io.schiar.tcc.model.repository.HotelRepositoryInterface
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.spy
import org.mockito.Mockito.verify

/**
 * Local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class HotelRepositoryUnitTest {
    private lateinit var hotelRepository: HotelRepositoryInterface

    @Before
    fun prepare() {
        hotelRepository = HotelRepository()
    }

    @Test
    fun selectedHotel_isInitiallyNull() {
        assertNull(hotelRepository.selectedHotel)
    }

    @Test
    fun fetch_callbackIsCalledSynchronously() {
        val fetchCallback: (List<Hotel>) -> Unit = mock()
        hotelRepository.fetch(fetchCallback)
        verify(fetchCallback).invoke(any())
    }

    @Test
    fun fetchById_callbackIsCalledSynchronously() {
        val fetchCallback: (List<Hotel>) -> Unit = { hotels ->
            val fetchByIdCallback: (Hotel) -> Unit = mock()
            hotelRepository.fetch(hotels.first().id, fetchByIdCallback)
            verify(fetchByIdCallback).invoke(any())
        }
        val fetchCallbackSpy = spy(fetchCallback)
        hotelRepository.fetch(fetchCallbackSpy)
        verify(fetchCallbackSpy).invoke(any())
    }
}

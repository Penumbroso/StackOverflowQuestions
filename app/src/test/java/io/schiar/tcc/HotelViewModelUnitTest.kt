package io.schiar.tcc

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.schiar.tcc.mock.GenericMockFactory.Companion.any
import io.schiar.tcc.mock.GenericMockFactory.Companion.mock
import io.schiar.tcc.mock.MockBitmapLoaderFactory
import io.schiar.tcc.mock.MockHotelRepository
import io.schiar.tcc.viewmodel.HotelDetailed
import io.schiar.tcc.viewmodel.HotelViewModel
import io.schiar.tcc.viewmodel.Preview
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.verify

class HotelViewModelUnitTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var hotelViewModel: HotelViewModel

    @Before
    fun prepare() {
        hotelViewModel = HotelViewModel(MockHotelRepository(), MockBitmapLoaderFactory)
    }

    @Test
    fun hotels_isInitiallyNull() {
        assertNull(hotelViewModel.hotels.value)
    }

    @Test
    fun selectedHotel_isInitiallyNull() {
        assertNull(hotelViewModel.selectedHotel.value)
    }

    @Test
    fun fetch_listOfHotelsIsUpdated() {
        val observer: Observer<List<Preview>> = mock()
        hotelViewModel.hotels.observeForever(observer)

        hotelViewModel.fetch()
        verify(observer).onChanged(any())
    }

    @Test
    fun fetch_listContainsHotelPreviews() {
        hotelViewModel.fetch()
        assertEquals(1, hotelViewModel.hotels.value!!.size)
        assertEquals("Mock Hotel", hotelViewModel.hotels.value!!.first().name)
    }

    @Test
    fun fetchByIndex_selectedHotelIsUpdated() {
        val observer: Observer<HotelDetailed> = mock()
        hotelViewModel.selectedHotel.observeForever(observer)

        hotelViewModel.fetch()
        hotelViewModel.fetch(0)
        verify(observer).onChanged(any())
    }

    @Test
    fun fetchByIndex_selectedHotelContainsHotelDetails() {
        hotelViewModel.fetch()
        hotelViewModel.fetch(0)
        assertEquals("Mock Hotel", hotelViewModel.selectedHotel.value!!.name)
        assertEquals("1 Mock Address", hotelViewModel.selectedHotel.value!!.address)
        assertEquals("1234567890", hotelViewModel.selectedHotel.value!!.phone)
        assertEquals("http://mock_hotel", hotelViewModel.selectedHotel.value!!.website)
        assertEquals(listOf("First amenity", "Second amenity"), hotelViewModel.selectedHotel.value!!.amenities)
        assertEquals(5, hotelViewModel.selectedHotel.value!!.stars)
    }
}
package io.schiar.tcc

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.schiar.tcc.mock.GenericMockFactory.Companion.any
import io.schiar.tcc.mock.GenericMockFactory.Companion.mock
import io.schiar.tcc.mock.MockBitmapLoaderFactory
import io.schiar.tcc.mock.MockCarRepository
import io.schiar.tcc.mock.MockHotelRepository
import io.schiar.tcc.mock.MockTripRepository
import io.schiar.tcc.viewmodel.Reservation
import io.schiar.tcc.viewmodel.TripViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.verify
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class TripViewModelUnitTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var tripViewModel: TripViewModel

    @Before
    fun prepare() {
        tripViewModel = TripViewModel(MockTripRepository(), MockBitmapLoaderFactory)
        Locale.setDefault(Locale.Category.DISPLAY, Locale("en", "US"))
    }

    @Test
    fun trip_isInitiallyNull() {
        assertNull(tripViewModel.trip.value)
    }

    @Test
    fun addCarToTrip_tripIsUpdated() {
        val observer: Observer<Reservation> = mock()
        tripViewModel.trip.observeForever(observer)

        tripViewModel.addCarToTrip(MockCarRepository())
        verify(observer).onChanged(any())
    }

    @Test
    fun addCarToTrip_tripContainsCarPreview() {
        tripViewModel.addCarToTrip(MockCarRepository())
        assertEquals("Mock Car", tripViewModel.trip.value!!.carPreview!!.name)
    }

    @Test
    fun addHotelToTrip_tripIsUpdated() {
        val observer: Observer<Reservation> = mock()
        tripViewModel.trip.observeForever(observer)

        tripViewModel.addHotelToTrip(MockHotelRepository())
        verify(observer).onChanged(any())
    }

    @Test
    fun addHotelToTrip_tripContainsHotelPreview() {
        tripViewModel.addHotelToTrip(MockHotelRepository())
        assertEquals("Mock Hotel", tripViewModel.trip.value!!.hotelPreview!!.name)
    }

    @Test
    fun addPeriodToTrip_tripIsUpdated() {
        val observer: Observer<Reservation> = mock()
        tripViewModel.trip.observeForever(observer)

        tripViewModel.addPeriodToTrip(100000, 200000)
        verify(observer).onChanged(any())
    }

    @Test
    fun addPeriodToTrip_tripContainsBeginDateAndEndDate() {
        tripViewModel.addPeriodToTrip(1410480000000, 1419494400000)
        val simpleDateFormat = SimpleDateFormat("MM/dd/yy", Locale.ENGLISH)
        val formatter = DateFormat.getDateInstance(DateFormat.SHORT)
        assertEquals(formatter.format(simpleDateFormat.parse("9/11/14")), tripViewModel.trip.value!!.beginDate)
        assertEquals(formatter.format(simpleDateFormat.parse("12/25/14")), tripViewModel.trip.value!!.endDate)
    }

    @Test
    fun addAdultsToTrip_tripIsUpdated() {
        val observer: Observer<Reservation> = mock()
        tripViewModel.trip.observeForever(observer)

        tripViewModel.addAdultsToTrip(2)
        verify(observer).onChanged(any())
    }

    @Test
    fun addAdultsToTrip_tripContainsAdults() {
        tripViewModel.addAdultsToTrip(3)
        assertEquals(3, tripViewModel.trip.value!!.adults)
    }

    @Test
    fun addChildrenToTrip_tripIsUpdated() {
        val observer: Observer<Reservation> = mock()
        tripViewModel.trip.observeForever(observer)

        tripViewModel.addChildrenToTrip(1)
        verify(observer).onChanged(any())
    }

    @Test
    fun addChildrenToTrip_tripContainsChildren() {
        tripViewModel.addChildrenToTrip(4)
        assertEquals(4, tripViewModel.trip.value!!.children)
    }
}
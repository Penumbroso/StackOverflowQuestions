package io.schiar.tcc

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.schiar.tcc.mock.GenericMockFactory.Companion.any
import io.schiar.tcc.mock.GenericMockFactory.Companion.mock
import io.schiar.tcc.mock.MockBitmapLoaderFactory
import io.schiar.tcc.mock.MockCarRepository
import io.schiar.tcc.viewmodel.CarDetailed
import io.schiar.tcc.viewmodel.CarViewModel
import io.schiar.tcc.viewmodel.Preview
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.verify

class CarViewModelUnitTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var carViewModel: CarViewModel

    @Before
    fun prepare() {
        carViewModel = CarViewModel(MockCarRepository(), MockBitmapLoaderFactory)
    }

    @Test
    fun cars_isInitiallyNull() {
        assertNull(carViewModel.cars.value)
    }

    @Test
    fun selectedCar_isInitiallyNull() {
        assertNull(carViewModel.selectedCar.value)
    }

    @Test
    fun fetch_listOfCarsIsUpdated() {
        val observer: Observer<List<Preview>> = mock()
        carViewModel.cars.observeForever(observer)

        carViewModel.fetch()
        verify(observer).onChanged(any())
    }

    @Test
    fun fetch_listContainsCarPreviews() {
        carViewModel.fetch()
        assertEquals(1, carViewModel.cars.value!!.size)
        assertEquals("Mock Car", carViewModel.cars.value!!.first().name)
    }

    @Test
    fun fetchByIndex_selectedCarIsUpdated() {
        val observer: Observer<CarDetailed> = mock()
        carViewModel.selectedCar.observeForever(observer)

        carViewModel.fetch()
        carViewModel.fetch(0)
        verify(observer).onChanged(any())
    }

    @Test
    fun fetchByIndex_selectedCarContainsCarDetails() {
        carViewModel.fetch()
        carViewModel.fetch(0)
        assertEquals("Mock Car", carViewModel.selectedCar.value!!.name)
        assertEquals(2019, carViewModel.selectedCar.value!!.year)
        assertEquals("Mock", carViewModel.selectedCar.value!!.brand)
        assertEquals("Gasoline", carViewModel.selectedCar.value!!.fuel)
        assertEquals("Mock Car Description", carViewModel.selectedCar.value!!.description)
    }
}
package io.schiar.tcc

import io.schiar.tcc.mock.GenericMockFactory.Companion.any
import io.schiar.tcc.mock.GenericMockFactory.Companion.mock
import io.schiar.tcc.model.Car
import io.schiar.tcc.model.repository.CarRepository
import io.schiar.tcc.model.repository.CarRepositoryInterface
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
class CarRepositoryUnitTest {
    private lateinit var carRepository: CarRepositoryInterface

    @Before
    fun prepare() {
        carRepository = CarRepository()
    }

    @Test
    fun selectedCar_isInitiallyNull() {
        assertNull(carRepository.selectedCar)
    }

    @Test
    fun fetch_callbackIsCalledSynchronously() {
        val fetchCallback: (List<Car>) -> Unit = mock()
        carRepository.fetch(fetchCallback)
        verify(fetchCallback).invoke(any())
    }

    @Test
    fun fetchById_callbackIsCalledSynchronously() {
        val fetchCallback: (List<Car>) -> Unit = { cars ->
            val fetchByIdCallback: (Car) -> Unit = mock()
            carRepository.fetch(cars.first().id, fetchByIdCallback)
            verify(fetchByIdCallback).invoke(any())
        }
        val fetchCallbackSpy = spy(fetchCallback)
        carRepository.fetch(fetchCallbackSpy)
        verify(fetchCallbackSpy).invoke(any())
    }
}

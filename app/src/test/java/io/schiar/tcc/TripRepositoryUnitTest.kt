package io.schiar.tcc

import io.schiar.tcc.mock.GenericMockFactory.Companion.any
import io.schiar.tcc.mock.GenericMockFactory.Companion.mock
import io.schiar.tcc.model.Car
import io.schiar.tcc.model.Hotel
import io.schiar.tcc.model.Period
import io.schiar.tcc.model.Trip
import io.schiar.tcc.model.repository.TripRepository
import io.schiar.tcc.model.repository.TripRepositoryInterface
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

/**
 * Local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class TripRepositoryUnitTest {
    private lateinit var tripRepository: TripRepositoryInterface

    @Before
    fun prepare() {
        tripRepository = TripRepository()
    }

    @Test
    fun fetch_callbackIsCalledSynchronously() {
        val fetchCallback: (Trip) -> Unit = mock()
        tripRepository.fetch(fetchCallback)
        Mockito.verify(fetchCallback).invoke(any())
    }

    @Test
    fun fetch_callbackIsCalledSynchronouslyWithAValidTrip() {
        tripRepository.fetch {
            assertTrue(it.id.isNotEmpty())
        }
    }

    @Test
    fun selectCar_callbackIsCalledSynchronously_tripHasSelectedCar() {
        val car = Car("test_id", "Test Car", "http://test_photo_url")
        tripRepository.selectCar(car) {
            assertEquals(car, it.car)
        }
    }

    @Test
    fun selectHotel_callbackIsCalledSynchronously_tripHasSelectedHotel() {
        val hotel = Hotel("test_id", "Test Hotel", "http://test_photo_url")
        tripRepository.selectHotel(hotel) {
            assertEquals(hotel, it.hotel)
        }
    }

    @Test
    fun selectPeriod_callbackIsCalledSynchronously_tripHasSelectedPeriod() {
        val period = Period(1000, 2000)
        tripRepository.selectPeriod(period) {
            assertEquals(period, it.period)
        }
    }

    @Test
    fun selectAdults_callbackIsCalledSynchronously_tripHasSelectedAdults() {
        val adults = 2
        tripRepository.selectAdults(adults) {
            assertEquals(adults, it.adults)
        }
    }

    @Test
    fun selectChildren_callbackIsCalledSynchronously_tripHasSelectedChildren() {
        val children = 1
        tripRepository.selectChildren(children) {
            assertEquals(children, it.children)
        }
    }
}

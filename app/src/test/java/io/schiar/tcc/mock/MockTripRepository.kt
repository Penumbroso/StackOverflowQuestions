package io.schiar.tcc.mock

import io.schiar.tcc.model.Car
import io.schiar.tcc.model.Hotel
import io.schiar.tcc.model.Period
import io.schiar.tcc.model.Trip
import io.schiar.tcc.model.repository.TripRepositoryInterface

class MockTripRepository : TripRepositoryInterface {
    override fun fetch(callback: (Trip) -> Unit) {
        callback(Trip("1", null, null, null, null, null))
    }

    override fun selectCar(car: Car, callback: (Trip) -> Unit) {
        callback(Trip("1", car, null, null, null, null))
    }

    override fun selectHotel(hotel: Hotel, callback: (Trip) -> Unit) {
        callback(Trip("1", null, hotel, null, null, null))
    }

    override fun selectPeriod(period: Period, callback: (Trip) -> Unit) {
        callback(Trip("1", null, null, period, null, null))
    }

    override fun selectAdults(adults: Int, callback: (Trip) -> Unit) {
        callback(Trip("1", null, null, null, adults, null))
    }

    override fun selectChildren(children: Int, callback: (Trip) -> Unit) {
        callback(Trip("1", null, null, null, null, children))
    }

}
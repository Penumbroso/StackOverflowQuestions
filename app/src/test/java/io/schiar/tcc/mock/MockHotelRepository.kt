package io.schiar.tcc.mock

import io.schiar.tcc.model.Hotel
import io.schiar.tcc.model.Star
import io.schiar.tcc.model.repository.HotelRepositoryInterface

class MockHotelRepository : HotelRepositoryInterface {
    override val selectedHotel: Hotel?
        get() = Hotel("0", "Mock Hotel", "http://mock_hotel_photo")

    override fun fetch(callback: (List<Hotel>) -> Unit) {
        callback(listOf(Hotel("0", "Mock Hotel", "http://mock_hotel_photo")))
    }

    override fun fetch(id: String, callback: (Hotel) -> Unit) {
        callback(
            Hotel(
                id,
                "Mock Hotel",
                "http://mock_hotel_photo",
                "1 Mock Address",
                "1234567890",
                "http://mock_hotel",
                listOf("First amenity", "Second amenity"),
                Star.LUXURY
            )
        )
    }
}
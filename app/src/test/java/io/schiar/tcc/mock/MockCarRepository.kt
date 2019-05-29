package io.schiar.tcc.mock

import io.schiar.tcc.model.Car
import io.schiar.tcc.model.Fuel
import io.schiar.tcc.model.repository.CarRepositoryInterface

class MockCarRepository : CarRepositoryInterface {
    override val selectedCar: Car?
        get() = Car("0", "Mock Car", "http://mock_car_photo")

    override fun fetch(callback: (List<Car>) -> Unit) {
        callback(listOf(Car("0", "Mock Car", "http://mock_car_photo")))
    }

    override fun fetch(id: String, callback: (Car) -> Unit) {
        callback(
            Car(
                id,
                "Mock Car",
                "http://mock_car_photo",
                2019,
                "Mock",
                Fuel.GASOLINE,
                "Mock Car Description"
            )
        )
    }
}
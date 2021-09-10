package ru.shelest

data class ParkingSpot(private val number: Int, private var busy: Boolean, private var car: Car) {

    fun number() = number

    fun busy() = busy

    fun car() = Car(this.car.number(), this.car.color())

    fun setBusy(busy: Boolean) {
        this.busy = busy
    }

    fun setCar(car: Car) {
        this.car = Car(car.number(), car.color())
    }

    fun park(car: Car) {
        if (busy) {
            println("This spot is busy.")
        } else {
            busy = true
            this.car = Car(car.number(), car.color())
            println("${this.car.color()} car parked in spot $number.")
        }
    }

    fun leave() {
        if (!busy) {
            println("There is no car in spot $number.")
        } else {
            println("Spot $number is free.")
            busy = false
            car = Car()
        }
    }
}
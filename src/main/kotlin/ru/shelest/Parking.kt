package ru.shelest

class Parking(private val size: Int = 0) {

    private val parking: MutableList<ParkingSpot> = MutableList(this.size) { ParkingSpot(0, false, Car()) }

    private var count = 0

    private var firstEmpty = 0

    init {
        parking.indices.forEach { parking[it] = ParkingSpot(it + 1, false, Car()) }
    }

    fun size() = this.size

    fun park(car: Car) {
        checkForFree()

        if (firstEmpty == -1) {
            println("Sorry, the parking lot is full.")
            return
        }

        this.parking[firstEmpty].park(car)
        count++
    }

    fun leave(number: Int) {
        this.parking[number - 1].leave()
        count--

        checkForFree()
    }

    fun isEmpty() = count == 0

    fun status() = this.parking.filter { it.busy() }.toList()

    private fun checkForFree() {
        for (i in parking.indices) {
            if (!parking[i].busy()) {
                firstEmpty = i
                return
            }
        }

        firstEmpty = -1
    }
}
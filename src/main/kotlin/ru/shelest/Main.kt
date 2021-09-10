package ru.shelest

import java.util.Scanner

fun main() {
    var parking = Parking()

    Scanner(System.`in`).run {
        do {
            val command = this.next().trim().lowercase()
            val properties = this.nextLine().trim()

            when (command) {
                "create" -> parking = createParking(properties.toInt())
                "park" -> parkCar(parking, properties)
                "leave" -> leave(parking, properties)
                "status" -> status(parking)
                "reg_by_color" -> regByColor(parking, properties)
                "spot_by_color" -> spotByColor(parking, properties)
                "spot_by_reg" -> spotByReg(parking, properties)
            }
        } while (command != "exit")
    }
}

fun createParking(size: Int = 0): Parking {
    if (size > 0) {
        println("Created a parking lot with $size spots.")
    }

    return Parking(if (size > 0) size else 0)
}

fun parkCar(parking: Parking, properties: String) = if (parking.size() == 0) {
    println("Sorry, a parking lot has not been created.")
} else {
    val (carNumber, carColor) = properties.split("\\s+".toRegex())
    val car = Car(carNumber, carColor)

    parking.park(car)
}

fun leave(parking: Parking, properties: String) = if (parking.size() == 0) {
    println("Sorry, a parking lot has not been created.")
} else {
    parking.leave(properties.toInt())
}

fun status(parking: Parking) = when {
    parking.size() == 0 -> println("Sorry, a parking lot has not been created.")
    parking.isEmpty() -> println("Parking lot is empty.")
    else -> parking.status().forEach { println("${it.number()} ${it.car().number()} ${it.car().color()}") }
}

fun regByColor(parking: Parking, color: String) = if (parking.size() == 0) {
    println("Sorry, a parking lot has not been created.")
} else {
    val spots = parking.status().filter { it.car().color().lowercase() == color.lowercase() }

    println(if (spots.isEmpty()) {
        "No cars with color ${color.uppercase()} were found."
    } else {
        spots.joinToString(separator = ", ") { it.car().number() }
    })
}

fun spotByColor(parking: Parking, color: String) = if (parking.size() == 0) {
    println("Sorry, a parking lot has not been created.")
} else {
    val spots = parking.status().filter { it.car().color().lowercase() == color.lowercase() }

    println(if (spots.isEmpty()) {
        "No cars with color ${color.uppercase()} were found."
    } else {
        spots.map { it.number() }.joinToString(separator = ", ")
    })
}

fun spotByReg(parking: Parking, regNumber: String) = if (parking.size() == 0) {
    println("Sorry, a parking lot has not been created.")
} else {
    val spots = parking.status().filter { it.car().number().lowercase() == regNumber.lowercase() }

    println(if (spots.isEmpty()) {
        "No cars with registration number ${regNumber.uppercase()} were found."
    } else {
        spots.map { it.number() }.joinToString(separator = ", ")
    })
}
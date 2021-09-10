package ru.shelest

data class Car(private val number: String = "", private val color: String = "") {

    fun number() = number

    fun color() = color
}
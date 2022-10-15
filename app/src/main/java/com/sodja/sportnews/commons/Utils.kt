package com.sodja.sportnews.commons

object Utils {
    fun <T> List<T>.mix(other: List<T>): List<T> {
        val first = iterator()
        val second = other.iterator()
        val list = ArrayList<T>(minOf(this.size, other.size))
        while (first.hasNext() && second.hasNext()) {
            list.add(first.next())
            list.add(second.next())
        }
        return list
    }
}
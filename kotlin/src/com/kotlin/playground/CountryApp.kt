package com.kotlin.playground

/**
 * 函数作为参数传递
 * 解耦操作
 */
data class Country(
    val name: String,
    val continent: String,
    val population: Int
)

class CountryApp {
    fun filterCountries(countries: List<Country>): List<Country> {
        val res = mutableListOf<Country>()
        for (c in countries) {
            if (c.continent == "EU"){
                res.add(c)
            }
        }
        return res
    }
}

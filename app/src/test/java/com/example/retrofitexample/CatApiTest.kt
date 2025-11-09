package com.example.retrofitexample

import com.example.retrofitexample.network.RetrofitInstance
import kotlinx.coroutines.runBlocking
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.*

class CatApiTest {

    @Test
    fun `should fetch random cat successfully`() {
        runBlocking {
            val cats = RetrofitInstance.api.getRandomCat()

            println("Respuesta: $cats")

            expectThat(cats).isNotEmpty()
            expectThat(cats.first().url)
                .isNotBlank()
                .contains("https://cdn2.thecatapi.com")
        }
    }
}

package com.example.playground

import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mockk.every
import io.mockk.mockk

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        val car = mockk<Car>()
        every { car.drive() } returns 101
        val result = car.drive()
        assertEquals(101, result)
    }
}


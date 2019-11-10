package klisp

import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import klisp.List
import java.util.*

internal class ListTest {

    @BeforeEach
    fun setUp() {
    }


    @Test
    fun `read EmptyList`() {
        val scanner = Scanner("nil")
        val s = scanner.next()
        assertEquals(EmptyList, NonEmptyList.read(scanner, s))
    }

    @Test
    fun `read NonEmptyList`() {
        val scanner = Scanner("( nil . nil )")
        val s = scanner.next()
        assertEquals(NonEmptyList(EmptyList, EmptyList), NonEmptyList.read(scanner, s))
    }

}
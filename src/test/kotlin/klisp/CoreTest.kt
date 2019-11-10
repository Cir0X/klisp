package klisp

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

internal class CoreTest {

    private val symbolA = Symbol("a")
    private val symbolValue = Symbol("42")
    private val list = NonEmptyList(symbolA, NonEmptyList(symbolValue, EmptyList))
    private val env = NonEmptyList(NonEmptyList(symbolA, NonEmptyList(symbolValue, EmptyList)), EmptyList)
    private val condList = NonEmptyList(NonEmptyList(Core.t, NonEmptyList(symbolValue, EmptyList)), EmptyList)

    @BeforeEach
    fun setUp() {
    }

    @Test
    fun `EmptyList and EmptyList should be equal`() {
        assertEquals(Core.t, Core.eq(EmptyList, EmptyList))
    }

    @Test
    fun `EmptyList and NonEmptyList shouldn't be equal`() {
        assertNotEquals(Core.t, Core.eq(EmptyList, NonEmptyList(symbolA, EmptyList)))
    }

    @Test
    fun `Symbols with same String value should be equal`() {
        assertEquals(Core.t, Core.eq(Symbol("a"), Symbol("a")))
    }

    @Test
    fun `Symbols with different values shouldn't be equal`() {
        assertNotEquals(Core.t, Core.eq(Symbol("a"), Symbol("b")))
    }

    @Test
    fun `car on EmptyList should return EmptyList`() {
        assertEquals(EmptyList, Core.car(EmptyList))
    }

    @Test
    fun `car on NonEmptyList should return Symbol`() {
        assertEquals(symbolA, Core.car(list))
    }

    @Test
    fun `cdr on EmptyList should return EmptyList`() {
        assertEquals(EmptyList, Core.cdr(EmptyList))
    }

    @Test
    fun `cdr on NonEmptyList should return tail`() {
        assertEquals(list.cdr, Core.cdr(list))
    }

    @Test
    fun `cond should return EmptyList when called on EmptyList`() {
        assertEquals(EmptyList, Core.cond(EmptyList))
    }

    @Test
    fun `cond should return Symbol`() {
        assertEquals(Core.t, Core.cond(condList))
    }

    @Test
    fun `assoc_ should return EmptyList when EmptyList given`() {
        assertEquals(EmptyList, Core.assoc_(symbolA, EmptyList))
    }

    @Test
    fun `assoc_ should return value of Symbol in env`() {
        println(list)
        assertEquals(symbolValue, Core.assoc_(symbolA, env))
    }
}
package klisp

import read
import java.util.*

/**
 * Algebraic Data Type
 * A [List] can either be an [EmptyList] or an [NonEmptyList]
 */
sealed class List() : Expr()

object EmptyList : List() {
    override fun eval(env: List) = env

    override fun toString() = "nil"

    override fun equals(other: Any?) = (other is EmptyList)
}

class NonEmptyList(
    val car: Expr,
    val cdr: List
) : List() {

    override fun toString() = "($car . $cdr)"

    override fun eval(env: List): Expr = when (car) {
        is Symbol -> when (car.s) {
            "quote" -> Core.quote(cdr)
            "atom" -> Core.atom(cdr)
            "eq" -> Core.eq(Core.carOrEmptyList_(cdr), Core.cdrOrEmptyList_(cdr))
            "car" -> Core.car(cdr)
            "cdr" -> Core.cdr(cdr)
            "cons" -> TODO()
            "cond" -> Core.cond(cdr)
            else -> Core.assoc_(car, env).let { TODO() }
        }
        is NonEmptyList -> TODO()
        else -> EmptyList
    }

    override fun equals(other: Any?) =
        (other is NonEmptyList) && car == other.car && cdr == other.cdr

    companion object {
        fun read(scanner: Scanner, value: String): Expr {
            if (value != "(") {
                return EmptyList
            }
            val expr: Expr = read(scanner)
            val dot: String = scanner.next()
            val list: List = read(scanner).let {
                when (it) {
                    is NonEmptyList -> if (it.car == EmptyList) EmptyList else (
                            (if (it.car.toString() == "(") this.read(
                                scanner,
                                "("
                            ) else EmptyList) as List)
                    else -> EmptyList
                }
            }
            val end: String = scanner.next()
            return NonEmptyList(expr, list);
        }
    }
}
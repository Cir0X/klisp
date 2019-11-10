package klisp

import java.util.*

class Symbol(val s: String) : Expr() {

    override fun equals(other: Any?) = if (other !is Symbol) false else s == (other as Symbol).s

    override fun toString() = s

    override fun eval(env: List) = Core.assoc_(this, env)

    companion object {
        fun read(scanner: Scanner, value: String): Expr = Symbol(value)
    }
}
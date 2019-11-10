package klisp

abstract class Expr {
    abstract fun eval(env: List): Expr
}


package klisp

import java.util.Scanner

object Util {

    fun read(scanner: Scanner): Expr {
        val s = scanner.next();
        return if (s == "(") NonEmptyList.read(scanner, s) else Symbol.read(scanner, s)
    }

}
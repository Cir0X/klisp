import klisp.EmptyList
import klisp.Expr
import klisp.NonEmptyList
import klisp.Symbol
import java.util.*

fun main() {
    println("klisp")
    val scanner = Scanner(System.`in`)
    while (scanner.hasNext()) {
//        print("> ")
        val exp = read(scanner)
        val res = exp.eval(EmptyList)
        println(res)
    }
}

fun read(scanner: Scanner): Expr = scanner.next().let {
    if (it == "nil")
        EmptyList
    else (if (it == "(")
        NonEmptyList.read(scanner, it)
    else
        Symbol.read(scanner, it))
}
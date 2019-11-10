package klisp

object Core {

    val t = Symbol("t")

    fun car(list: List): Expr = when (list) {
        is EmptyList -> EmptyList
        is NonEmptyList -> list.car
    }

    fun cdr(list: List): Expr = when (list) {
        is EmptyList -> EmptyList
        is NonEmptyList -> list.cdr
    }

    fun cons(element: Expr, list: List): Expr = NonEmptyList(element, list)

    fun quote(expr: Expr): Expr = expr

    fun atom(expr: Expr): Expr = if (expr !is NonEmptyList) EmptyList else t

    fun eq(a: Expr, b: Expr): Expr = if (a == b) t else EmptyList

    fun cond(list: List): Expr = when (list) {
        is EmptyList -> EmptyList
        is NonEmptyList -> when (list.car) {
            is NonEmptyList -> if (eq(
                    list.car.car,
                    EmptyList
                ) == EmptyList
            ) cdrOrEmptyList_(list.car.car) else cond(list.cdr)
            else -> EmptyList
        }
    }

    fun nil_(expr: Expr): Expr = if (expr is EmptyList) EmptyList else t

    fun append_(): Expr = TODO()

    fun pair_(): Expr = TODO()

    fun assoc_(a: Symbol, env: List): Expr = when (env) {
        is EmptyList -> EmptyList
        is NonEmptyList -> when (env.car) {
            is NonEmptyList -> if (eq(a, env.car.car) !is EmptyList) carOrEmptyList_(env.car.cdr) else assoc_(a, env.cdr)
            else -> EmptyList
        }
    }

    fun carOrEmptyList_(expr: Expr) = when (expr) {
        is EmptyList -> EmptyList
        is NonEmptyList -> expr.car
        else -> expr
    }

    fun cdrOrEmptyList_(expr: Expr) = when (expr) {
        is EmptyList -> EmptyList
        is NonEmptyList -> expr.cdr
        else -> expr
    }
}

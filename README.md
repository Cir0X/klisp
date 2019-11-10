# klisp

Lisp interpreter written in Kotlin

## Usage

````bash
./gradlew run
````

```lisp
> (cdr . (quote . (a . (b . (c . nil)))))
(b . (c . nil))
```
package com.example.calculator.arithmetic

import java.util.*

class Calculator {
    fun calculate(s: String): String {
        return try {
            var result = s.toRPN().evalRPN().toString()
            if (result.takeLast(2) == ".0") {
                result = result.dropLast(2)
            }
            result
        } catch (e : java.lang.Exception) {
            e.printStackTrace()
            "ERROR"
        }
    }

    // 中缀表达式转前缀表达式
    private fun String?.toRPN(): List<String> {
        if (isNullOrEmpty()) {
            return Collections.emptyList()
        }

        val list = ArrayList<String>()
        val stack = ArrayDeque<Char>()
        var num = 0
        outer@
        for ((index, c) in this.withIndex()) {
            when (c) {
                ' ' -> {
                }
                in '0'..'9' -> {
                    num = num * 10 + (c.toInt() - 48)
                    if (index + 1 >= length || this[index + 1] !in '0'..'9') {
                        list.add("$num")
                        num = 0
                    }
                }

                '(' -> stack.push(c)

                ')' -> {
                    while ('(' != stack.peek()) {
                        list.add("${stack.pop()}")
                    }
                    stack.pop() // 最后弹出 (
                }

                else -> {
                    // 先输出优先级相同或者更高的运算符
                    while (c.priority() <= stack.peek().priority()) {
                        list.add("${stack.pop()}")
                    }
                    stack.push(c)
                }
            }
        }
        while (stack.isNotEmpty()) {
            list.add("${stack.pop()}")
        }
        return list
    }

    // 运算符优先级
    private fun Char?.priority(): Int {
        return when (this) {
            '+', '-' -> 1
            '*', '/' -> 2
            '(', ')' -> -1
            null -> -1
            else -> throw IllegalStateException()
        }
    }

    private fun Deque<Float>.eval(operator: (Float, Float) -> Float): Float {
        val num1 = pop()
        val num2 = pop()

        return operator(num2, num1)
    }

    private fun List<String>?.evalRPN(): Float {
        if (this.isNullOrEmpty()) {
            return 0f
        }

        val stack = ArrayDeque<Float>()

        for (token in this) {
            when (token) {
                "+" -> {
                    stack.push(stack.eval { o1, o2 -> o1 + o2 })
                }
                "-" -> {
                    stack.push(stack.eval { o1, o2 -> o1 - o2 })
                }
                "*" -> {
                    stack.push(stack.eval { o1, o2 -> o1 * o2 })
                }
                "/" -> {
                    stack.push(stack.eval { o1, o2 -> o1 / o2 })
                }
                else -> {
                    stack.push(token.toFloat())
                }
            }
        }
        return stack.pop()
    }
}
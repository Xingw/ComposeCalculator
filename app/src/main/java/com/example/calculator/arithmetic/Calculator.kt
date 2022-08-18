package com.example.calculator.arithmetic

import java.util.*
import kotlin.collections.ArrayDeque

class Calculator {
    var display = ""

    fun calculate(s: String): Int {
        System.out.println(s.toRPN().joinToString())
        return s.toRPN().evalRPN()
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
        for ((index, c) in this!!.withIndex()) {
            when (c) {
                ' ' -> {
                }
                in '0'..'9' -> {
                    num = num * 10 + (c.toInt() - 48)
                    if (index + 1 >= length || !(this[index + 1] in '0'..'9')) {
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
            null -> -1
            else -> throw IllegalStateException()
        }
    }

    private fun Deque<Int>.eval(operator: (Int, Int) -> Int): Int {
        val num1 = pop()
        val num2 = pop()

        return operator(num2, num1)
    }

    private fun List<String>?.evalRPN(): Int {
        if (this.isNullOrEmpty()) {
            return 0
        }

        val stack = ArrayDeque<Int>()

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
                    stack.push(token.toInt())
                }
            }
        }
        return stack.pop()
    }
}
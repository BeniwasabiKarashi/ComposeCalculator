package com.example.composecalculator.data

import android.util.Log

class Calculate {
    fun getResultString(formula: String): String {
        val result = calculateRpn(inToRpn(formula))
        return if (isInt(result)){
            result.toInt().toString()
        } else {
            result.toString()
        }
    }

    private fun isInt(result: Double): Boolean {
        val regex = """\d+\.+(\d+)""".toRegex()
        return regex.matchEntire(result.toString())?.destructured?.component1().toString() == "0"
    }

    private fun inToRpn(formula: String): String {
        val operatorStack = ArrayDeque<Char>()
        val calculateList = mutableListOf<String>()
        var tempNumStr = ""

        Log.d("DEBUG LOG: formula",formula)
        try {
            formula.map { target ->
                when (target) {
                    '(' -> {
                        calculateList.add(tempNumStr)
                        tempNumStr = ""
                        operatorStack.add(target)
                    }
                    ')' -> {
                        calculateList.add(tempNumStr)
                        tempNumStr = ""
                        while (operatorStack.last() != '(') {
                            calculateList.add(operatorStack.removeLast().toString())
                        }
                        operatorStack.removeLast()
                    }
                    '×', '÷' -> {
                        calculateList.add(tempNumStr)
                        tempNumStr = ""
                        if (operatorStack.size > 0) {
                            when (operatorStack.last()) {
                                '×', '÷' -> {
                                    calculateList.add(operatorStack.removeLast().toString())
                                }
                            }
                        }
                        operatorStack.add(target)
                    }
                    '%' -> {
                        calculateList.add(tempNumStr)
                        tempNumStr = ""
                        if (operatorStack.size > 0) {
                            when (operatorStack.last()) {
                                '%', '×', '÷' -> {
                                    calculateList.add(operatorStack.removeLast().toString())
                                }
                            }
                        }
                        operatorStack.add(target)
                    }
                    '+', '-' -> {
                        calculateList.add(tempNumStr)
                        tempNumStr = target.toString()
                        if (operatorStack.size > 0) {
                            when (operatorStack.last()) {
                                '+', '-', '%', '×', '÷' -> {
                                    calculateList.add(operatorStack.removeLast().toString())
                                }
                            }
                        }
                        operatorStack.add('+')
                    }
                    else -> {
                        tempNumStr += target
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("ERROR LOG: Reverse Polish Notation", e.stackTrace.toString())
        }
        if (tempNumStr.toDoubleOrNull() != null ){
            calculateList.add(tempNumStr)
        }

        while (operatorStack.size > 0) {
            calculateList.add(operatorStack.removeLast().toString())
        }

        Log.d("DEBUG LOG: Reverse Polish Notation",calculateList.joinToString(separator = " "))

        return calculateList.joinToString(separator = " ")
    }

    private fun calculateRpn(formula: String): Double {
        val calculateList = formula.split(" ")
        val calculateStack = ArrayDeque<Double>()
        try {
            calculateList.map { target ->
                when {
                    target.toDoubleOrNull() != null -> {
                        calculateStack.add(target.toDouble())
                    }
                    target.contains('+') -> {
                        Log.d("DEBUG LOG: stack", calculateStack.joinToString(separator = " "))
                        val second = calculateStack.removeLast()
                        val first = calculateStack.removeLast()
                        calculateStack.add(first + second)
                    }
                    target.contains('%') -> {
                        val second = calculateStack.removeLast()
                        val first = calculateStack.removeLast()
                        calculateStack.add(first % second)
                    }
                    target.contains('×') -> {
                        val second = calculateStack.removeLast()
                        val first = calculateStack.removeLast()
                        calculateStack.add(first * second)
                    }
                    target.contains('÷') -> {
                        val second = calculateStack.removeLast()
                        val first = calculateStack.removeLast()
                        calculateStack.add(first / second)
                    }
                    else -> {
                        Log.d("DEBUG LOG: invalid data", target)
                    }
                }
            }
        } catch (e: Exception) {
        Log.e("ERROR LOG: Calculate RPN", e.stackTrace.toString())
    }
        return calculateStack.last()
    }
}

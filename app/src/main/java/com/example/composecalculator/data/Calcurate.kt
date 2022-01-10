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

    private fun isInt(result: Float): Boolean {
        val regex = """\d+\.+(\d+)""".toRegex()
        return regex.matchEntire(result.toString())?.destructured?.component1().toString() == "0"
    }

    private fun inToRpn(formula: String): String {
        val operatorStack = ArrayDeque<Char>()
        val calculateList = mutableListOf<String>()
        var tempNumStr = ""

        Log.d("DEBUG LOG: formula",formula)
        for (i in formula.indices) {
            when (formula[i]) {
                '(' -> {
                    calculateList.add(tempNumStr)
                    tempNumStr = ""
                    operatorStack.add(formula[i])
                }
                ')' -> {
                    calculateList.add(tempNumStr)
                    tempNumStr = ""
                    while (operatorStack.last() != '(') {
                        calculateList.add(operatorStack.removeLast().toString())
                    }
                    operatorStack.removeLast()
                }
                '×','÷' -> {
                    calculateList.add(tempNumStr)
                    tempNumStr = ""
                    if (operatorStack.size > 0) {
                        when(operatorStack.last()) {
                            '×','÷' -> {
                                calculateList.add(operatorStack.removeLast().toString())
                            }
                        }
                    }
                    operatorStack.add(formula[i])
                }
                '%' -> {
                    calculateList.add(tempNumStr)
                    tempNumStr = ""
                    if (operatorStack.size > 0) {
                        when(operatorStack.last()) {
                            '%','×','÷' -> {
                                calculateList.add(operatorStack.removeLast().toString())
                            }
                        }
                    }
                    operatorStack.add(formula[i])
                }
                '+','-' -> {
                    calculateList.add(tempNumStr)
                    tempNumStr = ""
                    if (operatorStack.size > 0) {
                        when(operatorStack.last()) {
                            '+','-','%','×','÷' -> {
                                calculateList.add(operatorStack.removeLast().toString())
                            }
                        }
                    }
                    operatorStack.add(formula[i])
                }
                else -> {
                    tempNumStr += formula[i]
                }

            }
        }

        if (tempNumStr.toFloatOrNull() != null ){
            calculateList.add(tempNumStr)
        }

        while (operatorStack.size > 0) {
            calculateList.add(operatorStack.removeLast().toString())
        }

        Log.d("DEBUG LOG: Reverse Polish Notation",calculateList.joinToString(separator = " "))

        return calculateList.joinToString(separator = " ")
    }

    private fun calculateRpn(formula: String): Float {
        val calculateList = formula.split(" ")
        val calculateStack = ArrayDeque<Float>()
        for (i in calculateList.indices) {
            when {
                calculateList[i].toFloatOrNull() != null -> {
                    calculateStack.add(calculateList[i].toFloat())
                }
                calculateList[i].contains('+') -> {
                    Log.d("DEBUG LOG: stack",calculateStack.joinToString(separator = " "))
                    val second = calculateStack.removeLast()
                    val first = calculateStack.removeLast()
                    calculateStack.add(first+second)
                }
                calculateList[i].contains('-') -> {
                    val second = calculateStack.removeLast()
                    val first = calculateStack.removeLast()
                    calculateStack.add(first-second)
                }
                calculateList[i].contains('%') -> {
                    val second = calculateStack.removeLast()
                    val first = calculateStack.removeLast()
                    calculateStack.add(first%second)
                }
                calculateList[i].contains('×') -> {
                    val second = calculateStack.removeLast()
                    val first = calculateStack.removeLast()
                    calculateStack.add(first*second)
                }
                calculateList[i].contains('÷') -> {
                    val second = calculateStack.removeLast()
                    val first = calculateStack.removeLast()
                    calculateStack.add(first/second)
                }
                else -> {
                    Log.d("DEBUG LOG: invalid data",calculateList[i])
                }
            }
        }
        return calculateStack.last()
    }
}
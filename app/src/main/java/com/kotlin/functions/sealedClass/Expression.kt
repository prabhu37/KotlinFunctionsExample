package com.kotlin.functions.sealedClass
/**
 * Created by prabhakaranpanjalingam on 18,July,2021
 */
sealed class Expression{
    data class Const(val number:Double): Expression()
    data class Sum(val e1: Expression, val e2: Expression): Expression()
    object NotAnNumber: Expression()
}

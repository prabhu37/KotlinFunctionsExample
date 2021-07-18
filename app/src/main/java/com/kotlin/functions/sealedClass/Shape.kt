package com.kotlin.functions.sealedClass
/**
 * Created by prabhakaranpanjalingam on 18,July,2021
 */
sealed class Shape{
    object None : Shape()
    data class Rectangle(val width:Float,var height:Float):Shape()
    data class Circle(val radius:Float):Shape()
}

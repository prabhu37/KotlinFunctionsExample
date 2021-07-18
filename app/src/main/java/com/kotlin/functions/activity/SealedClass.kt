package com.kotlin.functions.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import com.kotlin.functions.R
import com.kotlin.functions.sealedClass.Entity
import com.kotlin.functions.sealedClass.Expression
import com.kotlin.functions.sealedClass.Shape
import kotlin.math.PI
import kotlin.math.pow
/**
 * Created by prabhakaranpanjalingam on 18,July,2021
 */
class SealedClass : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sealed_class)



    }

    fun exampleOne(v: View){
        val shape = Shape.Rectangle(12f,16f)
         val result =  areaOf(shape)
        Toast.makeText(this,"we can't instantiate the sealed class.",Toast.LENGTH_LONG).show()
        println("SealedClass example one : ${result}")
        Handler().postDelayed({
            Toast.makeText(this,"Sealed means you can not use this class as a base class.",Toast.LENGTH_LONG).show()
        },3000)
    }

    fun exampleTwo(v: View){
        val expression = Expression.Sum(Expression.Const(1.0), Expression.Const(2.0))
        val result = evaluation(expression)
        Toast.makeText(this,"Sealed class have different properties and only have primary constructor",Toast.LENGTH_LONG).show()
        println("SealedClass example two : ${result}")
    }

    fun exampleThree(v: View){
        val entity = Entity.Low("12","LowLevel")
        val result = checkLevel(entity)
        Toast.makeText(this,"Sealed class have defined as class and objects or data classes",Toast.LENGTH_LONG).show()
        println("SealedClass example two : ${result}")
    }

    private fun evaluation(expression: Expression):Double = when(expression){
        is Expression.Const ->expression.number
        Expression.NotAnNumber -> Double.NaN
        is Expression.Sum -> evaluation(expression.e1)+evaluation(expression.e2)


    }

    private fun areaOf(shape: Shape):Number  = when(shape){
        is Shape.Circle -> shape.radius.pow(2)* PI
        is Shape.None -> 0
        is Shape.Rectangle -> shape.width+shape.height
    }

    private fun checkLevel(entity: Entity):String  = when(entity){
        is Entity.Help -> entity.helpName
        is Entity.High -> entity.name
        is Entity.Low -> entity.name
        is Entity.Medium -> entity.name
    }
}


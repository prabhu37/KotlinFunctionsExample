package com.kotlin.functions.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import com.kotlin.functions.R
import com.kotlin.functions.data.Square

/**
 * Created by prabhakaranpanjalingam on 18,July,2021
 */
/* Kotlin Scope function
+-----------+---------------+------------------+
| FUnctions |Context Object | Return Value     |
+-----------+---------------+------------------+
|   let     |   it          | lambda result    |
|   run     |   this        | lambda result    |
|   with    |   this        | lambda result    |
|   apply   |   this        | context object   |
|   also    |   it          | content object   |
+-----------+---------------+------------------+
*/

class ScopedFunctions : AppCompatActivity() {
    var square: Square?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scoped_functions)
    }

    fun alsoButton(v: View){
        val  square = Square(10,20).also {
            it.text = "Also function"
            it.color = "Red"
            it.width++
        }
        Toast.makeText(this@ScopedFunctions, "If you want to do some additional object configuration or operations", Toast.LENGTH_LONG).show()
        println("get all Width Value from also's context object :${square.width}")
    }
    fun applyButton(v:View){
        val  square = Square().apply {
              width = 40
              height = 60
              text = "Apply Function"
              color = "Yellow"

        }
        Toast.makeText(this@ScopedFunctions, "If you want to initialize or configure an object", Toast.LENGTH_LONG).show()
        println("get all height Value from apply's context object :${square.height}")

    }
    fun withButton(v:View){
       val square = Square(10,20,"","")
       val getHeight:Int =  with(square) {
            text = "With function"
            color = "Blue"
             height-10
        }
        Toast.makeText(this@ScopedFunctions, "If you want to operate non-nullable object", Toast.LENGTH_LONG).show()
        println("Get Currently updated Height Value  from with :$getHeight")

    }
    fun runButton(v:View){
        square = Square(10,20)
        val  getText:String? = square?.run {
            text = "Run function"
             color = "Green"
            text!!
        }
        Toast.makeText(this@ScopedFunctions, "If you want operate nullable object and execute lambda expression", Toast.LENGTH_LONG).show()
        println("Get Currently updated text Value from run:$getText")
        Handler().postDelayed({ Toast.makeText(this@ScopedFunctions, "also avoid null pointer exception", Toast.LENGTH_LONG).show() },5000)

        Handler().postDelayed({ Toast.makeText(this@ScopedFunctions, "Run function combination of with and let", Toast.LENGTH_LONG).show() },7000)
    }
    fun letButton(v:View){
          //if square value is null
          val getHeight:Int =  square?.let { square ->
                square.text = "Let function"
                square.color= "Red"
               square.height = 10
               square.width = 5
                square.height +10
            } ?:0
        Toast.makeText(this@ScopedFunctions, "If you want execute lambda expression on a nullable object", Toast.LENGTH_LONG).show()
        println("Get Currently updated Width Value from let :$getHeight")
        Handler().postDelayed({ Toast.makeText(this@ScopedFunctions, "And avoid null pointer exception", Toast.LENGTH_LONG).show() },3000)

    }
}
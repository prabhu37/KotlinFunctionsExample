package com.kotlin.functions.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.kotlin.functions.R
/**
 * Created by prabhakaranpanjalingam on 18,July,2021
 */
/*Higher order function
A higher-order function is a function that takes functions as parameters or returns a function.

It's a function which can take do two things:

Can take functions as parameters
Can return a function


*/

class HigherOrderFunction : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_higher_order_function)
    }

    fun functionAsArgument(v: View){
        higherOrderFunction1(::sayHello, "Prabha")
        Toast.makeText(this, "Function that takes function as parameter",Toast.LENGTH_LONG).show()
    }

    fun functionAsReturnValue(v:View){
        val functionName = higherOrderFunction2()
        functionName("Prabhakaran")
        Toast.makeText(this, "Function that returns a function",Toast.LENGTH_LONG).show()
    }

    private fun higherOrderFunction1(functionName: (name: String)-> Unit, name: String){
        println("In higher order function")
        println("Calling sayHello() function...")
        functionName(name)
    }
    private fun sayHello(name: String){
        println("In sayHello() function")
        println("Say hello to $name")
    }

    private fun higherOrderFunction2(): (String) -> Unit {
        println("In higher order function")
        // return the sayHello function
        return ::sayHello
    }

}
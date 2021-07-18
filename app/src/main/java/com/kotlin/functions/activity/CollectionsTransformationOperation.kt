package com.kotlin.functions.activity

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kotlin.functions.R
/**
 * Created by prabhakaranpanjalingam on 18,July,2021
 */
class CollectionsTransformationOperation : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flap_map)

    }

    @ExperimentalStdlibApi
    fun map(v: View){
        val numbers = setOf(1, 2, 3)
        println(numbers.map { it * 3 })
        println(numbers.mapIndexed { idx, value -> value * idx })
        println(numbers.mapNotNull { if ( it == 2) null else it * 3 })
        println(numbers.mapIndexedNotNull { idx, value -> if (idx == 0) null else value * idx })
        val numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key11" to 11)
        println(numbersMap.mapKeys { it.key.uppercase() })
        println(numbersMap.mapValues { it.value + it.key.length })
        Toast.makeText(this@CollectionsTransformationOperation, "creates a collection from the results of a function on the elements of another collection.",Toast.LENGTH_LONG).show()
        Handler().postDelayed(object :Runnable{
            override fun run() {
                Toast.makeText(this@CollectionsTransformationOperation, "that additionally uses the element index as an argument",Toast.LENGTH_LONG).show()
            }

        },2000)
    }

    fun zip(v: View){
        val colors = listOf("red", "brown", "grey")
        val animals = listOf("fox", "bear", "wolf")
        println(colors zip animals)

        val twoAnimals = listOf("fox", "bear")
        println(colors.zip(twoAnimals))

        Toast.makeText(this@CollectionsTransformationOperation, "zip() returns the List of Pair objects",Toast.LENGTH_LONG).show()
        Handler().postDelayed(object :Runnable{
            override fun run() {
                Toast.makeText(this@CollectionsTransformationOperation, "building pairs from elements with the same positions in both collections",Toast.LENGTH_LONG).show()
            }

        },2000)
    }

    fun unZip(v: View){
        val numberPairs = listOf("one" to 1, "two" to 2, "three" to 3, "four" to 4)
        println(numberPairs.unzip())
        Toast.makeText(this@CollectionsTransformationOperation, "you can do the reverse transformation – unzipping",Toast.LENGTH_LONG).show()
        Handler().postDelayed(object :Runnable{
            override fun run() {
                Toast.makeText(this@CollectionsTransformationOperation, "that builds two lists from these pairs",Toast.LENGTH_LONG).show()
            }

        },2000)
    }

    fun flatten(v: View){
        val numberSets = listOf(setOf(1, 2, 3), setOf(4, 5, 6), setOf(1, 2))
        println(numberSets.flatten())
        Toast.makeText(this@CollectionsTransformationOperation, "You can call it on a collection of collections",Toast.LENGTH_LONG).show()
        Handler().postDelayed(object :Runnable{
            override fun run() {
                Toast.makeText(this@CollectionsTransformationOperation, "The function returns a single List of all the elements of the nested collections",Toast.LENGTH_LONG).show()
            }

        },2000)
    }

    fun flatMap(v: View){
        data class StringContainer(val values: List<String>)
        val containers = listOf(
            StringContainer(listOf("one", "two", "three")),
            StringContainer(listOf("four", "five", "six")),
            StringContainer(listOf("seven", "eight")))
        println(containers.flatMap { it.values })
        Toast.makeText(this@CollectionsTransformationOperation, "provides a flexible way to process nested collections",Toast.LENGTH_LONG).show()
        Handler().postDelayed(object :Runnable{
            override fun run() {
                Toast.makeText(this@CollectionsTransformationOperation, "behaves as a subsequent call of map() and flatten()",Toast.LENGTH_LONG).show()
            }

        },2000)

    }

    @ExperimentalStdlibApi
    fun associate(v:View){
        val numbers = listOf("one", "two", "three", "four")
        println(numbers.associateWith { it.length })

        println(numbers.associateBy { it.first().uppercaseChar() })
        println(numbers.associateBy(keySelector = { it.first().uppercaseChar() }, valueTransform = { it.length }))
        Toast.makeText(this@CollectionsTransformationOperation, "creates a Map in which the elements of the original collection are keys, and",Toast.LENGTH_LONG).show()
        Handler().postDelayed(object :Runnable{
            override fun run() {
                Toast.makeText(this@CollectionsTransformationOperation, "values are produced from them by the given transformation function",Toast.LENGTH_LONG).show()
            }

        },2000)

    }



}
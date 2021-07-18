package com.kotlin.functions.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.kotlin.functions.R
/**
 * Created by prabhakaranpanjalingam on 18,July,2021
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun scopedFunctionsButton(v: View){
        val intent = Intent(this,ScopedFunctions::class.java)
        startActivity(intent)

    }

    fun higherOrderFunction(v: View){
        val intent = Intent(this,HigherOrderFunction::class.java)
        startActivity(intent)

    }
    fun SealedClass(v:View){
        val intent = Intent(this,SealedClass::class.java)
        startActivity(intent)

    }
    fun collectionTransformation(v:View){
        val intent = Intent(this,CollectionsTransformationOperation::class.java)
        startActivity(intent)

    }
    fun coroutines(v:View){
        val intent = Intent(this,Coroutines::class.java)
        startActivity(intent)

    }
}
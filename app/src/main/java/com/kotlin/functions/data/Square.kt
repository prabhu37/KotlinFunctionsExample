package com.kotlin.functions.data

import android.util.Log
/**
 * Created by prabhakaranpanjalingam on 18,July,2021
 */
class Square( var width:Int,
              var height:Int,
               var color:String?=null,
               var text:String?=null) {
    constructor() : this(width = 0,height = 0)


    override fun toString(): String {
        return "Square(width=$width, height=$height, color=$color, text=$text)"
    }
}
package com.kotlin.functions.sealedClass
/**
 * Created by prabhakaranpanjalingam on 18,July,2021
 */
sealed class Entity {

    data class Low(val id:String,val name:String):Entity()
    data class Medium(val id:String,val name:String):Entity()
    data class High(val id:String,val name:String):Entity()
    object Help:Entity(){
        val helpName = "help"
    }
}
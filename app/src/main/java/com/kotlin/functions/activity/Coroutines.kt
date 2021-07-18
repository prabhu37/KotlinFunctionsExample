package com.kotlin.functions.activity

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.kotlin.functions.R
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlin.system.measureTimeMillis
/**
 * Created by prabhakaranpanjalingam on 18,July,2021
 */
class Coroutines : AppCompatActivity() {

    private val JOB_TIMEOUT = 8000L
    private val INTERVAL_DELAY = 3000L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines)
    }

    fun delay(v: View) {
        delayFuncttion()
    }

    fun timeOut(v: View) {
        timeOutFunction()
    }

    fun parallel(v: View) {
        parallelFunctions()
    }

    fun sequential(v: View) {
        sequentialFunctions()

    }

    fun exceptionHandling(v: View) {
        exceptionHandler()
    }


    fun supervisorScope(v: View) {
        supervisorScopeFunction()
    }

    val handler = CoroutineExceptionHandler { _, exception ->
        Log.e("Error :", "Exception thrown in one of the children : $exception")
    }

    fun exceptionHandler() {
        val parent = CoroutineScope(IO).launch(handler) {

            val jobA = launch {
                val resultA = getResult(1)
                Log.e("ResultA : ", resultA.toString())
            }
            jobA.invokeOnCompletion { throwable ->
                if (throwable != null) {
                    Log.e("Exception : ", "Error getting resultA: ${throwable}")
                }

            }
            val jobB = launch {
                val resultA = getResult(2)
                Log.e("ResultB : ", resultA.toString())
            }
            jobB.invokeOnCompletion { throwable ->
                if (throwable != null) {
                    Log.e("Exception : ", "Error getting resultB: ${throwable}")
                }
            }
            val jobC = launch {
                val resultA = getResult(3)
                Log.e("ResultC : ", resultA.toString())
            }
            jobC.invokeOnCompletion { throwable ->
                if (throwable != null) {
                    Log.e("Exception : ", "Error getting resultC: ${throwable}")
                }
            }
            Toast.makeText(
                this@Coroutines,
                "If we might be handle with the scenario for exception, we can use cancellationException",
                Toast.LENGTH_LONG
            ).show()

        }

        parent.invokeOnCompletion { throwable ->
            if (throwable != null) {
                Log.e("Exception : ", "ParentJob : ${throwable}")
            } else {
                Log.e("Exception : ", "ParentJob Success")
            }
        }

    }

    fun supervisorScopeFunction() {
        val parent = CoroutineScope(IO).launch(handler) {

            supervisorScope {
                val jobA = launch {
                    val resultA = getSuperVisorResult(1)
                    Log.e("ResultA : ", resultA.toString())
                }
                jobA.invokeOnCompletion { throwable ->
                    if (throwable != null) {
                        Log.e("Exception : ", "Error getting resultA: ${throwable}")
                    }
                }
                val jobB = launch {
                    val resultA = getSuperVisorResult(2)
                    Log.e("ResultB : ", resultA.toString())
                }
                jobB.invokeOnCompletion { throwable ->
                    if (throwable != null) {
                        Log.e("Exception : ", "Error getting resultB: ${throwable}")
                    }

                }
                val jobC = launch {
                    val resultA = getSuperVisorResult(3)
                    Log.e("ResultC : ", resultA.toString())
                }
                jobC.invokeOnCompletion { throwable ->
                    if (throwable != null) {
                        Log.e("Exception : ", "Error getting resultC: ${throwable}")
                    }
                }

            }

            Toast.makeText(
                this@Coroutines,
                "if anyone of the task might be getting an error other tasks will run without any affecting",
                Toast.LENGTH_LONG
            ).show()
        }

        parent.invokeOnCompletion { throwable ->
            if (throwable != null) {
                Log.e("Exception : ", "ParentJob : ${throwable}")
            } else {
                Log.e("Exception : ", "ParentJob Success")
            }
        }

    }

    suspend fun getResult(value: Int): Int {
        delay(value * 500L)
        if (value == 2) {
            throw CancellationException("Error getting result from: $value")
        }

        return value * 2

    }

    suspend fun getSuperVisorResult(value: Int): Int {
        delay(value * 500L)
        if (value == 2) {
            throw Exception("Error getting result from: $value")
        }

        return value * 2

    }

    fun parallelFunctions() {
        CoroutineScope(IO).launch {
            val executionsTime = measureTimeMillis {
                val getFirstRecord: Deferred<String> = async {
                    firstParallel()
                }
                val getSecondRecord: Deferred<String> = async {
                    secondParallel()
                }
                Log.e("FirstParallel Result:", getFirstRecord.await())
                Log.e("SecondParallel Result:", getSecondRecord.await())

            }
            Log.e(
                "ParallelExecutionTimes:",
                (executionsTime / 1000).toFloat().toString()
            )

        }

        Toast.makeText(
            this@Coroutines,
            "we can execute two coroutines tasks at the same time",
            Toast.LENGTH_LONG
        ).show()
    }

    @SuppressLint("LongLogTag")
    fun sequentialFunctions() {
        CoroutineScope(IO).launch {
            val executionsTime = measureTimeMillis {
                val getFirstRecord: String = async {
                    firstSequential()
                }.await()
                val getSecondRecord: String = async {
                    secondSequential(getFirstRecord)
                }.await()
                Log.e("SecondSequentialResult:", getSecondRecord)

            }
            Log.e(
                "SequentialExecutionTime:",
                (executionsTime / 1000).toString()
            )

        }

        Toast.makeText(
            this@Coroutines,
            "we can  execute two coroutines tasks at one by one",
            Toast.LENGTH_LONG
        ).show()
    }


    suspend fun firstParallel(): String {
        val time = System.currentTimeMillis()
        delay(3000)
        Log.e("FirstParallel Called:", (System.currentTimeMillis() - time).toString())
        return "FirstParallel"
    }

    suspend fun secondParallel(): String {
        val time = System.currentTimeMillis()
        delay(1500)
        Log.e("SecondParallel Called:", (System.currentTimeMillis() - time).toString())
        return "SecondParallel"
    }

    suspend fun firstSequential(): String {
        val time = System.currentTimeMillis()
        delay(2000)
        Log.e("FirstSequential Called:", (System.currentTimeMillis() - time).toString())
        return "FirstSequential"
    }

    @SuppressLint("LongLogTag")
    suspend fun secondSequential(value: String): String {
        val time = System.currentTimeMillis()
        if (value.equals("FirstSequential"))
            return "SecondSequential Success"
        else
            return "SecondSequential Failed"

    }


    fun delayFuncttion() {
        GlobalScope.launch(Dispatchers.IO) {
            while (true) {
                launch(Dispatchers.IO) {
                    runOnUiThread {
                        Toast.makeText(
                            this@Coroutines,
                            "Delay will call every interval time with while statement from coroutineScope",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }
                Log.e(
                    "Delay :",
                    "Delay will call every interval time from respective Coroutines scope"
                )
                delay(INTERVAL_DELAY)
            }
        }
    }


    fun timeOutFunction() {
        GlobalScope.launch(Dispatchers.IO) {
            val job = withTimeoutOrNull(JOB_TIMEOUT) {

                firstFunction()
                secondFunction()
                thirdFunction()

            }

            if (job == null) {
                runOnUiThread {
                    Toast.makeText(
                        this@Coroutines,
                        "Timeout : cancelling the function call",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.e(
                        "TimeOut :",
                        "function call cancelled"
                    )
                }
            }


        }
    }

    suspend fun firstFunction() {
        delay(INTERVAL_DELAY)
        runOnUiThread {
            Toast.makeText(
                this@Coroutines,
                "Timeout : calling first function",
                Toast.LENGTH_SHORT
            ).show()
            Log.e(
                "TimeOut :",
                "First function called "
            )
        }
    }

    suspend fun secondFunction() {
        delay(INTERVAL_DELAY)
        runOnUiThread {
            Toast.makeText(
                this@Coroutines,
                "Timeout : calling second function",
                Toast.LENGTH_SHORT
            ).show()
            Log.e(
                "TimeOut :",
                "Second function called "
            )
        }
    }

    suspend fun thirdFunction() {
        delay(INTERVAL_DELAY)
        runOnUiThread {
            Toast.makeText(
                this@Coroutines,
                "Timeout : calling third function",
                Toast.LENGTH_SHORT
            ).show()
            Log.e(
                "TimeOut :",
                "Third function called "
            )
        }
    }


}

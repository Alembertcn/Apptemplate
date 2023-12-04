package com.king.template

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.coroutines.CoroutineContext

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        runBlocking {
           var job = launch(start = CoroutineStart.LAZY) {
                System.out.println("inner before delay")
                delay(20)
                System.out.println("inner after delay")
            }
            job.start()

            System.out.println("before delay")
            delay(20)
            System.out.println("after delay")
        }
        System.out.println("end")

    }
}

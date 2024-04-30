package B_2024_04

import java.io.*
import java.util.*

class BOJ11022 {
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val T = br.readLine().toInt();
        for(i in 1..T) {
            val line_1 = br.readLine().split(" ")
            val A = line_1[0].toInt()
            val B = line_1[1].toInt()
            println("Case #${i}: ${A} + ${B} = ${A+B}")
        }
    }
}
package B_2024_04

import java.io.*
import java.util.*
import kotlin.math.max

class BOJ10101 {
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val a1 = br.readLine().toInt()
        val a2 = br.readLine().toInt()
        val a3 = br.readLine().toInt()
        println(rst(a1, a2, a3))
    }

    fun rst(a1:Int, a2:Int, a3:Int): String {
        return if(a1==60 && a2==60 && a3==60) "Equilateral"
        else if(a1+a2+a3!=180) "Error"
        else if(a1==a2 || a1==a3 || a2==a3) "Isosceles"
        else "Scalene"
    }
}
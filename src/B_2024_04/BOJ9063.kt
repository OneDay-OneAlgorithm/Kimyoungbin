package B_2024_04

import java.io.*
import java.util.*
import kotlin.math.max

class BOJ9063 {
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val n = br.readLine().toInt()
        val INF = 0x3f3f3f3f
        var minX = INF
        var minY = INF
        var maxX = -INF
        var maxY = -INF
        repeat(n) {
            val (x,y) = br.readLine().split(" ").map { it.toInt() }
            minX = Math.min(minX, x)
            minY = Math.min(minY, y)
            maxX = Math.max(maxX, x)
            maxY = Math.max(maxY, y)
        }
        println((maxX-minX)*(maxY-minY))
    }
}
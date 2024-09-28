package B_2024_09

import java.io.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    while (true) {
        val input = br.readLine() // null인 경우 반복문 종료
        val tokens = input.split(" ")
        val N = tokens[0].toInt()
        val M = tokens[1].toInt()
        if (N == 0 && M == 0) break
        if (N > M) println("Yes")
        else println("No")
    }
}

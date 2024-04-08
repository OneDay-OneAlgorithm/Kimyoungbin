package B_2024_04

import java.io.*

class BOJ25314 {
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val N = br.readLine().toInt()

        for(i in 1..N/4) {
            print("long ")
        }
        print("int")
    }
}
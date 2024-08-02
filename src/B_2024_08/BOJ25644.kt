package B_2024_08

import java.io.*
import java.util.*

class BOJ25644 {
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val N = br.readLine().toInt()
        var arr = IntArray(N+1)
        var dp = IntArray(N+1) // dp[i]: 0..dp[i] 사이에 존재하는 최소값
        var rst = 0
        val line_2 = br.readLine().split(" ")
        for(i in 0..N-1) {
            arr[i] = line_2[i].toInt()
        }

        dp[0] = arr[0]
        for(i in 1..N-1) {
            dp[i] = Math.min(dp[i-1], arr[i])
        }
        for(i in 0..N-1) {
            rst = Math.max(rst, arr[i]-dp[i])
        }
        println(rst)
    }
}
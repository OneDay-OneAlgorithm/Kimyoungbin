package B_2024_04

import java.io.*
import java.util.*

class BOJ2872 {
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val N = br.readLine().toInt()
        var arr = IntArray(N+1)
        for(i in 1..N) {
            arr[i] = br.readLine().toInt()
        }
        var sortArr = arr.clone()
        Arrays.sort(sortArr)
        // 정렬된 부분 제외
        var tar = N
        var len = 0
        for(i in N downTo 1) {
            if(arr[i] == sortArr[tar]) {
                tar--
                len++
            }
        }

        println(N-len)
    }
}
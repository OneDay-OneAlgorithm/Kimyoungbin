package B_2024_04

import java.io.*
import java.util.*

class BOJ5073 {
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        while(true) {
            val input = br.readLine().split(" ")
            val s1 = input[0].toInt()
            val s2 = input[1].toInt()
            val s3 = input[2].toInt()
            if(s1==0 && s2==0 && s3==0) break
            println(rst(s1, s2, s3))
        }
    }

    fun rst(s1:Int, s2:Int, s3:Int): String {
        val arr = arrayOf(s1, s2, s3)
        Arrays.sort(arr)
        return if(arr[2]>=arr[0]+arr[1]) "Invalid"
        else if(arr[0]==arr[1]&& arr[1]==arr[2]) "Equilateral"
        else if(arr[0]==arr[1] || arr[1]==arr[2]) "Isosceles"
        else "Scalene"
    }
}
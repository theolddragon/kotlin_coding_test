import kotlin.math.sqrt

fun main() {
    val solution = 소수_만들기()
//    println(solution.solution(intArrayOf(1, 2, 3, 4)))
//    println(solution.solution(intArrayOf(1, 2, 7, 6, 4)))
//    println(solution.solution(intArrayOf(13, 100, 900)))
    println(solution.solution(intArrayOf(2, 4, 5, 6, 7, 8)))
}

private class 소수_만들기 {
    fun solution(nums: IntArray): Int {
        if (nums.size < 3 || nums.size > 50) {
            throw IllegalArgumentException()
        }

        if (nums.any { it < 1 || it > 1_000}) {
            throw IllegalArgumentException()
        }

        if (nums.groupBy { it }.any { it.value.size > 1}) {
            throw IllegalArgumentException()
        }

        return solve(nums)
    }

    private fun solve(nums: IntArray): Int {
        val indices = IntRange(0, 2)
        val minNumber = nums.sortedArray()
            .slice(indices)
            .sum()

        val sortedNumbers = nums.sortedArrayDescending()
        val maxNumber = sortedNumbers
            .slice(indices)
            .sum()

        val primeList = getPrimeList(minNumber, maxNumber)
        return isMatched(primeList, sortedNumbers)
    }

    private fun getPrimeList(start: Int, end: Int): List<Int> {
        val isPrime = BooleanArray(end + 1) { true }
        for (index in 2..sqrt(end.toDouble()).toInt()) {
            if (isPrime[index]) {
                for (value in index * index..end step index) {
                    isPrime[value] = false
                }
            }
        }

        IntRange(0, start - 1).forEach { index -> isPrime[index] = false }
        return isPrime
            .asList()
            .mapIndexedNotNull() { index, bool ->
                if (bool) index
                else null
            }
    }

    private fun isMatched(primeNumbers: List<Int>, sortedNumbers: IntArray): Int {
        println("primeNumbers = $primeNumbers")
        println("sortedNumbers = ${sortedNumbers.asList()}")

        var answer = 0
        var remainPrimeNumber = primeNumbers
        for (firstIndex in sortedNumbers.indices) {
            val firstNumber = sortedNumbers[firstIndex]
            for (secondIndex in firstIndex + 1 ..< sortedNumbers.size) {
                val secondNumber = sortedNumbers[secondIndex]
                for (thirdIndex in secondIndex + 1 ..< sortedNumbers.size) {
                    val thirdNumber = sortedNumbers[thirdIndex]
                    val sumNumbers = firstNumber + secondNumber + thirdNumber
                    println("$firstNumber + $secondNumber + $thirdNumber = $sumNumbers")
                    if (remainPrimeNumber.contains(sumNumbers)) {
                        answer++
                        remainPrimeNumber = remainPrimeNumber.filter { it != sumNumbers }

                        if (remainPrimeNumber.isEmpty()) {
                            break;
                        }
                    }
                }
            }
        }
        return answer
    }
}
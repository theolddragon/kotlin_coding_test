fun main() {
    val solution = 명예의_전당()
    println(solution.solution(3, intArrayOf(10, 100, 20, 150, 1, 100, 200)).asList())
    println(solution.solution(4, intArrayOf(0, 300, 40, 300, 20, 70, 150, 50, 500, 1000)).asList())
}

class 명예의_전당 {
    fun solution(k: Int, score: IntArray): IntArray {
        if (k < 3 || k > 100) {
            throw IllegalArgumentException()
        }

        val scoreLength = score.size
        if (scoreLength < 7 || scoreLength > 1_000) {
            throw IllegalArgumentException()
        }

        if (score.asList().any { it < 0 || it > 2_000 }) {
            throw IllegalArgumentException()
        }

        return solve(k, score)
    }

    private fun solve(k: Int, score: IntArray): IntArray {
        var todayTopScores: List<Int> = IntRange(1, k).map { -1 }
        var result = IntArray(score.size)
        score.forEachIndexed { dayIndex, value ->
            todayTopScores = (todayTopScores + value)
                .sortedByDescending { it }
                .slice(IntRange(0, k - 1))
            println(todayTopScores)

            result[dayIndex] = todayTopScores.last { it >= 0 }
        }
        return result
    }
}
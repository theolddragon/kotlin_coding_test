fun main() {
    val solution = 안전지대()

    val result1 = solution.solution(
        arrayOf(
            intArrayOf(0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0),
            intArrayOf(0, 0, 1, 0, 0),
            intArrayOf(0, 0, 0, 0, 0),
        )
    )
    println(result1)

    val result2 = solution.solution(
        arrayOf(
            intArrayOf(0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0),
            intArrayOf(0, 0, 1, 1, 0),
            intArrayOf(0, 0, 0, 0, 0),
        )
    )
    println(result2)

    val result3 = solution.solution(
        arrayOf(
            intArrayOf(1, 1, 1, 1, 1, 1),
            intArrayOf(1, 1, 1, 1, 1, 1),
            intArrayOf(1, 1, 1, 1, 1, 1),
            intArrayOf(1, 1, 1, 1, 1, 1),
            intArrayOf(1, 1, 1, 1, 1, 1),
            intArrayOf(1, 1, 1, 1, 1, 1),
        )
    )
    println(result3)
}

private class 안전지대 {
    fun solution(board: Array<IntArray>): Int {
        if (board.isEmpty() || board.size > 100) {
            throw IllegalArgumentException()
        }
        if (board.any { it.isEmpty() || it.size > 100 }) {
            throw IllegalArgumentException()
        }

        return solve(board)
    }

    fun solve(board: Array<IntArray>): Int {
        val safeZone: Array<BooleanArray> = board
            .map { law -> law.map { true }.toBooleanArray() }
            .toTypedArray()

        val lawLength = board.size
        board.forEachIndexed { lawIndex, law ->
            val columnLength = law.size
            law.forEachIndexed { columnIndex, column ->
                if (column == 1) {
                    if (lawIndex > 0) {
                        if (columnIndex > 0) safeZone[lawIndex-1][columnIndex-1] = false
                        safeZone[lawIndex-1][columnIndex] = false
                        if (columnIndex + 1 < columnLength) safeZone[lawIndex-1][columnIndex+1] = false
                    }
                    if (columnIndex > 0) safeZone[lawIndex][columnIndex-1] = false
                    safeZone[lawIndex][columnIndex] = false
                    if (columnIndex + 1 < columnLength) safeZone[lawIndex][columnIndex+1] = false
                    if (lawIndex + 1 < lawLength) {
                        if (columnIndex > 0) safeZone[lawIndex+1][columnIndex-1] = false
                        safeZone[lawIndex+1][columnIndex] = false
                        if (columnIndex + 1 < columnLength) safeZone[lawIndex+1][columnIndex+1] = false
                    }
                }
            }
        }

        println(safeZone.asList().forEach { println(it.asList()) })
        return safeZone.asList().sumOf { law -> law.count { column -> column } }
    }
}
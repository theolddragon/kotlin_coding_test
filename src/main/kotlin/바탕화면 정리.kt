import kotlin.math.max
import kotlin.math.min

fun main() {
    val solution = 바탕화면_정리()
    val message1 = solution.solution(arrayOf(".#...", "..#..", "...#."))
    println(message1.asList())

    val message2 = solution.solution(arrayOf("..........", ".....#....", "......##..", "...##.....", "....#....."))
    println(message2.asList())

    val message3 = solution.solution(arrayOf(".##...##.", "#..#.#..#", "#...#...#", ".#.....#.", "..#...#..", "...#.#...", "....#...."))
    println(message3.asList())

    val message4 = solution.solution(arrayOf("..", "#."))
    println(message4.asList())
}

private class 바탕화면_정리 {
    fun solution(wallpaper: Array<String>): IntArray {
        if (wallpaper.isEmpty() || wallpaper.size > 50) {
            throw IllegalArgumentException()
        }

        if (wallpaper.any { law -> law.isEmpty() || law.length > 50 }) {
            throw IllegalArgumentException()
        }

        if (wallpaper.map { law -> law.length }.distinct().size > 1) {
            throw IllegalArgumentException()
        }

        val regex = Regex("[#.]*")
        if (wallpaper.any { law -> !law.matches(regex) }) {
            throw IllegalArgumentException()
        }

        if (wallpaper.none { law -> law.contains("#") }) {
            throw IllegalArgumentException()
        }

        return solve(wallpaper)
    }

    private fun solve(wallpaper: Array<String>): IntArray {
        var luPoint: Pair<Int, Int>? = null
        var rdPoint: Pair<Int, Int>? = null

        wallpaper.forEachIndexed { lawIndex, law ->
            law.toCharArray().forEachIndexed { columnIndex, char ->
                if (char == '#') {
                    if (luPoint == null) {
                        luPoint = lawIndex to columnIndex
                    } else {
                        val lux = min(luPoint!!.first, lawIndex)
                        val luy = min(luPoint!!.second, columnIndex)
                        luPoint = lux to luy
                    }

                    if (rdPoint == null) {
                        rdPoint = lawIndex + 1 to columnIndex + 1
                    } else {
                        val rdx = max(rdPoint!!.first, lawIndex + 1)
                        val rdy = max(rdPoint!!.second, columnIndex + 1)
                        rdPoint = rdx to rdy
                    }
                }
            }
        }

        return intArrayOf(luPoint!!.first, luPoint!!.second, rdPoint!!.first, rdPoint!!.second)
    }
}
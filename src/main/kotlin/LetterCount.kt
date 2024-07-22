import kotlin.math.max

fun LetterCount(str: String): String {
    var result = "" to -1
    str.split(" ").forEach { letter ->
        val maxCharCount = letter.toCharArray().groupBy { it }.map { it.value.count() }.max()
        if (maxCharCount > 1 && result.second < maxCharCount) {
            result = letter to maxCharCount
        }
    }
    return if (result.second == -1) "-1"
    else result.first
}
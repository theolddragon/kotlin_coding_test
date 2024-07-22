class ArrayRotation {
    fun ArrayRotation(arr: Array<Int>): Int {
        val firstInteger = arr[0]
        val backArray = arr.sliceArray(IntRange(firstInteger, arr.size))
        val frontArray = arr.sliceArray(IntRange(0, firstInteger))
        return backArray
            .plus(frontArray)
            .map { "$it" }
            .fold("") { acc, i -> acc + i }
            .toInt()

    }
}
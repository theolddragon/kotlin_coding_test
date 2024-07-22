class StepWalking {
    private fun fibonacciDefalut(n: Int): Int {
        return if (n <= 2) 1
        else fibonacciDefalut(n - 1) + fibonacciDefalut(n - 2)
    }
}
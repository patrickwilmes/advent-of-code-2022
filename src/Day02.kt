private fun translate(input: String): String = when (input) {
    "X" -> "A"
    "Y" -> "B"
    "Z" -> "C"
    else -> throw IllegalArgumentException("Not a valid RPS symbol")
}

private fun getScoreFor(rpc: String): Int = when (rpc) {
    "A" -> 1 // rock
    "B" -> 2 // paper
    "C" -> 3 // scissors
    else -> throw IllegalArgumentException("Invalid RPC: $rpc")
}

private fun getScoreFor(opponent: String, own: String): Int {
    if (opponent == own) return getScoreFor(own) + 3
    return when (opponent) {
        "A" -> if (own == "C") getScoreFor(own) else getScoreFor(own) + 6
        "B" -> if (own == "C") getScoreFor(own) + 6 else getScoreFor(own)
        "C" -> if (own == "A") getScoreFor(own) + 6 else getScoreFor(own)
        else -> throw IllegalArgumentException("Invalid RPC: $opponent")
    }
}

private fun determineMySymbol(opponent: String, roundEnding: String): String {
    if (roundEnding == "Y") return opponent
    if (roundEnding == "X") return when(opponent) {
        "A" -> "C"
        "B" -> "A"
        "C" -> "B"
        else -> throw IllegalArgumentException("No valid condition found")
    }
    return when(opponent) {
        "A" -> "B"
        "B" -> "C"
        "C" -> "A"
        else -> throw IllegalArgumentException("No valid condition found")
    }
}

fun main() {
    fun List<String>.parse(shouldTranslate: Boolean = false) = map {
        val parts = it.split(" ")
        val opponent = parts[0]
        val own = if (shouldTranslate) translate(parts[1]) else parts[1]
        opponent to own
    }

    fun part1(input: List<String>): Int =
        input.parse(shouldTranslate = true).sumOf {
                getScoreFor(it.first, it.second)
            }

    fun part2(input: List<String>): Int =
        input.parse().sumOf {
            val opponent = it.first
            val own = determineMySymbol(opponent, it.second)
            getScoreFor(opponent, own)
        }

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}

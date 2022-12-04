private fun List<String>.buildRanges(): Pair<Set<Int>, Set<Int>> {
    val ranges = this.map { it.split("-").map { it.toInt() } }
    val firstRange = (ranges[0][0]..ranges[0][1]).toSet()
    val secondRange = (ranges[1][0]..ranges[1][1]).toSet()
    return firstRange to secondRange
}

// 513, 878
private fun List<String>.mapToRanges(): List<Pair<Set<Int>, Set<Int>>> =
    map {
        val parts = it.split(",")
        parts.buildRanges()
    }

private fun oneSetIsFullyContainedInOther(it: Pair<Set<Int>, Set<Int>>) =
    it.first.intersect(it.second) == it.first ||
            it.first.intersect(it.second) == it.second
private fun setsOverlap(it: Pair<Set<Int>, Set<Int>>) =
    it.first.intersect(it.second).isNotEmpty() ||
            it.first.intersect(it.second).isNotEmpty()
fun main() {
    fun part1(input: List<String>): Int = input
        .mapToRanges()
        .filter(::oneSetIsFullyContainedInOther).size

    fun part2(input: List<String>): Int = input.mapToRanges()
        .filter(::setsOverlap).size

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}

typealias ElvesWithTotalCalories = List<List<Int>>
fun main() {
    val calculateCaloriesCarriedByAllElves: (List<String>) -> ElvesWithTotalCalories = {
        it.fold(mutableListOf(mutableListOf<Int>())) { acc, item ->
            if (item.isBlank()) {
                acc.add(mutableListOf())
            } else {
                acc.last().add(item.toInt())
            }
            acc
        }
    }

    fun part1(input: List<String>): Int {
        val elvesCalories = calculateCaloriesCarriedByAllElves(input)
        return elvesCalories.maxOfOrNull {
            it.sum()
        } ?: 0
    }

    fun part2(input: List<String>): Int {
        return calculateCaloriesCarriedByAllElves(input)
            .map { it.sum() }
            .sortedDescending()
            .subList(0, 3)
            .sum()
    }

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}

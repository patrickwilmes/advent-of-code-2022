data class Rucksack(
    val firstCompartment: Set<Char>,
    val secondCompartment: Set<Char>,
) {
    fun getItemsLocatedInBothCompartments(): Set<Char> =
        firstCompartment.intersect(secondCompartment).toSet()
}


val encodedLowerCaseLetters = run {
    var i = 0
    (97..122).associate {
        i += 1
        i to it.toChar()
    }
}

val encodedUpperCaseLetters = run {
    var i = 26
    (65..90).associate {
        i += 1
        i to it.toChar()
    }
}

private fun convertCharToPrio(char: Char): Int = when {
    char.isLowerCase() -> encodedLowerCaseLetters.entries.first { it.value == char }.key
    char.isUpperCase() -> encodedUpperCaseLetters.entries.first { it.value == char }.key
    else -> throw IllegalArgumentException("Not a valid char")
}

fun main() {
    fun part1(input: List<String>): Int = input.asSequence().map {
        val firstCompartment = it.substring(0, it.length / 2).toSet()
        val secondCompartment = it.substring(it.length / 2).toSet()
        Rucksack(firstCompartment, secondCompartment)
    }.map {
        it.getItemsLocatedInBothCompartments()
    }.flatten()
        .map(::convertCharToPrio)
        .sum()

    fun part2(input: List<String>): Int = input
        .asSequence()
        .chunked(3)
        .map {
            val items = it.map { rucksack -> rucksack.toCharArray().toSet() }
            val commonElements = items[0].intersect(items[1]).intersect(items[2])
            commonElements
        }.map {
            it.map(::convertCharToPrio)
        }.flatten()
        .sum()

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}

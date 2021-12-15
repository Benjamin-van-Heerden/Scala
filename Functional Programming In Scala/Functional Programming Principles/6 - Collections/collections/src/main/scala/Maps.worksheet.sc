// Map[Key, Value] is a data structure that associates keys of type Key with values of type Value

val romanNumerals = Map("I" -> 1, "V" -> 5, "X" -> 10)

// Maps are iterables, so they support the common methods

romanNumerals.map((x, y) => (y, x))

// Maps are also functions. In particular, maps can be applied to key arguments
romanNumerals("V")

// this will produce a NoSuchElement
// romanNumerals("M")

// in stead, query a map beforehand

romanNumerals.get("I")
romanNumerals.get("M")

// the result of a get operation is an Option value
// since Options are defined as case classes they can be decomposed using pattern matching

def showNumeral(num: String) = romanNumerals.get(num) match
    case Some(thing) => thing
    case None => "Missing data"

showNumeral("I")
showNumeral("M")

// update a Map

romanNumerals + ("M" -> 50)
// keys are unique
romanNumerals + ("I" -> 42)

// bulk update
val otherRoman = Map("P" -> 64, "Q" -> 8, "R" -> 90)

romanNumerals ++ otherRoman

// Sorted and GroupBy

val fruit = List("apple", "pears", "orange", "pineapple")

fruit.sortBy(x => x.length)
fruit.sorted


// goupBy is available on Scala collections. It partitions the collection into a map o collections according to a 
// discrimintator function
fruit.groupBy(_.length)
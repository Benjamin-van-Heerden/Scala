// abstract class JSON
// object JSON {
//     case class Seq(elems: List[JSON]) extends JSON
//     case class Obj(bindings: Map[String, JSON]) extends JSON
//     case class Num(num: Double) extends JSON
//     case class Str(str: String) extends JSON
//     case class Bool(b: Boolean) extends JSON
//     case object Null extends JSON
// }

// case class hierarchies can be represented more concisely as enums
enum JSON:
    case Seq(elems: List[JSON])
    case Obj(bindings: Map[String, JSON])
    case Num(num: Double)
    case Str(str: String)
    case Bool(b: Boolean)
    case Null

val jsData = JSON.Obj(Map(
    "firstName" -> JSON.Str("John"),
    "lastName" -> JSON.Str("Smith"),
    "address" -> JSON.Obj(Map(
        "streetAddress" -> JSON.Str("21 2nd Street"),
        "state" -> JSON.Str("NY"),
        "postalCOde" -> JSON.Num(10021)
    )),
    "phoneNumbers" -> JSON.Seq(List(
        JSON.Obj(Map(
            "type" -> JSON.Str("home"),
            "number" -> JSON.Str("212 555-1234")
        )),
        JSON.Obj(Map(
            "type" -> JSON.Str("fax"),
            "number" -> JSON.Str("646 555-4567")
        ))
    )) 
))

def show(json: JSON): String = json match
    case JSON.Seq(elems) =>
        elems.map(show).mkString("[\n", ",\n", "\n]")
    case JSON.Obj(bindings) =>
        val assocs = bindings.map(
            (key, value) => s"${inQuotes(key)}: ${show(value)}"
        )
        assocs.mkString("{\n", ",\n", "\n}")
    case JSON.Num(num) => num.toString
    case JSON.Str(str) => inQuotes(str)
    case JSON.Bool(b) => b.toString
    case JSON.Null => "null"

def inQuotes(str: String): String = "\"" + str + "\""

show(jsData)

def bindings(x: JSON): List[(String, JSON)] = x match
    case JSON.Obj(b) => b.toList
    case _ => Nil

for 
    case ("phoneNumbers", JSON.Seq(numberInfos)) <- bindings(jsData)
    numberInfo <- numberInfos
    case ("number", JSON.Str(number)) <- bindings(numberInfo)
    if number.startsWith("212")
yield number 
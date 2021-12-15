case class Book(title: String, authors: List[String])

val books: List[Book] = List(
    Book(
        title = "Structure and Interpretation of Computer Programs",
        authors = List("Abelson, Harald", "Sussman, Gerald J.")
    ),
    Book(
        title = "Introduction to Funtional Programming",
        authors = List("Bird, Richard", "Wadler, Phill")
    ),
    Book(
        title = "Effective Java",
        authors = List("Bloch, Joshua")
    ),
    Book(
        title = "Java Puzzlers",
        authors = List("Bloch, Joshua", "Gafter, Neal")
    ),
    Book(
        title = "Programming in Scala",
        authors = List("Odersky, Martin", "Spoon, Lex", "Venners, Bill")
    )
)

// titles of books whose author's name is Bird:

for
    b <- books
    a <- b.authors
    if a.startsWith("Bird")
yield b.title

books.flatMap(b => 
    b.authors.withFilter(a => 
        a.startsWith("Bird")).map(a => b.title))

// all books with "Program" in the title

for 
    b <- books if b.title.indexOf("Program") >= 0
yield b

// all authors who have witten at least two books present in the database

for
    b1 <- books.toSet
    b2 <- books.toSet
    if b1 != b2
    a1 <- b1.authors
    a2 <- b2.authors
    if a1 == a2
yield a1
package lectures.part2oop

import playground.{Cinderella => Princess, PrinceCharming => Man}

import java.sql
import java.util.Date
import java.sql.{Date => sqlDate}

object PackagingAndImports extends App {
	// package members are accesible by their simple name
	val writer = new Writer("Daniel", "rock", 2018)
	println(writer)

	//import the package
	val princess = new Princess
	//alternatively
	//val princess = new playground.Cinderella

	// packages are ordered in hierarchy
	// matching folder structure in the file system

	// package object
	sayHello
	println(speedOfLight)

	// imports cont.
	val prince = new Man

	//1. Use fully qualified name
	val d = new Date
	val sqlDates = new sql.Date(2018, 5, 4)

	//2.
//	val sqlDates2 = new sqlDate

	//default imports

	//java.lang - String, Object, Exception
	//scala - Int, Nothing, Function
	//scala.Predef - println, ???
}

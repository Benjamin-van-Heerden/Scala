package lectures.part3fp

object TuplesAndMaps extends App {
	// tuples = finite ordere "lists"
	val aTuple = new Tuple2(2, "hello, scala") // = (Int, String)
	// or simply
	val anotherTuple = (2, "hello scala")

	println(aTuple._1)

	println(aTuple.copy(_2 = "goodbye Java"))

	println(aTuple.swap)

	// Maps - keys -> values

	val aMap: Map[String, Int] = Map()
	// Maps take tuples
	val phonebook = Map(("Jim", 555), ("Dan", 789))
	// or simple arrows ->
	val anotherPhonebook = Map("Jim" -> 555, "Dan" -> 789)

	//map operations

	println(phonebook.contains("Jim"))
	println(phonebook("Jim"))
	//println(phonebook("Mary")) // wil crash unless default value is specified

	val guardedPhonebook = Map(("Jim", 555), ("Dan", 789)).withDefaultValue(-1)

	println(guardedPhonebook("Mary"))

	// add a pairing?
	val newPairing = ("Mary", 456)
	val newPhonebook = phonebook + newPairing

	println(newPhonebook)

	// functionals on maps
	// map, flatmap, filter
	// NB these methods take a PAIRING

	println(phonebook.map(pair => pair._1.toLowerCase -> pair._2))

	//filterKeys (deprecated -> use filter), mapValues (also deprecated -> use map)

	println(phonebook.filter(pair => pair._1.startsWith("J")))
	println(phonebook.map(pair => pair._1 -> ("1234-"+pair._2*10)))

	println(phonebook.toList)
	// other way around as well
	val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")

	println(names.groupBy(name => name.charAt(0))('J'))

	/*
	1. What would happen if in the lowercase map if we had two original entries "Jim" -> 555 and "JIM" -> 900?
	!!!! CAREFUL WITH MAPPING KEYS

	2. Overly simplified social network based on maps
		Person = String
		- add a person to the network
		- remove
		- friend (mutual)
		- unfriend
		- number of friends of a person
		- how many people have no friends
		- if there is a social connection between two people (direct or not)?
	 */

	//1
	val testPhonebook = Map("Jim" -> 555, "Kyle" -> 123, "JIM" -> 678, "Mary" -> 222, "jim" -> 777)

	val whatHappens = testPhonebook.map(pair => pair._1.toLowerCase -> pair._2)

	println(whatHappens)
	println("++++++++++++++++++++++++++++++++++++")
	//2
	case class SocialNetwork(persons: List[String] = List("Benjamin", "Daniel", "Kyle", "Loser"), network: List[(String, String)] = List("Benjamin" -> "Daniel", "Daniel" -> "Benjamin", "Benjamin" -> "Kyle", "Kyle" -> "Benjamin")) {
		def numberOfFriends(person: String) = network.groupBy(_._1).map(pair => (pair._1 -> pair._2.length))(person)

		def addPerson(person: String) = new SocialNetwork(person :: persons, network)

		def removePerson(person: String) = {
			val clearedNetwork = network.filter(pair => pair._1 != person || pair._2 != person)
			val clearedPersons = persons.filter(p => p != person)
			new SocialNetwork(clearedPersons, clearedNetwork)
		}

		def friendMutual(p1: String, p2: String) = new SocialNetwork(persons, network ++ List(p1 -> p2, p2 -> p1))

		def unfriend(p1: String, p2: String) = new SocialNetwork(persons, network.filter(pair => (pair._1 != p1 && pair._2 != p2)||(pair._1 != p2 && pair._2 != p1)))

		def noFriends = {
			val friendMap = network.groupBy(_._1).withDefaultValue(-1)
			persons.filter(p => friendMap(p) == -1)
		}

		def socialConnection(n1: String, n2: String) = {
			persons
		}
	}

	val socialNetwork = new SocialNetwork()

	println(socialNetwork.numberOfFriends("Benjamin"))

	println(socialNetwork.noFriends)

	//
	println("--------------------------------------")

	def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
		network + (person -> Set())
	}

	def friend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
		val friendsA = network(a)
		val friendsB = network(b)

		network + (a -> (friendsA + b)) + (b -> (friendsB + a))
	}

	def unfriend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
		val friendsA = network(a)
		val friendsB = network(b)

		network + (a -> (friendsA - b)) + (b -> (friendsB - a))
	}

	def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
		def removeAux(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] = {
			if (friends.isEmpty) networkAcc
			else removeAux(friends.tail, unfriend(networkAcc, person, friends.head))
		}

		val unfriended = removeAux(network(person), network)
		unfriended - person
	}

	//

	val empty: Map[String, Set[String]] = Map()
	val network = add(add(add(empty, "Kyle"), "Bob"), "Mary")

	println(network)
	println(friend(network, "Bob", "Mary"))
	println(unfriend(friend(network, "Bob", "Mary"), "Bob", "Mary"))

	println(remove(friend(network, "Bob", "Mary"), "Bob"))

	val people = add(add(add(add(empty, "Loser"), "Jim"), "Bob"), "Mary")
	val jimBob = friend(people, "Bob", "Jim")
	val testNet = friend(jimBob, "Bob", "Mary")

	println(testNet)

	def nFriends(network: Map[String, Set[String]], person: String): Int = {
		if (!network.contains(person)) 0
		else network(person).size
	}

	println(nFriends(testNet, "Bob"))

	def mostFriends(network: Map[String, Set[String]]): String = {
		network.maxBy(pair => pair._2.size)._1
	}

	println(mostFriends(testNet))

	def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int = {
		network.count(_._2.isEmpty)
	}

	println(nPeopleWithNoFriends(testNet))

	println(".................................")
	def socialConnection(network: Map[String, Set[String]], a: String, b: String): Boolean = {
		def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean = {
			if (discoveredPeople.isEmpty) false
			else {
				val person = discoveredPeople.head
				println(person, discoveredPeople.tail, target)
				if (person == target) true
				else if(consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
				else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person))
			}
		}
		bfs(b, Set(), network(a) + a)
	}

	println(socialConnection(testNet, "Mary", "Jim"))
	println(socialConnection(testNet, "Mary", "Loser"))


}

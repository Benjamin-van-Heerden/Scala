package lectures.part3fp

import scala.util.Random

object Options extends App {
	val myFirstOption: Option[Int] = Some(4)
	val noOption: Option[Int] = None

	println(myFirstOption)
	println(noOption)

	// options were invented to deal with unsafe API's
	def unsafeMethod(): String = null

	// val result = Some(unsafeMethod()) // WRONG
	val result = Option(unsafeMethod()) // Some or None
	println(result)

	//chained methods
	def backupMethod(): String = "A valid result"

	val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod()))

	println(chainedResult)

	// DESIGN unsafe APIs
	def betterUnsafeMethod(): Option[String] = None
	def betterBackupMethod(): Option[String] = Some("A valid result")

	val betterChainedMethod = betterUnsafeMethod() orElse betterBackupMethod()

	// functions on Options
	println(myFirstOption.isEmpty)

	println(myFirstOption.get) //UNSAFE - DO NOT USE THIS

	// map, flatmap, filter
	println(myFirstOption.map(_ * 2))
	println(myFirstOption.filter(x => x > 10))
	println(myFirstOption.flatMap(x => Option(x * 10)))

	// for-comprehensions

	/*
	Exercise
	 */

	val config: Map[String, String] = Map(
		// fetched from elsewhere
		"host" -> "176.45.36.1",
		"port" -> "80"
	)

	class Connection {
		def connect = "Connected" // connect to some server
	}

	object Connection {
		val random = new Random(System.nanoTime())
		def apply(host: String, port: String): Option[Connection] = {
			if (random.nextBoolean()) Some(new Connection)
			else None
		}
	}

//	// try to establish a connection, if so - print the connect method
//	println("_----------------------------")
//	val connection = Connection
//	val active = connection(config("host"), config("port"))
//	val thisResult = {
//		if (!active.isEmpty) (new Connection).connect
//	}
//
//	println(thisResult)

	//alt
	val host = config.get("host")
	val port = config.get("port")

	val connection = host.flatMap(h => port.flatMap(p => Connection(h, p))) //option of connection
	val connectionStatus = connection.map(c => c.connect)
	println(connectionStatus)
	connectionStatus.foreach(println)
	/*
	if (h != null)
		if (p != null)
			return Connection.apply(h, p)
	return null

	if (c != null)
		return c.connect
	return null
	 */

	// chained calls
	config.get("host")
	  .flatMap(h => config.get("port")
	  .flatMap(p => Connection(h, p))
	  .map(connection => connection.connect))
	  .foreach(println)

	// for-comprehensions

	val conStatus = for {
		host <- config.get("host")
		port <- config.get("port")
		connection <- Connection(host, port)
	} yield connection.connect

	println(";;;;;;;")
	conStatus.foreach(println)


}

class Tweet(val user: String, val text: String, val retweets: Int):
  override def toString: String =
    "User: " + user + "\n" +
    "Text: " + text + " [" + retweets + "]"

trait TweetSetInterface:
  def isEmpty: Boolean
  def incl(tweet: Tweet): TweetSet
  def remove(tweet: Tweet): TweetSet
  def contains(tweet: Tweet): Boolean
  def foreach(f: Tweet => Unit): Unit
  def union(that: TweetSet): TweetSet
  def mostRetweeted: Tweet
//   def descendingByRetweet: TweetList

abstract class TweetSet extends TweetSetInterface:

  /**
   * This method takes a predicate and returns a subset of all the elements
   * in the original set for which the predicate is true.
   *
   * Question: Can we implement this method here, or should it remain abstract
   * and be implemented in the subclasses?
   */
  def filter(p: Tweet => Boolean): TweetSet = filterAcc(p, new Empty)

  /**
   * This is a helper method for `filter` that propagates the accumulated tweets.
   */
  def filterAcc(p: Tweet => Boolean, acc: TweetSet): TweetSet

  /**
   * Returns a new `TweetSet` that is the union of `TweetSet`s `this` and `that`.
   *
   * Question: Should we implement this method here, or should it remain abstract
   * and be implemented in the subclasses?
   */
  def union(that: TweetSet): TweetSet

  /**
   * Returns the tweet from this set which has the greatest retweet count.
   *
   * Calling `mostRetweeted` on an empty set should throw an exception of
   * type `java.util.NoSuchElementException`.
   *
   * Question: Should we implement this method here, or should it remain abstract
   * and be implemented in the subclasses?
   */

  /**
   * Returns a list containing all tweets of this set, sorted by retweet count
   * in descending order. In other words, the head of the resulting list should
   * have the highest retweet count.
   *
   * Hint: the method `remove` on TweetSet will be very useful.
   * Question: Should we implement this method here, or should it remain abstract
   * and be implemented in the subclasses?
   */
//   def descendingByRetweet: TweetList = ???

  /**
   * The following methods are already implemented
   */

  /**
   * Returns a new `TweetSet` which contains all elements of this set, and the
   * the new element `tweet` in case it does not already exist in this set.
   *
   * If `this.contains(tweet)`, the current set is returned.
   */
  def incl(tweet: Tweet): TweetSet

  /**
   * Returns a new `TweetSet` which excludes `tweet`.
   */
  def remove(tweet: Tweet): TweetSet

  /**
   * Tests if `tweet` exists in this `TweetSet`.
   */
  def contains(tweet: Tweet): Boolean

  /**
   * This method takes a function and applies it to every element in the set.
   */
  def foreach(f: Tweet => Unit): Unit



class Empty extends TweetSet:
  def filterAcc(p: Tweet => Boolean, acc: TweetSet): TweetSet = acc

  def isEmpty = true

  def mostRetweeted = throw new NoSuchElementException

  /**
   * The following methods are already implemented
   */

  def contains(tweet: Tweet): Boolean = false

  def incl(tweet: Tweet): TweetSet = NonEmpty(tweet, Empty(), Empty())

  def remove(tweet: Tweet): TweetSet = this

  def foreach(f: Tweet => Unit): Unit = ()

  def union(that: TweetSet) = that

class NonEmpty(val elem: Tweet, val left: TweetSet, val right: TweetSet) extends TweetSet:

  def isEmpty = false

  def filterAcc(p: Tweet => Boolean, acc: TweetSet): TweetSet = 
    if p(elem) then right.filterAcc(p, left.filterAcc(p, acc.incl(elem))) 
    else right.filterAcc(p, left.filterAcc(p, acc)) 

  def union(that: TweetSet) = 
    def traverse(current: TweetSet, acc: TweetSet = new Empty): TweetSet =
      if current.isEmpty then acc
      else
        val c = current.asInstanceOf[NonEmpty] 
        traverse(c.right, traverse(c.left, acc.incl(c.elem)))
    val first = traverse(this)
    traverse(that, first)

  def mostRetweeted: Tweet =
    def traverseFindMax(current: TweetSet, maxTweet: Tweet): Tweet =
      if current.isEmpty then maxTweet
      else
        val c = current.asInstanceOf[NonEmpty] 
        if c.elem.retweets > maxTweet.retweets then
          val newMax = c.elem
          val a = traverseFindMax(c.left, newMax)
          val b = traverseFindMax(c.right, newMax)
          if a.retweets > b.retweets then a else b
        else 
          val a = traverseFindMax(c.left, maxTweet)
          val b = traverseFindMax(c.right, maxTweet)
          if a.retweets > b.retweets then a else b
    traverseFindMax(this, elem)

    
    

  /**
   * The following methods are already implemented
   */

  def contains(x: Tweet): Boolean =
    if x.text < elem.text then
      left.contains(x)
    else if elem.text < x.text then
      right.contains(x)
    else true

  def incl(x: Tweet): TweetSet =
    if x.text < elem.text then
      NonEmpty(elem, left.incl(x), right)
    else if elem.text < x.text then
      NonEmpty(elem, left, right.incl(x))
    else
      this

  def remove(tw: Tweet): TweetSet =
    if tw.text < elem.text then
      NonEmpty(elem, left.remove(tw), right)
    else if elem.text < tw.text then
      NonEmpty(elem, left, right.remove(tw))
    else
      left.union(right)

  def foreach(f: Tweet => Unit): Unit =
    f(elem)
    left.foreach(f)
    right.foreach(f)

val set1 = Empty()
val set2 = set1.incl(Tweet("a", "a body", 28))
val set3 = set2.incl(Tweet("b", "b body", 14))
val c = Tweet("c", "c body", 42)
val d = Tweet("d", "d body", 69)
val set4c = set3.incl(c)
val set4d = set3.incl(d)
val set5 = set4c.incl(d)

def asSet(tweets: TweetSet): Set[Tweet] =
  var res = Set[Tweet]()
  tweets.foreach(res += _)
  res

def size(set: TweetSet): Int = asSet(set).size

size(set1.filter(tw => tw.user == "a"))

size(set5.filter(tw => tw.user == "a"))

size(set5.filter(tw => tw.retweets == 20))

size(set4c.union(set4d))

size(set5.union(set1))

size(set1.union(set5))

set5.mostRetweeted

val g = List("android", "Android", "galaxy", "Galaxy", "nexus", "Nexus")

val apple = List("ios", "iOS", "iphone", "iPhone", "ipad", "iPad")

val s = "Kindle Paperwhite. Review: Forget Everything android// Else, This Is"

val words = s.replaceAll("[,:/.]", "").split(" ")

words.intersect(g).length

words.intersect(apple).length
// These problems are extracted from "Programming Scala", by
// Dean Wampler.

object Problem1 {
    def main( args:Array[String] ) = {
      // Reverse each element of the args array and print out the
      // result, with each element printed on a new line.
      // Note that the String class has a 'reverse' method.
      //
      // For example:
      //
      // scala Problem1 foo bar baz
      // oof
      // rab
      // zab

      for (elem <- args) {
        println(elem.reverse)
      }
    }
}

object Problem2 {
  // A binary tree node.  The field `ord` is declared with
  // `var`, so it is mutable.  For example, you can do:
  //
  // val n = Node(...)
  // n.ord = (1 -> 2)
  //
  // Because we introduced the `var`, you may modify _this_ `var`.
  // You may not introduce any other `var`s.
  case class Node(var ord:(Int,Int), 
                  left:Option[Node],
                  right:Option[Node])

  def main( args:Array[String] ) = {
    // example tree
    val tree = Node( (-1,-1), 
      None,
      Some(Node( (-1,-1),
                Some(Node( (-1,-1), None, None )),
                Some(Node( (-1,-1), Some(Node( (-1,-1), None, None )), None ))
      ))
    )
    
    // set the tree nodes' labels and print the tree. note that case
    // classes are automatically given a toString method, so we don't
    // need to define our own.  Your solution must be general, in that
    // it can work with arbitrary trees.
    order( tree )
    println( tree )

    // For example:
    //
    // scala Problem2
    // Node((0,4),None,Some(Node((1,3),Some(Node((2,0),None,None)),Some(Node((3,2),Some(Node((4,1),None,None)),None)))))
  }

  def order( node:Node ) {
    // use a nested method inside this method as a helper function to
    // traverse the given tree and set each Node's 'ord' field to the
    // tuple '(preorder, postorder)', where 'preorder' is the Node's
    // preorder label and 'postorder' is the Node's postorder
    // label. For consistent numbers, visit left children before right
    // children. Labels should start at 0 (i.e., the root node's
    // preorder label should be 0).

    // As a hint, you'll need to use recursion here.  The nested
    // method should have an auxilliary parameter, representing the
    // currently available ID.  The nested method should return the
    // next available ID.  This is equivalent to an approach of
    // having a mutable variable elsewhere and incrementing it
    // each time we need a new ID, which is likely a more obvious
    // solution if you're coming from an imperative background.  This
    // is equivalent, because the mutable variable sets up an implicit
    // data dependency between recursive calls, whereas with functional
    // purity we must make this data dependency explicit.
  }
}

object Problem3 {
  def main( args:Array[String] ) = {
    val list = args.toList

    // Use the foldLeft method of list to print the following:
    //
    // 1. the largest element in args (using string comparison)
    // 2. args with no duplicate elements
    // 3. a run-length-encoded version of args
      val largestElement: String = list.foldLeft("")((a, b) => if (a > b) a else b)
      println(largestElement)

      val nonDuplicates: List[String] = list.foldLeft(List[String]())((a, b) => if (!a.contains(b)) b :: a else a)
      val rightOrderNonDuplicates = nonDuplicates.reverse
      println(rightOrderNonDuplicates)

      val listOfStrings: List[(String, Int)] = list.foldLeft(List[(String, Int)]())((a,b) =>
        if (a.isEmpty) {
          (b, 1) :: a
          } else if (a.head._1 == b) {
             (b, a.head._2 + 1) :: a.tail 
            } else {
              (b,1) :: a
            })

      println(listOfStrings.reverse)
      
// foldLeft[B](z: B)(op: (B, A) ⇒ B): B
    // NOTES
    //
    // If the initial value given to foldLeft is an empty List you
    // need to explicitly give the type of the List, e.g., List[Int]()
    // or List[String](), otherwise the compiler won't be able to
    // figure out the types.
    //
    // To determine if a string `s1` is greater than another string `s2`,
    // you can use `>` like so: `s1 > s2`.  The `compareTo` method on
    // `String` can also be used.
    // 
    // You may use reverse as part of your solution.
    //
    // For run-length-encoding specifics, see
    // http://en.wikipedia.org/wiki/Run_length_encoding.

    // For example:
    //
    // scala Problem3 foo bar bar baz moo moo moo cow
    // moo
    // List(foo, bar, baz, moo, cow)
    // List((foo,1), (bar,2), (baz,1), (moo,3), (cow,1))
  }
}
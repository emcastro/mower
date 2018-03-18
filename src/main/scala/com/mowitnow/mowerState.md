# `mowerState` Rationale

Many representation are possible:

 * A global representation of the world with the _lawn_, the _mowers_, and the relations between the lawn and each mower
   – It lends to a classical object-relation representation using mutable objects. The classical problems of this 
   approach are the difficulty of keeping track of past states of the system without doing a global copy of all the 
   world. It is not is the immutable state tradition of languages such as Scala.
 * A representation from the point of view of each mower – after we build a _mower_, not a _lawn_ nor a more global 
   system. The representation is less complex,  making it simpler, and more easy to 
   implement using immutable objects.
   
We choose the second representation, biased toward the state of each mower only.


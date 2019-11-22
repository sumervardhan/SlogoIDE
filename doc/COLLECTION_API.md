### In your experience using these collections, are they hard or easy to use?
Easy because all the collections implement similar methods. Some overlapping methods include `size()`, `addAll()`, `removeAll()`, and `contains()`. These methods have names which make their functionality obvious.

You can convert collections between each other. For example, you can make an `ArrayList` by instantiating it with the `keySet` of a map.

### In your experience using these collections, do you feel mistakes are easy to avoid?

Easy to avoid because each collection only offers a limited number of methods. This allows the programmers of the Collections interfaces to strictly decide how we can use and modify a particular collection. For example, there is no way to access a specific element of a Set; you must access each element through a foreach loop. You can't get a null pointer exception under any circumstances.


### How many interfaces do specific concrete collection classes implement (such as LinkedList)? What do you think is the purpose of each interface?
LinkedList implements Serializable, Cloneable, Iterable, Collection, Deque, List and Queue. 

Implementing multiple compatible interaces in this manner allows LinkedList to get behaviors from many different existing Java constructs.

Serializable - This is a marker interface that contains no member methods or data. It signifies that LinkedList objects should be serializable and deserializable.

Clonable - Signifies that the LinkedList class has a version of the .clone() method which can be used to create and return a copy of the list

Iterable - Siginifies that the LinkedList class implements can return an iterator on request to iterate through the LinkedList

Dequeue - A linear collection that allows insertion and deletion at both ends. This allows LinkedList to provide methods for insertion and removal at the head and tail

List - Signifies that the insertion order is preserved which makes it possible to get an element of the list by index.


### How many different implementations are there for a specific collection class (such as Set)? Do you think the number justifies it being an interface or not?
The Set interface is implemented as AbstractSet, ConcurrentHashMap.KeySetView, ConcurrentSkipListSet, CopyOnWriteArraySet, EnumSet, HashSet, JobStateReasons, LinkedHashSet, TreeSet. Yes, this justifies the existence of Set as an interface. If Set were not an interface, its code would be duplicated eight times over. Additionally, having a Set interface allows everyone using HashSet and TreeSet to know that these two classes are related by a common ancestor. So calling `add()` on a TreeSet is supposed to have the same functionality and return value as calling `add()` on a HashSet.

### How many levels of superclasses do specific concrete collection classes have? What do you think is the purpose of each inheritance level?
The `LinkedList` class has four levels of superclasses, including `java.lang.Object`, `java.util.AbstractCollection`, `java.util.AbstractList`, and `java.util.AbstractSequentialList`. Each inheritance level provides an added degree of specificity for the object being used. For example, inheriting `Object` gives the ability to use `toString()` and `equals()`. Adding `Collection` gives says that the `LinkedList` will be grouping objects together. Adding `AbstractList` allows for the adding and deleting of elements. And finally, adding `AbstractSequentialList` says the a `LinkedList` will store data sequentially according to the `compareTo` method defined by the objects it is storing.


### Why does it make sense to have the utility classes instead of adding that functionality to the collection types themselves? Are there any overlapping methods (ones that are in both a specific collection and a utility class)? If so, is there any guidance on which one you should use?

It makes more sense to add utility classes because it allows the utilities that a programmer uses to be more customizable for the application. There are many classes which use methods like "add" or "remove", so creating an interface to organize these properties makes sense. However, they have to be devided enough to ensure that certain methods that a programmer may not want in their program (such as indexing in a queue) won't be in their program as the queue will not implement those methods. This is the customizability that is present in the utility class, making it a very functional class.
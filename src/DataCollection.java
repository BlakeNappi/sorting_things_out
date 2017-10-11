/*
	DataCollection.java

		This interface defines a collection of data items.


*/

import java.awt.Graphics;							//	AWT = "Abstract Window Toolkit"


public interface DataCollection
{

//
//	D a t a C o l l e c t i o n
//	===========================
//
//	The DataCollection interface is meant to manipulate a collection of items,
//		implemented by the Item class.
//
//	Central to the definition of that interface (and the manipulation of the
//		collection), is the notion of a 'selected item.'
//
//	There is at most one item selected in a collection. It is the item that was
//		last added, or the item that will be next handled.
//
//	The DataCollection interface requires
//		the implementation of the following methods.
//

	//
	//	a d d
	//	=====
	//
	public void add(Item someItem);
			//
			//	Adds the given Item to the collection.
			//	That item becomes the item currently selected.

	//
	//	r e m o v e
	//	===========
	//
	public void remove();
			//
			//	Removes the selected item (if any).
			//	No item is selected any more.

	//
	//	r e s e t
	//	=========
	//
	public void reset();
			//
			//	By default, the reset method resets the selected item
			//		to the 'beginning' of the collection.

	public void reset(Item someItem);
			//
			//	If the given item is part of the collection,
			//		that item becomes the selected item.
			//	If the given item is not part of the collection,
			//		no item remains selected.

	//
	//	h a s N e x t
	//	=============
	//
	public boolean hasNext();
			//
			//	Determines whether there is a selected item
			//		(in other words, returns true,
			//		if an invocation to next would return an item).

	//
	//	n e x t
	//	=======
	//
	public Item next();
			//
			//	If an item is currently selected,
			//		returns a reference to that item and sets
			//		the following item as the selected item (if any).
			//	If no item is currently selected, returns null.

	//
	//	p a i n t
	//	=========
	//
	public void paint(Graphics pane);
			//
			//	The only "graphical" method visualizes the collection.


//
//	N o t e :
//	=========
//
//	The 3 methods hasNext, next, and remove make up the 3 methods required for what is known and
//		defined in Java as the Iterator interface.  Other than using Object's, rather than Item's,
//		there is a slight difference, however, in the behavior of the remove method.
//
//	For comparison, here is the definition of the Iterator interface, which requires the
//		implementation of the following methods.
//
//	boolean	hasNext():	returns true if the executing object contains one or more objects that
//						have not been returned by the next method.
//
//	Object	next():		returns a reference to the next object in the Iterator.
//
//	void	remove():	removes the item most recently returned by the next method from the
//						underlying collection.
//

}	// end DataCollection

/*
	ArrayDataCollection.java

		This class defines and implements a collection of data items.

		The implementation is based on an array of Items.

*/

import java.awt.*;							//	AWT = "Abstract Window Toolkit"


public class ArrayDataCollection implements DataCollection
{
	private final int SIZE = 15;			//	Default size for the collection

	//
	//	The default constructor sets the collection at an arbitrary location.
	//
	public ArrayDataCollection()
	{
		this(199, 199);						//	Somewhere ...
	}
	
	//
	//	The non-default constructor receives a specific location for the collection.
	//	It sets the initial capacity of array to 15.
	//	There are no items in collection, and none are selected.
	//
	public ArrayDataCollection(int x, int y)
	{										//	Instantiate an array of a
		theItems = new Item[SIZE];			//	beginning default size.
		size = 0;							//	There are no items yet
		selected = -1;						//		and no selected item either
		this.x = x;							//	Keeps track of the location
		this.y = y;
	}
	
	//
	//	c h a n g e S e l e c t e d
	//	===========================
	//
	//  Since only the selected Item is highlighted, this makes sure any previously
	//		selected Item is no longer highlighted, before highlighting the new selected Item.
	//
	//	Note:  This method is not part of the DataCollection interface.
	//
	private void changeSelected(int newSelected)
	{
		if (selected != -1 && selected < size) {
			theItems[selected].highlight(false);
		}
		if (newSelected != -1 && newSelected < size) {
			theItems[newSelected].highlight(true);
		}
		selected = newSelected;
	}

	//
	//	a d d
	//	=====
	//
	//	Adds the given Item to the collection.
	//	That item becomes the item currently selected.
	//
	public void add(Item someItem)
	{
		if (selected == theItems.length) {
			Item[] temp = new Item[theItems.length * 2];
			for(int i = 0; i< theItems.length; i++)
				temp[i] = theItems[i];
			theItems = temp;
		}
		
		someItem.setLocation(x + size*Item.OVERALL_WIDTH, y);
		theItems[size] = someItem;			//	Set the new element as		
		changeSelected(size++);				//	selected
	}

	//
	//	r e m o v e
	//	===========
	//
	//	Removes the selected item (if any).
	//	No item is selected any more.
	//
	public void remove()
	{
		if ((selected >= 0) && (selected < size)) {
			for(int i = size-1; i > selected; i--)
				theItems[i].setX(theItems[i-1].getX());
			for (int i = selected ; i < size-1; i++)
				theItems[i] = theItems[i+1];
			size--;
			changeSelected(-1);
		}
	}

	//
	//	r e s e t
	//	=========
	//
	//	By default, the reset method resets the selected item
	//		to the 'beginning' of the collection.
	//
	public void reset()
	{
		changeSelected(0);					//	We go back to the beginning
	}

	//
	//	r e s e t
	//	=========
	//
	//	If the given item is part of the collection,
	//		that item becomes the selected item.
	//	If the given item is not part of the collection,
	//		no item remains selected.
	//
	public void reset(Item someItem)
	{
		int i = 0;							//	To run through the array
		changeSelected(-1);					//	In case we do not have a match
		while ( (selected == -1) && (i < size) ) {
			if ( theItems[i] == someItem )
				changeSelected(i);
			i++;
		}
	}

	//
	//	h a s N e x t
	//	=============
	//
	//	Determines whether there is a selected item
	//		(in other words, returns true,
	//		if an invocation to next would return an item).
	//
	public boolean hasNext()
	{
		return (size > 0) && (selected >= 0) && (selected < size);
	}

	//
	//	n e x t
	//	=======
	//
	//	If an item is currently selected,
	//		returns a reference to that item and sets
	//		the following item as the selected item (if any).
	//	If no item is currently selected, returns null.
	//
	public Item next()
	{
		Item result = null;

		if ((size > 0) && (selected >= 0) && (selected < size)){
			result = theItems[selected];
			changeSelected(selected + 1);
		}

		return result;
	}

	//
	//	p a i n t
	//	=========
	//
	//	The only "graphical" method in the class visualizes the collection.
	//
	public void paint(Graphics pane)
	{
		if (theItems != null){
			for(int i = 0; i<size; i++)
				theItems[i].paint(pane);
		}
	}


	private Item [] theItems;				//	Holds the items
	private int size;						//	Counts the number of items

	private int selected;					//	Keeps track of the "selected" item

	private int x, y;						//	Location of the "first" item
											//		in the collection
	

}	// end DataCollection

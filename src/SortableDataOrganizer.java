import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;

/**
 * 
 * @author Blake C. Nappi
 * Date - 3/26/2015
 * Program 4 - Sorting Things Out		
 * 
 * This class can sort the data using a merge sort
 *
 */

public class SortableDataOrganizer extends DataOrganizer{
	
	private Abutton sortButton;	//the Sort Button

	//constructor
	public SortableDataOrganizer(Applet theApplet){
		
		super(theApplet);//calls DataOrganizer Constructor
		setup();//creates button
		
	}//SortableDataOrganizer
	
	
	//creates the sort button
	private void setup(){
		
		x -= 1.5*Abutton.BUTTON_WIDTH; 
		y += 0.5*Abutton.BUTTON_HEIGHT;	//creates button location
		
		sortButton = new Abutton("Sort", Color.white, x, y);
		
		x += 1.5*Abutton.BUTTON_WIDTH;
		y += 1*Abutton.BUTTON_HEIGHT;
		
	}//setup
	
	
	
	//checks the location of the mouse clicks
	protected void check(){
		
		if(sortButton.isInside(lastX, lastY)){
			startSort();			//calls operations for sort
		}else
			super.check();			//calls Collection's check
										//for other buttons
	}//check
	
	
	
	//calls methods for sort
	private void startSort() {
		
		collection = breakDown(collection);
		shift();
		
	}//startSort


	
	
	//starts the sort
	private DataCollection breakDown(DataCollection aCollection) {
		
		aCollection.reset(); // Start at the beginning of collection
		DataCollection result = new ArrayDataCollection(8565, 8383);

		Item myItem = aCollection.next(); // To go through collection
		Item check = aCollection.next(); // To check if collection has only one item
		aCollection.reset(check);

		if( (aCollection==null) || (check==null) )	//if there is no collection or items left in it
			return (DataCollection) aCollection;		//get out of the method (recursion break)
		else{
			DataCollection tempCollect1 = new ArrayDataCollection(70, 85); 		//temp collections to use
			DataCollection tempCollect2 = new ArrayDataCollection(67, 75);	

			while(myItem!=null){	//puts 1 item in different collections
				tempCollect1.add(myItem); 
				if( (myItem = aCollection.next()) != null )
				tempCollect2.add(myItem);
				
				myItem = aCollection.next(); // get next item
			}

			tempCollect1 = breakDown(tempCollect1);	//	recursion
			tempCollect2 = breakDown(tempCollect2);	

			result = mergeSort(tempCollect1, tempCollect2);	//merge the collections
		}

		return result;	// Return the sorted Collection
		
	}//sort

	
	
	//finds difference between two arguments
	private int difference(Item first, Item second){
		return first.getValue() - second.getValue();
	}//compare

	
	
	//executes the merge sort on the two datacollections
	private DataCollection mergeSort(DataCollection collect1, DataCollection collect2) {
		
		DataCollection result = new ArrayDataCollection(847, 384);

		collect1.reset(); // Start at the beginning of each collection
		collect2.reset();

		Item current1 = collect1.next(); // go through the collection
		Item current2 = collect2.next();

		while ((current1 != null) && (current2 != null)){ 	//	As long as neither collection is empty
			if ( difference(current1, current2) <= 0 ) {
				result.add(current1);
				current1 = collect1.next(); // updates the both
			}else{
				result.add(current2);
				current2 = collect2.next(); 
			}
		}

		while(current1 != null || current2 != null){ // check if any of the two collections still have								
			if(current1 != null)						// any items If there is add it to collection
				result.add(current1);
			
			current1 = collect1.next();	

			if(current2 != null)				//updates both 'current's
				result.add(current2);
			
			current2 = collect2.next(); 
		}
		
		return result;
	}//mergeSort

	
	
	//moves the X and Y locations over for all items in collection
	private void shift(){
		
		collection.reset(); // Start at the beginning

		Item temp = collection.next(); // Then go through the collection

		int x = 2*Abutton.BUTTON_WIDTH;	// First x location

		DataCollection result = new ArrayDataCollection(x, y); // a temp collection

		while(temp!=null){			
			temp.setLocation(x, y);			//puts the X + Y of item back to where they need to be
			result.add(temp);	
			temp = collection.next();
			x += Item.WIDTH;				
		}
		
		result.reset(null); // Check to make sure that nothing is left selected
		collection = result;
		
	}//organize

	
	
	
	//Paint everything
	public void paint(Graphics pane) {
		
		super.paint(pane);			//paint other buttons + items
		
		if(sortButton != null)		//paint sortButton
			sortButton.paint(pane);
		
	}//paint
	
	
	
}//End SortableDataOrganizer



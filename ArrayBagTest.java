/* A test of a few of the constructors and the methods of ArrayBag
 * Defries, Kevin */

public class ArrayBagTest
{
  public static void main(String[] args)
  {
    // Adding to an initially empty bag with sufficient capacity
    System.out.println("Testing an initially empty bag with the capacity to hold at least 6 strings:");
    BagInterface<String> aBag = new ArrayBag<String> ();
    String[] contentsOfBag1 = {"A", "B", "C", "D", "E", "F"};
    testAdd(aBag, contentsOfBag1);
    
    // Filling an initially empty bag to capacity
    System.out.println("Testing an initially empty bag that will be filled to capacity:");
    aBag = new ArrayBag<String>(7);
    String[] contentsOfBag2 = {"A", "B", "C", "D", "E", "F", "G", "H"};
    testAdd(aBag, contentsOfBag2);
  }// end main
  
  // Tests the method add.
  private static void testAdd(BagInterface<String> aBag, String[] content)
  {
    System.out.println("Adding the following " + content.length + " strings to the bag: ");
    for (int index = 0; index < content.length; index++)
    {
      if (aBag.add(content[index]))
        System.out.print(content[index] + " " );
      else
        System.out.print("\nUnable to add " + content[index] + " to the bag.");
    }// end for 
    System.out.println();
    
    displayBag(aBag);
  }// end testAdd
  
  // Tests the method toArray while displaying the bag.
  private static void displayBag(BagInterface<String> aBag)
  {
    System.out.println("The bag contains the following string(s):");
    Object[] bagArray = aBag.toArray();
    for (int index = 0; index < bagArray.length; index++)
    {
      System.out.print(bagArray[index] + " ");
    }// end for
    
    System.out.println();
  }// end displayBag
}// end ArrayBagTest

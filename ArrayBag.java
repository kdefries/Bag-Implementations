/* A class of bags whose entries are stored in an array.
 * Defries, Kevin */

import java.util.Arrays;

public class ArrayBag<T> implements BagInterface<T>
{
  private T[] bag;
  private int numberOfEntries;
  private static final int DEFAULT_CAPACITY = 25;
  private boolean initialized = false;
  private static final int MAX_CAPACITY = 10000;
  
  /* Creates an empty bag whose initial capacity is 25. */
  public ArrayBag()
  {
    this(DEFAULT_CAPACITY);
  }// end default constructor
  
  /* Creates an empty bag having a given initial capacity.
   * @param capacity The integer capacity desired. */
  public ArrayBag(int desiredCapacity)
  {
    if (desiredCapacity <= MAX_CAPACITY)
    {
      // The cast is safe because the new array contains null entries.
      @SuppressWarnings("unchecked")
      T[] tempBag = (T[])new Object[desiredCapacity]; // Unchecked cast
      bag = tempBag;
      numberOfEntries = 0;
      initialized = true;
    }
    else
      throw new IllegalStateException("Attempt to create a bag whose capacity exceeds allowed maximum.");
  }// end constructor
  
  /* Adds a new entry to this bag.
   * @param newEntry The object to be added as a new entry.
   * @return True if the addition is successful, or false if not. */
  public boolean add(T newEntry)
  {
    checkInitialization();
    boolean result = true;
    if(isArrayFull())
    {
      doubleCapacity();
    }// end if
    
    bag[numberOfEntries] = newEntry;
    numberOfEntries++;
    
    return true;
  }//end add 
  
  // Returns true if the bag is full, or false if not.
  private boolean isArrayFull()
  {
    return numberOfEntries >= bag.length;
  }// end isArrayFull
  
  /* Doubles the size of the array bag.
   * Precondition: checkInitialization has been called. */
  private void doubleCapacity()
  {
    int newLength = 2 * bag.length;
    checkCapacity(newLength);
    bag = Arrays.copyOf(bag, newLength);
  }// end doubleCapacity
  
  // Throws an exception  if this object is not initialized.
  private void checkInitialization()
  {
    if (!initialized)
      throw new SecurityException("ArrayBag object is not initialized properly.");
  }// end checkInitialization
  
  // Throws an exception if the client requests a capacity that is too large.
  private void checkCapacity(int capacity)
  {
    if (capacity > MAX_CAPACITY)
      throw new IllegalStateException("Attempt to create a bag whose capacity exeeds allowed "
                                        + "maximum of " + MAX_CAPACITY);
  }// end checkCapacity
  
  public T remove()
  {
    checkInitialization();
    T result = null;
    if (numberOfEntries > 0)
    {
      result = bag[numberOfEntries - 1];
      bag[numberOfEntries - 1] = null;
      numberOfEntries--;
    }// end if
    
    return result;
  }// end remove
  
  public boolean remove(T anEntry)
  {
    checkInitialization();
    int index = getIndexOf(anEntry);
    T result = removeEntry(index);
    return anEntry.equals(result);
  }// end remove
  
  /* Removes and returns the entry at a given index within the array bag.
   * If no such entry exists, returns null.
   * Preconditions: 0 <= givenIndex < numberOfEntries; checkInitialization has been called. */
  private T removeEntry(int givenIndex)
  {
    T result = null;
    if (!isEmpty() && (givenIndex >= 0))
    {
      result = bag[givenIndex];
      bag[givenIndex] = bag[numberOfEntries -1];
      
      bag[numberOfEntries -1] = null;
      numberOfEntries--;
    }// end if
    
    return result;
  }// end removeEntry
  
  /* Locates a given entry within the array bag.
   * Returns the index of the entry, if located, or -1 otherwise.
   * Precondition: checkInitialization has been caleld. */
  private int getIndexOf(T anEntry)
  {
    int where = -1;
    boolean found = false;
    int index = 0;
    while (!found && (index < numberOfEntries))
    {
      if (anEntry.equals(bag[index]))
      {
        found = true;
        where = index;
      }// end if
      
      index++;
    }// end while
    
    // Assertion: If where > -1, anEntry is in the array bag, and it
    // equals bag[where]; otherwise, anEntry is not in the array.
    return where;
  }// end getIndexOf
  
  /** Removes all entries from this bag. **/
  public void clear()
  {
    while (!isEmpty())
      remove();
  }// end clear
  
  public boolean isEmpty()
  {
    return numberOfEntries == 0;
  }// end isEmpty
  
  public int getCurrentSize()
  {
    return numberOfEntries;
  }// end getCurrentSize
  
  public int getFrequencyOf(T anEntry)
  {
    checkInitialization();
    int counter = 0;
    
    for (int index = 0; index < numberOfEntries; index++)
    {
      if (anEntry.equals(bag[index]))
      {
        counter++;
      }// end if
    }// end for
    
    return counter;
  }//end getFrequencyOf
  
  public boolean contains(T anEntry)
  {
    checkInitialization();
    return getIndexOf(anEntry) > -1;
  }// end contains
  
  /* Retrieves all entries that are in this bag.
   * @return A newly allocated array of all the entries in the bag. */
  public T[] toArray()
  {
    // The cast is safe because the new array contains null entries
    checkInitialization();
    @SuppressWarnings("unchecked")
    T[] result = (T[])new Object[numberOfEntries]; // Unchecked cast
    for (int index = 0; index < numberOfEntries; index++)
    {
      result[index] = bag[index];
    }// end for
    
    return result;
  }// end toArray
}// end ArrayBag
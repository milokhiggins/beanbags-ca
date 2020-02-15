package beanbags;

import java.io.Serializable;

/**
 * Stores {@link Object} items in an array list which is more efficent in 
 * adding at the cost of more storage. Note that the internal representation
 * does not decrease the array size stored, only increase capacity. It should 
 * not be used to store null references.
 * 
 * @author Jonathan Fieldsend 
 * @version 1.2
 */
public class ObjectArrayList implements Serializable
{
    private Object[] array;
    private int elementsUsed;

    /**
     * Creates initial instance of an ObjectArrayList with no contents
     */
    public ObjectArrayList() {
        this.array = new Object[10];
        this.elementsUsed = 0;
    }

    /**
     * Method adds the argument to the end of the list. <code>null</code> reference 
     * elements are not supported, so ensure that you do not add them.
     * 
     * @param o Object instance to be added
     */
    public void add(Object o) {
        if (this.elementsUsed == this.array.length)
            this.resizeArray();
        this.array[elementsUsed] = o;
        this.elementsUsed++;
    }

    /*
     * Method doubles the capacity of the array
     */
    private void resizeArray(){
        Object[] tempArray = new Object[this.array.length*2];
        // Efficiently copy all the elements of array into tempArray
        System.arraycopy(this.array,0,tempArray,0,this.array.length);
        this.array = tempArray;
    }

    /**
     * Method returns the element of the list at the index provided, will
     * return <code>null</code> if the index is invalid
     * 
     * @param index index of element in list to be returned
     * @return Object at corresponding index
     */
    public Object get(int index) {
        if (this.isInvalid(index))
            return null;
        return this.array[index];
    }

    /*
     * Checks validity of index given current range, returns true if not valid
     */
    private boolean isInvalid(int index) {
        return ((index < 0) || (index >= this.elementsUsed));
    }

    /**
     * Method removes the argument if it is contained in the list and returns <code>true</true>
     * if it is successful. It will return <code>false</code> if <code>o</code> is not contained 
     * in the list and therefore not removed
     * 
     * @param index index of element in list to be returned
     * @return Object at corresponding index 
     * 
     */
    public boolean remove(Object o) {
        for (int i = 0; i < this.elementsUsed; i++) {
            if (this.array[i].equals(o)) {
                this.contract(i);
                return true;
            }
        }
        return false;
    }

    /*
     * Method contracts effectively removing the index item. If item beyond range of 
     * array, returns false, otherwise returns true on successful removal
     */
    private void contract(int index) {
        // Efficiently copy all the elements of array beyond index down one 
        // space, effectively removing the index element
        System.arraycopy(this.array,index+1,this.array,index,this.elementsUsed-(index+1));
        this.elementsUsed--;
    }

    /**
     * Method removes the element of the list at the index provided, will
     * return <code>null</code> if the index is invalid. Otherwise will 
     * return the instance removed
     * 
     * @param index index of element in list to be returned
     * @return Object at corresponding index 
     * 
     */
    public Object remove(int index) {
        if (this.isInvalid(index))
            return null;
        Object value = this.get(index);    
        this.contract(index);
        return value;
    }

    /**
     * Method replaces the element of the list at the index provided, will
     * return <code>false</code> if the index is invalid, otherwise will return true.
     * 
     * @param o object to be placed in the list
     * @param index index of element in list to be replaced
     * @return true if sucessfully replaced, otherwise false if index is
     *         out of the range of stored data
     */
    public boolean replace(Object o, int index) {
        if (this.isInvalid(index))
            return false;
        this.array[index] = o;   
        return true;
    }

    /**
     * Method returns the total number of elements in the list
     * 
     * @return number of elements in the list 
     */
    public int size(){
        return this.elementsUsed;
    }
}
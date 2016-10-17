package sets;

import java.util.Iterator;

public class LinkedSet<E> implements Set<E> {
  private LinkedNode<E> head = null;

  // Constructors
  public LinkedSet() {
  }

  public LinkedSet(E e) {
    this.head = new LinkedNode<E>(e, null);
  }

  private LinkedSet(LinkedNode<E> head) {
    this.head = head;
  }

  @Override
  public int size() {
   LinkedNodeIterator<E> counter = new LinkedNodeIterator<E>(head);
   int count = 0;
   while (counter.hasNext() && count++ != -1){
	   counter.next();
   }
   return count;
  }

  @Override
  public boolean isEmpty() {
    return head == null;
  }

  @Override
  public Iterator<E> iterator() {
    return new LinkedNodeIterator<E>(this.head);
  }

  @Override
  public boolean contains(Object o) {
    LinkedNodeIterator<E> i = new LinkedNodeIterator<E>(head);
    while (i.hasNext()){
    	if (i.next() == o)
    		return true;
    }
    return false;
  }

  @Override
  public boolean isSubset(Set<E> that) {
    LinkedNodeIterator<E> i = new LinkedNodeIterator<E>(head);
    while (i.hasNext()){
    	E cur = i.next();
    	if (!that.contains(cur))
    		return false;
    }
    return true;
  }

  @Override
  public boolean isSuperset(Set<E> that) {
    return that.isSubset(this);
  }

  @Override
  public Set<E> adjoin(E e) {
	  return !this.contains(e) ? new LinkedSet<E>(new LinkedNode<E>(e, head)) : this;
  }

  @Override
  public Set<E> union(Set<E> that) {
    Set<E>	 newSet = new LinkedSet<E>(head);
    Iterator<E> i = this.iterator();
    while (i.hasNext())
    	newSet = newSet.adjoin(i.next());
    i = that.iterator();
    while (i.hasNext())
    	newSet = newSet.adjoin(i.next());
    return newSet;
  }

  @Override
  public Set<E> intersect(Set<E> that) {
    Set<E> newSet = new LinkedSet<E>();
    Iterator<E> i = this.iterator();
    while (i.hasNext()){
    	E currObj = i.next();
    	if (that.contains(currObj))
    		newSet = newSet.adjoin(currObj);
    }
    return newSet;
  }

  @Override
  public Set<E> subtract(Set<E> that) {
   Set<E> newSet = new LinkedSet<E>();
   Iterator<E> i = this.iterator();
   if (!this.isSubset(that)){
	   while (i.hasNext()){
		   E currObj = i.next();
		   if (!that.contains(currObj))
			   newSet = newSet.adjoin(currObj);
	   }
    }
   return newSet;
  }

  @Override
  public Set<E> remove(E e) {
	  Set<E> newSet = new LinkedSet<E>();
	  if (!this.contains(e))
		  return this;
	  Iterator<E> i = this.iterator();
	  while(i.hasNext()){
		 E currObj = i.next();
		 if (!currObj.equals(e))
			 newSet = newSet.adjoin(currObj);
		}
	  return newSet;
  }

  @Override
  @SuppressWarnings("unchecked")
  public boolean equals(Object o) {
    if (! (o instanceof Set)) {
      return false;
    }
    Set<E> that = (Set<E>)o;
    return this.isSubset(that) && that.isSubset(this);
  }

  @Override
    public int hashCode() {
    int result = 0;
    for (E e : this) {
      result += e.hashCode();
    }
    return result;
  }
  
  public String toString()
  {
	  String output = "";
	  for (E element : this)
	  {
		  output += element + ", ";
	  }
	  output = output.substring(0, output.length()-2);
	  return output;
  }
}

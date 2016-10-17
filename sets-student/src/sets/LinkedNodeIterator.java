package sets;

import java.util.Iterator;
import java.util.NoSuchElementException;

class LinkedNodeIterator<E> implements Iterator<E> {
    private LinkedNode<E> head;
  
  // Constructors
  public LinkedNodeIterator(LinkedNode<E> head) {
     this.head = head;
  }

  @Override
  public boolean hasNext() {
    return (head != null);
  }

  @Override
  public E next() {
    E currentNode;
    if (hasNext()){
    	currentNode = head.getData();
    	head = head.getNext();
    } else {
    	throw new NoSuchElementException();
    }
    return currentNode;
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }
}

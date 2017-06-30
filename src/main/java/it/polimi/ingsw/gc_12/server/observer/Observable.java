package it.polimi.ingsw.gc_12.server.observer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public abstract class Observable<C> {

	private List<Observer<C>> observers;
	
	public Observable(){
		this.observers=new ArrayList<Observer<C>>();
	}
	
	public void registerObserver(Observer<C> o){
		this.observers.add(o);
	}

	public void unregisterObserver(Observer<C> o){
		this.observers.remove(o);
	}

	public void unregisterObserver(Class c) {
		observers.removeIf(c::isInstance);
	}
	
	public void notifyObserver(C c) {
		System.out.println("I am the " + this.getClass().getSimpleName() + ". I have been notified about " + c.getClass());
		ListIterator<Observer<C>> itr = this.observers.listIterator();
		while(itr.hasNext()) {
			Observer<C> o = itr.next();
			o.update(c);
		}
	}
	
	protected void notifyObserver() {
		for(Observer<C> o: this.observers){
			o.update();
		}		
	}
}

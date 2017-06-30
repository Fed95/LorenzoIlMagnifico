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
	
	public synchronized void registerObserver(Observer<C> o){
		this.observers.add(o);
	}

	public synchronized void unregisterObserver(Observer<C> o){
		this.observers.remove(o);
	}

	public synchronized void unregisterObserver(Class c) {
		observers.removeIf(c::isInstance);
	}
	
	public synchronized void notifyObserver(C c) {
		System.out.println("I am the " + this.getClass().getSimpleName() + ". I have been notified about " + c.getClass());
		List<Observer<C>> newObservers = new ArrayList<>();
		newObservers.addAll(observers);
		for(Observer<C> o: newObservers) {
			o.update(c);
		}
	}
	
	protected synchronized void notifyObserver() {
		List<Observer<C>> newObservers = new ArrayList<>();
		newObservers.addAll(observers);
		for(Observer<C> o: newObservers) {
			o.update();
		}
	}
}

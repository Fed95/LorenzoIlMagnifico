package it.polimi.ingsw.gc12.misc.observer;

import java.util.ArrayList;
import java.util.List;

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
		List<Observer<C>> newObservers = new ArrayList<>();
		newObservers.addAll(observers);
		for(Observer<C> o: newObservers) {
			o.update(c);
		}
	}
	
	public void notifyObserver() {
		List<Observer<C>> newObservers = new ArrayList<>();
		newObservers.addAll(observers);
		for(Observer<C> o: newObservers) {
			o.update();
		}
	}
}

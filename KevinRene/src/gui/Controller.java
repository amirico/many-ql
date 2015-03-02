package gui;

import java.util.HashMap;
import java.util.Map;
import java.util.Observer;

import cons.Value;
import cons.ValueEnvironment;
import cons.ql.ast.expression.Identifier;

public class Controller {
	
	private final Map<Identifier, Widget> widgets;
	private final ValueEnvironment valueEnv;
	
	public Controller(ValueEnvironment valueEnv) {
		this.widgets = new HashMap<Identifier, Widget>();
		this.valueEnv = valueEnv;
	}
	
	public void addObserver(Identifier x, Observer obs) {
		widgets.get(x).addObserver(obs);
	}
	
	// Listens to every identifier
	public void addGlobalObserver(Observer obs) {
		for(Identifier id : widgets.keySet()) {
			addObserver(id, obs);
		}
	}
	
	public void putObservable(Identifier x, Widget obs) {
		widgets.put(x, obs);
	}
		
	public ValueEnvironment getValueEnvironment() {
		return this.valueEnv;
	}
}

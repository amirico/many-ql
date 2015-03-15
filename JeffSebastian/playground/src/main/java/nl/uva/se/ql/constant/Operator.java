package nl.uva.se.ql.constant;

public enum Operator {
	OR("||"),
	AND("&&"),
	EQUAL("=="),
	NOT_EQUAL("!="),
	GREATER_THAN(">"),
	LESS_THEN("<"),
	GREATER_OR_EQUAL(">="),
	LESS_OR_EQUAL("<="),
	NOT("!"),
	POWER("^"),
	MODULO("%"),
	DIVIDE("/"),
	MULTIPLY("*"),
	MINUS("-"),
	PLUS("+");
	
	private String name;
	
	private Operator(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public static Operator getByName(String name) {
		for (Operator operator : Operator.values()) {
			if (operator.getName().equals(name)) {
				return operator;
			}
		}
		
		return null;
	}
}
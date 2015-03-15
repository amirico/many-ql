package uva.qls.ast.statements;

import java.util.List;

import uva.qls.ast.CodeLines;
import uva.qls.ast.component.Component;
import uva.qls.ast.primitive.Type;
import uva.qls.ast.statements.visitor.StatementVisitor;
import uva.qls.ast.style.Style;
import uva.qls.ast.value.GenericValue;
import uva.qls.supporting.*;

public class DefaultValue extends Statement {
	
	private Type type;
	private Component component;
	private List<Style> style;

	public DefaultValue (Type _type,Component _component, CodeLines _codeLines ){
		super(_codeLines);
		this.component=_component;
		this.type=_type;
	}
	
	public DefaultValue(Type _type, List<Style> _style, CodeLines _codeLines){
		super(_codeLines);
		this.style = _style;
		this.type = _type;
	}
	
	public Type getType(){
		return this.type;
	}
	public List<Style> getStyle(){
		return this.style;
	}
	
	public Component getComponent(){
		return this.component;
	}
	
	@Override
	public <T> T accept(StatementVisitor<T> visitor) {
		return this.component != null 	? visitor.visitDefaultValueComponent(this)
										: visitor.visitDefaultValueStatements(this);
	}
	
	@Override
	public Tuple<Integer, Integer> getLOCTuple() {
		return this.codeLines.getCodeLocation();
	}

	@Override
	public CodeLines getLOC() {
		return this.codeLines;
	}
	@Override
	public GenericValue<?> evaluate() {
		return null;
	}
	
	@Override
	public String toString(){
		return this.style 		!= null ? "DefaultValue(" + this.getType().toString() + "," + this.style.toString()
								: "DefaultValue(" + this.getType().toString() + "," + this.component.toString() + ")";
	}
}
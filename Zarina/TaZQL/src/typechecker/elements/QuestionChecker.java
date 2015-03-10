package typechecker.elements;

import java.util.ArrayList;
import java.util.Map;

import typechecker.TypeRepository;
import typechecker.errors.ErrorCollector;
import ast.type.Type;

public class QuestionChecker {
	private final String id, label;
	private final Type type;
	private final ErrorCollector errorCollector;
	private final TypeRepository typeRepository;
	
	public QuestionChecker(String id, String label, Type type, ErrorCollector errorCollector, TypeRepository typeRepository) {
		this.id = id;
		this.label = label;
		this.type = type;
		this.errorCollector = errorCollector;
		this.typeRepository = typeRepository;
	}

	public ErrorCollector getErrors() {
		return this.errorCollector;
	}
	
	// duplicate question declarations with different types
	public void checkDuplicateDeclaration() {
	  if(!this.typeRepository.empty()) {
		if (!this.typeRepository.isDeclared(id) || this.typeRepository.getValue(id).equals(type)) {
			return;
		}
		
		this.errorCollector.addError("Question declaration *" + id + "* is duplicated. "
									+ "It is used with a different type.");
	  }
	}
	
	//duplicate labels (warning)
	public void checkDuplicateLabels() {
	  if(!this.typeRepository.empty()) {
					
		for(Map.Entry<String, String> entry : this.typeRepository.getLabelRepository().entrySet()) {
			String key = entry.getKey();
			String labelValue = entry.getValue();
			
			if(!labelValue.equals(this.label) || key.equals(id)) {
				continue;
			}
			this.errorCollector.addWarning("Warning! Duplicated label *" + labelValue + "* in question *" + key + "*.");	
		}
	  }	
	}
}

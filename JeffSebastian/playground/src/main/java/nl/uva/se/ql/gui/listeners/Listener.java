package nl.uva.se.ql.gui.listeners;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import nl.uva.se.ql.gui.validators.Validator;
import nl.uva.se.ql.gui.widgets.questions.BaseQuestion;

public class Listener<T> {
	
	public ChangeListener<T> addListener(BaseQuestion<T> question, Validator<T> validator) {
		return new ChangeListener<T>() {

			@Override
			public void changed(ObservableValue<? extends T> observable,
					T oldValue, T newValue) {
				if(validator.isValid(newValue)){					
				}else{
					question.undoChange(newValue, oldValue);
				}
			}
		};
	}
}

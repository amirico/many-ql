package org.uva.student.calinwouter.qlqls.application.gui.widgets.question.stringwidgets;

import org.uva.student.calinwouter.qlqls.application.gui.StateWrapper;
import org.uva.student.calinwouter.qlqls.application.gui.widgets.IWidget;
import org.uva.student.calinwouter.qlqls.ql.QLInterpreter;
import org.uva.student.calinwouter.qlqls.ql.model.VariableTable;
import org.uva.student.calinwouter.qlqls.ql.types.StringValue;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.awt.*;

public class TextboxWidget implements IWidget {
    private final JTextField widget;
    private final String questionIdentifier;
    private final QLInterpreter qlInterpreter;
    private final StateWrapper stateWrapper;

    @Override
    public Component getWidgetComponent() {
        return widget;
    }

    @Override
    public void resetValue() {
        widget.setText("");
    }

    private DocumentListener createTextboxDocumentListener() {
        return new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateField();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateField();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateField();
            }

            private void updateField() {
                final String textboxText = widget.getText();
                final VariableTable variableTable = stateWrapper.getVariableTable();
                final StringValue stringValue = new StringValue(textboxText);
                variableTable.setVariable(questionIdentifier, stringValue);
                final VariableTable newVariableTable = qlInterpreter.interpret(variableTable);
                stateWrapper.setVariableTable(newVariableTable);
            }
        };
    }

    private void createUpdateTextboxListener() {
        final Document textboxDocument = widget.getDocument();
        textboxDocument.addDocumentListener(createTextboxDocumentListener());
    }

    public TextboxWidget(String questionIdentifier, QLInterpreter qlInterpreter, StateWrapper stateWrapper) {
        this.questionIdentifier = questionIdentifier;
        this.qlInterpreter = qlInterpreter;
        this.stateWrapper = stateWrapper;
        this.widget = new JTextField(20);
        createUpdateTextboxListener();
    }
}

package nl.uva.softwcons.qls.ui.converter;

import static nl.uva.softwcons.ql.eval.value.UndefinedValue.UNDEFINED;
import nl.uva.softwcons.ql.eval.value.NumberValue;
import nl.uva.softwcons.ql.eval.value.Value;
import nl.uva.softwcons.ql.ui.converter.ValueConverter;

public class NumberToNumberValueConverter implements ValueConverter<Number> {

    private final Double start;
    private final Double end;

    public NumberToNumberValueConverter(double start, double end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public Value toValue(Number value) {
        return new NumberValue(value);
    }

    @Override
    public Number fromValue(Value value) {
        if (value == UNDEFINED) {
            return start;
        }
        return value.getNumber();
    }

}
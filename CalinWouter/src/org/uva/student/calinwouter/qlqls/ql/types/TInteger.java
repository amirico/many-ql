package org.uva.student.calinwouter.qlqls.ql.types;

import org.uva.student.calinwouter.qlqls.ql.interpreter.TypeCallback;

public class TInteger extends TypeModel<Integer> {
    public static final String TYPE_REFERENCE = "int";

    @Override
    public TypeModel<?> add(TypeModel<?> typeModel) {
        if (typeModel.getTypeModelClass() == Integer.class) {
            if (getValue() == null)
                return new TInteger(null);
            return new TInteger(getValue() + (Integer) typeModel.getValue());
        }
        return super.add(typeModel);
    }

    @Override
    public TypeModel<?> sub(TypeModel<?> typeModel) {
        if (typeModel.getTypeModelClass() == Integer.class) {
            if (getValue() == null)
                return new TInteger(null);
            return new TInteger(getValue() - (Integer) typeModel.getValue());
        }
        return super.sub(typeModel);
    }

    @Override
    public TypeModel<?> mul(TypeModel<?> typeModel) {
        if (typeModel.getTypeModelClass() == Integer.class) {
            if (getValue() == null)
                return new TInteger(null);
            return new TInteger(getValue() * (Integer) typeModel.getValue());
        }
        return super.mul(typeModel);
    }

    @Override
    public TypeModel<?> div(TypeModel<?> typeModel) {
        if (typeModel.getTypeModelClass() == Integer.class) {
            if (getValue() == null)
                return new TInteger(null);
            return new TInteger(getValue() / (Integer) typeModel.getValue());
        }
        return super.div(typeModel);
    }

    @Override
    public TypeModel<?> mod(TypeModel<?> typeModel) {
        if (typeModel.getTypeModelClass() == Integer.class) {
            if (getValue() == null)
                return new TInteger(null);
            return new TInteger(getValue() % (Integer) typeModel.getValue());
        }
        return super.mod(typeModel);
    }

    @Override
    public Class<Integer> getTypeModelClass() {
        return Integer.class;
    }

    @Override
    public TypeModel<?> lt(TypeModel<?> typeModel) {
        if (typeModel.getTypeModelClass() == Integer.class) {
            if (getValue() == null)
                return new TBool(null);
            return new TBool(getValue() < (Integer) typeModel.getValue());
        }
        return super.lt(typeModel);
    }

    @Override
    public TypeModel<?> gt(TypeModel<?> typeModel) {
        if (typeModel.getTypeModelClass() == Integer.class) {
            if (getValue() == null)
                return new TBool(null);
            return new TBool(getValue() > (Integer) typeModel.getValue());
        }
        return super.gt(typeModel);
    }

    @Override
    public TypeModel<?> lte(TypeModel<?> typeModel) {
        if (typeModel.getTypeModelClass() == Integer.class) {
            if (getValue() == null)
                return new TBool(null);
            return new TBool(getValue() <= (Integer) typeModel.getValue());
        }
        return super.lte(typeModel);
    }

    @Override
    public TypeModel<?> gte(TypeModel<?> typeModel) {
        if (typeModel.getTypeModelClass() == Integer.class) {
            if (getValue() == null)
                return new TBool(null);
            return new TBool(getValue() >= (Integer) typeModel.getValue());
        }
        return super.gte(typeModel);
    }

    @Override
    public void apply(TypeCallback typeCallback) {
        typeCallback.usesInteger();
    }

    public TInteger(Integer value) {
        super(value);
    }
}

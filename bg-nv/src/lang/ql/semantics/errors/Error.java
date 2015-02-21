package lang.ql.semantics.errors;

import lang.ql.ast.types.Type;

/**
 * Created by bore on 20/02/15.
 */
public class Error extends Message
{
    public static Error typeMismatch(String id, Type leftChildType, Type rightChildType, int line)
    {
        String m = String.format("Error (Line %d): expression of type %s cannot have children of different types: %s and %s",
                line, id, leftChildType.getTitle(), rightChildType.getTitle());
        return new Error(m);
    }

    public static Error incorrectTypes(String id, Type leftChildType, int line)
    {
        return new Error(String.format("Error (Line %d): expression of type %s cannot children of type %s",
                line, id, leftChildType.getTitle()));
    }

    public static Error ifConditionShouldBeBoolean(int line)
    {
        return new Error(String.format("Error (Line %d): if statements should have conditions of type boolean", line));
    }

    public static Error undeclaredIdentifier(String id, int line)
    {
        return new Error(String.format("Error (Line %d): identifier \"%s\" is not declared", line, id));
    }

    public static Error identifierDefEvalMismatch(String id, String defined, String evaluated, int line)
    {
        return new Error(String.format(
                "Error (Line %d): Question \"%s\" is defined as type %s, but is calculated as type %s",
                line, id, defined, evaluated));
    }

    public static Error identifierAlreadyDeclared(String id, int line1, int line2)
    {
        return new Error(String.format("Error (Line %d): identifier \"%s\" is already declared twice on lines %d and %d",
                line1, id, line1, line2));
    }

    public static Error identifierDeclaredOfDiffType(String id, int line1, int line2)
    {
        return new Error(String.format(
                "Error (Line: %d): Question \"%s\" is declared twice with a different type on line %d and %d",
                line1, id, line1, line2));
    }

    public Error(String message)
    {
        super(message);
    }
}

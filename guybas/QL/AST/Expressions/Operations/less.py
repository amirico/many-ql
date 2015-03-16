import QL.AST.Expressions.Elements.element as e
import QL.Grammar.constants as constants
import QL.AST.Expressions.Operations.binary_expression as b


class Less(b.BinaryExpression):

    # get the return _type of the _expression
    def return_type_string(self, type_dict):
        return constants.NUMBER

    # get all variables in the _expression
    def get_dependencies(self):
        return [self._operand1.get_dependencies(), self._operand2.get_dependencies()]
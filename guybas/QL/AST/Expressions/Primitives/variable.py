import QL.AST.Expressions.Primitives.primitive as p


class Variable(p.Primitive):
    def __init__(self, name):
        self.__name = name

    def return_type_string(self, type_dict):
        return type_dict[self.__name]

    def pretty_print(self):
        return self.__name

    # The dependency of the calling object is this variable
    def get_dependency_collection(self):
        return [self.__name]
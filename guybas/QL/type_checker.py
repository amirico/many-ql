# Type Checker
import collections
from grammar import *

class TypeChecker:

    def __init__(self, form):
        self.form = form
        ids = TypeChecker.check_ids(self.form.questions)
        labels = TypeChecker.check_labels(self.form.questions)
        dependencies = TypeChecker.check_dependencies(self.form.questions)
        print(dependencies)

    def check_duplicates(list):
        # check for duplicates
        duplicates =  [x for x, y in collections.Counter(list).items() if y > 1]
        return duplicates

    def check_ids(questions):
        ids = []
        for question in questions:
            ids += (question.all_ids())
        duplicates =  TypeChecker.check_duplicates(ids)
        if duplicates:
            print("There are duplicate ids: " + str(duplicates))
        return ids

    def check_labels(questions):
        labels = []
        for question in questions:
            labels +=  question.all_labels()
        duplicates = TypeChecker.check_duplicates(labels)
        if duplicates:
            print("There are duplicate labels: " + str(duplicates))
        return labels

    def check_dependencies(questions):
        dependencies = {}
        for question in questions:
            new_dependencies = question.all_dependencies()
            dependencies = dict(list(dependencies.items()) + list(new_dependencies.items()))
        return dependencies

    @staticmethod
    def type_checker(cinput, ctype=False):
        """
        This function allows to return the input type or to compare input type
        with pre-defined type
        :param cinput: the input to check
        :param ctype: The expected type to compare with, False to return the input type
        :return: True | False | 'number' | 'text' | 'bool' | 'list'
        """
        type_class = ...
        if isinstance(cinput, str):
            type_class = QuestionTypes.textName
        elif isinstance(cinput, (int, float, complex)): # in python 3 int = long
            type_class = QuestionTypes.numberName
        elif isinstance(cinput, bool):
            type_class = QuestionTypes.booleanName
        elif isinstance(cinput, list):
            type_class = QuestionTypes.listName

        if ctype & ctype is type_class:
            return True
        elif ctype:
            return False
        return type_class
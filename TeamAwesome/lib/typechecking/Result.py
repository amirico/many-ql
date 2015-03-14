# Why the vague name? We're not sure that in the future we want
# the type checker to be able to return more than simply a list
# of messages. Maybe something the evaluator can use.
# It's the Result Object pattern.

# It's also an object algebra! :)
class ResultAlg:
    def empty(self):
        pass

    def withError(self, error):
        pass

    def withWarning(self, warning):
        pass

    def merge(self, results):
        pass


# Default implementation.
# Allows you to extract a list of errors and warnings.
class DefaultResult(ResultAlg):
    def empty(self):
        return DefaultResult()

    @property
    def errors(self):
        return self._errors

    @property
    def warnings(self):
        return self._warnings

    def withError(self, error):
        return DefaultResult(self.errors + [error], self.warnings)

    def withWarning(self, warning):
        return DefaultResult(self.errors, self.warnings + [warning])

    def merge(self, results):
        result = self.empty()
        for r in results:
            result = DefaultResult(
                result.errors + r.errors,
                result.warnings + r.warnings
            )
        return result

    def __init__(self, errors = [], warnings = []):
        self._errors = errors
        self._warnings = warnings
package ast

sealed trait Type
case class BooleanType() extends Type
case class NumberType() extends Type
case class StringType() extends Type
case class UndefinedType() extends Type

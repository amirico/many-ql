package parser

import ast.QLAST

import scala.util.parsing.combinator.JavaTokenParsers

class QLParser extends JavaTokenParsers with QLAST {
  
  override val whiteSpace = """(\s|//.*|(?m)/\*(\*(?!/)|[^*])*\*/)+""".r

  def form: Parser[Form] = "form" ~> ident ~ expression ^^ {
    case name ~ expr => Form(name, expr)
  }

  def const: Parser[Const] = ("true" | "false") ^^ Const

  def variable: Parser[Variable] = ident ^^ Variable

  def label: Parser[String] = stringLiteral

  def expression: Parser[Expr] = "{" ~> rep(questionExpression | ifExpression) <~ "}" ^^ Sequence

  // parse questions
  def questionExpression: Parser[QuestionExpr] = "question" ~> variable ~ label ~ answer ^^ {
    case v ~ label ~ "boolean" => BooleanQuestion(v, label)
    case v ~ label ~ "integer" => IntegerQuestion(v, label)
    case v ~ label ~ "string" => StringQuestion(v, label)
  }

  def answer: Parser[String] = "answer" ~> ("boolean" | "integer" | "string")

  // parse if statements
  def ifExpression: Parser[IfExpr] = ("if" ~> variable) ~ expression ~ ("else" ~> expression ?) ^^ {
    case v ~ expr1 ~ expr2 => IfExpr(v, expr1, expr2)
  }

  // parse boolean expression
  def booleanExpression: Parser[Expr] = orExpression

  def orExpression: Parser[Expr] = rep1sep(andExpression, "or") ^^ {
    _.reduceLeft(Or)
  }

  def andExpression: Parser[Expr] = rep1sep(notExpression, "and") ^^ {
    _.reduceLeft(And)
  }

  def notExpression: Parser[Expr] = opt("not") ~ atom ^^ {
    case Some(_) ~ x => Not(x)
    case _ ~ x => x
  }

  def atom: Parser[Expr] = (const
    | variable
    | "(" ~> booleanExpression <~ ")"
    )

}
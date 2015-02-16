#
# DO NOT MODIFY!!!!
# This file is automatically generated by Racc 1.4.12
# from Racc grammer file "".
#

require 'racc/parser.rb'
module QL
  class Parser < Racc::Parser


  require_relative 'form'

  def initialize tokenizer
    @tokenizer = tokenizer
    super()
  end

  def next_token
    @tokenizer.next_token
  end

  def parse
    do_parse
  end
##### State transition tables begin ###

racc_action_table = [
    32,     8,    33,    34,    35,    36,    37,    38,    39,    40,
    41,    42,    43,    44,    59,    21,    33,    34,    35,    36,
    37,    38,    39,    40,    41,    42,    43,    44,    17,    26,
    27,     4,    60,    29,    30,    31,     3,    16,    25,    22,
    61,    33,    34,    35,    36,    37,    38,    39,    40,    41,
    42,    43,    44,    33,    34,    35,    36,    37,    38,    39,
    40,    41,    42,    43,    44,    33,    34,    35,    36,    37,
    38,    39,    40,    41,    42,    43,    44,    33,    34,    35,
    36,    37,    38,    39,    40,    41,    42,    43,    44,    33,
    34,    35,    36,    37,    38,    39,    40,    41,    42,    43,
    44,    33,    34,    35,    36,    37,    38,    39,    40,    41,
    42,    43,    44,    33,    34,    35,    36,    37,    38,    39,
    40,    41,    42,    43,    44,    33,    34,    35,    36,    37,
    38,    39,    40,    41,    42,    43,    44,    33,    34,    35,
    36,    37,    38,    39,    40,    41,    42,    43,    44,    33,
    34,    35,    36,    37,    38,    39,    40,    41,    42,    43,
    44,    33,    34,    35,    36,    37,    38,    39,    40,    41,
    42,    43,    44,    33,    34,    35,    36,    37,    38,    39,
    40,    41,    42,    43,    44,    26,    27,    26,    27,    26,
    27,    26,    27,     7,    25,    23,    25,     3,    25,   nil,
    25,    26,    27,    26,    27,    26,    27,    26,    27,   nil,
    25,   nil,    25,    17,    25,   nil,    25,    26,    27,    26,
    27,   nil,    16,    26,    27,    17,    25,    17,    25,    26,
    27,    18,    25,    17,    16,    17,    16,   nil,    25,    63,
    26,    27,    16,   nil,    16,   nil,   nil,   nil,   nil,    25 ]

racc_action_check = [
    24,     4,    24,    24,    24,    24,    24,    24,    24,    24,
    24,    24,    24,    24,    45,    13,    45,    45,    45,    45,
    45,    45,    45,    45,    45,    45,    45,    45,    46,    35,
    35,     1,    46,    23,    23,    23,     1,    46,    35,    16,
    46,    55,    55,    55,    55,    55,    55,    55,    55,    55,
    55,    55,    55,    49,    49,    49,    49,    49,    49,    49,
    49,    49,    49,    49,    49,    48,    48,    48,    48,    48,
    48,    48,    48,    48,    48,    48,    48,    56,    56,    56,
    56,    56,    56,    56,    56,    56,    56,    56,    56,    57,
    57,    57,    57,    57,    57,    57,    57,    57,    57,    57,
    57,    54,    54,    54,    54,    54,    54,    54,    54,    54,
    54,    54,    54,    47,    47,    47,    47,    47,    47,    47,
    47,    47,    47,    47,    47,    53,    53,    53,    53,    53,
    53,    53,    53,    53,    53,    53,    53,    51,    51,    51,
    51,    51,    51,    51,    51,    51,    51,    51,    51,    58,
    58,    58,    58,    58,    58,    58,    58,    58,    58,    58,
    58,    50,    50,    50,    50,    50,    50,    50,    50,    50,
    50,    50,    50,    52,    52,    52,    52,    52,    52,    52,
    52,    52,    52,    52,    52,    34,    34,    36,    36,    37,
    37,    38,    38,     3,    34,    20,    36,     0,    37,   nil,
    38,    39,    39,    33,    33,    41,    41,    42,    42,   nil,
    39,   nil,    33,    32,    41,   nil,    42,    43,    43,    44,
    44,   nil,    32,    25,    25,     6,    43,     9,    44,    22,
    22,     9,    25,    61,     6,    62,     9,   nil,    22,    62,
    40,    40,    61,   nil,    62,   nil,   nil,   nil,   nil,    40 ]

racc_action_pointer = [
   192,    31,   nil,   190,     1,   nil,   223,   nil,   nil,   225,
   nil,   nil,   nil,    12,   nil,   nil,    27,   nil,   nil,   nil,
   188,   nil,   226,    25,   -13,   220,   nil,   nil,   nil,   nil,
   nil,   nil,   211,   200,   182,    26,   184,   186,   188,   198,
   237,   202,   204,   214,   216,     1,    26,    98,    50,    38,
   146,   122,   158,   110,    86,    26,    62,    74,   134,   nil,
   nil,   231,   233,   nil ]

racc_action_default = [
   -34,   -34,    -2,   -34,   -34,    -1,   -34,    -4,    64,   -34,
    -6,    -7,    -8,   -34,   -14,   -15,   -34,   -33,    -3,    -5,
   -34,   -10,   -34,   -34,   -34,   -34,   -31,   -32,    -9,   -11,
   -12,   -13,   -34,   -34,   -34,   -34,   -34,   -34,   -34,   -34,
   -34,   -34,   -34,   -34,   -34,   -34,   -34,   -18,   -19,   -20,
   -21,   -22,   -23,   -24,   -25,   -26,   -27,   -28,   -29,   -30,
   -16,   -34,   -34,   -17 ]

racc_goto_table = [
     9,    19,    24,     2,     5,    45,    20,     6,    28,     1,
   nil,   nil,   nil,    47,    48,    49,    50,    51,    52,    53,
    54,    55,    56,    57,    58,   nil,    46,   nil,   nil,   nil,
   nil,   nil,   nil,   nil,   nil,   nil,   nil,   nil,    19,   nil,
   nil,   nil,   nil,   nil,   nil,   nil,   nil,   nil,   nil,   nil,
   nil,   nil,   nil,   nil,    19,    62 ]

racc_goto_check = [
     4,     5,    13,     2,     2,    13,     9,     3,    10,     1,
   nil,   nil,   nil,    13,    13,    13,    13,    13,    13,    13,
    13,    13,    13,    13,    13,   nil,     4,   nil,   nil,   nil,
   nil,   nil,   nil,   nil,   nil,   nil,   nil,   nil,     5,   nil,
   nil,   nil,   nil,   nil,   nil,   nil,   nil,   nil,   nil,   nil,
   nil,   nil,   nil,   nil,     5,     4 ]

racc_goto_pointer = [
   nil,     9,     3,     4,    -6,    -8,   nil,   nil,   nil,    -7,
   -15,   nil,   nil,   -20 ]

racc_goto_default = [
   nil,   nil,   nil,   nil,   nil,    10,    11,    12,    13,   nil,
   nil,    14,    15,   nil ]

racc_reduce_table = [
  0, 0, :racc_error,
  2, 28, :_reduce_1,
  1, 28, :_reduce_2,
  4, 29, :_reduce_3,
  1, 30, :_reduce_none,
  2, 31, :_reduce_5,
  1, 31, :_reduce_6,
  1, 32, :_reduce_none,
  1, 32, :_reduce_none,
  4, 33, :_reduce_9,
  1, 36, :_reduce_none,
  1, 37, :_reduce_none,
  1, 37, :_reduce_none,
  1, 37, :_reduce_none,
  1, 34, :_reduce_none,
  1, 34, :_reduce_none,
  6, 38, :_reduce_16,
  8, 39, :_reduce_17,
  3, 40, :_reduce_18,
  3, 40, :_reduce_19,
  3, 40, :_reduce_20,
  3, 40, :_reduce_21,
  3, 40, :_reduce_22,
  3, 40, :_reduce_23,
  3, 40, :_reduce_24,
  3, 40, :_reduce_25,
  3, 40, :_reduce_26,
  3, 40, :_reduce_27,
  3, 40, :_reduce_28,
  3, 40, :_reduce_29,
  3, 40, :_reduce_none,
  1, 40, :_reduce_none,
  1, 40, :_reduce_32,
  1, 35, :_reduce_33 ]

racc_reduce_n = 34

racc_shift_n = 64

racc_token_table = {
  false => 0,
  :error => 1,
  :STRING => 2,
  :VARIABLE_NAME => 3,
  :INTEGER => 4,
  "form" => 5,
  "end" => 6,
  ":" => 7,
  "boolean" => 8,
  "integer" => 9,
  "string" => 10,
  "if" => 11,
  "(" => 12,
  ")" => 13,
  "else" => 14,
  "==" => 15,
  "<=" => 16,
  "<" => 17,
  ">=" => 18,
  ">" => 19,
  "!=" => 20,
  "&&" => 21,
  "||" => 22,
  "*" => 23,
  "/" => 24,
  "+" => 25,
  "-" => 26 }

racc_nt_base = 27

racc_use_result_var = true

Racc_arg = [
  racc_action_table,
  racc_action_check,
  racc_action_default,
  racc_action_pointer,
  racc_goto_table,
  racc_goto_check,
  racc_goto_default,
  racc_goto_pointer,
  racc_nt_base,
  racc_reduce_table,
  racc_token_table,
  racc_shift_n,
  racc_reduce_n,
  racc_use_result_var ]

Racc_token_to_s_table = [
  "$end",
  "error",
  "STRING",
  "VARIABLE_NAME",
  "INTEGER",
  "\"form\"",
  "\"end\"",
  "\":\"",
  "\"boolean\"",
  "\"integer\"",
  "\"string\"",
  "\"if\"",
  "\"(\"",
  "\")\"",
  "\"else\"",
  "\"==\"",
  "\"<=\"",
  "\"<\"",
  "\">=\"",
  "\">\"",
  "\"!=\"",
  "\"&&\"",
  "\"||\"",
  "\"*\"",
  "\"/\"",
  "\"+\"",
  "\"-\"",
  "$start",
  "forms",
  "form",
  "form_name",
  "statements",
  "statement",
  "question",
  "conditional",
  "string",
  "variable_name",
  "type",
  "if",
  "if_else",
  "expression" ]

Racc_debug_parser = false

##### State transition tables end #####

# reduce 0 omitted

def _reduce_1(val, _values, result)
 result = val[0].push(val[1]) 
    result
end

def _reduce_2(val, _values, result)
 result = [ val[0] ] 
    result
end

def _reduce_3(val, _values, result)
 result = Form.new(name: val[1], statements: val[2]) 
    result
end

# reduce 4 omitted

def _reduce_5(val, _values, result)
 result = val[0].push(val[1]) 
    result
end

def _reduce_6(val, _values, result)
 result = [ val[0] ] 
    result
end

# reduce 7 omitted

# reduce 8 omitted

def _reduce_9(val, _values, result)
 result = Question.new(description: val[0], variable_name: val[1], type: val[3]) 
    result
end

# reduce 10 omitted

# reduce 11 omitted

# reduce 12 omitted

# reduce 13 omitted

# reduce 14 omitted

# reduce 15 omitted

def _reduce_16(val, _values, result)
 result = If.new(expression: val[2], statements: val[4]) 
    result
end

def _reduce_17(val, _values, result)
 result = IfElse.new(expression: val[2], statements_true: val[4], statements_false: val[6]) 
    result
end

def _reduce_18(val, _values, result)
 result = CompareExpression.new(operator: :==, arguments: [val[0], val[2]]) 
    result
end

def _reduce_19(val, _values, result)
 result = CompareExpression.new(operator: :<=, arguments: [val[0], val[2]]) 
    result
end

def _reduce_20(val, _values, result)
 result = CompareExpression.new(operator: :<, arguments: [val[0], val[2]]) 
    result
end

def _reduce_21(val, _values, result)
 result = CompareExpression.new(operator: :>=, arguments: [val[0], val[2]]) 
    result
end

def _reduce_22(val, _values, result)
 result = CompareExpression.new(operator: :>, arguments: [val[0], val[2]]) 
    result
end

def _reduce_23(val, _values, result)
 result = CompareExpression.new(operator: :==,  arguments:[val[0], val[2]]) 
    result
end

def _reduce_24(val, _values, result)
 result = BooleanExpression.new(operator: :and, arguments: [val[0], val[2]]) 
    result
end

def _reduce_25(val, _values, result)
 result = BooleanExpression.new(operator: :or, arguments: [val[0], val[2]]) 
    result
end

def _reduce_26(val, _values, result)
 result =  NumberExpression.new(operator: :* , arguments: [val[0], val[2]]) 
    result
end

def _reduce_27(val, _values, result)
 result =  NumberExpression.new(operator: :/ , arguments: [val[0], val[2]]) 
    result
end

def _reduce_28(val, _values, result)
 result =  NumberExpression.new(operator: :+ , arguments: [val[0], val[2]]) 
    result
end

def _reduce_29(val, _values, result)
 result =  NumberExpression.new(operator: :- , arguments: [val[0], val[2]]) 
    result
end

# reduce 30 omitted

# reduce 31 omitted

def _reduce_32(val, _values, result)
 result = val[0].to_i 
    result
end

def _reduce_33(val, _values, result)
 result = val[0][1..-2] 
    result
end

def _reduce_none(val, _values, result)
  val[0]
end

  end   # class Parser
  end   # module QL

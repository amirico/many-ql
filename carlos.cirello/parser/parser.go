//line parser.y:2
package parser

import __yyfmt__ "fmt"

//line parser.y:3
import (
	"fmt"
	"strconv"
	"text/scanner"

	"github.com/software-engineering-amsterdam/many-ql/carlos.cirello/ast"
)

var finalQuestionaire *ast.QuestionaireNode

//Top Ends Here

//line parser.y:20
type qlSymType struct {
	yys     int
	content string
	num     float32

	evaluatables []ast.Evaluatable
	evaluatable  ast.Evaluatable
	ifNode       *ast.IfNode
	question     *ast.QuestionNode
	questionaire *ast.QuestionaireNode
	questionType ast.Parser
	stack        []*ast.ActionNode
	termNode     *ast.TermNode

	position scanner.Position
}

const BlockBeginToken = 57346
const BlockEndToken = 57347
const FormToken = 57348
const IfToken = 57349
const ParenBeginToken = 57350
const ParenEndToken = 57351
const QuotedStringToken = 57352
const TextToken = 57353
const StringQuestionToken = 57354
const IntQuestionToken = 57355
const BoolQuestionToken = 57356
const LessThanToken = 57357
const LessOrEqualsThanToken = 57358
const MoreThanToken = 57359
const MoreOrEqualsThanToken = 57360
const EqualsToToken = 57361
const NumericToken = 57362

var qlToknames = []string{
	"'+'",
	"'-'",
	"'*'",
	"'/'",
	"BlockBeginToken",
	"BlockEndToken",
	"FormToken",
	"IfToken",
	"ParenBeginToken",
	"ParenEndToken",
	"QuotedStringToken",
	"TextToken",
	"StringQuestionToken",
	"IntQuestionToken",
	"BoolQuestionToken",
	"'('",
	"')'",
	"LessThanToken",
	"LessOrEqualsThanToken",
	"MoreThanToken",
	"MoreOrEqualsThanToken",
	"EqualsToToken",
	"NumericToken",
}
var qlStatenames = []string{}

const qlEofCode = 1
const qlErrCode = 2
const qlMaxDepth = 200

//line parser.y:252

//line yacctab:1
var qlExca = []int{
	-1, 1,
	1, -1,
	-2, 0,
}

const qlNprod = 27
const qlPrivate = 57344

var qlTokenNames []string
var qlStates []string

const qlLast = 56

var qlAct = []int{

	20, 6, 32, 33, 34, 35, 25, 11, 11, 37,
	10, 10, 5, 26, 13, 24, 23, 12, 4, 29,
	31, 28, 30, 27, 48, 7, 36, 22, 38, 39,
	40, 41, 42, 43, 44, 45, 46, 3, 21, 47,
	18, 15, 16, 17, 32, 33, 34, 35, 34, 35,
	19, 14, 9, 8, 2, 1,
}
var qlPact = []int{

	27, -1000, -1000, 3, -15, -1000, -3, -1000, -1000, -1000,
	2, -5, 25, 1, -23, -1000, -1000, -1000, -1000, -7,
	-2, -1000, -1000, -1000, -1000, 1, -18, 1, 1, 1,
	1, 1, 1, 1, 1, 1, 40, -1000, 40, 40,
	40, 40, 40, 42, 42, -1000, -1000, -4, -1000,
}
var qlPgo = []int{

	0, 55, 54, 1, 53, 52, 51, 0, 50, 38,
}
var qlR1 = []int{

	0, 1, 2, 3, 3, 3, 4, 4, 6, 6,
	6, 6, 5, 8, 8, 8, 8, 8, 8, 7,
	7, 7, 7, 7, 9, 9, 9,
}
var qlR2 = []int{

	0, 1, 5, 0, 2, 2, 3, 5, 1, 1,
	1, 1, 7, 3, 3, 3, 3, 3, 1, 3,
	3, 3, 3, 1, 1, 1, 1,
}
var qlChk = []int{

	-1000, -1, -2, 10, 15, 27, -3, 28, -4, -5,
	14, 11, 15, 19, -6, 16, 17, 18, 15, -8,
	-7, -9, 26, 15, 14, 29, 20, 25, 23, 21,
	24, 22, 4, 5, 6, 7, -7, 27, -7, -7,
	-7, -7, -7, -7, -7, -7, -7, -3, 28,
}
var qlDef = []int{

	0, -2, 1, 0, 0, 3, 0, 2, 4, 5,
	0, 0, 0, 0, 6, 8, 9, 10, 11, 0,
	18, 23, 24, 25, 26, 0, 0, 0, 0, 0,
	0, 0, 0, 0, 0, 0, 7, 3, 13, 14,
	15, 16, 17, 19, 20, 21, 22, 0, 12,
}
var qlTok1 = []int{

	1, 3, 3, 3, 3, 3, 3, 3, 3, 3,
	3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
	3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
	3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
	19, 20, 6, 4, 3, 5, 3, 7, 3, 3,
	3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
	3, 29, 3, 3, 3, 3, 3, 3, 3, 3,
	3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
	3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
	3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
	3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
	3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
	3, 3, 3, 27, 3, 28,
}
var qlTok2 = []int{

	2, 3, 8, 9, 10, 11, 12, 13, 14, 15,
	16, 17, 18, 21, 22, 23, 24, 25, 26,
}
var qlTok3 = []int{
	0,
}

//line yaccpar:1

/*	parser for yacc output	*/

var qlDebug = 0

type qlLexer interface {
	Lex(lval *qlSymType) int
	Error(s string)
}

const qlFlag = -1000

func qlTokname(c int) string {
	// 4 is TOKSTART above
	if c >= 4 && c-4 < len(qlToknames) {
		if qlToknames[c-4] != "" {
			return qlToknames[c-4]
		}
	}
	return __yyfmt__.Sprintf("tok-%v", c)
}

func qlStatname(s int) string {
	if s >= 0 && s < len(qlStatenames) {
		if qlStatenames[s] != "" {
			return qlStatenames[s]
		}
	}
	return __yyfmt__.Sprintf("state-%v", s)
}

func qllex1(lex qlLexer, lval *qlSymType) int {
	c := 0
	char := lex.Lex(lval)
	if char <= 0 {
		c = qlTok1[0]
		goto out
	}
	if char < len(qlTok1) {
		c = qlTok1[char]
		goto out
	}
	if char >= qlPrivate {
		if char < qlPrivate+len(qlTok2) {
			c = qlTok2[char-qlPrivate]
			goto out
		}
	}
	for i := 0; i < len(qlTok3); i += 2 {
		c = qlTok3[i+0]
		if c == char {
			c = qlTok3[i+1]
			goto out
		}
	}

out:
	if c == 0 {
		c = qlTok2[1] /* unknown char */
	}
	if qlDebug >= 3 {
		__yyfmt__.Printf("lex %s(%d)\n", qlTokname(c), uint(char))
	}
	return c
}

func qlParse(qllex qlLexer) int {
	var qln int
	var qllval qlSymType
	var qlVAL qlSymType
	qlS := make([]qlSymType, qlMaxDepth)

	Nerrs := 0   /* number of errors */
	Errflag := 0 /* error recovery flag */
	qlstate := 0
	qlchar := -1
	qlp := -1
	goto qlstack

ret0:
	return 0

ret1:
	return 1

qlstack:
	/* put a state and value onto the stack */
	if qlDebug >= 4 {
		__yyfmt__.Printf("char %v in %v\n", qlTokname(qlchar), qlStatname(qlstate))
	}

	qlp++
	if qlp >= len(qlS) {
		nyys := make([]qlSymType, len(qlS)*2)
		copy(nyys, qlS)
		qlS = nyys
	}
	qlS[qlp] = qlVAL
	qlS[qlp].yys = qlstate

qlnewstate:
	qln = qlPact[qlstate]
	if qln <= qlFlag {
		goto qldefault /* simple state */
	}
	if qlchar < 0 {
		qlchar = qllex1(qllex, &qllval)
	}
	qln += qlchar
	if qln < 0 || qln >= qlLast {
		goto qldefault
	}
	qln = qlAct[qln]
	if qlChk[qln] == qlchar { /* valid shift */
		qlchar = -1
		qlVAL = qllval
		qlstate = qln
		if Errflag > 0 {
			Errflag--
		}
		goto qlstack
	}

qldefault:
	/* default state action */
	qln = qlDef[qlstate]
	if qln == -2 {
		if qlchar < 0 {
			qlchar = qllex1(qllex, &qllval)
		}

		/* look through exception table */
		xi := 0
		for {
			if qlExca[xi+0] == -1 && qlExca[xi+1] == qlstate {
				break
			}
			xi += 2
		}
		for xi += 2; ; xi += 2 {
			qln = qlExca[xi+0]
			if qln < 0 || qln == qlchar {
				break
			}
		}
		qln = qlExca[xi+1]
		if qln < 0 {
			goto ret0
		}
	}
	if qln == 0 {
		/* error ... attempt to resume parsing */
		switch Errflag {
		case 0: /* brand new error */
			qllex.Error("syntax error")
			Nerrs++
			if qlDebug >= 1 {
				__yyfmt__.Printf("%s", qlStatname(qlstate))
				__yyfmt__.Printf(" saw %s\n", qlTokname(qlchar))
			}
			fallthrough

		case 1, 2: /* incompletely recovered error ... try again */
			Errflag = 3

			/* find a state where "error" is a legal shift action */
			for qlp >= 0 {
				qln = qlPact[qlS[qlp].yys] + qlErrCode
				if qln >= 0 && qln < qlLast {
					qlstate = qlAct[qln] /* simulate a shift of "error" */
					if qlChk[qlstate] == qlErrCode {
						goto qlstack
					}
				}

				/* the current p has no shift on "error", pop stack */
				if qlDebug >= 2 {
					__yyfmt__.Printf("error recovery pops state %d\n", qlS[qlp].yys)
				}
				qlp--
			}
			/* there is no state on the stack with an error shift ... abort */
			goto ret1

		case 3: /* no shift yet; clobber input char */
			if qlDebug >= 2 {
				__yyfmt__.Printf("error recovery discards %s\n", qlTokname(qlchar))
			}
			if qlchar == qlEofCode {
				goto ret1
			}
			qlchar = -1
			goto qlnewstate /* try again in the same state */
		}
	}

	/* reduction by production qln */
	if qlDebug >= 2 {
		__yyfmt__.Printf("reduce %v in:\n\t%v\n", qln, qlStatname(qlstate))
	}

	qlnt := qln
	qlpt := qlp
	_ = qlpt // guard against "declared and not used"

	qlp -= qlR2[qln]
	qlVAL = qlS[qlp+1]

	/* consult goto table to find next state */
	qln = qlR1[qln]
	qlg := qlPgo[qln]
	qlj := qlg + qlS[qlp].yys + 1

	if qlj >= qlLast {
		qlstate = qlAct[qlg]
	} else {
		qlstate = qlAct[qlj]
		if qlChk[qlstate] != -qln {
			qlstate = qlAct[qlg]
		}
	}
	// dummy call; replaced with literal code
	switch qlnt {

	case 1:
		//line parser.y:62
		{
			finalQuestionaire = qlS[qlpt-0].questionaire
		}
	case 2:
		//line parser.y:69
		{
			qlVAL.questionaire = &ast.QuestionaireNode{
				Label: qlS[qlpt-3].content,
				Stack: qlS[qlpt-1].stack,
			}
		}
	case 4:
		//line parser.y:79
		{
			q := qlS[qlpt-0].question
			qs := qlVAL.stack
			action := &ast.ActionNode{Action: q}
			qs = append(qs, action)
			qlVAL.stack = qs
		}
	case 5:
		//line parser.y:87
		{
			ifNode := qlS[qlpt-0].ifNode
			qs := qlVAL.stack
			action := &ast.ActionNode{Action: ifNode}
			qs = append(qs, action)
			qlVAL.stack = qs
		}
	case 6:
		//line parser.y:98
		{
			qlVAL.question = &ast.QuestionNode{
				Label:      qlS[qlpt-2].content,
				Identifier: qlS[qlpt-1].content,
				Content:    qlS[qlpt-0].questionType,
			}
		}
	case 7:
		//line parser.y:107
		{
			qlVAL.question = &ast.QuestionNode{
				Label:           qlS[qlpt-4].content,
				Identifier:      qlS[qlpt-3].content,
				Content:         qlS[qlpt-2].questionType,
				ComputedContent: qlS[qlpt-0].evaluatable,
			}
		}
	case 8:
		//line parser.y:121
		{
			qlVAL.questionType = new(ast.StringQuestion)
		}
	case 9:
		//line parser.y:125
		{
			qlVAL.questionType = new(ast.IntQuestion)
		}
	case 10:
		//line parser.y:129
		{
			qlVAL.questionType = new(ast.BoolQuestion)
		}
	case 11:
		//line parser.y:133
		{
			qllex.Error(fmt.Sprintf("Question type must be 'string', 'integer', 'bool'. Found: %s", qlS[qlpt-0].content))
		}
	case 12:
		//line parser.y:140
		{
			ifNode := new(ast.IfNode)
			ifNode.Conditions = qlS[qlpt-4].evaluatable
			ifNode.Stack = qlS[qlpt-1].stack
			qlVAL.ifNode = ifNode

			qlVAL.evaluatable = new(ast.Evaluatable)
			qlVAL.stack = []*ast.ActionNode{}
			qlS[qlpt-4].evaluatable = new(ast.Evaluatable)
			qlS[qlpt-1].stack = []*ast.ActionNode{}
		}
	case 13:
		//line parser.y:155
		{
			condition := new(ast.EqualsNode)
			condition.LeftTerm = qlS[qlpt-2].evaluatable
			condition.RightTerm = qlS[qlpt-0].evaluatable
			qlVAL.evaluatable = condition
		}
	case 14:
		//line parser.y:162
		{
			condition := new(ast.MoreThanNode)
			condition.LeftTerm = qlS[qlpt-2].evaluatable
			condition.RightTerm = qlS[qlpt-0].evaluatable
			qlVAL.evaluatable = condition
		}
	case 15:
		//line parser.y:169
		{
			condition := new(ast.LessThanNode)
			condition.LeftTerm = qlS[qlpt-2].evaluatable
			condition.RightTerm = qlS[qlpt-0].evaluatable
			qlVAL.evaluatable = condition
		}
	case 16:
		//line parser.y:176
		{
			condition := new(ast.MoreOrEqualsThanNode)
			condition.LeftTerm = qlS[qlpt-2].evaluatable
			condition.RightTerm = qlS[qlpt-0].evaluatable
			qlVAL.evaluatable = condition
		}
	case 17:
		//line parser.y:183
		{
			condition := new(ast.LessOrEqualsThanNode)
			condition.LeftTerm = qlS[qlpt-2].evaluatable
			condition.RightTerm = qlS[qlpt-0].evaluatable
			qlVAL.evaluatable = condition
		}
	case 19:
		//line parser.y:194
		{
			condition := new(ast.MathAddNode)
			condition.LeftTerm = qlS[qlpt-2].evaluatable
			condition.RightTerm = qlS[qlpt-0].evaluatable
			qlVAL.evaluatable = condition
		}
	case 20:
		//line parser.y:201
		{
			condition := new(ast.MathSubNode)
			condition.LeftTerm = qlS[qlpt-2].evaluatable
			condition.RightTerm = qlS[qlpt-0].evaluatable
			qlVAL.evaluatable = condition
		}
	case 21:
		//line parser.y:208
		{
			condition := new(ast.MathMulNode)
			condition.LeftTerm = qlS[qlpt-2].evaluatable
			condition.RightTerm = qlS[qlpt-0].evaluatable
			qlVAL.evaluatable = condition
		}
	case 22:
		//line parser.y:215
		{
			condition := new(ast.MathDivNode)
			condition.LeftTerm = qlS[qlpt-2].evaluatable
			condition.RightTerm = qlS[qlpt-0].evaluatable
			qlVAL.evaluatable = condition
		}
	case 23:
		//line parser.y:222
		{
			qlVAL.evaluatable = qlS[qlpt-0].termNode
		}
	case 24:
		//line parser.y:229
		{
			num, _ := strconv.ParseFloat(qlS[qlpt-0].content, 32)
			qlVAL.num = float32(num)
			termNode := new(ast.TermNode)
			termNode.NumericConstant = qlVAL.num
			termNode.Type = ast.NumericConstantNodeType
			qlVAL.termNode = termNode
		}
	case 25:
		//line parser.y:238
		{
			termNode := new(ast.TermNode)
			termNode.IdentifierReference = qlS[qlpt-0].content
			termNode.Type = ast.IdentifierReferenceNodeType
			qlVAL.termNode = termNode
		}
	case 26:
		//line parser.y:245
		{
			termNode := new(ast.TermNode)
			termNode.StringConstant = qlS[qlpt-0].content
			termNode.Type = ast.StringConstantNodeType
			qlVAL.termNode = termNode
		}
	}
	goto qlstack /* stack new state and value */
}
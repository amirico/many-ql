package nl.uva.softwcons.qls.ast;

import static nl.uva.softwcons.ql.ast.type.BooleanType.BOOLEAN_TYPE;
import static nl.uva.softwcons.ql.ast.type.DateType.DATE_TYPE;
import static nl.uva.softwcons.ql.ast.type.NumberType.NUMBER_TYPE;
import static nl.uva.softwcons.ql.ast.type.StringType.STRING_TYPE;
import static nl.uva.softwcons.ql.ast.type.UndefinedType.UNDEFINED_TYPE;

import java.util.List;
import java.util.stream.Collectors;

import nl.uva.softwcons.generated.QLSBaseVisitor;
import nl.uva.softwcons.generated.QLSParser.CheckboxContext;
import nl.uva.softwcons.generated.QLSParser.DefaultStatementContext;
import nl.uva.softwcons.generated.QLSParser.DropdownContext;
import nl.uva.softwcons.generated.QLSParser.PageContext;
import nl.uva.softwcons.generated.QLSParser.QuestionWithWidgetContext;
import nl.uva.softwcons.generated.QLSParser.QuestionWithoutWidgetContext;
import nl.uva.softwcons.generated.QLSParser.RadioContext;
import nl.uva.softwcons.generated.QLSParser.SectionContext;
import nl.uva.softwcons.generated.QLSParser.SliderContext;
import nl.uva.softwcons.generated.QLSParser.SpinboxContext;
import nl.uva.softwcons.generated.QLSParser.StyleContext;
import nl.uva.softwcons.generated.QLSParser.StylePropertyContext;
import nl.uva.softwcons.generated.QLSParser.StylesheetContext;
import nl.uva.softwcons.generated.QLSParser.TextContext;
import nl.uva.softwcons.generated.QLSParser.WidgetWithStyleContext;
import nl.uva.softwcons.generated.QLSParser.WidgetWithoutStyleContext;
import nl.uva.softwcons.ql.ast.LineInfo;
import nl.uva.softwcons.ql.ast.expression.identifier.Identifier;
import nl.uva.softwcons.ql.ast.type.Type;
import nl.uva.softwcons.ql.util.Utils;
import nl.uva.softwcons.qls.ast.segment.Page;
import nl.uva.softwcons.qls.ast.segment.PageSegment;
import nl.uva.softwcons.qls.ast.segment.Question;
import nl.uva.softwcons.qls.ast.segment.Section;
import nl.uva.softwcons.qls.ast.style.Style;
import nl.uva.softwcons.qls.ast.style.StyleProperty;
import nl.uva.softwcons.qls.ast.stylesheet.Stylesheet;
import nl.uva.softwcons.qls.ast.widget.StylizedType;
import nl.uva.softwcons.qls.ast.widget.StylizedWidget;
import nl.uva.softwcons.qls.ast.widget.type.CheckboxType;
import nl.uva.softwcons.qls.ast.widget.type.DropdownType;
import nl.uva.softwcons.qls.ast.widget.type.RadioButtonType;
import nl.uva.softwcons.qls.ast.widget.type.SliderType;
import nl.uva.softwcons.qls.ast.widget.type.SpinboxType;
import nl.uva.softwcons.qls.ast.widget.type.TextType;
import nl.uva.softwcons.qls.ast.widget.type.WidgetType;

import org.antlr.v4.runtime.Token;

public class ASTBuilderVisitor extends QLSBaseVisitor<ASTNode> {

    @Override
    public Stylesheet visitStylesheet(final StylesheetContext ctx) {
        final Identifier id = new Identifier(ctx.ID().getText(), extractLineInfo(ctx.ID().getSymbol()));
        final List<Page> pages = ctx.page().stream().map(st -> (Page) st.accept(this)).collect(Collectors.toList());

        return new Stylesheet(id, pages, extractLineInfo(ctx.ID().getSymbol()));
    }

    @Override
    public Page visitPage(final PageContext ctx) {
        final Identifier id = new Identifier(ctx.ID().getText(), extractLineInfo(ctx.ID().getSymbol()));
        final List<PageSegment> sections = ctx.pageSegment().stream().map(st -> (PageSegment) st.accept(this))
                .collect(Collectors.toList());

        final List<StylizedType> styles = ctx.defaultStatement().stream().map(st -> (StylizedType) st.accept(this))
                .collect(Collectors.toList());

        return new Page(id, sections, styles, extractLineInfo(ctx.ID().getSymbol()));
    }

    @Override
    public Section visitSection(final SectionContext ctx) {
        final String label = Utils.unquote(ctx.STRING().getText());
        final List<PageSegment> content = ctx.pageSegment().stream().map(st -> (PageSegment) st.accept(this))
                .collect(Collectors.toList());

        final List<StylizedType> styles = ctx.defaultStatement().stream().map(st -> (StylizedType) st.accept(this))
                .collect(Collectors.toList());

        return new Section(label, content, styles, extractLineInfo(ctx.STRING().getSymbol()));
    }

    @Override
    public StylizedType visitDefaultStatement(final DefaultStatementContext ctx) {
        final Type questionType = getType(ctx.type().getText());
        final StylizedWidget widget = (StylizedWidget) ctx.widget().accept(this);

        return new StylizedType(questionType, widget);

    }

    @Override
    public Question visitQuestionWithoutWidget(final QuestionWithoutWidgetContext ctx) {
        final Identifier id = new Identifier(ctx.ID().getText(), extractLineInfo(ctx.ID().getSymbol()));

        return new Question(id, extractLineInfo(ctx.ID().getSymbol()));
    }

    @Override
    public ASTNode visitQuestionWithWidget(final QuestionWithWidgetContext ctx) {
        final Identifier id = new Identifier(ctx.ID().getText(), extractLineInfo(ctx.ID().getSymbol()));
        final StylizedWidget widget = (StylizedWidget) ctx.widget().accept(this);

        return new Question(id, widget, extractLineInfo(ctx.ID().getSymbol()));
    }

    @Override
    public StylizedWidget visitWidgetWithoutStyle(final WidgetWithoutStyleContext ctx) {
        final WidgetType type = (WidgetType) ctx.widgetType().accept(this);

        return new StylizedWidget(type);
    }

    @Override
    public StylizedWidget visitWidgetWithStyle(final WidgetWithStyleContext ctx) {
        final WidgetType type = (WidgetType) ctx.widgetType().accept(this);
        final Style style = (Style) ctx.style().accept(this);

        return new StylizedWidget(type, style);
    }

    @Override
    public Style visitStyle(final StyleContext ctx) {
        final List<StyleProperty> styles = ctx.styleProperty().stream().map(st -> (StyleProperty) st.accept(this))
                .collect(Collectors.toList());
        return new Style(styles);
    }

    @Override
    public StyleProperty visitStyleProperty(final StylePropertyContext ctx) {
        return new StyleProperty(ctx.key.getText(), Utils.unquote(ctx.value().getText()));
    }

    @Override
    public CheckboxType visitCheckbox(final CheckboxContext ctx) {
        return new CheckboxType(Utils.unquote(ctx.yes.getText()));
    }

    @Override
    public DropdownType visitDropdown(final DropdownContext ctx) {
        return new DropdownType(Utils.unquote(ctx.yes.getText()), Utils.unquote(ctx.no.getText()));
    }

    @Override
    public SpinboxType visitSpinbox(final SpinboxContext ctx) {
        return new SpinboxType();
    }

    @Override
    public SliderType visitSlider(final SliderContext ctx) {
        return new SliderType();
    }

    @Override
    public TextType visitText(final TextContext ctx) {
        return new TextType();
    }

    @Override
    public RadioButtonType visitRadio(final RadioContext ctx) {
        return new RadioButtonType(Utils.unquote(ctx.yes.getText()), Utils.unquote(ctx.no.getText()));
    }

    private LineInfo extractLineInfo(final Token token) {
        return new LineInfo(token.getLine(), token.getCharPositionInLine());
    }

    private Type getType(final String typeName) {

        switch (typeName) {
        case "boolean":
            return BOOLEAN_TYPE;
        case "number":
            return NUMBER_TYPE;
        case "date":
            return DATE_TYPE;
        case "string":
            return STRING_TYPE;
        default:
            return UNDEFINED_TYPE;
        }
    }

}

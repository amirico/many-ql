package nl.uva.se.ql.gui;

//TODO REMOVE THIS CLASS BEFORE HANDING IN!
import javafx.application.Application;
import javafx.stage.Stage;
public class Gui extends Application {

	public static void main(String[] args) {
		//launch(args);
		System.out.println("isValid: " + isValid("11"));
		System.out.println("isValid: " + isValid("11.00"));
		System.out.println("isValid: " + isValid("11"));
	}
	
	public static boolean isValid(String input) {
		if (input.matches("\\d+(\\.\\d+)")) {
			return true;
		}
		return false;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		/*List<Statement> statements = Collections.emptyList();
		Form form = new Form("1", 1, 1, statements); 
		QuestionPane questionPane = new QuestionPane(form);
		
		Question age = new Question(1, 1, "2", new BooleanType(), "wat is your age?"); 
		Question that = new Question(1,1, "3", new DecimalType(), "wat is that?");	
				
		QuestionBox thatQuestionBox = new QuestionBox(that);
		
		Condition condition = new Condition(1, 2, null, statements);
		ConditionBox conditionBox = new ConditionBox(condition);
		conditionBox.addQuestionBox(thatQuestionBox);
		
		questionPane.addQuestion(age);
		questionPane.addConditionBox(conditionBox);
		
		
		Scene scene = new Scene(questionPane, 350, 350);
		primaryStage.setTitle(questionPane.getForm().getId());
		primaryStage.setScene(scene);
		primaryStage.show();	*/	
	}

}
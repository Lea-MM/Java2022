package exercise1;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
	
	// constant list of courses
	private final ObservableList<String> cmptrScienceList = FXCollections.observableArrayList("Python", "C#", "Java");
	private final ObservableList<String> businessList = FXCollections.observableArrayList("Economics", "Marketing", "Business Management");
	
	// instance variables
	private ObservableList<String> option;	// set the combo box value by default
	private ListView<String> selectedCourses = new ListView<String>();;	// stores the selected courses
	private TextField name = createTextField();
	private TextField address = createTextField();
	private TextField province = createTextField();
	private TextField city = createTextField();
	private TextField postalCode = createTextField();
	private TextField phone = createTextField();
	private TextField email = createTextField();
	private CheckBox isStudentCouncil = createCheckBox("Student Council");
	private CheckBox isVolunteer = createCheckBox("Volunteer Work");
	private ToggleGroup group = new ToggleGroup();
	private RadioButton btnComputerScience = createRadioButton("Computer Science");
	private RadioButton btnBusiness = createRadioButton("Business");
	TextArea txtArea = createTxtArea("Student information will be displayed here.");
	private String program = "Computer Science";
	
	
	// method to build the UI and listen to events
	protected VBox getPane() {
		
		// create the labels
		GridPane labels_gridpane1 = addLabel_Txt(); 
		
		// create the text fields and check boxes
		GridPane TxtFldAndChckBx_gridpane2 = addInput_TxtFldAndChckBx();
		
		// add the radio buttons, combo box, and list box
		VBox vBox1 = addRadioComboListBox();
		
		
		// create the horizontal layout: labels, text fields, check boxes, radio buttons, etc...
		HBox horizontalLayout = new HBox();
		horizontalLayout.setPadding(new Insets(5, 5, 0, 5));
		horizontalLayout.setSpacing(10);
		horizontalLayout.getChildren().addAll(labels_gridpane1, TxtFldAndChckBx_gridpane2, vBox1);
		
		
		// create the vertical layout: the above pane, button, and list view
		Button btn = createBtn(); 
		btn.setOnAction(e -> {
			try {
				displayInfo();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1, "Invalid Input", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		VBox verticalLayout = new VBox();
		verticalLayout.setStyle("-fx-background-color: #336699;");
		verticalLayout.setSpacing(10); verticalLayout.setAlignment(Pos.CENTER);
		verticalLayout.getChildren().addAll(horizontalLayout, btn, txtArea);
		
		return verticalLayout;
	}

	// override the parent class
	@Override
	public void start(Stage primaryStage) throws Exception {		
		// Create a scene and place it in the stage
	    Scene scene = new Scene(getPane(), 945, 500);
	    primaryStage.setTitle("Student Information Form");
	    primaryStage.setScene(scene);
	    primaryStage.show(); 
	}
	
	// run the main
	public static void main(String[] args) {
		launch(args);
	}
	
	
	// method to display the information
	public void displayInfo() throws Exception {	
		
		// check province if it is in the correct format
		if(!province.getText().matches("[A-Z]{2}")) { 
			province.setStyle("-fx-background-color: #FDA4BA; ");
			throw new Exception("Province should have two values between A and Z. Example ON for Ontario"); 
		}
		province.setStyle("-fx-background-color: white;");
		
		// check postal code if it is in the correct format
		if(!postalCode.getText().matches("[A-Za-z]\\d[A-Za-z][ ]?\\d[A-Za-z]\\d")) {
			postalCode.setStyle("-fx-background-color: #FDA4BA; ");
			throw new Exception("Postal code must be in the correct format. Example M1P 2E9 or m1p 2e9.");
		}
		postalCode.setStyle("-fx-background-color: white;");
		
		// check phone number if it is in the correct format
		if (!phone.getText().matches("\\(\\d{3}\\)[ ]\\d{3}\\-\\d{4}")) {
			phone.setStyle("-fx-background-color: #FDA4BA; ");
			throw new Exception("Phone number should be in the correct format. Example (123) 456-7890.");
		}
		phone.setStyle("-fx-background-color: white;");
		
		// check email if it is in the correct format
		if (!email.getText().matches("^[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*@[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*$")) {
			email.setStyle("-fx-background-color: #FDA4BA; ");
			throw new Exception("Email should be in the correct format. Example leamagbalot@email.com");
		}
		email.setStyle("-fx-background-color: white;");
		
		System.out.println(isVolunteer.getText());
				
		// check for the value and display
		if (!name.getText().equals("") || !address.getText().equals("") || !city.getText().equals("")) 
		{
			StringBuilder allCourses = new StringBuilder();
			for(String c:selectedCourses.getItems())
			{ 
				allCourses.append("    " + c); 
				allCourses.append("\n"); 
			}
			
			String schoolActivities = "Not currently active.";
			
			if(isVolunteer.isSelected() && isStudentCouncil.isSelected()) {
				schoolActivities = "   - Student Council\n   - Volunteer";
			}
			else if(!isVolunteer.isSelected() && isStudentCouncil.isSelected()) {
				schoolActivities = "   - Student Council";
			}
			else if(isVolunteer.isSelected() && !isStudentCouncil.isSelected()) {
				schoolActivities = "   - Volunteer";
			}
			
			txtArea.setText(
							   name.getText() + "\n" + 
							   address.getText() + ", " + 
							   city.getText() + ", " +
							   province.getText() + " " + 
							   postalCode.getText() + "\n" + 
							   phone.getText() + "\n" + 
							   email.getText() + "\n\n" + 
							   "Program: " + program + "\n" +
							   "Courses: " + "\n" + allCourses + "\n\n" + 
							   "School Activities:\n" + schoolActivities
							);
		}
	}
	
	// method to combine the radio buttons, combo box, and list box
	public VBox addRadioComboListBox() {
		
		// modify the size of the list view
		selectedCourses.setMaxHeight(147);
		selectedCourses.setMinWidth(300);
		 		
		// create the container for the radio button, combo box, and list box
		VBox vBox = new VBox();
		vBox.setAlignment(Pos.TOP_LEFT);
		vBox.setPadding(new Insets(10, 0, 0, 50));
		vBox.setSpacing(15);
		vBox.getChildren().addAll(addRadioBtn(), createComboBox(), selectedCourses);
		
		return vBox;
	}
	
	// method to create the radio button group
	public HBox addRadioBtn() {
		// set the container for the radio buttons
		HBox hBox = new HBox(15);
		hBox.setAlignment(Pos.TOP_LEFT);
		hBox.getChildren().addAll(btnComputerScience, btnBusiness);

		// set the radio buttons
		btnComputerScience.setToggleGroup(group);
		btnComputerScience.setUserData("Computer Science");
		btnComputerScience.setSelected(true);
		btnBusiness.setToggleGroup(group);
		btnBusiness.setUserData("Business");

		// set the default combo box based on computer science
		option = FXCollections.observableArrayList("Python", "C#", "Java");
		
		// events for the radio buttons
		btnComputerScience.setOnAction(e -> {
			if (btnComputerScience.isSelected()) {
				selectedCourses.getItems().clear();
				option.clear();
				option.addAll(cmptrScienceList);
				program = "Computer Science";
			}
		});

		btnBusiness.setOnAction(e -> {
			if (btnBusiness.isSelected()) {
				selectedCourses.getItems().clear();
				option.clear();
				option.addAll(businessList);
				program = "Business";
			}
		});

		return hBox;
	}

	// method to create HBox for text field and check box group
	public GridPane addInput_TxtFldAndChckBx() {

		// create the grid pane to separate text fields and check boxes
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(10, 10, 10, 10));
		gridPane.setVgap(5);
		gridPane.setHgap(10);

		// add the text fields to the layout
		gridPane.add(name, 1, 0);
		gridPane.add(address, 1, 1);
		gridPane.add(province, 1, 2);
		gridPane.add(city, 1, 3);
		gridPane.add(postalCode, 1, 4);
		gridPane.add(phone, 1, 5);
		gridPane.add(email, 1, 6);

		// add the check boxes to the layout
		gridPane.add(isStudentCouncil, 2, 1);
		gridPane.add(isVolunteer, 2, 5);

		// return the pane
		return gridPane;
	}

	// method to create the text label group
	public GridPane addLabel_Txt() {

		// create the grid pane
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(10, 10, 10, 10));
		gridPane.setVgap(5);

		// add the text labels to the layout
		gridPane.add(createText("Name:"), 1, 0);
		gridPane.add(createText("Address:"), 1, 1);
		gridPane.add(createText("Province:"), 1, 2);
		gridPane.add(createText("City:"), 1, 3);
		gridPane.add(createText("Postal Code:"), 1, 4);
		gridPane.add(createText("Phone Number:"), 1, 5);
		gridPane.add(createText("Email:"), 1, 6);

		// return the pane
		return gridPane;
	}
	

	
	// --creation of individual components--
	// method to create and edit a text
	public StackPane createText(String setText) {
		Text newText = new Text();
		newText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 14));
		newText.setFill(Color.WHITE); 
		newText.setText(setText);
		
		Rectangle container = new Rectangle(newText.getX(), newText.getY(), 100, 30);
		container.setFill(Color.TRANSPARENT);
		
		StackPane stackPane = new StackPane();
		stackPane.getChildren().addAll(container, newText);
		stackPane.setAlignment(Pos.CENTER_LEFT);
		stackPane.setPadding(new Insets(0, 60, 0, 0));
		
		return stackPane;
	}

	// method to create and edit text field 
	public TextField createTextField() {
		TextField textField = new TextField();
		textField.setMaxHeight(30);
		textField.setMinHeight(30);
		textField.setFont(Font.font("verdana", null, FontPosture.REGULAR, 14));
		return textField;
	}

	// method to create and edit check box
	public CheckBox createCheckBox(String setText) {
		CheckBox checkBox = new CheckBox(setText);
		checkBox.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
		checkBox.setTextFill(Color.WHITE);
		return checkBox;
	}
	
	// method to create and edit radio buttons
	public RadioButton createRadioButton(String setText) {
		RadioButton radioBtn = new RadioButton();
		radioBtn.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
		radioBtn.setTextFill(Color.WHITE);
		radioBtn.setText(setText);
		radioBtn.setUserData(setText);
		return radioBtn;
	}

	// method to create the combo box
	public ComboBox<String> createComboBox(){
		ComboBox<String> comboBox = new ComboBox<String>();
		comboBox.setItems(option);
		comboBox.setPromptText("Select a program course");
		comboBox.setStyle("fx-font-size: 14pt;");
		comboBox.setMinWidth(300);
		comboBox.setMinHeight(40);
		
		comboBox.setOnAction(e -> {
			boolean notInList = false;
			
			for(String i: selectedCourses.getItems()) {
				if(i.equals(comboBox.getValue())) {
					notInList = true;
				}
			}
			
			if(!notInList && comboBox.getValue() != null)
				selectedCourses.getItems().add(comboBox.getValue());
		});
		
		return comboBox;
	}
	
	// method to create the button
	public Button createBtn() {
		Button button = new Button("Display"); 
		button.setStyle("-fx-background-color: #89cff0;");
		button.setMinSize(150, 30);
		button.setFont(Font.font("verdana", FontWeight.MEDIUM, FontPosture.REGULAR, 14));
		return button;
	}
	
	// method to create the text area
	public TextArea createTxtArea(String setText) {
		TextArea info = new TextArea();
		info.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 14));
		info.setStyle("-fx-text-fill: #000000");
		info.setMaxHeight(150);
		info.setText(setText);
		return info;
	}
}

package exercise1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainUI extends Application {
	
	/* instance variables */
	static Statement globalStatement;
	private int player_game_id;
	private int game_id;
	private int player_id;
	private String game_title;
	private LocalDate date_played;
	private int game_score;
	private TableView table;
	private VBox mainInterface = new VBox();
	
	// stages 
	Stage secondStage = new Stage();
	
	// run the application
	public static void main(String[] args) {
		connection();
		launch(args);
	}
	
	protected static void connection() {
		// set the values for the database connection
		String connectionString = "jdbc:oracle:thin:@connectionString:port:SQLD";
		String userName = "username";
		String password = "password";

		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Connection c = DriverManager.getConnection(connectionString, userName, password);
			globalStatement = c.createStatement();
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}

	// override the methods to set the main scene
	@Override
	public void start(Stage primaryStage) throws Exception {
		// Create a scene and place it in the stage
		Scene scene = new Scene(getPane(), 550, 500);
		primaryStage.setTitle("Game Play UI");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	// build the main UI
	protected VBox getPane() {
		
		// create the text for the title
		Text title = new Text();
		title.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 16));
		title.setFill(Color.WHITE); 
		title.setText("Trending Games");
		
		// create the button to enter player information
		Button playerInfo = createBtn("New Player Info");
		playerInfo.setOnAction(e -> {
			displayPlayerInfo(true); // true if we are adding a player
		});
		
		// create a horizontal container for title and playerInfo button
		HBox hbox_titleAndBtn = new HBox();
		hbox_titleAndBtn.setAlignment(Pos.CENTER);
		hbox_titleAndBtn.setSpacing(220);
		hbox_titleAndBtn.getChildren().addAll(title, playerInfo);
		
		// create a horizontal line after the container
		Line line = new Line();
		line.setStartX(0);
		line.setStartY(hbox_titleAndBtn.getLayoutY());
		line.setEndX(945);
		line.setEndY(hbox_titleAndBtn.getLayoutY());
		line.setFill(Color.WHITE);
		line.setStroke(Color.WHITE);
		line.setStrokeWidth(3);
		
		// create the table
		table = createTable();
		
		// create the final container
		mainInterface.setStyle("-fx-background-color: #336699;");
		mainInterface.setSpacing(5);
		VBox.setMargin(hbox_titleAndBtn, new Insets(10, 0, 0, 0));
		VBox.setMargin(table, new Insets(10, 10, 10, 10));
		mainInterface.getChildren().addAll(hbox_titleAndBtn, line, table);

		return mainInterface;
	}

	// build a table to display the trending games and the player id
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected TableView createTable() {
		// define the table column
		TableColumn playerId = new TableColumn("Player ID");
		playerId.setCellValueFactory(new PropertyValueFactory<TableViewData, String>("playerId"));
		TableColumn gameName = new TableColumn("Game Name");
		gameName.setCellValueFactory(new PropertyValueFactory<TableViewData, String>("gameTitle"));
		TableColumn datePlayed = new TableColumn("Date Played");
		datePlayed.setCellValueFactory(new PropertyValueFactory<TableViewData, LocalDate>("datePlayed"));
		TableColumn score = new TableColumn("Score");
		score.setCellValueFactory(new PropertyValueFactory<TableViewData, String>("score"));
		
		// create the table
		TableView table = new TableView();
		table.setMaxWidth(900);
		table.setMinHeight(420);
		table.getColumns().addAll(playerId, gameName, datePlayed, score);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		// populate the table
		ObservableList<TableViewData> data = getTableData();
		table.setItems(data);
		
		// event handler for the selected row
		table.getSelectionModel().selectedItemProperty().addListener((observableValue, oldVal, newVal) -> {
			var record = (TableViewData)table.getSelectionModel().getSelectedItem();
			player_id = record.getPlayerId();
			game_title = record.getGameTitle();
			date_played = record.getDatePlayed();
			game_score = record.getScore();
			
			// TODO when clicked the record of the player will be displayed
			displayPlayerInfo(false);
		});
		return table;
	}

	
	// method to display player info
	private void displayPlayerInfo(boolean isNewPlayer) {
		// open another window to add player info
		StackPane scene_playerWindow = new StackPane();
        scene_playerWindow.getChildren().add(createPlayerUI(isNewPlayer));
        Scene secondScene = new Scene(scene_playerWindow, 500,480);
        secondStage.setScene(secondScene); // set the scene
        secondStage.setTitle("Player Info");
        secondStage.show();
	}
	
	// method to create player window 
	private VBox createPlayerUI(boolean isNewPlayer) {
		
		// create text fields
		TextField fName = createTextField(), 
				  lName = createTextField(), 
				  address = createTextField(), 
				  postal = createTextField(), 
				  province = createTextField(), 
				  phone = createTextField(),
				  gameTitle = createTextField(),
				  score = createTextField();
		
		// create date picker
		DatePicker datePicker = new DatePicker();
		datePicker.setMaxHeight(30);
		datePicker.setMinHeight(30);
		
		// create grid pane for the label and text fields
		GridPane gridPane = new GridPane();
		gridPane.setHgap(15);
		gridPane.setVgap(5);

		// add the label to the grid pane
		gridPane.add(createText("Player Information", 16), 0, 0, 2, 1);
		gridPane.add(createText("First Name", 14), 1, 1);
		gridPane.add(createText("Last Name", 14), 1, 2);
		gridPane.add(createText("Address", 14), 1, 3);
		gridPane.add(createText("Postal Code", 14), 1, 4);
		gridPane.add(createText("Province", 14), 1, 5);
		gridPane.add(createText("Phone Number", 14), 1, 6);
		
		// add another set of label for the game info
		gridPane.add(createText("Game Information", 16), 0, 8, 2, 1);
		gridPane.add(createText("Title", 14), 1, 9);
		gridPane.add(createText("Date Played", 14), 1, 10);
		gridPane.add(createText("Score", 14), 1, 11);
		
		// add the text field to the grid pane
		gridPane.add(fName, 2, 1);
		gridPane.add(lName, 2, 2);
		gridPane.add(address, 2, 3);
		gridPane.add(postal, 2, 4);
		gridPane.add(province, 2, 5);
		gridPane.add(phone, 2, 6);
		
		// add the text field and date picker
		gridPane.add(gameTitle, 2, 9);
		gridPane.add(datePicker, 2, 10);
		gridPane.add(score, 2, 11);
		
		if(!isNewPlayer) {
			// get the record and append to the text fields
			getRecord(fName, lName, address, postal, province, phone, gameTitle, datePicker, score); 
		}
		
		// create the buttons
		Button addBtn = createBtn("Add");
		addBtn.setOnAction(e -> {
			mainInterface.getChildren().remove(table);
			addInfo(fName.getText(), lName.getText(), address.getText(), postal.getText(), province.getText(), phone.getText(), gameTitle.getText(), datePicker.getValue(), score.getText());
		});
		
		Button deleteBtn = createBtn("Delete");
		deleteBtn.setOnAction(e -> {
			mainInterface.getChildren().remove(table);
			deleteInfo();
		});
		
		Button updateBtn = createBtn("Update");
		updateBtn.setOnAction(e -> {
			mainInterface.getChildren().remove(table);
			updateInfo(fName.getText(), lName.getText(), address.getText(), postal.getText(), province.getText(), phone.getText(), gameTitle.getText(), datePicker.getValue(), score.getText());
		});
		
		
		HBox hbox = new HBox();
		hbox.setAlignment(Pos.CENTER);
		hbox.setSpacing(15);
		hbox.getChildren().addAll(addBtn, updateBtn, deleteBtn);
		
		VBox vBox = new VBox();
		vBox.setStyle("-fx-background-color: #c4d8e2;");
		VBox.setMargin(hbox, new Insets(30, 0, 0, 0));
		VBox.setMargin(gridPane, new Insets(25, 25, 0, 25));
		vBox.getChildren().addAll(gridPane, hbox);
		return vBox;
	}
	
	// method to add data to the database
	private void addInfo(String fName, String lName, String address, String postal, String province, String phone, String gameTitle, LocalDate datePlayed, String score) {
						
		// SQL query
		String gameQuery = "INSERT INTO game VALUES(game_id_sequence.NEXTVAL, '" + gameTitle + "')";
		String playerQuery = "INSERT INTO player VALUES(player_id_sequence.NEXTVAL, '" + fName + "', '" + lName + "', '" + address + "', '" + postal + "', '" + province + "', '" + phone + "')";
		String playerAndGameQuery = "INSERT INTO playerandgame VALUES(playerandgame_id_sequence.NEXTVAL, game_id_sequence.CURRVAL, player_id_sequence.CURRVAL, '" + datePlayed + "', " + Integer.parseInt(score) + ")";
		
		try {
			globalStatement.executeQuery(gameQuery);
			globalStatement.executeQuery(playerQuery);
			globalStatement.executeQuery(playerAndGameQuery);
			globalStatement.executeQuery("COMMIT");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		JOptionPane.showMessageDialog(null, "Player Info added sucessfully!", "Query Result", JOptionPane.INFORMATION_MESSAGE);
		table = createTable();
		VBox.setMargin(table, new Insets(10, 10, 10, 10));
		mainInterface.getChildren().add(table);
		secondStage.close();
	}
	
	// method to update data to the database
	private void updateInfo(String fName, String lName, String address, String postal, String province, String phone, String gameTitle, LocalDate datePlayed, String score) {
		// update the record in the database
		// SQL query
		String playerQuery = "UPDATE player SET first_name = '" + fName + "', last_name = '" + lName + "', address = '" + address + "', postal_code = '" + postal + "', province = '" + province + "', phone_number = '" + phone + "' WHERE player_id = " + player_id;
		String gameQuery = "UPDATE game SET game_title = '" + gameTitle + "' WHERE game_id = " + game_id;
		String playerAndGameQuery = "UPDATE playerandgame SET playing_date = '" + datePlayed + "', score = " + score + " WHERE player_game_id = " + player_game_id;
		
		try {
			globalStatement.executeQuery(gameQuery);
			globalStatement.executeQuery(playerQuery);
			globalStatement.executeQuery(playerAndGameQuery);
			globalStatement.executeQuery("COMMIT");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		JOptionPane.showMessageDialog(null, "Player Info updated sucessfully!", "Query Result", JOptionPane.INFORMATION_MESSAGE);
		table = createTable();
		VBox.setMargin(table, new Insets(10, 10, 10, 10));
		mainInterface.getChildren().add(table);
		secondStage.close();
	}
	
	// method to delete data to the database
	private void deleteInfo() {
		// delete the record in the database
		String playerAndGameQuery = "DELETE FROM playerandgame WHERE player_game_id = " + player_game_id;
		String gameQuery = "DELETE FROM game WHERE game_id = " + game_id;
		String playerQuery = "DELETE FROM player WHERE player_id = " + player_id;
		
		try {
			globalStatement.executeQuery(playerAndGameQuery);
			globalStatement.executeQuery(gameQuery);
			globalStatement.executeQuery(playerQuery);
			globalStatement.executeQuery("COMMIT");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		JOptionPane.showMessageDialog(null, "Player Info deleted sucessfully!", "Query Result", JOptionPane.INFORMATION_MESSAGE);
		table = createTable();
		VBox.setMargin(table, new Insets(10, 10, 10, 10));
		mainInterface.getChildren().add(table);
		secondStage.close();
	}
	
	// method to get individual records
	private void getRecord(TextField fName, TextField lName, TextField address, TextField postal, TextField province, TextField phone, TextField gameTitle, DatePicker datePicker, TextField score) {
		// get the record to populate the player info window
		String query = "SELECT  p.player_id, p.first_name, p.last_name, p.address, p.postal_code, p.province, p.phone_number, g.game_title, g.game_id, pg.player_game_id"
				+ " FROM player p"
				+ " INNER JOIN playerandgame pg ON pg.player_game_id = p.player_id"
				+ " INNER JOIN game g ON pg.game_id = g.game_id"
				+ " WHERE p.player_id = " + player_id + " AND g.game_title = '" + game_title + "' AND pg.playing_date = '" + date_played + "' AND pg.score = " + game_score;
		
		try {
			ResultSet rs_tableView = globalStatement.executeQuery(query);
			
			while(rs_tableView.next()) {
				// add the record to the text fields
				fName.setText(rs_tableView.getString("first_name"));
				lName.setText(rs_tableView.getString("last_name"));
				address.setText(rs_tableView.getString("address"));
				postal.setText(rs_tableView.getString("postal_code"));
				province.setText(rs_tableView.getString("province"));
				phone.setText(rs_tableView.getString("phone_number"));
				gameTitle.setText(rs_tableView.getString("game_title"));
				datePicker.setValue(date_played);
				score.setText(Integer.toString(game_score));
				game_id = Integer.parseInt(rs_tableView.getString("game_id"));
				player_game_id = Integer.parseInt(rs_tableView.getString("player_game_id"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// method to get table view's data
	// Source for TableView: https://examples.javacodegeeks.com/desktop-java/javafx/tableview/javafx-tableview-example/
	private ObservableList<TableViewData> getTableData() {
		
		// create the list of array to store the data values from the database
		List<TableViewData> dataList = new ArrayList<TableViewData>(); 	
		
		try {
			ResultSet rs_tableView = globalStatement
					.executeQuery("SELECT pg.player_id AS playerID, g.game_title AS gameTitle, pg.playing_date AS datePlayed, pg.score AS gameScore "
								+ "FROM playerandgame pg " + "INNER JOIN game g ON pg.game_id = g.game_id");
			
			// get the data values and add them to the dummyList 
			while(rs_tableView.next()) {
				dataList.add(new TableViewData(rs_tableView.getInt("playerID"), rs_tableView.getString("gameTitle"), rs_tableView.getDate("datePlayed"), rs_tableView.getInt("gameScore")));	
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// the row data that will be used in the table
		ObservableList<TableViewData> data = FXCollections.observableArrayList(dataList);
		return data;
	}

	
	
	/* create individual components */
	// create the button component
	private Button createBtn(String setTextName) {
		Button button = new Button(setTextName);
		button.setStyle("-fx-background-color: #89cff0;");
		button.setMinSize(150, 30);
		button.setFont(Font.font("verdana", FontWeight.MEDIUM, FontPosture.REGULAR, 14));	
		return button;
	}
	
	// method to create a text
	private Text createText(String setText, int fontSize) {
		Text newText = new Text();
		newText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, fontSize));
		newText.setFill(Color.BLACK);
		newText.setText(setText);
		return newText;
	}
	
	// method to create text field 
	private TextField createTextField() {
		TextField textField = new TextField();
		textField.setMaxHeight(30);
		textField.setMinHeight(30);
		textField.setFont(Font.font("verdana", null, FontPosture.REGULAR, 14));
		return textField;
	}
}

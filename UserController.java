package application;


import java.io.IOException;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.Node;
import javafx.stage.Stage;

public class UserController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	MenuBar myMenuBar;
	
	public void logout(ActionEvent event) throws IOException {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout?");
		alert.setHeaderText("Logout of your account?");
		alert.setContentText("Changes will be saved.");
		
		if(alert.showAndWait().get() == ButtonType.OK) {
			root = FXMLLoader.load(getClass().getResource("Login.fxml"));
			stage = (Stage) myMenuBar.getScene().getWindow();
			scene = new Scene(root,640,480);
			stage.setScene(scene);
			stage.show();
		}
	}
	
	public void help(ActionEvent event) throws IOException {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("About This Page");
		alert.setHeaderText("Albums Page");
		alert.setContentText("You can create new albums, rename existing ones, or delete albums altogether. Click on an album to view its photos.");
		alert.showAndWait();
	}
	
	public void newAlbum(ActionEvent event) throws IOException {
		TextInputDialog inputDialog = new TextInputDialog();
		inputDialog.setTitle("New Album");
		inputDialog.setHeaderText("New Album");
		inputDialog.setContentText("Enter name for new album...");
		
		/*final Button ok = (Button) inputDialog.getDialogPane().lookupButton(ButtonType.OK);
        ok.addEventFilter(ActionEvent.ACTION, event2 ->
            System.out.println("OK was definitely pressed")
        );*/

        /*final Button cancel = (Button) inputDialog.getDialogPane().lookupButton(ButtonType.CANCEL);
        cancel.addEventFilter(ActionEvent.ACTION, event2 ->{
        	System.out.println("Cancel was definitely pressed");
        	return;
            }
        );*/
        
        Optional<String> nameInput = inputDialog.showAndWait();
		
        if (!nameInput.isPresent()){
			return;
		}
        else if (nameInput.get().isEmpty()){
			//System.out.println("name is empty");
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setHeaderText("Empty Name Entry");
			alert.setContentText("Cannot initialize an album with no name!");
			alert.showAndWait();
			return;
		}
		else {
			System.out.println(nameInput.get());
			//add new album to user's library
		}
	}
	
	public void renameAlbum(ActionEvent event) throws IOException {
		//if name match: ask for new name
		//else if nothing entered/nomatch: errormsg
	}
}

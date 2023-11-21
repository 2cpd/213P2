package application;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Node;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import Model.Album;
import Model.Photo;
import Model.User;

/*
 * AlbumController class
 * @author Chris Li
 * @author Tony Lu
 */
public class SearchController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	private static User albumUser;
	private String selectedPath = "";
	
	ObservableList<Photo> searchMatches;
	
	@FXML
	ImageView imageView;
	
	@FXML
	MenuBar myMenuBar;
	
	@FXML
	Label filenameDisplay;
	
	@FXML
	Label captionDisplay;
	
	@FXML
    //private ListView<String> photoList = new ListView<String>(albumUser.getPhotoNameList(UserController.goToAlbumName));
	private ListView<String> photoList;
	
	@FXML
	public void initialize() throws IOException {
		albumUser = new User(LoginController.getName());
		//for (Photo p:searchMatches) {
			//photoList.getItems().add(p.getNamePhoto());
		//}
	}
	
	public void displaySelected() {
		photoList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		    	selectedPath = photoList.getSelectionModel().getSelectedItem();
		        Image image = new Image(selectedPath);
		        imageView.setImage(image);
		        
		        String selectedFileName = selectedPath.substring(selectedPath.lastIndexOf("/")+1);
		        //String selectedCaption = ...
		        
		        filenameDisplay.setText(selectedFileName);
		        //captionDisplay.setText...
		    }
		});
	}
	
	public void help(ActionEvent event) throws IOException { //done
		Alert alert = new Alert(AlertType.INFORMATION);
		//String albumName = xxx.get();
		alert.setTitle("About This Page");
		alert.setHeaderText("Search Results Page");
		alert.setContentText("Here are the results of your search query. You can choose to create a new album with them.");
		alert.showAndWait();
	}
	
	public void addAlbum(ActionEvent event) throws IOException{
		TextInputDialog inputDialog = new TextInputDialog();
		inputDialog.setTitle("New Album");
		inputDialog.setHeaderText("New Album");
		inputDialog.setContentText("Enter name for new album...");
		//inputDialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        
        Optional<String> nameInput = inputDialog.showAndWait();
        
        if(!nameInput.isPresent()) {
			return;
        }
        
        String name = nameInput.get();
		
        if (name.isEmpty()){
        	Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("New Album");
			alert.setHeaderText("Empty Name Entry");
			alert.setContentText("Cannot initialize an album with no name!");
			alert.showAndWait();
			return;
		}
        
        if (!albumUser.createAlbum(name)){
			//System.out.println("name is empty");
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("New Album");
			alert.setHeaderText("Error");
			alert.setContentText("Please try a different name.");
			alert.showAndWait();
			return;
		}
		else {
			//write new album name to useralbum.txt
			File f = new File("data/"+ albumUser.getUsername() +"album.txt");
			if(!f.exists() && !f.isDirectory()) { 
				FileOutputStream createfile = new FileOutputStream("data/"+ albumUser.getUsername() +"album.txt");
				createfile.close();
			}
			
			
			FileInputStream file = new FileInputStream("data/"+ albumUser.getUsername() +"album.txt");
			int ch;
			
			FileOutputStream tempfile = new FileOutputStream("data/tempalbum.txt");
			while ((ch = file.read()) != -1) {
				tempfile.write(ch);
			}
			
			
			char[] tempArray = name.toCharArray();
			tempfile.write(',');
			for (int i = 0; i < tempArray.length; i++) {
				tempfile.write(tempArray[i]);
			}
			
			tempfile.close();
			file.close();
			
			File oldFile = new File("data/"+ albumUser.getUsername() +"album.txt");
			oldFile.delete();
			
			FileInputStream tempUserFile = new FileInputStream("data/tempalbum.txt");
			FileOutputStream newfile = new FileOutputStream("data/"+ albumUser.getUsername() +"album.txt");
			while ((ch = tempUserFile.read()) != -1) {
				newfile.write(ch);
			}
			
			tempUserFile.close();
			newfile.close();
			File ofile = new File ("data/tempalbum.txt");
			ofile.delete();
			
			//write new photos to userphoto.txt
			
			for (Photo p:searchMatches) {
				String path = p.getNamePhoto(); //Q: is name path?
				
				File f2 = new File("data/"+ albumUser.getUsername()+ name +"photo.txt");
				if(!f2.exists() && !f2.isDirectory()) { 
					FileOutputStream createfile = new FileOutputStream("data/"+ albumUser.getUsername()+ name +"photo.txt");
					createfile.close();
				}
				
				FileInputStream openfile = new FileInputStream("data/"+ albumUser.getUsername()+ name +"photo.txt");
				tempfile = new FileOutputStream("data/tempphoto.txt");
				while ((ch = openfile.read()) != -1) {
					tempfile.write(ch);
				}
				
				tempArray = path.toCharArray();
				tempfile.write(',');
				for (int i = 0; i < tempArray.length; i++) {
					tempfile.write(tempArray[i]);
				}
				
				tempfile.close();
				openfile.close();
				
				File oldFile2 = new File("data/"+ albumUser.getUsername()+ name +"photo.txt");
				oldFile2.delete();
				
				tempUserFile = new FileInputStream("data/tempphoto.txt");
				FileOutputStream newfile2 = new FileOutputStream("data/"+ albumUser.getUsername()+ name +"photo.txt");
				while ((ch = tempUserFile.read()) != -1) {
					newfile2.write(ch);
				}
				
				tempUserFile.close();
				newfile2.close();
				ofile = new File ("data/tempphoto.txt");
				ofile.delete();
			}
		}
	}
}
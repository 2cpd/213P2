package application;


import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Node;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AlbumControllerO {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	ImageView imageView;
	
	@FXML
	MenuBar myMenuBar;
	
	@FXML
    ListView<String> listOfPhotos;
	
	public void returnToUser(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/View/User.fxml"));
		stage = (Stage) myMenuBar.getScene().getWindow();
		scene = new Scene(root,640,480);
		stage.setScene(scene);
		stage.show();
		//maybe: add closer all other windows functionality
	}
	
	public void help(ActionEvent event) throws IOException {
		Alert alert = new Alert(AlertType.INFORMATION);
		//String albumName = xxx.get();
		alert.setTitle("About This Page");
		alert.setHeaderText("Album Page");
		alert.setContentText("Here, you can add, rename or delete photos, search for a specific photo, view a photo in a new window, or return to the previous page.");
		alert.showAndWait();
	}
	
	
	public void openFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        
        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
         
        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);
                  
        if (file == null) {
            //error msg
        }
        else {
        	Image image = new Image(file.toURI().toString());
            imageView.setImage(image);
            
            String name = file.getName();
            listOfPhotos.getItems().add(name);
            //also add create new Photo object (TBI)
        }
        
        //Needs fix: currently shows photo on right side of ui right when added
        //Add photo name to list, and only show when list item is selected
    }
	
	public void viewInNew(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/View/PhotoView.fxml"));
		Stage newStage = new Stage();
		newStage.setTitle("uPhotos");
		scene = new Scene(root,640,480); //needs fix:change to photo's resolution
		newStage.setScene(scene);
		newStage.show();
	}
	
	/*
	 * functionalities:
	 * menubar: add photo, return to albums page, search
	 * context menu: del, caption curr photo, disp in new window, add/del tag of curr photo, copy/paste between albums
	 */
}

import javafx.application.Application;
import javafx.scene.control.MenuBar;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.InvalidClassException;

/**
 * The class Chess is the driver class.
 * It has a ChessBoard which will be set
 * in the scene to be launched in the
 * JavaFX application. It also incorporates
 * a menu bar to access options like saving
 * and opening a game.
 */
public class Chess extends Application {
    /** The border pane will position the menu bar and chess board. */
    private BorderPane borderPane;
    
    /** The menu bar to access options for the game. */
    private MenuBar menuBar;
    
    /** The menu file has options for modifying a game file. */
    private Menu file;
    
    /** The menu item open loads a previously saved game. */
    private MenuItem open;
    
    /** The menu item save serializes the state of the board and saves it. */
    private MenuItem save;
    
    /** The chess board that contains the squares and pieces to play chess. */
    private ChessBoard board;
    
    /**
     * Launches the chess board
     * with a menu bar on top.
     * 
     * @param primaryStage a Stage
     */
    public void start(Stage primaryStage) {
        final int appWidth = 720;
        final int appHeight = 749;
        
        borderPane = new BorderPane();             
        file = new Menu("File");
        open = new MenuItem("Open", new ImageView(new Image("File:Pictures/OpenIcon.png",
                             20, 20, false, true)));
        save = new MenuItem("Save", new ImageView(new Image("File:Pictures/SaveIcon.png",
                             20, 20, false, true)));
        
        open.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                open(board);
            }
        });
        
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                save(board);
            }
        });
        
        file.getItems().addAll(open, save);
        menuBar = new MenuBar(file);
        board = new ChessBoard();
        
        borderPane.setTop(menuBar);
        borderPane.setCenter(board);         
        
        Scene scene = new Scene(borderPane, appWidth, appHeight);

        primaryStage.setTitle("Chess Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /**
     * This open method will be called when the
     * user clicks on the open option in the file
     * menu. It will deserialize a saved state of
     * the chess board. The method resetBoard will be
     * called to update all of the current chess board
     * settings to the deserialized chess board settings.
     * 
     * @param obj as ChessBoard
     */
    public void open(ChessBoard obj) {
        Alert fileNotFound = new Alert(Alert.AlertType.ERROR);
        fileNotFound.setHeaderText("File Not Found");
        fileNotFound.setContentText("Could not find file: \"Chess.ser\"");
        
        Alert invalidClass = new Alert(Alert.AlertType.ERROR);
        invalidClass.setHeaderText("Invalid Class");
        invalidClass.setContentText("See stack trace for details");
        
        try {
            FileInputStream file = new FileInputStream("Chess.ser");
            ObjectInputStream in = new ObjectInputStream(file);               
            ChessBoard savedBoard = new ChessBoard();
            savedBoard = (ChessBoard) in.readObject();
            obj.resetBoard(savedBoard.getSquareArray(), savedBoard.getPieceArray());
            in.close();
        } catch (InvalidClassException e) {
            invalidClass.showAndWait();
            e.printStackTrace();
        } catch (IOException e) {
            fileNotFound.showAndWait();
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * This save method will be called when the
     * user clicks on the save option in the file
     * menu. It will serialize the current state of
     * the chess board.
     * 
     * @param obj as ChessBoard
     */
    public void save(ChessBoard obj) {
        Alert saveSuccessful = new Alert(Alert.AlertType.INFORMATION);
        saveSuccessful.setHeaderText("Save Successful");
        saveSuccessful.setContentText("Game has saved successfully as file: \"Chess.ser\"");
        
        Alert saveUnsuccessful = new Alert(Alert.AlertType.ERROR);
        saveUnsuccessful.setHeaderText("Save Unsuccessful");
        saveUnsuccessful.setContentText("See stack trace for details");
        
        try {
            FileOutputStream file = new FileOutputStream("Chess.ser");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(obj);
            out.flush();
            out.close();
            saveSuccessful.showAndWait();
        } catch (IOException e) {
            saveUnsuccessful.showAndWait();
            e.printStackTrace();
        }
    }
    
    /**
     * Launches the JavaFX application.
     * 
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}

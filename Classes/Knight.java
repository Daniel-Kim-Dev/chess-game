import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * The class Knight is a Piece.
 * It implements the inherited abstract methods
 * like draw and getPiece.
 */
public class Knight extends Piece { 
    /** The default serial version UID. */
    private static final long serialVersionUID = 1L;
    
    /** This will contain the picture of a piece. */
    private transient ImageView piece;
    
    /**
     * Constructs using super to call
     * the constructor of the Piece class.
     * 
     * @param name as String
     * @param color as String
     * @param position as int
     * @param isSelected as boolean
     * @param turn as boolean
     */
    public Knight(String name, String color, int position, boolean isSelected, boolean turn) {
        super(name, color, position, isSelected, turn);
    }

    /**
     * This method instantiates a new ImageView object
     * which contains an image of a white knight and 
     * returns it.
     * 
     * @return the piece
     */
    @Override
    public ImageView drawWhite() {
        piece = new ImageView(new Image("File:Pictures/White_Knight.png",
                                                   70, 70, false, true));
        return piece;
    }

    /**
     * This method instantiates a new ImageView object
     * which contains an image of a black knight and 
     * returns it.
     * 
     * @return the piece
     */
    @Override
    public ImageView drawBlack() {
        piece = new ImageView(new Image("File:Pictures/Black_Knight.png",
                                                   70, 70, false, true));
        return piece;
    }
    
    /**
     * Returns the piece as an ImageView.
     * 
     * @return the piece
     */
    @Override
    public ImageView getPiece() {
        return piece;
    }
    
    /**
     * This method will be called when a piece has
     * been selected. It will loop through the
     * square array to set all the valid squares
     * that the selected piece can legally move to.
     * A knight can move in an "L" shape in any 
     * direction. It can jump over other pieces.
     * 
     * @param squareArray as Square[]
     */
    @Override
    public void validMoves(Square[] squareArray) {
        int currentCol = GridPane.getColumnIndex(piece);
        
        for (int i = 0; i < squareArray.length; i++) {
            if (squareArray[i].getPosition() == getPosition() - 17
                && currentCol > GridPane.getColumnIndex(squareArray[i].getRec())
                && !(squareArray[i].getHasAPiece())) {
                Square.setGreenFill(squareArray[i]);
                squareArray[i].setPieceCanMoveTo(true);
            } else if (squareArray[i].getPosition() == getPosition() - 15
                && currentCol < GridPane.getColumnIndex(squareArray[i].getRec())
                && !(squareArray[i].getHasAPiece())) {
                Square.setGreenFill(squareArray[i]);
                squareArray[i].setPieceCanMoveTo(true);
            } else if (squareArray[i].getPosition() == getPosition() - 10
                && currentCol > GridPane.getColumnIndex(squareArray[i].getRec())
                && !(squareArray[i].getHasAPiece())) {
                Square.setGreenFill(squareArray[i]);
                squareArray[i].setPieceCanMoveTo(true);
            } else if (squareArray[i].getPosition() == getPosition() - 6
                && currentCol < GridPane.getColumnIndex(squareArray[i].getRec())
                && !(squareArray[i].getHasAPiece())) {
                Square.setGreenFill(squareArray[i]);
                squareArray[i].setPieceCanMoveTo(true);
            } else if (squareArray[i].getPosition() == getPosition() + 6
                && currentCol > GridPane.getColumnIndex(squareArray[i].getRec())
                && !(squareArray[i].getHasAPiece())) {
                Square.setGreenFill(squareArray[i]);
                squareArray[i].setPieceCanMoveTo(true);
            } else if (squareArray[i].getPosition() == getPosition() + 10
                && currentCol < GridPane.getColumnIndex(squareArray[i].getRec())
                && !(squareArray[i].getHasAPiece())) {
                Square.setGreenFill(squareArray[i]);
                squareArray[i].setPieceCanMoveTo(true);
            } else if (squareArray[i].getPosition() == getPosition() + 15
                && currentCol > GridPane.getColumnIndex(squareArray[i].getRec())
                && !(squareArray[i].getHasAPiece())) {
                Square.setGreenFill(squareArray[i]);
                squareArray[i].setPieceCanMoveTo(true);
            } else if (squareArray[i].getPosition() == getPosition() + 17
                && currentCol < GridPane.getColumnIndex(squareArray[i].getRec())
                && !(squareArray[i].getHasAPiece())) {
                Square.setGreenFill(squareArray[i]);
                squareArray[i].setPieceCanMoveTo(true);
            }
        }      
    }
}

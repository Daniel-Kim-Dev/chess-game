import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * The class Rook is a Piece.
 * It implements the inherited abstract methods
 * like draw and getPiece.
 */
public class Rook extends Piece {
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
    public Rook(String name, String color, int position, boolean isSelected, boolean turn) {
        super(name, color, position, isSelected, turn);
    }

    /**
     * This method instantiates a new ImageView object
     * which contains an image of a white rook and 
     * returns it.
     * 
     * @return the piece
     */
    @Override
    public ImageView drawWhite() {
        piece = new ImageView(new Image("File:Pictures/White_Rook.png",
                                                   70, 70, false, true));
        return piece;
    }

    /**
     * This method instantiates a new ImageView object
     * which contains an image of a black rook and 
     * returns it.
     * 
     * @return the piece
     */
    @Override
    public ImageView drawBlack() {
        piece = new ImageView(new Image("File:Pictures/Black_Rook.png",
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
     * A rook can move horizontally or vertically
     * unlimited amount of spaces as long as there
     * is no other piece blocking it.
     * 
     * @param squareArray as Square[]
     */
    @Override
    public void validMoves(Square[] squareArray) {
        int currentRow = GridPane.getRowIndex(piece);
        
        int leftMoves = 1;
        int rightMoves = 1;
        
        int upMoves = 8;
        int downMoves = 8;
        
        for (int i = 63; i >= 0; i--) {
            if (squareArray[i].getPosition() == getPosition() - leftMoves) {
                if (squareArray[i].getHasAPiece() || 
                   (currentRow != GridPane.getRowIndex(squareArray[i].getRec()))) {
                   break;
                }
                Square.setGreenFill(squareArray[i]);
                squareArray[i].setPieceCanMoveTo(true);
                leftMoves++;
            }
        }
        
        for (int i = 0; i < squareArray.length; i++) {
            if (squareArray[i].getPosition() == getPosition() + rightMoves) {
                if (squareArray[i].getHasAPiece() || 
                   (currentRow != GridPane.getRowIndex(squareArray[i].getRec()))) {
                   break;
                }
                Square.setGreenFill(squareArray[i]);
                squareArray[i].setPieceCanMoveTo(true);
                rightMoves++;
            }
        }
        
        for (int i = 63; i >= 0; i--) {
            if (squareArray[i].getPosition() == getPosition() - upMoves) {
                if (squareArray[i].getHasAPiece()) {
                   break;
                }
                Square.setGreenFill(squareArray[i]);
                squareArray[i].setPieceCanMoveTo(true);
                upMoves += 8;
            }
        }
        
        for (int i = 0; i < squareArray.length; i++) {
            if (squareArray[i].getPosition() == getPosition() + downMoves) {
                if (squareArray[i].getHasAPiece()) {
                   break;
                }
                Square.setGreenFill(squareArray[i]);
                squareArray[i].setPieceCanMoveTo(true);
                downMoves += 8;
            }
        }    
    }
}

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * The class King is a Piece.
 * It implements the inherited abstract methods
 * like draw and getPiece.
 */
public class King extends Piece {
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
    public King(String name, String color, int position, boolean isSelected, boolean turn) {
        super(name, color, position, isSelected, turn);
    }
    
    /**
     * This method instantiates a new ImageView object
     * which contains an image of a white king and 
     * returns it.
     * 
     * @return the piece
     */
    @Override
    public ImageView drawWhite() {
        piece = new ImageView(new Image("File:Pictures/White_King.png",
                                                   70, 70, false, true));
        return piece;
    }

    /**
     * This method instantiates a new ImageView object
     * which contains an image of a black king and
     * returns it.
     * 
     * @return the piece
     */
    @Override
    public ImageView drawBlack() {
        piece = new ImageView(new Image("File:Pictures/Black_King.png",
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
     * A king can move in any direction only one space
     * as long as there is no other piece blocking it.
     * 
     * @param squareArray as Square[]
     */
    @Override
    public void validMoves(Square[] squareArray) {
        int currentRow = GridPane.getRowIndex(piece);
        int currentCol = GridPane.getColumnIndex(piece);
        
        int leftMoves = 1;
        int rightMoves = 1;
        
        int upMoves = 8;
        int downMoves = 8;
        
        int upLeftMoves = 9;
        int upRightMoves = 7;
        
        int downLeftMoves = 7;
        int downRightMoves = 9;
        
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
            if (leftMoves > 1) {
                break;
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
            if (rightMoves > 1) {
                break;
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
            if (upMoves > 8) {
                break;
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
            if (downMoves > 8) {
                break;
            }
        }
        
        for (int i = 63; i >= 0; i--) {
            if (squareArray[i].getPosition() == getPosition() - upLeftMoves) {
                if (squareArray[i].getHasAPiece() || --currentCol != GridPane.getColumnIndex(squareArray[i].getRec())) {
                   break;
                }
                Square.setGreenFill(squareArray[i]);
                squareArray[i].setPieceCanMoveTo(true);
                upLeftMoves += 9;
            }
            if (upLeftMoves > 9) {
                break;
            }
        }
        
        currentCol = GridPane.getColumnIndex(piece);
        
        for (int i = 0; i < squareArray.length; i++) {
            if (squareArray[i].getPosition() == getPosition() + downLeftMoves) {
                if (squareArray[i].getHasAPiece() || --currentCol != GridPane.getColumnIndex(squareArray[i].getRec()) ) {
                   break;
                }
                Square.setGreenFill(squareArray[i]);
                squareArray[i].setPieceCanMoveTo(true);
                downLeftMoves += 7;
            }
            if (downLeftMoves > 7) {
                break;
            }
        }
        
        currentCol = GridPane.getColumnIndex(piece);
        
        for (int i = 63; i >= 0; i--) {
            if (squareArray[i].getPosition() == getPosition() - upRightMoves) {
                if (squareArray[i].getHasAPiece() || ++currentCol != GridPane.getColumnIndex(squareArray[i].getRec())) {
                   break;
                }
                Square.setGreenFill(squareArray[i]);
                squareArray[i].setPieceCanMoveTo(true);
                upRightMoves += 7;
            }
            if (upRightMoves > 7) {
                break;
            }
        }
        
        currentCol = GridPane.getColumnIndex(piece);
        
        for (int i = 0; i < squareArray.length; i++) {
            if (squareArray[i].getPosition() == getPosition() + downRightMoves) {
                if (squareArray[i].getHasAPiece() || ++currentCol != GridPane.getColumnIndex(squareArray[i].getRec())) {
                   break;
                }
                Square.setGreenFill(squareArray[i]);
                squareArray[i].setPieceCanMoveTo(true);
                downRightMoves += 9;
            }
            if (downRightMoves > 9) {
                break;
            }
        }     
    }
}

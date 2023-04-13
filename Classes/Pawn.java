import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The class Pawn is a Piece.
 * It implements the inherited abstract methods
 * like draw and getPiece. 
 */
public class Pawn extends Piece {
    /** The default serial version UID. */
    private static final long serialVersionUID = 1L;

    /** This will contain the picture of a piece. */
    private transient ImageView piece;
    
    /** True if it's the pawn's first move of the game. */
    private boolean firstMove;
    
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
    public Pawn(String name, String color, int position, boolean isSelected, boolean turn) {
        super(name, color, position, isSelected, turn);
        firstMove = true;
    }
    
    /**
     * Returns firstMove as a boolean.
     * 
     * @return true or false
     */
    @Override
    public boolean getFirstMove() {
        return firstMove;
    }
    
    /**
     * Sets firstMove as true or false.
     * 
     * @param firstMove the firstMove to set
     */
    @Override
    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }

    /**
     * This method instantiates a new ImageView object
     * which contains an image of a white pawn and 
     * returns it.
     * 
     * @return the piece
     */
    @Override
    public ImageView drawWhite() {
        piece = new ImageView(new Image("File:Pictures/White_Pawn.png",
                                                   70, 70, false, true));
        return piece;
    }

    /**
     * This method instantiates a new ImageView object
     * which contains an image of a black pawn and 
     * returns it.
     * 
     * @return the piece
     */
    @Override
    public ImageView drawBlack() {
        piece = new ImageView(new Image("File:Pictures/Black_Pawn.png",
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
     * A white pawn can only move up and a black pawn
     * can only move down. On the pawn's first turn,
     * they can move 2 spaces and after that they
     * are limited to only moving 1 space.
     * 
     * @param squareArray as Square[]
     */
    @Override
    public void validMoves(Square[] squareArray) {
        int upMoves = 8;
        int downMoves = 8;
        
        if (getColor().equals("white")) {
            for (int i = 63; i >= 0; i--) {
                if (squareArray[i].getPosition() == getPosition() - upMoves) {
                    if (squareArray[i].getHasAPiece()) {
                       break;
                    }
                    Square.setGreenFill(squareArray[i]);
                    squareArray[i].setPieceCanMoveTo(true);
                    upMoves += 8;
                }
                if (firstMove) {
                    if (upMoves > 16) {
                        break;
                    }
                } else if (!firstMove) {
                    if (upMoves > 8) {
                        break;
                    }
                }
            }
        }
        
        if (getColor().equals("black")) {
            for (int i = 0; i < squareArray.length; i++) {
                if (squareArray[i].getPosition() == getPosition() + downMoves) {
                    if (squareArray[i].getHasAPiece()) {
                       break;
                    }
                    Square.setGreenFill(squareArray[i]);
                    squareArray[i].setPieceCanMoveTo(true);
                    downMoves += 8;
                }
                if (firstMove) {
                    if (downMoves > 16) {
                        break;
                    }
                } else if (!firstMove) {
                    if (downMoves > 8) {
                        break;
                    }
                }
            }
        }
    }
}

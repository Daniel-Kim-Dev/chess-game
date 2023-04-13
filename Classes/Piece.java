import javafx.scene.image.ImageView;
import java.io.Serializable;

/**
 * The class Piece is an abstract class
 * which will be extended by the actual 
 * pieces of a chess game.
 */
public abstract class Piece implements Serializable {
    /** The default serial version UID. */
    private static final long serialVersionUID = 1L;

    /** The name of the type of piece (rook, bishop, pawn, etc). */
    private final String name;
    
    /** The color of a piece that is either black or white */
    private final String color;
    
    /** The position of the piece on the board. */
    private int position;
    
    /** True if the piece is clicked on by a mouse. */
    private boolean isSelected;
    
    /** True if it's this piece's turn, false otherwise. */
    private boolean turn;
    
    /**
     * Constructor for the class Piece.
     * 
     * @param name as String
     * @param color as String
     * @param position as int
     * @param isSelected as boolean
     * @param turn as boolean
     */
    public Piece(String name, String color, int position, boolean isSelected, boolean turn) {
        this.name = name;
        this.color = color;
        this.position = position;
        this.isSelected = isSelected;
        this.turn = turn;
    }
    
    /** Returns the image of a piece. */
    public abstract ImageView getPiece();
    
    /** Instantiates an ImageView with an image of a white piece and returns it. */
    public abstract ImageView drawWhite();
    
    /** Instantiates an ImageView with an image of a black piece and returns it. */
    public abstract ImageView drawBlack();
    
    /**
     * This method loops through the square array and sets the
     * square's pieceCanMoveTo to true if a certain piece can
     * legally move there.
     * 
     * @param squareArray as Square[]
     */
    public abstract void validMoves(Square[] squareArray);
    
    /**
     * Returns the name as a String.
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Returns the color as a String.
     * 
     * @return the color
     */
    public String getColor() {
        return color;
    }
    
    /**
     * Returns the position as an int.
     * 
     * @return the position
     */
    public int getPosition() {
        return position;
    }
    
    /**
     * Sets the position.
     * 
     * @param position the position to set
     */
    public void setPosition(int position) {
        this.position = position;
    }
    
    /**
     * Returns the isSelected as a boolean
     * 
     * @return isSelected
     */
    public boolean getIsSelected() {
        return isSelected;
    }
    
    /**
     * Sets isSelected as true or false.
     * 
     * @param isSelected the isSelected to set
     */
    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
    
    /**
     * Returns the turn as a boolean
     * 
     * @return turn
     */
    public boolean getTurn() {
        return turn;
    }
    
    /**
     * Sets turn as true or false.
     * 
     * @param turn the turn to set
     */
    public void setTurn(boolean turn) {
        this.turn = turn;
    }
    
    /**
     * This method will be overridden in the
     * class Pawn. It returns the firstMove
     * status of a pawn as true or false.
     * 
     * @return true or false
     */
    public boolean getFirstMove() {
        return false;
    }
    
    /**
     * This method will be overridden in the
     * class Pawn. It sets the firstMove to
     * false if a pawn piece has used its
     * first move.
     * 
     * @param firstMove the firstMove to set
     */
    public void setFirstMove(boolean firstMove) {}
    
    /**
     * This method resets all of the piece's selected
     * status back to false.
     * 
     * @param pieceArray as Piece[]
     */
    public static void resetAllSelectedPieces(Piece[] pieceArray) {
        for (int i = 0; i < pieceArray.length; i++) {
            pieceArray[i].setIsSelected(false);
        }
    }
    
    /**
     * This method loops through the piece array
     * to check if a certain piece has the selected
     * status as true. Then it returns that piece.
     * 
     * @param pieceArray as Piece[]
     * @return a piece
     */
    public static Piece getSelectedPiece(Piece[] pieceArray) {
        for (int i = 0; i < pieceArray.length; i++) {
            if (pieceArray[i].getIsSelected()) {
                return pieceArray[i];
            }
        }
        return null;
    }
    
    /**
     * This method loops through the piece array
     * to check if a piece has it's selected status
     * to true. The method returns true if a piece is 
     * selected, false otherwise.
     * 
     * @param pieceArray as Piece[]
     * @return true or false
     */
    public static boolean isThereSelectedPiece(Piece[] pieceArray) {
        for (int i = 0; i < pieceArray.length; i++) {
            if (pieceArray[i].getIsSelected()) {
                return true;
            }         
        }
        return false;
    }
    
    /**
     * This method loops through the piece array to
     * return the piece with the same position as a clicked
     * on square.
     * 
     * @param square as Square
     * @param pieceArray as Piece[]
     * @return a piece
     */
    public static Piece getSamePositionPiece(Square square, Piece[] pieceArray) {
        for (int i = 0; i < pieceArray.length; i++) {
            if (pieceArray[i].getPosition() == square.getPosition()) {
                return pieceArray[i];
            }
        }
        return null;
    }
    
    /**
     * This method will set all of the white
     * piece's turn status to true and the
     * black piece's to false.
     * 
     * @param pieceArray as Piece[]
     */
    public static void setWhiteTurnTrue(Piece[] pieceArray) {
        for (int i = 0; i < pieceArray.length; i++) {
            if (pieceArray[i].getColor().equals("white")) {
                pieceArray[i].setTurn(true);
            } else {
                pieceArray[i].setTurn(false);
            }
        }
    }
    
    /**
     * This method will set all of the black
     * piece's turn status to true and the
     * white piece's to false.
     * 
     * @param pieceArray as Piece[]
     */
    public static void setBlackTurnTrue(Piece[] pieceArray) {
        for (int i = 0; i < pieceArray.length; i++) {
            if (pieceArray[i].getColor().equals("black")) {
                pieceArray[i].setTurn(true);
            } else {
                pieceArray[i].setTurn(false);
            }
        }
    }
}

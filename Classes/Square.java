import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.paint.Color;
import java.io.Serializable;

/**
 * The class Square utilizes the Rectangle
 * class to draw squares for the chess board.
 * It holds information of it's position on 
 * the board and whether or not it has a piece
 * on it.
 */
public class Square implements Serializable {
    /** The default serial version UID. */
    private static final long serialVersionUID = 1L;

    /** The position of the square on the chess board. */
    private final int position;
    
    /** The actual shapes that will be drawn on the chess board. */
    private transient Rectangle rec;
    
    /** True if the square has a piece on it. */
    private boolean hasAPiece;
    
    /** True if a selected piece can move to this square with its legal moves. */
    private boolean pieceCanMoveTo;
    
    /**
     * The constructor for the class Square.
     * 
     * @param position as int
     * @param hasAPiece as boolean
     */
    public Square(int position, boolean hasAPiece) {
        this.position = position;
        this.hasAPiece = hasAPiece;
        pieceCanMoveTo = false;
    }
    
    /**
     * Returns rec as a Rectangle.
     * 
     * @return the rectangle
     */
    public Rectangle getRec() {
        return rec;
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
     * Returns the hasAPiece as a boolean.
     * 
     * @return true or false
     */
    public boolean getHasAPiece() {
        return hasAPiece;
    }
    
    /**
     * Sets the status of hasAPiece.
     * 
     * @param hasAPiece the hasAPiece to set
     */
    public void setHasAPiece(boolean hasAPiece) {
        this.hasAPiece = hasAPiece;
    }
    
    /**
     * Returns the pieceCanMoveTo as a boolean.
     * 
     * @return true or false
     */
    public boolean getPieceCanMoveTo() {
        return pieceCanMoveTo;
    }
    
    /**
     * Sets the status of pieceCanMoveTo.
     * 
     * @param pieceCanMoveTo the pieceCanMoveTo to set
     */
    public void setPieceCanMoveTo(boolean pieceCanMoveTo) {
        this.pieceCanMoveTo = pieceCanMoveTo;
    }
    
    /**
     * This method instantiates a new rectangle object.
     * Then, it returns a rectangle of either two colors
     * depending on it's row and position on the board.
     * 
     * @param row as String
     * @return rec
     */
    public Rectangle draw(String row) {
        rec = new Rectangle(90, 90);
        
        if (row.equalsIgnoreCase("evenRow")) {
            if (position % 2 == 0) {
                rec.setFill(Color.BURLYWOOD);
            } else {
                rec.setFill(Color.BLANCHEDALMOND);
            }
        }
        
        if (row.equalsIgnoreCase("oddRow")) {
            if (position % 2 == 0) {
                rec.setFill(Color.BLANCHEDALMOND);
            } else {
                rec.setFill(Color.BURLYWOOD);
            }
        }       
        return rec;
    }
    
    /**
     * This method highlights a square green if it's the clicked
     * on piece's turn.
     * 
     * @param square as Square
     */
    public static void setGreenStroke(Square square) {
        square.getRec().setStrokeType(StrokeType.INSIDE);
        square.getRec().setStrokeWidth(3);
        square.getRec().setStroke(Color.GREEN);
    }
    
    /**
     * This method highlights a square red if it's not the
     * clicked on piece's turn. It will also highlight
     * a square red if it has no piece on it.
     * 
     * @param square as Square
     */
    public static void setRedStroke(Square square) {
        square.getRec().setStrokeType(StrokeType.INSIDE);
        square.getRec().setStrokeWidth(3);
        square.getRec().setStroke(Color.RED);
    }
    
    /**
     * This method will set the square's fill color
     * to green if a selected piece can move to
     * that square.
     * 
     * @param square as Square
     */
    public static void setGreenFill(Square square) {
        square.getRec().setFill(Color.rgb(0, 204, 0, 0.5));
    }
    
    /**
     * This method resets all of the square's strokes back to
     * its default fill color.
     * 
     * @param squareArray as Square[]
     */
    public static void resetAllStrokes(Square[] squareArray) {
        int index = 0;
        
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (row % 2 == 0) {
                    if (squareArray[index].getPosition() % 2 == 0) {
                        squareArray[index].getRec().setStrokeType(StrokeType.INSIDE);
                        squareArray[index].getRec().setStrokeWidth(3);
                        squareArray[index].getRec().setStroke(Color.BURLYWOOD);
                    } else {
                        squareArray[index].getRec().setStrokeType(StrokeType.INSIDE);
                        squareArray[index].getRec().setStrokeWidth(3);
                        squareArray[index].getRec().setStroke(Color.BLANCHEDALMOND);
                    }                           
                } else {
                    if (squareArray[index].getPosition() % 2 == 0) {
                        squareArray[index].getRec().setStrokeType(StrokeType.INSIDE);
                        squareArray[index].getRec().setStrokeWidth(3);
                        squareArray[index].getRec().setStroke(Color.BLANCHEDALMOND);
                    } else {
                        squareArray[index].getRec().setStrokeType(StrokeType.INSIDE);
                        squareArray[index].getRec().setStrokeWidth(3);
                        squareArray[index].getRec().setStroke(Color.BURLYWOOD);
                    }
                }
                index++;
            }             
        }
    }
    
    /**
     * This method resets all of the square's fills back to
     * its default fill color.
     * 
     * @param squareArray as Square[]
     */
    public static void resetAllFills(Square[] squareArray) {
        int index = 0;
        
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (row % 2 == 0) {
                    if (squareArray[index].getPosition() % 2 == 0) {
                        squareArray[index].getRec().setFill(Color.BURLYWOOD);
                    } else {
                        squareArray[index].getRec().setFill(Color.BLANCHEDALMOND);
                    }                           
                } else {
                    if (squareArray[index].getPosition() % 2 == 0) {
                        squareArray[index].getRec().setFill(Color.BLANCHEDALMOND);
                    } else {
                        squareArray[index].getRec().setFill(Color.BURLYWOOD);
                    }
                }
                index++;
            }
        }
    }
    
    /**
     * This method resets all of the square's pieceCanMoveTo
     * status back to false.
     * 
     * @param squareArray as Square[]
     */
    public static void resetAllPieceCanMoveToSquares(Square[] squareArray) {
        for (int i = 0; i < squareArray.length; i++) {
            squareArray[i].setPieceCanMoveTo(false);
        }
    }
}

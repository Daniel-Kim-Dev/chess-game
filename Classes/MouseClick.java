import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;
import java.io.Serializable;

/**
 * The class MouseClick is the event handler
 * class that contains methods responding to
 * mouse clicks on the chess board.
 */
public class MouseClick implements Serializable {
    /** The default serial version UID. */
    private static final long serialVersionUID = 1L;
    
    /** This holds the square that a piece was previously on before moving. */
    Square hadPiecePreviously;
    
    /**
     * This method first calls the method to reset
     * all the square's strokes back to its default
     * fill color. Then, it checks to see if there is
     * currently a selected piece and if the square clicked
     * on has a piece on it or not. If both conditions are met,
     * the piece selected will move to the clicked on square.
     * Clicking on just the squares will set a stroke color of
     * either green or red. Green if the square has a piece and 
     * red if the square doesn't have a piece.
     * 
     * @param squareArray as Square[]
     * @param square as Square
     * @param pieceArray as Piece[]
     * @param pane as GridPane
     */
    public void squareClicked(Square[] squareArray, Square square,
                              Piece[] pieceArray, GridPane pane) {
        square.getRec().setOnMouseClicked(new EventHandler<MouseEvent>() {
            
            @Override 
            public void handle(MouseEvent e) {
                Square.resetAllStrokes(squareArray);
                Square.resetAllFills(squareArray);
                
                int rowDest;
                int colDest;
                
                if (!square.getHasAPiece() && !square.getPieceCanMoveTo()) {
                    Piece.resetAllSelectedPieces(pieceArray);
                    Square.resetAllPieceCanMoveToSquares(squareArray);  
                    Square.setRedStroke(square);
                }
                
                if (square.getHasAPiece()) {
                    Piece.resetAllSelectedPieces(pieceArray);
                    Square.resetAllPieceCanMoveToSquares(squareArray);
                    
                    if (Piece.getSamePositionPiece(square, pieceArray).getTurn()) {
                           Square.setGreenStroke(square);
                           hadPiecePreviously = square;
                           Piece.getSamePositionPiece(square, pieceArray).validMoves(squareArray);
                           Piece.getSamePositionPiece(square, pieceArray).setIsSelected(true);                          
                    } else {
                        Square.setRedStroke(square);
                    }
                }
                              
                if (Piece.isThereSelectedPiece(pieceArray) && !(square.getHasAPiece())
                    && square.getPieceCanMoveTo()) {
                    rowDest = GridPane.getRowIndex(square.getRec());
                    colDest = GridPane.getColumnIndex(square.getRec());
                    
                    pane.getChildren().remove(Piece.getSelectedPiece(pieceArray).getPiece());
                    pane.add(Piece.getSelectedPiece(pieceArray).getPiece(), colDest, rowDest);
                    
                    Piece.getSelectedPiece(pieceArray).setPosition(square.getPosition());
                    
                    if (Piece.getSelectedPiece(pieceArray).getName().equals("pawn")) {
                        Piece.getSelectedPiece(pieceArray).setFirstMove(false);
                    }
                    
                    if (Piece.getSelectedPiece(pieceArray).getColor().equals("white")) {
                        Piece.setBlackTurnTrue(pieceArray);
                    } else {
                        Piece.setWhiteTurnTrue(pieceArray);
                    }
                    
                    hadPiecePreviously.setHasAPiece(false);
                    square.setHasAPiece(true);
                    Piece.resetAllSelectedPieces(pieceArray);
                    Square.resetAllPieceCanMoveToSquares(squareArray);
                }
            }
        });
    }
    
    /**
     * This method first calls the methods to
     * reset all strokes of square back to its default
     * fill color and all selected status of pieces to false.
     * Then, it will use a loop to find the same position of 
     * the piece to the square it's on. That square will get
     * a stroke of color green to indicate the piece has been
     * clicked. Lastly, the piece that was clicked will get its
     * selected status set to true.
     * 
     * @param piece as Piece
     * @param squareArray as Square[]
     * @param pieceArray as Piece[]
     */
    public void pieceClicked(Piece piece, Square[] squareArray, Piece[] pieceArray) {
        piece.getPiece().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                Square.resetAllStrokes(squareArray);
                Square.resetAllFills(squareArray);
                Piece.resetAllSelectedPieces(pieceArray);
                Square.resetAllPieceCanMoveToSquares(squareArray);              
                              
                for (int i = 0; i < squareArray.length; i++) {
                    if (squareArray[i].getPosition() == piece.getPosition()) {
                        if (piece.getTurn()) {
                            Square.setGreenStroke(squareArray[i]);
                            hadPiecePreviously = squareArray[i];
                            piece.setIsSelected(true);
                            piece.validMoves(squareArray);
                            break;
                        } else {
                            Square.setRedStroke(squareArray[i]);
                            break;
                        }                                   
                    }
                }              
            }                    
        });
    }
}

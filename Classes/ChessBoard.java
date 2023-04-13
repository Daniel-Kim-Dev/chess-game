import javafx.scene.layout.GridPane;
import javafx.geometry.HPos;
import java.io.Serializable;

/**
 * The class ChessBoard is a GridPane
 * to display all the squares and pieces
 * on a chess board.
 */
public class ChessBoard extends GridPane implements Serializable {
    /** The default serial version UID. */
    private static final long serialVersionUID = 1L;

    /** An array that holds all 64 squares of a chess board. */
    private Square[] squareArray;
    
    /** An array that holds all 32 pieces of a chess game. */
    private Piece[] pieceArray;
    
    /** A Mouse event handler to apply to the squares and pieces. */
    private MouseClick click;
    
    /**
     * The constructor for the class ChessBoard.
     */
    public ChessBoard() {
        squareArray = new Square[64];
        pieceArray = new Piece[32];
        click = new MouseClick();
        drawSquares();
        drawPieces();
    }
    
    /**
     * Returns the squareArray as a Square[].
     * 
     * @return squareArray
     */
    public Square[] getSquareArray() {
        return squareArray;
    }
    
    /**
     * Returns the pieceArray as a Piece[].
     * 
     * @return pieceArray
     */
    public Piece[] getPieceArray() {
        return pieceArray;
    }
    
    /**
     * This method instantiates a new square object
     * into each index of the square array.
     */
    public void loadSquareArray() {
        int position = 1;
        
        for (int i = 0; i < squareArray.length; i++) {
            if ((position >= 1 && position <= 16) || (position >= 49 && position <= 64)) {
                squareArray[i] = new Square(position, true);
            } else {
                squareArray[i] = new Square(position, false);
            }         
            position++;
        }
    }
    
    /**
     * This method instantiates a new piece object
     * into each index of the piece array.
     */
    public void loadPieceArray() {
        int position = 9;
        int position2 = 49;
        
        pieceArray[0] = new Rook("rook", "black", 1, false, false);
        pieceArray[1] = new Knight("knight", "black", 2, false, false);
        pieceArray[2] = new Bishop("bishop", "black", 3, false, false);
        pieceArray[3] = new Queen("queen", "black", 4, false, false);
        pieceArray[4] = new King("king", "black", 5, false, false);
        pieceArray[5] = new Bishop("bishop", "black", 6, false, false);
        pieceArray[6] = new Knight("knight", "black", 7, false, false);
        pieceArray[7] = new Rook("rook", "black", 8, false, false);
        
        for (int i = 8; i < 16; i++) {
            pieceArray[i] = new Pawn("pawn", "black", position, false, false);
            position++;
        }
        
        for (int i = 16; i < 24; i++) {
            pieceArray[i] = new Pawn("pawn", "white", position2, false, true);
            position2++;
        }
                     
        pieceArray[24] = new Rook("rook", "white", position2, false, true);
        pieceArray[25] = new Knight("knight", "white", ++position2, false, true);
        pieceArray[26] = new Bishop("bishop", "white", ++position2, false, true);
        pieceArray[27] = new Queen("queen", "white", ++position2, false, true);
        pieceArray[28] = new King("king", "white", ++position2, false, true);
        pieceArray[29] = new Bishop("bishop", "white", ++position2, false, true);
        pieceArray[30] = new Knight("knight", "white", ++position2, false, true);
        pieceArray[31] = new Rook("rook", "white", ++position2, false, true);
    }
    
    /**
     * This method calls upon the method to
     * load up the square array and adds each
     * square in the array to the grid pane.
     */
    public void drawSquares() {
        int index = 0;
        
        loadSquareArray();
                   
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (row % 2 == 0) {
                    add(squareArray[index].draw("evenRow"), col, row);
                } else {
                    add(squareArray[index].draw("oddRow"), col, row);
                }
                click.squareClicked(squareArray, squareArray[index], pieceArray, this);
                index++;
            }
        }
    }
    
    /**
     * This method calls upon the method to
     * load up the piece array and adds each
     * piece in the array to the grid pane.
     */
    public void drawPieces() {
        loadPieceArray();
        
        int index = 0;
        int whiteRow = 6;
        
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 8; col++) {
                if (index <= 15) {               
                    add(pieceArray[index].drawBlack(), col, row);             
                    setHalignment(pieceArray[index].getPiece(), HPos.CENTER);
                    click.pieceClicked(pieceArray[index], squareArray, pieceArray);
                    index++;
                } else {                   
                    add(pieceArray[index].drawWhite(), col, whiteRow);
                    setHalignment(pieceArray[index].getPiece(), HPos.CENTER);
                    click.pieceClicked(pieceArray[index], squareArray, pieceArray);
                    index++; 
                }
            }
            if (index == 24) {
                whiteRow++;
            }     
        }
    }
    
    /**
     * This method will be called when the user
     * successfully opens a saved game. It takes in
     * the square and piece array of the saved game
     * and copies all of its variables to the current
     * game. All of the image view of the pieces will
     * be removed and redrawn to the positions of where
     * they were in the saved game.
     * 
     * @param squareArray as Square[]
     * @param pieceArray as Piece[]
     */
    public void resetBoard(Square[] squareArray, Piece[] pieceArray) {
        int rowDest;
        int colDest;
        
        for (int i = 0; i < this.pieceArray.length; i++) {
            getChildren().remove(this.pieceArray[i].getPiece());
        }
        
        for (int i = 0; i < this.pieceArray.length; i++) {
            for (int j = 0; j < this.squareArray.length; j++) {
                if (pieceArray[i].getPosition() == squareArray[j].getPosition()) {
                    rowDest = GridPane.getRowIndex(this.squareArray[j].getRec());
                    colDest = GridPane.getColumnIndex(this.squareArray[j].getRec());
                    
                    add(this.pieceArray[i].getPiece(), colDest, rowDest);
                }
            }
        }
        
        for (int i = 0; i < this.pieceArray.length; i++) {
            if (this.pieceArray[i].getName().equals("pawn")) {
                this.pieceArray[i].setFirstMove(pieceArray[i].getFirstMove());
            }
            this.pieceArray[i].setTurn(pieceArray[i].getTurn());
            this.pieceArray[i].setPosition(pieceArray[i].getPosition());
        }
        
        for (int i = 0; i < this.squareArray.length; i++) {
            this.squareArray[i].setHasAPiece(squareArray[i].getHasAPiece());
        }
        
        Square.resetAllStrokes(this.squareArray);
        Square.resetAllFills(this.squareArray);
        Piece.resetAllSelectedPieces(this.pieceArray);
        Square.resetAllPieceCanMoveToSquares(this.squareArray);
    }
}

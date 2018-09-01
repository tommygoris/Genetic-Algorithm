/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.examples.evolveann.playchess;

import geneticalgorithm.examples.evolveann.playchess.Player.HumanPlayer;
import geneticalgorithm.examples.evolveann.playchess.Player.Player;
import geneticalgorithm.examples.evolveann.playchess.pieces.*;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tommy
 */
public class ChessGameMainWithUI extends Application {

    private GridPane gameBoardGridPane;
    private ChessBoard gameChessBoard;
    private List<AbstractChessPiece> topChessPieces = this.createAndGetTopChessPieces();
    private List<AbstractChessPiece> bottomChessPieces = this.createAndGetBottomChessPieces();
    private Player playerOne = new HumanPlayer();
    private Player playerTwo = new HumanPlayer();

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Chess Game");
        gameChessBoard = new ChessBoard();
        gameBoardGridPane = this.createAndGetChessBoardGridPane(JavaFXProperties.chessBoardColorOne, JavaFXProperties.chessBoardColorTwo);
        this.setChessBoardConstraints(gameBoardGridPane);
        primaryStage.setScene(new Scene(gameBoardGridPane, 400, 400));
        primaryStage.show();

        ChessEngine chessEngine = new ChessEngine(gameChessBoard, playerOne, playerTwo);

        this.setupInitialChessBoardPositions();
        this.setUpGridPane();

    }

    private void setChessBoardConstraints(GridPane gridPane) {
        for (int i = 0; i < ChessProperties.MAX_CHESSBOARD_SIZE; i++) {
            gridPane.getColumnConstraints().add(new ColumnConstraints(5, Control.USE_PREF_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, HPos.CENTER, true));
            gridPane.getRowConstraints().add(new RowConstraints(5, Control.USE_PREF_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, VPos.CENTER, true));
        }
    }

    private ObservableList<Node> getGameBoardGridPaneChildren(){
        return this.gameBoardGridPane.getChildren();
    }

    private List<AbstractChessPiece> createAndGetTopChessPieces(){
        List<AbstractChessPiece> listOfTopChessPieces = new ArrayList<>();

        for (int i = 0; i <NumberOfPiecesEnum.PAWN.getValue(); i++){
            listOfTopChessPieces.add(new Pawn(i, 1, true, JavaFXProperties.topPlayerColor));
        }

        listOfTopChessPieces.add(new Rook(0, 0, true, JavaFXProperties.topPlayerColor));
        listOfTopChessPieces.add(new Rook(7, 0, true, JavaFXProperties.topPlayerColor));

        listOfTopChessPieces.add(new Knight(1, 0, true, JavaFXProperties.topPlayerColor));
        listOfTopChessPieces.add(new Knight(6, 0, true, JavaFXProperties.topPlayerColor));

        listOfTopChessPieces.add(new Bishop(2, 0, true, JavaFXProperties.topPlayerColor));
        listOfTopChessPieces.add(new Bishop(5, 0, true, JavaFXProperties.topPlayerColor));

        listOfTopChessPieces.add(new King(3, 0, true, JavaFXProperties.topPlayerColor));

        listOfTopChessPieces.add(new Queen(4, 0, true, JavaFXProperties.topPlayerColor));

        return listOfTopChessPieces;
    }

    private List<AbstractChessPiece> createAndGetBottomChessPieces(){
        List<AbstractChessPiece> listOfBottomChessPieces = new ArrayList<>();

        for (int i = 0; i <NumberOfPiecesEnum.PAWN.getValue(); i++){
            listOfBottomChessPieces.add(new Pawn(i, 6, false, JavaFXProperties.bottomPlayerColor));
        }

        listOfBottomChessPieces.add(new Rook(0, 7, false, JavaFXProperties.bottomPlayerColor));
        listOfBottomChessPieces.add(new Rook(7, 7, false, JavaFXProperties.bottomPlayerColor));

        listOfBottomChessPieces.add(new Knight(1, 7, false, JavaFXProperties.bottomPlayerColor));
        listOfBottomChessPieces.add(new Knight(6, 7, false, JavaFXProperties.bottomPlayerColor));

        listOfBottomChessPieces.add(new Bishop(2, 7, false, JavaFXProperties.bottomPlayerColor));
        listOfBottomChessPieces.add(new Bishop(5, 7, false, JavaFXProperties.bottomPlayerColor));

        listOfBottomChessPieces.add(new King(3, 7, false, JavaFXProperties.bottomPlayerColor));

        listOfBottomChessPieces.add(new Queen(4, 7, false, JavaFXProperties.bottomPlayerColor));

        return listOfBottomChessPieces;
    }

    private void setupInitialChessBoardPositions()
    {
        this.setupInitialTopChessBoardPositions();
        this.setupInitialBottomChessBoardPositions();

    }

    private void setupInitialTopChessBoardPositions()
    {
        for (AbstractChessPiece piece : this.topChessPieces)
        {
            this.gameChessBoard.chessBoard[piece.currentPosition.getValue()][piece.currentPosition.getKey()] = new ChessSquare(piece);
        }
    }

    private void setupInitialBottomChessBoardPositions()
    {
        for (AbstractChessPiece piece : this.bottomChessPieces)
        {
            this.gameChessBoard.chessBoard[piece.currentPosition.getValue()][piece.currentPosition.getKey()] = new ChessSquare(piece);
        }
    }

    public void setUpGridPane()
    {
        for (ChessSquare[] chessSquareArray : this.gameChessBoard.chessBoard)
        {
            for (ChessSquare chessSquare : chessSquareArray)
            {
                if (chessSquare != null && chessSquare.chessPiece != null)
                {
                    String chessPieceName = chessSquare.chessPiece.toString();
                    Text chessPieceText =  chessSquare.chessPiece.isTopSide ? JavaFXProperties.getTopPieceText(chessPieceName) : JavaFXProperties.getBottomPieceText(chessPieceName);
                    chessPieceText.setOnMouseClicked(textMouseHandler);
                    this.gameBoardGridPane.add(chessPieceText, chessSquare.chessPiece.currentPosition.getKey(), chessSquare.chessPiece.currentPosition.getValue());
                }
            }
        }
    }


    public GridPane createAndGetChessBoardGridPane(String chessBoardColorOne, String chessBoardColorTwo) {
        GridPane gridPane = new GridPane();
        for (int row = 0; row < ChessProperties.MAX_CHESSBOARD_SIZE; row++) {
            for (int col = 0; col < ChessProperties.MAX_CHESSBOARD_SIZE; col++) {
                StackPane square = new StackPane();
                String chosenColor;
                if ((row + col) % 2 == 0) {
                    chosenColor = chessBoardColorOne;
                } else {
                    chosenColor = chessBoardColorTwo;
                }
                square.setStyle("-fx-background-color: " + chosenColor + ";");
                square.setOnMouseClicked(stackPaneMouseHandler);
                gridPane.add(square, col, row);
            }
        }
        return gridPane;
    }
    private final EventHandler<MouseEvent> stackPaneMouseHandler = selectedStackPane -> {
        int columnIndexSelectionX = GridPane.getColumnIndex((StackPane)selectedStackPane.getSource());
        int rowIndexSelectionY = GridPane.getRowIndex((StackPane)selectedStackPane.getSource());
    };

    private final EventHandler<MouseEvent> textMouseHandler = selectedStackPane -> {
        int columnIndexSelectionX = GridPane.getColumnIndex((Text)selectedStackPane.getSource());
        int rowIndexSelectionY = GridPane.getRowIndex((Text)selectedStackPane.getSource());
    };
}
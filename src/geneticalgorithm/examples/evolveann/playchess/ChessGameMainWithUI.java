/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.examples.evolveann.playchess;

import geneticalgorithm.examples.evolveann.playchess.Player.HumanPlayer;
import geneticalgorithm.examples.evolveann.playchess.pieces.AbstractChessPiece;
import geneticalgorithm.examples.evolveann.playchess.pieces.Pawn;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;

/**
 *
 * @author Tommy
 */
public class ChessGameMainWithUI extends Application {

    private GridPane gameBoardGridPane;
    private String chessBoardColorOne = "white";
    private String chessBoardColorTwo = "black";
    private ObservableList<Node> topChessPieces;
    private ObservableList<Node> buttomChessPieces;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Chess Game");
        ChessBoard gameBoard = new ChessBoard();
        gameBoardGridPane = gameBoard.createAndGetChessBoardGridPane(chessBoardColorOne, chessBoardColorTwo);
        gameBoard.setUpGridPane(gameBoardGridPane);
        this.setChessBoardConstraints(gameBoardGridPane);
        primaryStage.setScene(new Scene(gameBoardGridPane, 400, 400));
        primaryStage.show();

        ChessEngine chessEngine = new ChessEngine(gameBoard, new HumanPlayer(), new HumanPlayer());

    }

    private void setChessBoardConstraints(GridPane gridPane) {
        final int maxChessBoardSize = 8;
        for (int i = 0; i < maxChessBoardSize; i++) {
            gridPane.getColumnConstraints().add(new ColumnConstraints(5, Control.USE_PREF_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, HPos.CENTER, true));
            gridPane.getRowConstraints().add(new RowConstraints(5, Control.USE_PREF_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, VPos.CENTER, true));
        }
    }

    private ObservableList<Node> getGameBoardGridPaneChildren(){
        return this.gameBoardGridPane.getChildren();
    }

    private List<AbstractChessPiece> createAndGetTopChessPieces(){
        List<AbstractChessPiece> listOfTopChessPieces = null;
        final int numberOfPawns = 5;
        for (int i = 0; i <numberOfPawns; i++){
            listOfTopChessPieces.add(new Pawn());
        }

        return listOfTopChessPieces;
    }


}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.examples.evolveann.playchess.pieces;

import geneticalgorithm.examples.evolveann.playchess.ChessBoard;
import javafx.util.Pair;

import java.util.ArrayList;

/**
 *
 * @author TommyGoris
 */
public abstract class AbstractChessPiece {
    public ArrayList<Pair<Integer, Integer>> pastMoves = new ArrayList<>();
    public Pair<Integer, Integer> startingPosition;
    public Pair<Integer, Integer> currentPosition;
    public ArrayList<Pair<Integer, Integer>> possibleMoves;
    public boolean isTopSide;
    public String color;

    public AbstractChessPiece(int x, int y, boolean isTopSide, String color) {
        this.startingPosition = new Pair<>(x, y);
        this.currentPosition = new Pair<>(x, y);
        this.isTopSide = isTopSide;
        this.possibleMoves = new ArrayList<>();
        this.color = color;
    }

    abstract void UpdateMoves(ChessBoard board);
}

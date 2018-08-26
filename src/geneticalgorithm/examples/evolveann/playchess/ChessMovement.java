/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.examples.evolveann.playchess;

import javafx.util.Pair;

import java.util.ArrayList;

/**
 *
 * @author TommyGoris
 */
public class ChessMovement {
    
    public static Pair<Integer, Integer> moveNorth = new Pair<>(0,1);
    public static Pair<Integer, Integer> moveEast = new Pair<>(1,0);
    public static Pair<Integer, Integer> moveSouth = new Pair<>(0,-1);
    public static Pair<Integer, Integer> moveWest = new Pair<>(-1,0);
    public static Pair<Integer, Integer> moveNorthEast = new Pair<>(1,1);
    public static Pair<Integer, Integer> moveSouthEast = new Pair<>(1,-1);
    public static Pair<Integer, Integer> moveSouthWest = new Pair<>(-1,-1);
    public static Pair<Integer, Integer> moveNorthWest = new Pair<>(-1,1);
    public static Pair<Integer, Integer> moveNorthTwo = new Pair<>(0,2);
    public static Pair<Integer, Integer> moveSouthTwo = new Pair<>(0,-2);
    public static Pair<Integer, Integer> TwoLeftOneDownKnightMove = new Pair<>(-2,-1);
    public static Pair<Integer, Integer> TwoLeftOneUpKnightMove = new Pair<>(-2,1);
    public static Pair<Integer, Integer> TwoUpOneLeftKnightMove = new Pair<>(-1,2);
    public static Pair<Integer, Integer> TwoUpOneRightKnightMove = new Pair<>(1,2);
    public static Pair<Integer, Integer> TwoRightOneUpKnightMove = new Pair<>(2,1);
    public static Pair<Integer, Integer> TwoRightOneDownKnightMove = new Pair<>(2,-1);
    public static Pair<Integer, Integer> TwoDownOneLeftKnightMove = new Pair<>(-1,-2);
    public static Pair<Integer, Integer> TwoDownOneRightKnightMove = new Pair<>(1,-2);

    public static ArrayList<Pair<Integer, Integer>> GetAllPossibleMovesNorthWestDiagonol(ChessBoard board, boolean isTopSide) {
        ArrayList<Pair<Integer, Integer>> possibleMoves = new ArrayList<>();

        Pair<Integer, Integer> northWest = ChessMovement.moveNorthWest;
        int xNorthWestLocation = northWest.getKey();
        int yNorthWestLocation = northWest.getValue();
        for (int i = 0; i <board.chessBoard.length; i++)
        {
            if (board.canMove(xNorthWestLocation, yNorthWestLocation))
            {
                possibleMoves.add(new Pair<>(xNorthWestLocation, yNorthWestLocation));
            }
            else if (board.canCapture(xNorthWestLocation, yNorthWestLocation, isTopSide))
            {
                possibleMoves.add(new Pair<>(xNorthWestLocation, yNorthWestLocation));
                break;
            }
            else
            {
                break;
            }
            xNorthWestLocation -= 1;
            yNorthWestLocation += 1;
        }

        return possibleMoves;
    }

    public static ArrayList<Pair<Integer, Integer>> GetAllPossibleMovesNorthEastDiagonol(ChessBoard board, boolean isTopSide) {
        ArrayList<Pair<Integer, Integer>> possibleMoves = new ArrayList<>();

        Pair<Integer, Integer> northEast = ChessMovement.moveNorthEast;
        int xNorthEastLocation = northEast.getKey();
        int yNorthEastLocation = northEast.getValue();
        for (int i = 0; i <board.chessBoard.length; i++)
        {
            if (board.canMove(xNorthEastLocation, yNorthEastLocation))
            {
                possibleMoves.add(new Pair<>(xNorthEastLocation, yNorthEastLocation));
            }
            else if (board.canCapture(xNorthEastLocation, yNorthEastLocation, isTopSide))
            {
                possibleMoves.add(new Pair<>(xNorthEastLocation, yNorthEastLocation));
                break;
            }
            else
            {
                break;
            }
            xNorthEastLocation += 1;
            yNorthEastLocation += 1;
        }

        return possibleMoves;
    }

    public static ArrayList<Pair<Integer, Integer>> GetAllPossibleMovesSouthWestDiagonol(ChessBoard board, boolean isTopSide) {
        ArrayList<Pair<Integer, Integer>> possibleMoves = new ArrayList<>();

        Pair<Integer, Integer> southWest = ChessMovement.moveSouthWest;
        int xSouthWestLocation = southWest.getKey();
        int ySouthWestLocation = southWest.getValue();
        for (int i = 0; i <board.chessBoard.length; i++)
        {
            if (board.canMove(xSouthWestLocation, ySouthWestLocation))
            {
                possibleMoves.add(new Pair<>(xSouthWestLocation, ySouthWestLocation));
            }
            else if (board.canCapture(xSouthWestLocation, ySouthWestLocation, isTopSide))
            {
                possibleMoves.add(new Pair<>(xSouthWestLocation, ySouthWestLocation));
                break;
            }
            else
            {
                break;
            }
            xSouthWestLocation -= 1;
            ySouthWestLocation -= 1;
        }

        return possibleMoves;
    }

    public static ArrayList<Pair<Integer, Integer>> GetAllPossibleMovesSouthEastDiagonol(ChessBoard board, boolean isTopSide) {
        ArrayList<Pair<Integer, Integer>> possibleMoves = new ArrayList<>();

        Pair<Integer, Integer> southEast = ChessMovement.moveSouthEast;
        int xSouthEastLocation = southEast.getKey();
        int ySouthEastLocation = southEast.getValue();
        for (int i = 0; i <board.chessBoard.length; i++)
        {
            if (board.canMove(xSouthEastLocation, ySouthEastLocation))
            {
                possibleMoves.add(new Pair<>(xSouthEastLocation, ySouthEastLocation));
            }
            else if (board.canCapture(xSouthEastLocation, ySouthEastLocation, isTopSide))
            {
                possibleMoves.add(new Pair<>(xSouthEastLocation, ySouthEastLocation));
                break;
            }
            else
            {
                break;
            }
            xSouthEastLocation += 1;
            ySouthEastLocation -= 1;
        }

        return possibleMoves;
    }

    public static ArrayList<Pair<Integer, Integer>> GetAllPossibleMovesNorth(ChessBoard board, boolean isTopSide){
        ArrayList<Pair<Integer, Integer>> possibleMoves = new ArrayList<>();
        Pair<Integer, Integer> north = ChessMovement.moveNorth;
        int xNorthLocation = north.getKey();
        int yNorthLocation = north.getValue();
        for (int i = 0; i <board.chessBoard.length; i++)
        {
            if (board.canMove(xNorthLocation, yNorthLocation))
            {
                possibleMoves.add(new Pair<>(xNorthLocation, yNorthLocation));
            }
            else if (board.canCapture(xNorthLocation, yNorthLocation, isTopSide))
            {
                possibleMoves.add(new Pair<>(xNorthLocation, yNorthLocation));
                break;
            }
            else
            {
                break;
            }
            yNorthLocation += 1;
        }


        return possibleMoves;
    }

    public static ArrayList<Pair<Integer, Integer>> GetAllPossibleMovesSouth(ChessBoard board, boolean isTopSide){
        ArrayList<Pair<Integer, Integer>> possibleMoves = new ArrayList<>();
        Pair<Integer, Integer> south = ChessMovement.moveSouth;
        int xSouthLocation = south.getKey();
        int ySouthLocation = south.getValue();
        for (int i = 0; i <board.chessBoard.length; i++)
        {
            if (board.canMove(xSouthLocation, ySouthLocation))
            {
                possibleMoves.add(new Pair<>(xSouthLocation, ySouthLocation));
            }
            else if (board.canCapture(xSouthLocation, ySouthLocation, isTopSide))
            {
                possibleMoves.add(new Pair<>(xSouthLocation, ySouthLocation));
                break;
            }
            else
            {
                break;
            }
            ySouthLocation -= 1;
        }


        return possibleMoves;
    }

    public static ArrayList<Pair<Integer, Integer>> GetAllPossibleMovesEast(ChessBoard board, boolean isTopSide){
        ArrayList<Pair<Integer, Integer>> possibleMoves = new ArrayList<>();
        Pair<Integer, Integer> east = ChessMovement.moveEast;
        int xEastLocation = east.getKey();
        int yEastLocation = east.getValue();
        for (int i = 0; i <board.chessBoard.length; i++)
        {
            if (board.canMove(xEastLocation, yEastLocation))
            {
                possibleMoves.add(new Pair<>(xEastLocation, yEastLocation));
            }
            else if (board.canCapture(xEastLocation, yEastLocation, isTopSide))
            {
                possibleMoves.add(new Pair<>(xEastLocation, yEastLocation));
                break;
            }
            else
            {
                break;
            }
            xEastLocation += 1;
        }


        return possibleMoves;
    }

    public static ArrayList<Pair<Integer, Integer>> GetAllPossibleMovesWest(ChessBoard board, boolean isTopSide){
        ArrayList<Pair<Integer, Integer>> possibleMoves = new ArrayList<>();
        Pair<Integer, Integer> west = ChessMovement.moveWest;
        int xWestLocation = west.getKey();
        int yWestLocation = west.getValue();
        for (int i = 0; i <board.chessBoard.length; i++)
        {
            if (board.canMove(xWestLocation, yWestLocation))
            {
                possibleMoves.add(new Pair<>(xWestLocation, yWestLocation));
            }
            else if (board.canCapture(xWestLocation, yWestLocation, isTopSide))
            {
                possibleMoves.add(new Pair<>(xWestLocation, yWestLocation));
                break;
            }
            else
            {
                break;
            }
            xWestLocation -= 1;
        }


        return possibleMoves;
    }
}

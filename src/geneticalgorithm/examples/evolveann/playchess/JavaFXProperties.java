package geneticalgorithm.examples.evolveann.playchess;

import javafx.scene.text.Text;

public class JavaFXProperties {
    public static String topPlayerColor = "red";
    public static String bottomPlayerColor = "blue";
    public static String chessBoardColorOne = "#8b4513";
    public static String chessBoardColorTwo = "#b5651d";

    public static Text getTopPieceText(String Name)
    {
        Text pieceText = new Text(Name);
        pieceText.setStyle("-fx-text-inner-color: red;");
        pieceText.setStyle("-fx-text-fill: green; -fx-font-size: 16px;");
        return pieceText;
    }

    public static Text getBottomPieceText(String Name)
    {
        Text pieceText = new Text(Name);
        pieceText.setStyle("-fx-text-inner-color: red;");
        pieceText.setStyle("-fx-text-fill: green; -fx-font-size: 16px;");
        return pieceText;
    }
}

package application;

import chess.ChessPiece;

class UI {
    private static char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};

    public static void printBoard(ChessPiece[][] pieces) {
        for(int i=0; i<pieces.length; i++) {
            System.out.print((pieces.length - i) + " ");
            for(int j=0; j<pieces[0].length; j++) {
                printPiece(pieces[i][j]);
            }
            System.out.println();
        }

        System.out.print("  ");
        for (int i=0; i<pieces[0].length; i++)
            System.out.print(alphabet[i] + " ");
        System.out.println();
    }

    private static void printPiece (ChessPiece piece) {
        if (piece == null)
            System.out.print("-");
        else
            System.out.print(piece);

        System.out.print(" ");
    }
}

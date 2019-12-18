package application;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class UI {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_WHITE = "\u001B[37m";

    private static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";


    private static char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};

    static ChessPosition readChessPosition(Scanner sc) {
        try {
            String s = sc.nextLine();
            char column = s.charAt(0);
            int row = Integer.parseInt(s.substring(1));
            return new ChessPosition(column, row);
        }
        catch(RuntimeException e) {
            throw new InputMismatchException("Error reading chess position. Valid values are from a1 to h8");
        }

    }

    private static void printBoard(ChessPiece[][] pieces) {
        for(int i=0; i<pieces.length; i++) {
            System.out.print((pieces.length - i) + " ");
            for(int j=0; j<pieces[0].length; j++) {
                printPiece(pieces[i][j], false);
            }
            System.out.println();
        }

        System.out.print("  ");
        for (int i=0; i<pieces[0].length; i++)
            System.out.print(alphabet[i] + " ");
        System.out.println();
    }

    static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
        for(int i=0; i<pieces.length; i++) {
            System.out.print((pieces.length - i) + " ");
            for(int j=0; j<pieces[0].length; j++) {
                printPiece(pieces[i][j], possibleMoves[i][j]);
            }
            System.out.println();
        }

        System.out.print("  ");
        for (int i=0; i<pieces[0].length; i++)
            System.out.print(alphabet[i] + " ");
        System.out.println();
    }

    private static void printPiece (ChessPiece piece, boolean background) {
        if (background)
            System.out.print(ANSI_BLUE_BACKGROUND);

        if (piece == null)
            System.out.print("-" + ANSI_RESET);
        else {
            if (piece.getColor() == Color.WHITE) {
                System.out.print(ANSI_WHITE + piece + ANSI_RESET);
            }
            else {
                System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
            }
        }
        System.out.print(" ");
    }

    static void printMatch(ChessMatch chessMatch, List<ChessPiece> captured) {
        printBoard(chessMatch.getPieces());
        System.out.println();
        printCapturedPieces(captured);
        System.out.println();
        System.out.println("Turn: " + chessMatch.getTurn());
        if (chessMatch.isNotCheckMate()) {
            System.out.println("Waiting player: " + chessMatch.getCurrentPlayer());
            if (chessMatch.isCheck())
                System.out.println("CHECK!");
        }
        else {
            System.out.println("CHECKMATE!");
            System.out.println("Winner: " + chessMatch.getCurrentPlayer());
        }
    }

    static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void printCapturedPieces(List<ChessPiece> captured) {
        List<ChessPiece> black;
        black = captured.stream().filter(x -> x.getColor() == Color.BLACK).collect(Collectors.toList());
        System.out.println("Captured pieces:");
        System.out.println("White: ");
        System.out.print(ANSI_WHITE);
        System.out.println(Arrays.toString(captured.stream().filter(x -> x.getColor() == Color.WHITE).toArray()));
        System.out.print(ANSI_RESET);

        System.out.println("Black: ");
        System.out.print(ANSI_YELLOW);
        System.out.println(Arrays.toString(black.toArray()));
        System.out.print(ANSI_RESET);
    }
}

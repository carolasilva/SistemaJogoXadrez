package chess;

import boardgame.BoardException;

public class ChessException extends BoardException {
    ChessException(String msg) {
        super(msg);
    }
}

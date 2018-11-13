package com.chesstama.model;

/**
 * Student
 *
 * @author rjanardhana
 * @since Aug 2017
 */
public class Student extends Piece {

    public Student(final Player player, final Position position) {
        this(PieceType.STUDENT, player, position);
    }

    public Student(final PieceType pieceType, final Player player, final Position position) {
        super(pieceType, player, position);
    }
}

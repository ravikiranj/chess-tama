package com.chesstama.model;

/**
 * Student
 *
 * @author rjanardhana
 * @since Aug 2017
 */
public class Student extends Piece {

    public Student(Player player, Position position) {
        this(PieceType.STUDENT, player, position);
    }

    public Student(PieceType pieceType, Player player, Position position) {
        super(pieceType, player, position);
    }
}

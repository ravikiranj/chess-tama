package com.chesstama.model;

/**
 * Student
 *
 * @author rjanardhana
 * @since Aug 2017
 */
public class Student extends Piece
{
    public Student(Position position, Player player)
    {
        this(PieceType.STUDENT, position, player);
    }

    public Student(PieceType pieceType, Position position, Player player)
    {
        super(pieceType, position, player);
    }
}

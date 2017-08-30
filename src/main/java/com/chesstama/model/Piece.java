package com.chesstama.model;

/**
 * Piece
 *
 * @author rjanardhana
 * @since Aug 2017
 */
public abstract class Piece
{
    protected PieceType pieceType;
    protected Position position;
    protected Player player;

    public Piece(PieceType pieceType, Position position, Player player)
    {
        this.pieceType = pieceType;
        this.position = position;
        this.player = player;
    }

    public boolean isMaster()
    {
        return this.pieceType == PieceType.MASTER;
    }

    public String name()
    {
        return this.pieceType.getName();
    }

    public String shortName()
    {
        return this.pieceType.getShortName();
    }

    public Player getPlayer()
    {
        return this.player;
    }

    public Position getPosition()
    {
        return this.position;
    }

    @Override
    public String toString()
    {
        return shortName();
    }

    enum PieceType
    {
        MASTER("Master", "M"),
        STUDENT("Student", "S");

        private final String name;
        private final String shortName;

        PieceType(String name, String shortName)
        {
            this.name = name;
            this.shortName = shortName;
        }

        public String getName()
        {
            return name;
        }

        public String getShortName()
        {
            return shortName;
        }
    }
}

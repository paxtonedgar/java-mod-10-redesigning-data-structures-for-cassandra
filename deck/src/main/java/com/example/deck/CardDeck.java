package com.example.deck;

import java.io.Serializable;
import java.util.UUID;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
@Table
public class CardDeck implements Serializable {

    @PrimaryKey
    private Long id;
    private UUID uuid;
    private String name;
    private String suit;
    private Long deckNumber;
    private Long position;

    private Long points;


    public CardDeck(Long id, UUID uuid, String name, String suit, Long deckNumber, Long position, Long points) {
        this.id = id;
        this.uuid = uuid;
        this.name = name;
        this.suit = suit;
        this.deckNumber = deckNumber;
        this.position = position;
        this.points = points;
    }


    public Long getID() {
        return id;
    }

    public void setID(Long id) {
        this.id = id;
    }
    public UUID getUUID() {
        return uuid;
    }

    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public Long getDeckNumber() {
        return deckNumber;
    }

    public void setDeckNumber(Long deckNumber) {
        this.deckNumber = deckNumber;
    }

    public Long getPosition() {
        return position;
    }

    public void setPosition(Long position) {
        this.position = position;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points){this.points = points;}


}
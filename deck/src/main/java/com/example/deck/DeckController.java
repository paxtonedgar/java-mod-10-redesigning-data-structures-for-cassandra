package com.example.deck;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static java.util.UUID.randomUUID;

@RestController
public class DeckController {

    @Autowired
    private CardDeckRepository cardDeckRepository;

    @GetMapping("/new")
    public List<CardDeck> newDeck(@RequestParam(value = "decks", defaultValue = "1") Long decks) {

        cardDeckRepository.deleteAll();

        List<String> names = new ArrayList<String>(Arrays.asList(
                "Two",
                "Three",
                "Four",
                "Five",
                "Six",
                "Seven",
                "Eight",
                "Nine",
                "Ten",
                "Jack",
                "Queen",
                "King",
                "Ace"
        ));

        List<Long> points = new ArrayList<>(Arrays.asList(
                2L,
                3L,
                4L,
                5L,
                6L,
                7L,
                8L,
                9L,
                10L,
                10L,
                10L,
                10L,
                11L
        ));

        List<String> suits = Arrays.asList("Clubs", "Hearts", "Spades", "Diamonds");

        Long position = 1L;
        long id = 1L;


        for (Long deck = 1L; deck <= decks; deck++) {
            for (String suit : suits) {
                for (int i = 0; i < names.size(); i++) {
                    String name = names.get(i);
                    Long point = points.get(i);
                    UUID uuid = randomUUID();


                    cardDeckRepository.save(new CardDeck(
                            id,
                            uuid,
                            name,
                            suit,
                            position,
                            point,
                            deck
                    ));
                    ++id;
                    ++position;
                }
            }
        }

        Iterable<CardDeck> cardDecksIterable = cardDeckRepository.findAll();
        List<CardDeck> cardDecks = new ArrayList<>();
        for (CardDeck cardDeckIterable : cardDecksIterable) {
            cardDecks.add(cardDeckIterable);
        }

        return cardDecks;
    }

    @GetMapping("/shuffle")
    public String shuffleDeck() {

        Iterable<CardDeck> cardDeck = cardDeckRepository.findAll();
        List<Long> order = new ArrayList<>();

        cardDeck.forEach(card -> {
            order.add(card.getPosition());
        });

        return "Deck shuffled.";
    }

    @GetMapping("/deal")
    public String dealCard() {
        Iterable<CardDeck> cardDecks = cardDeckRepository.findAll();

        Long position = 0L;
        Long id = 0L;

        for (CardDeck cardDeck : cardDecks) {
            if (position < cardDeck.getPosition()) {
                position = cardDeck.getPosition();
                id = cardDeck.getID();
            }
        }

        CardDeck cardDeckToBeDeleted = cardDeckRepository.findById(id).get();

        cardDeckRepository.delete(cardDeckToBeDeleted);

        return String.format("Dealt %s of %s: Worth %s points.", cardDeckToBeDeleted.getName(), cardDeckToBeDeleted.getSuit(),
                cardDeckToBeDeleted.getPoints());

    }
}

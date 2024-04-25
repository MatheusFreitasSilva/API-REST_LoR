package org.example;

import org.example.entities.Card;
import org.example.entities.Set;
import org.example.repositories.CardRepository;
import org.example.repositories.SetRepository;

public class MainTeste {
    public static void main(String[] args) {
        Set set = new Set("teste", "teste", "teste", 5);
        Card card = new Card("teste", "teste", "teste", "teste", "teste", "teste", "teste",10, 10, 5, 1);

        SetRepository setRepo = new SetRepository();
        CardRepository cardRepo = new CardRepository();

//        setRepo.create(set);
        cardRepo.create(card);

    }
}

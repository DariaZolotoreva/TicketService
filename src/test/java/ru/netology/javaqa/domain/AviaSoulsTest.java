package ru.netology.javaqa.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

public class AviaSoulsTest {

    Ticket ticket1 = new Ticket(
            "Riga", "Moscow", 250, 14, 18); //4

    Ticket ticket2 = new Ticket(
            "Riga", "Moscow", 450, 13, 15); //2

    Ticket ticket3 = new Ticket(
            "Riga", "Moscow", 390, 19, 22); //3

    Ticket ticket4 = new Ticket(
            "Riga", "Moscow", 500, 9, 10);  //1

    Ticket ticket5 = new Ticket(
            "Moscow", "Tallinn", 100, 4, 5);

    Ticket ticket6 = new Ticket(
            "Riga", "Moscow", 250, 8, 12); //4


    @Test
    public void ShouldCompareLowerPrice() {
        AviaSouls aviaSouls = new AviaSouls();
        aviaSouls.add(ticket1);
        aviaSouls.add(ticket3);

        int expected = -1;
        int actual = ticket1.compareTo(ticket3);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void ShouldCompareHigherPrice() {
        AviaSouls aviaSouls = new AviaSouls();
        aviaSouls.add(ticket4);
        aviaSouls.add(ticket6);

        int expected = 1;
        int actual = ticket4.compareTo(ticket6);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void ShouldCompareSamePrice() {
        AviaSouls aviaSouls = new AviaSouls();
        aviaSouls.add(ticket1);
        aviaSouls.add(ticket6);

        int expected = 0;
        int actual = ticket1.compareTo(ticket6);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void ShouldSearchAndSortTickets() {
        AviaSouls aviaSouls = new AviaSouls();
        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);
        aviaSouls.add(ticket3);
        aviaSouls.add(ticket4);
        aviaSouls.add(ticket5);
        aviaSouls.add(ticket6);

        Ticket[] expected = {ticket1, ticket6, ticket3, ticket2, ticket4};
        Ticket[] actual = aviaSouls.search("Riga", "Moscow");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void ShouldSearchAndSortOneTicket() {
        AviaSouls aviaSouls = new AviaSouls();
        aviaSouls.add(ticket1);

        Ticket[] expected = {ticket1};
        Ticket[] actual = aviaSouls.search("Riga", "Moscow");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void ShouldSearchAndSortNonTicket() {
        AviaSouls aviaSouls = new AviaSouls();


        Ticket[] expected = {};
        Ticket[] actual = aviaSouls.search("Riga", "Moscow");
        Assertions.assertArrayEquals(expected, actual);
    }

    // + na pustoi  i s odnim biletom

    @Test
    public void ShouldSearchAndSortByFlightTime() {
        AviaSouls aviaSouls = new AviaSouls();
        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);
        aviaSouls.add(ticket3);
        aviaSouls.add(ticket4);
        aviaSouls.add(ticket5);
        aviaSouls.add(ticket6);
        Comparator<Ticket> comparator = new TicketTimeComparator();

        Ticket[] expected = {ticket4, ticket2, ticket3, ticket1, ticket6};
        Ticket[] actual = aviaSouls.searchAndSortBy("Riga", "Moscow", comparator);
        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void ShouldSearchAndSortOneFlightByFlightTime() {
        AviaSouls aviaSouls = new AviaSouls();
        aviaSouls.add(ticket2);

        Comparator<Ticket> comparator = new TicketTimeComparator();

        Ticket[] expected = {ticket2};
        Ticket[] actual = aviaSouls.searchAndSortBy("Riga", "Moscow", comparator);
        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void ShouldSearchAndSortNonFlightsByFlightTime() {
        AviaSouls aviaSouls = new AviaSouls();
        Comparator<Ticket> comparator = new TicketTimeComparator();

        Ticket[] expected = {};
        Ticket[] actual = aviaSouls.searchAndSortBy("Riga", "Moscow", comparator);
        Assertions.assertArrayEquals(expected, actual);

    }

}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black.jack;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Robert Woods, tuf08373@temple.edu
 */
public class Player {

    ArrayList<Card> hand;
    int[] points;
    boolean isStanding = false;
    int playerNumber;

    public Player(int i) {
        playerNumber = i;
        hand = new ArrayList<Card>();
    }

    public int getPoints() {
        points = calcPoints();
        if (points[1] <= 21) {
            hand.get(0).sg.eraseAllDrawables("" + playerNumber);
            hand.get(0).sg.drawText("Score: " + points[1], 200, (500 * playerNumber) + 100, Color.red, 1.0, "" + playerNumber);
            return points[1];
        }
        hand.get(0).sg.eraseAllDrawables("" + playerNumber);
        hand.get(0).sg.drawText("Score: " + points[0], 200, (500 * playerNumber) + 100, Color.red, 1.0, "" + playerNumber);
        return points[0];
    }

    public int[] calcPoints() {
        int[] total = new int[2];
        int sum1 = 0;
        int sum2 = 0;
        for (Card card : hand) {
            sum1 += card.points;
            if (card.points == 1) {
                sum2 += 11;
            } else {
                sum2 += card.points;
            }
        }
        total[0] = sum1;
        total[1] = sum2;
        return total;
    }

    public void hitMe(Deck deck) {
        hand.add(deck.hitMe());
        visualize();
    }

    public void stand() {
        isStanding = true;
    }

    public void aiTurn(Deck deck) {
        if (!isStanding) {
            points = calcPoints();
            if (getPoints() < 17) {
                hitMe(deck);
            } else {
                stand();
            }
        }
    }

    public void visualize() {
        int tracker = 0;
        for (Card cards : hand) {
            cards.visualize(10 + tracker * 200, (500 * playerNumber) + 100);
            tracker++;
        }
        switch(playerNumber){
            case 0:
                hand.get(0).sg.drawText("Dealer", 160, playerNumber*500+100);
                break;
            case 1:
                hand.get(0).sg.drawText("Player", 160, playerNumber*500+100);
                break;
        }
            
    }

}

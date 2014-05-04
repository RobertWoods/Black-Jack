/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black.jack;

import simplegui.SimpleGUI;

/**
 *
 * @author Robert Woods, tuf08373@temple.edu
 */
public class Card {
    final int DIAMOND = 1;
    final int HEART = 2;
    final int SPADE = 3;
    final int CLUB = 4;
    int suit;
    int value;
    int points;
    static SimpleGUI sg = new SimpleGUI(1020,900);

    public Card(int s, int v) {
        suit = s;
        value = v;
        if (value > 10) {
            points = 10;
        } else {
            points = v;
        }
    }

    public void visualize(int x, int y) {
        String cardName;
        switch (suit) {
            case DIAMOND:
                cardName = "cards/D" + value + ".png";
                break;
            case HEART:
                cardName = "cards/H" + value + ".png";
                break;
            case SPADE:
                cardName = "cards/S" + value + ".png";
                break;
            default:
                cardName = "cards/C" + value + ".png";
                break;
        }
        sg.drawImage(cardName, x, y); 
    }
}

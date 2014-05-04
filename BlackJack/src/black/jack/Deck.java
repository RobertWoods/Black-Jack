/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black.jack;

import java.util.ArrayList;
import java.util.Random;
import simplegui.DrwImage;
import simplegui.SimpleGUI;

/**
 *
 * @author Robert Woods, tuf08373@temple.edu
 */
public class Deck {

    ArrayList<Card> cards;
    static SimpleGUI sg;

    public Deck() {
        cards = buildDeck();
        sg = cards.get(0).sg;
    }

    public ArrayList<Card> buildDeck() {
        ArrayList<Card> stack = new ArrayList<Card>(52);
        ArrayList<Card> temp = new ArrayList<Card>(52);
        Random rand = new Random();
        for (int i = 1; i <= 13; i++) {
            for (int j = 1; j <= 4; j++) {
                temp.add(new Card(j, i));
            }
        }
        while (!temp.isEmpty()) {
            int r = rand.nextInt(temp.size());
            stack.add(temp.get(r));
            temp.remove(r);
        }
        return stack;
    }

    public void shuffle() {
        ArrayList<Card> temp = new ArrayList<Card>(cards.size());
        Random rand = new Random();
        while (!cards.isEmpty()) {
            int r = rand.nextInt(cards.size());
            temp.add(cards.get(r));
            cards.remove(r);
        }
        cards = temp;
    }

    public boolean isEmpty() {
        if (cards.isEmpty()) {
            return true;
        }
        return false;
    }

   
    public Card hitMe() {
        Card current = cards.get(0);
        cards.remove(0);
        return current;
    }

    public void visualize() {
        if (!cards.isEmpty()) {
            String cardName = "cards/back.jpg";
            int height = Math.round(cards.size()/10)+1;
            DrwImage[] img = new DrwImage[height];
            int xpos = 350;
            int ypos = 375;
            for (DrwImage imgs : img) {
                imgs = new DrwImage(cardName);
                sg.drawImage(imgs, xpos, ypos, 160, 200, "");
                xpos+=3;
                ypos+=3;
            }
            sg.drawText("Remaining Cards:"+cards.size(), 330, 373);
        }
    }
    
    public int getSum(){
        int sum=0;
        for(Card card : cards){
            sum+=card.points;
        }
        return sum;
    }

}

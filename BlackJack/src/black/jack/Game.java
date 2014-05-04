/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black.jack;

import java.awt.Color;
import simplegui.GUIListener;
import simplegui.SimpleGUI;

/**
 *
 * @author Robert Woods, tuf08373@temple.edu
 */
public class Game implements GUIListener {

    public int playerPoints;
    public int housePoints;
    public Deck deck;
    Player ai;
    Player player;
    static SimpleGUI sg;

    public Game(Deck d) {
        deck = d;
        ai = new Player(0);
        player = new Player(1);
        sg = deck.cards.get(0).sg;
        sg.setTitle("BlackJack");
        sg.setBackgroundColor(Color.green);
    }

    public void playGame() throws InterruptedException {
        sg.labelButton1("Hit");
        sg.labelButton2("Stand");
        Player winner = null;
        Thread thread = new Thread();
        for (int i = 0; i < 2; i++) {
            ai.hitMe(deck);
            player.hitMe(deck);
        }
        sg.registerToGUI(this);
        while (checkWinner() == null) {
            thread.sleep(100);
        }
        player.stand();
        ai.stand();
        if (checkWinner() == ai) {
            sg.drawText("You Lose!", 10, 450);
        } else {
            sg.drawText("You Win!", 10, 450);
        }
        sg.labelButton1("Play again?");
        sg.waitForButton1();
        sg.eraseAllDrawables();
    }

    public Player checkWinner() {
        if (player.getPoints() > 21 || ai.getPoints() == 21) {
            return ai;
        } else if (ai.getPoints() > 21 || player.getPoints() == 21) {
            return player;
        } else if (ai.isStanding && player.isStanding) {
            if (ai.getPoints() > player.getPoints()) {
                return ai;
            } else if (ai.getPoints() < player.getPoints()) {
                return player;
            } else if (ai.getPoints() == player.getPoints()) {
                return ai; //House wins pushes
            }
        }
        return null;
    }

    @Override
    public void reactToButton1() {
        if (!player.isStanding) {
            player.hitMe(deck);
        }
        ai.aiTurn(deck);
    }

    @Override
    public void reactToButton2() {
        if (!player.isStanding) {
            player.stand();
            ai.aiTurn(deck);
            sg.labelButton1("Next turn");
        }
    }

    @Override
    public void reactToSwitch(boolean bln) {
    }

    @Override
    public void reactToSlider(int i) {
    }

}

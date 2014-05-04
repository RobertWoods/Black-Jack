/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black.jack;

/**
 *
 * @author Robert
 */
public class BlackJack {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        Deck deck = new Deck();
        deck.sg.drawImage("cards/title screen.jpg",0, 0);
        deck.sg.waitForMouseClick();
        deck.sg.eraseAllDrawables();
        while (1 == 1) {
            if (deck.getSum() < 42) {
                deck = new Deck();
            }
            deck.visualize();
            Game game = new Game(deck);
            game.playGame();
        }
    }
}

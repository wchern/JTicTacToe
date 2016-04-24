import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

/**
 * GameLogic
 * 
 * @author William Chern
 * @version 3/16/2016 updated 4/12/2016
 */
public class GameLogic
{
    String turn;
    Cell[][] gameBoard;
    int boardSize = 3;

    public GameLogic() {
        gameBoard = new Cell[boardSize][boardSize];
        turn = "O";

        for (int i = 0; i < boardSize; i++) // rows
            for (int j = 0; j < boardSize; j++) // columns
                gameBoard[i][j] = new Cell();
            
    }

    public void createGameBoard() {
        JFrame fr = new JFrame("Tic Tac Toe – William Chern");
        fr.setSize(boardSize*100,boardSize*100);
        fr.setResizable(false);
        JPanel p = new JPanel();

        GridLayout g = new GridLayout(boardSize,boardSize);
        p.setLayout(g);

        for (int i = 0; i < boardSize; i++)  // rows
            for (int j = 0; j < boardSize; j++) // columns
                p.add(gameBoard[i][j]);

        fr.add(p);
        fr.setVisible(true);
    }

    public void nextTurn() {
        if (turn.equals("O")) turn = "X";
        else turn = "O";
    }
    
    public void checkForWinner() {
        boolean win = false;
        // look through columns
        for (int c = 0; c < 3; c++) {
            if ((!gameBoard[0][c].isOpen()) && (gameBoard[0][c].getText().equals(gameBoard[1][c].getText())) && (gameBoard[0][c].getText().equals(gameBoard[2][c].getText())))
                win = true;
        }
        
        // look through rows
        for (int r = 0; r < 3; r++) {
            if ((!gameBoard[r][0].isOpen()) && (gameBoard[r][0].getText().equals(gameBoard[r][1].getText())) && (gameBoard[r][0].getText().equals(gameBoard[r][2].getText())))
                win = true;
        }
        
        // look through diagonals
        if(!gameBoard[0][0].isOpen() && gameBoard[0][0].getText().equals(gameBoard[1][1].getText()) && gameBoard[0][0].getText().equals(gameBoard[2][2].getText()))
            win = true;
        else if(!gameBoard[0][2].isOpen() && gameBoard[0][2].getText().equals(gameBoard[1][1].getText()) && gameBoard[0][2].getText().equals(gameBoard[2][0].getText()))
            win = true;
            
        if (win) JOptionPane.showMessageDialog(null, "Player " + turn + " has won!!");
    }

    class Cell extends JButton {
        boolean open;
        public Cell() {
            open = true;
            class CellListener implements ActionListener {
                public void actionPerformed(ActionEvent e) {
                    // change text displayed on the button
                    JButton c = (Cell) e.getSource();
                    c.setText(turn);
                    c.removeActionListener(this);
                    open = false;
                    checkForWinner();
                    
                    nextTurn();
                }
            }
            CellListener n = new CellListener();
            this.addActionListener(n);
        }

        public boolean isOpen() { return open; }
    }
}

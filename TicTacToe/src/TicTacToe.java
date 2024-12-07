import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static java.awt.Color.*;

public class TicTacToe {
    int brwidht = 600;
    int brheight = 650;

    JFrame frame = new JFrame("TicTacToe");
JLabel label = new JLabel();
JPanel panel = new JPanel();
JPanel brpanel = new JPanel();
JButton[][] board = new JButton[3][3];
    String plyX = "X";
    String plyO = "O";
    String currply = plyX;
boolean gameOver = false;
int turns = 0;
    TicTacToe() {
        frame.setVisible(true);
        frame.setSize(brwidht , brheight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.setTitle("TicTacToe");

        label.setBackground(Color.BLACK);
        label.setForeground(orange);
        label.setFont(new Font("Arial", Font.BOLD, 50));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setText("Tic Tac Toe");
        label.setOpaque(true);

        panel.setLayout(new BorderLayout());
        panel.add(label);
        frame.add(panel, BorderLayout.NORTH);

        brpanel.setLayout(new GridLayout(3,3));
        brpanel.setBackground(Color.darkGray);
        frame.add(brpanel);

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                JButton tile = new JButton();
                board[r][c] = tile;
                brpanel.add(tile);

                tile.setBackground(Color.darkGray);
                tile.setForeground(yellow);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                           if (gameOver) return;
                        JButton tile = (JButton) e.getSource();
                        if (tile.getText() == ""){
                            tile.setText(currply);
                            turns++;
                         checkWinner();
                            if (!gameOver){
                                currply = currply == plyX ? plyO : plyX;
                         label.setText(currply + "-ni navbati!");}

                        }
                    }
                });
            }
        }
    }

void checkWinner() {
    //gorozontal
    for (int r = 0; r < 3; r++) {
        if (board[r][0].getText() == "") continue;

        if (board[r][0].getText() == board[r][1].getText() &&
                board[r][1].getText() == board[r][2].getText()) {
            for (int i = 0; i < 3; i++) {
                setWinner(board[r][i]);
            }
            gameOver = true;
            return;
        }

    }

//    //vertikL
    for (int c = 0; c < 3; c++) {
        if (board[0][c].getText() == "") continue;
        if (board[0][c].getText() == board[1][c].getText() &&
                board[1][c].getText() == board[2][c].getText()) {
            for (int i = 0; i < 3; i++) {
                setWinner(board[i][c]);
            }
            gameOver = true;
            return;
        }

    }
    //diagonally
    if (board[0][0].getText() == board[1][1].getText() &&
            board[1][1].getText() == board[2][2].getText() &&
            board[0][0].getText() != "") {
        for (int i = 0; i < 3; i++) {
            setWinner(board[i][i]);
        }
        gameOver = true;
        return;
    }

    //anti-diagonally
    if (board[0][2].getText() == board[1][1].getText() &&
            board[1][1].getText() == board[2][0].getText() &&
            board[0][2].getText() != "") {
        setWinner(board[0][2]);
        setWinner(board[1][1]);
        setWinner(board[2][0]);
        gameOver = true;
        return;
    }

    if (turns == 9) {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                setTie(board[r][c]);
            }
        }
        gameOver = true;
    }
}
    void setWinner (JButton tile){
        tile.setForeground(green);
        tile.setBackground(gray);
        label.setText(currply + " g'olib!");
    }
    void setTie(JButton tile) {
        tile.setForeground(Color.orange);
        tile.setBackground(Color.gray);
        label.setText("Tie!");
    }
}
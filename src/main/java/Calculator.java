// Import necessary libraries
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener{
    public JButton button;
    public JFrame calculator;
    public JButton[][] buttonRows = new JButton[5][4];
    public JTextField text = new JTextField();

    public Calculator(){

        // Declare variables
        JPanel[] panelRows = new JPanel[6];

        // Create new instance of the calculator gui
        calculator= new JFrame("Solitaire");
        calculator.setTitle("Calculator");
        calculator.setSize(new Dimension(400, 600));
        calculator.setVisible(true);
        calculator.setLayout(new GridLayout(6, 0));

        // Create special panel for text zone
        JPanel textPanel = new JPanel(new GridLayout(0, 1));
        textPanel.setName("row1");
        panelRows[0] = textPanel;

        // Add text window to first panel

        text.setEditable(true);
        text.setFont(new Font("Consolas", Font.PLAIN, 24));
        text.setText("");
        text.setHorizontalAlignment(SwingConstants.RIGHT);
        textPanel.add(text);

        // Add first panel to the frame
        calculator.add(textPanel);

        // Split calculator into rows
        for (int i = 1; i <= 5; i++){
            panelRows[i] = new JPanel(new GridLayout(0, 4));
            panelRows[i].setName("row" + (i + 1));

            // Add buttons to rows
            for (int j = 0; j <= 3; j++){
                buttonRows[i-1][j] = new JButton();
                buttonRows[i-1][j].setFont(new Font("Consolas", Font.PLAIN, 24));
                panelRows[i].add(buttonRows[i-1][j]);

            }

            // Add panel to main frame
            calculator.add(panelRows[i]);
        }

        // Button text variables
        int tempIndex = 0;
        String[] buttonTextList = {"AC", "DEL", "EXIT", "+", "1", "2", "3", "-", "4", "5", "6", "*", "7", "8", "9", "/", ".", "0", "%", "="};

        // Set button text
        for (int i = 0; i <= 4; i++){
            for (int j = 0; j <= 3; j++){
                button = buttonRows[i][j];
                button.setText(buttonTextList[tempIndex]);
                button.addActionListener(this);
                tempIndex++;
            }
        }



        // Revalidate and repaint the gui
        calculator.invalidate();
        calculator.repaint();
        calculator.revalidate();

    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonRows[0][3]) {
            text.setText(text.getText() + "+");
        } else if (e.getSource() == buttonRows[1][0]) {
            text.setText(text.getText() + "1");

        } else if (e.getSource() == buttonRows[1][1]) {
            text.setText(text.getText() + "2");
        } else if (e.getSource() == buttonRows[1][2]) {
            text.setText(text.getText() + "3");
        } else if (e.getSource() == buttonRows[1][3]) {
            text.setText(text.getText() + "-");
        } else if (e.getSource() == buttonRows[2][0]) {
            text.setText(text.getText() + "4");
        } else if (e.getSource() == buttonRows[2][1]) {
            text.setText(text.getText() + "5");
        } else if (e.getSource() == buttonRows[2][2]) {
            text.setText(text.getText() + "6");
        } else if (e.getSource() == buttonRows[2][3]) {
            text.setText(text.getText() + "*");
        } else if (e.getSource() == buttonRows[3][0]) {
            text.setText(text.getText() + "7");
        } else if (e.getSource() == buttonRows[3][1]) {
            text.setText(text.getText() + "8");
        } else if (e.getSource() == buttonRows[3][2]) {
            text.setText(text.getText() + "9");
        } else if (e.getSource() == buttonRows[3][3]) {
            text.setText(text.getText() + "/");
        } else if (e.getSource() == buttonRows[4][0]) {
            text.setText(text.getText() + ".");
        } else if (e.getSource() == buttonRows[4][1]) {
            text.setText(text.getText() + "0");
        } else if (e.getSource() == buttonRows[4][2]) {
            text.setText(text.getText() + "%");
        }
    }

    public static void main(String[] args) {
        //Creates a new Main Menu
       Calculator calc = new Calculator();

    }
}

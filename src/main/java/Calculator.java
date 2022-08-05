// Import necessary libraries
import javax.swing.*;
import java.awt.*;

public class Calculator extends JFrame{
    public static void main(String[] args){

        // Declare variables
        JPanel[] panelRows = new JPanel[6];
        JButton[][] buttonRows = new JButton[5][4];

        // Create new instance of the calculator gui
        Calculator calculator = new Calculator();
        calculator.setTitle("Calculator");
        calculator.setSize(new Dimension(400, 600));
        calculator.setVisible(true);
        calculator.setLayout(new GridLayout(6, 0));

        // Create special panel for text zone
        JPanel textPanel = new JPanel(new GridLayout(0, 1));
        textPanel.setName("row1");
        panelRows[0] = textPanel;

        // Add text window to first panel
        JTextField text = new JTextField();
        text.setEditable(false);
        text.setFont(new Font("Consolas", Font.PLAIN, 24));
        text.setText("Placeholder text");
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
                JButton button = buttonRows[i][j];
                button.setText(buttonTextList[tempIndex]);
                tempIndex++;
            }
        }

        // Revalidate and repaint the gui
        calculator.invalidate();
        calculator.repaint();
        calculator.revalidate();
    }
}

// Import necessary libraries
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener{
    public JButton button;
    public JFrame calculator;
    public JButton[][] buttonRows = new JButton[5][4];
    public JTextField text = new JTextField();

    public static void main(String[] args) {
        // Create new calculator instance
        Calculator calc = new Calculator();
    }

    public Calculator(){

        // Declare variables
        JPanel[] panelRows = new JPanel[6];

        // Create new instance of the calculator gui
        calculator = this;
        calculator.setTitle("Calculator");
        calculator.setSize(new Dimension(400, 600));
        calculator.setVisible(true);
        calculator.setLayout(new GridLayout(6, 0));
        calculator.setDefaultCloseOperation(EXIT_ON_CLOSE);

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
        // Replace Syntax text
        text.setText(text.getText().replace("Syntax Error", ""));

        if (e.getSource() == buttonRows[0][0]){
            text.setText("");
        }
        else if (e.getSource() == buttonRows[0][1]) {
            if (text.getText().length() >= 1){
                text.setText(text.getText().substring(0, text.getText().length()-1));
            }
        }
        else if (e.getSource() == buttonRows[0][2]) {
            // Verify if user actually wants to exit
            Toolkit.getDefaultToolkit().beep();

            int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit confirmation", JOptionPane.YES_NO_CANCEL_OPTION);

            if (option == 0){
                System.exit(0);
            }
        }
        else if (e.getSource() == buttonRows[0][3]) {
            text.setText(text.getText() + "+");
        }
        else if (e.getSource() == buttonRows[1][0]) {
            text.setText(text.getText() + "1");
        }
        else if (e.getSource() == buttonRows[1][1]) {
            text.setText(text.getText() + "2");
        }
        else if (e.getSource() == buttonRows[1][2]) {
            text.setText(text.getText() + "3");
        }
        else if (e.getSource() == buttonRows[1][3]) {
            text.setText(text.getText() + "-");
        }
        else if (e.getSource() == buttonRows[2][0]) {
            text.setText(text.getText() + "4");
        }
        else if (e.getSource() == buttonRows[2][1]) {
            text.setText(text.getText() + "5");
        }
        else if (e.getSource() == buttonRows[2][2]) {
            text.setText(text.getText() + "6");
        }
        else if (e.getSource() == buttonRows[2][3]) {
            text.setText(text.getText() + "*");
        }
        else if (e.getSource() == buttonRows[3][0]) {
            text.setText(text.getText() + "7");
        }
        else if (e.getSource() == buttonRows[3][1]) {
            text.setText(text.getText() + "8");
        }
        else if (e.getSource() == buttonRows[3][2]) {
            text.setText(text.getText() + "9");
        }
        else if (e.getSource() == buttonRows[3][3]) {
            text.setText(text.getText() + "/");
        }
        else if (e.getSource() == buttonRows[4][0]) {
            text.setText(text.getText() + ".");
        }
        else if (e.getSource() == buttonRows[4][1]) {
            text.setText(text.getText() + "0");
        }
        else if (e.getSource() == buttonRows[4][2]) {
            text.setText(text.getText() + "%");
        }
        else if (e.getSource() == buttonRows[4][3]) {
            text.setText(eval(text.getText()));
        }
    }

    public String eval(String string){
        // Check for invalid syntax first

        // Check if string starts and ends with a number
        try{
            Integer.parseInt(String.valueOf(string.charAt(0)));
            Integer.parseInt(String.valueOf(string.charAt(string.length() - 1)));
        }
        catch (Exception e){
            return "Syntax Error";
        }

        // Check for preceding operators because they are syntax errors
        // -- and ++ are valid preceding operators while **, //, *-, %%, etc are not
        for (int i = 0; i < string.length() - 1; i++){
            char character = string.charAt(i);

            if (isNonNumerical(character)){
                // If operator is + or -
                if (character == '+' || character == '-'){
                    // Loop to check for invalid preceding operators
                    for (int j = i + 1; j < string.length(); j++){
                        char character2 = string.charAt(j);
                        if (isNonNumerical(character2)){
                            if (character2 != '+' && character2 != '-'){
                                return "Syntax Error";
                            }
                        }
                        else{
                            break;
                        }
                    }
                }
                // If operator is not + or -
                if (character != '+' && character != '-'){
                    // Loop to check for invalid preceding operators
                    for (int j = i + 1; j < string.length(); j++){
                        char character2 = string.charAt(j);
                        if (isNonNumerical(character2)){
                            return "Syntax Error";
                        }
                        else{
                            break;
                        }
                    }
                }
            }
        }

        // Simplify substrings like "--" into "+"
        String newString = "";

        for (int i = 0; i < string.length(); i++){
            if (string.charAt(i) != '-' && string.charAt(i) != '+'){
                newString += String.valueOf(string.charAt(i));
            }
            else{
                char plusminus = string.charAt(i);
                for (int j = i + 1; j < string.length(); j++){
                    if (string.charAt(j) != '-' && string.charAt(j) != '+'){
                        newString += String.valueOf(plusminus);
                        break;
                    }
                    else {
                        if (plusminus == '-' && string.charAt(j) == '-'){
                            plusminus = '+';
                        }
                        else if (plusminus == '-' && string.charAt(j) == '+'){
                            plusminus = '-';
                        }
                        else if (plusminus == '+' && string.charAt(j) == '-'){
                            plusminus = '-';
                        }
                        else if (plusminus == '+' && string.charAt(j) == '+'){
                            plusminus = '+';
                        }
                        i++;
                    }
                }
            }
        }

        // Perform arithmetic
        String newString1 = newString + "";

        // Calculate * and / first (BEDMAS) then add to a new string variable
        for (int i = 1; i < newString1.length() - 1; i++){
            if (newString1.charAt(i) == '*' || newString1.charAt(i) == '/' || newString1.charAt(i) == '%'){
                char operator = newString1.charAt(i);

                // Find number on the left
                String num1 = "";
                for (int j = i - 1; j >= 0; j--){
                    if (isNonDecimal(newString1.charAt(j))){
                        break;
                    }
                    else{
                        num1 = newString1.charAt(j) + num1;
                    }
                }

                // Find number on the right
                String num2 = "";
                for (int j = i + 1; j < newString1.length(); j++){
                    if (isNonDecimal(newString1.charAt(j))){
                        break;
                    }
                    else{
                        num2 = num2 + newString1.charAt(j);
                        i++;
                    }
                }

                // Calculate and add value to new string
                if (operator == '*'){
                    String value = String.valueOf(Double.parseDouble(num1) * Double.parseDouble(num2));
                    newString1 = newString1.replaceFirst(num1 + "\\*" + num2, value);
                    i = 1;
                }
                else if (operator == '/'){
                    String value = String.valueOf(Double.parseDouble(num1) / Double.parseDouble(num2));
                    newString1 = newString1.replaceFirst(num1 + "/" + num2, value);
                    i = 1;
                }
                else if (operator == '%'){
                    String value = String.valueOf(Double.parseDouble(num1) % Double.parseDouble(num2));
                    newString1 = newString1.replaceFirst(num1 + "%" + num2, value);
                    i = 1;
                }
            }
        }

        // Finish + and - operators
        for (int i = 1; i < newString1.length() - 1; i++){
            if (newString1.charAt(i) == '+' || newString1.charAt(i) == '-'){
                char operator = newString1.charAt(i);

                // Find number on the left
                String num1 = "";
                for (int j = i - 1; j >= 0; j--){
                    if (newString1.charAt(j) != '+' && newString1.charAt(j) != '-'){
                        num1 = String.valueOf(newString1.charAt(j)) + num1;
                    }
                    else{
                        break;
                    }
                }

                // Find number on the right
                String num2 = "";

                for (int j = i + 1; j < newString1.length(); j++){
                    if (newString1.charAt(j) != '+' && newString1.charAt(j) != '-'){
                        num2 = num2 + String.valueOf(newString1.charAt(j));
                    }
                    else{
                        break;
                    }
                }

                // Calculate and add value to string
                if (operator == '+'){
                    String value = String.format("%.5f", Double.parseDouble(num1) + Double.parseDouble(num2));
                    newString1 = newString1.replaceFirst(num1 + "\\+" + num2, String.valueOf(value));
                    i = 1;
                }
                else if (operator == '-'){
                    String value = String.format("%.5f", Double.parseDouble(num1) - Double.parseDouble(num2));
                    newString1 = newString1.replaceFirst(num1 + "-" + num2, String.valueOf(value));
                    i = 1;
                }
            }
        }

        return newString1;
    }

    // Method to determine if a character is not a numerical value
    public boolean isNonNumerical(char character){
        try{
            Integer.parseInt(String.valueOf(character));
            return false;
        }
        catch (Exception e){
            return true;
        }
    }

    // Method to determine if a character is a mathematical operator
    public boolean isNonDecimal(char character){
        if (character == '*' || character == '/' || character == '%' || character == '+' || character == '-'){
            return true;
        }
        else{
            return false;
        }
    }
}

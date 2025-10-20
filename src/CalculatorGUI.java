import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Math;

public class CalculatorGUI implements ActionListener, KeyListener {
    JFrame frame;
    JTextField textField;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[11];
    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, equButton, delButton, clrButton;
    JButton sqrtButton, percentButton, powerButton;
    JPanel panel;

    Font myFont = new Font("Arial", Font.BOLD, 20);

    double num1 = 0, num2 = 0, result = 0;
    char operator;

    public CalculatorGUI() {
        frame = new JFrame("Modern Java Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 580);
        frame.setLayout(null);

        textField = new JTextField();
        textField.setBounds(50, 25, 300, 60);
        textField.setFont(new Font("Consolas", Font.BOLD, 26));
        textField.setEditable(false);
        textField.setBackground(Color.WHITE);
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        textField.addKeyListener(this);

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("Del");
        clrButton = new JButton("Clr");
        sqrtButton = new JButton("√");
        percentButton = new JButton("%");
        powerButton = new JButton("^");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;
        functionButtons[8] = sqrtButton;
        functionButtons[9] = percentButton;
        functionButtons[10] = powerButton;

        for (int i = 0; i < 11; i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setFocusable(false);

            // Orange for operators, grey for others
            if (i <= 5 || i == 8 || i == 9 || i == 10) {
                functionButtons[i].setBackground(new Color(255, 140, 0)); // Orange
                functionButtons[i].setForeground(Color.WHITE);
            } else {
                functionButtons[i].setBackground(Color.DARK_GRAY);
                functionButtons[i].setForeground(Color.GRAY);
            }
        }

        // Blue buttons for DEL and CLR
        delButton.setBackground(new Color(0, 102, 204)); // Bright blue
        delButton.setForeground(Color.WHITE);

        clrButton.setBackground(new Color(0, 102, 204)); // Bright blue
        clrButton.setForeground(Color.WHITE);

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
            numberButtons[i].setBackground(Color.LIGHT_GRAY);
        }

        delButton.setBounds(50, 460, 145, 50);
        clrButton.setBounds(205, 460, 145, 50);

        panel = new JPanel();
        panel.setBounds(50, 100, 300, 350);
        panel.setLayout(new GridLayout(5, 4, 10, 10));

        panel.add(sqrtButton);
        panel.add(percentButton);
        panel.add(powerButton);
        panel.add(divButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);

        frame.add(panel);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(textField);
        frame.getContentPane().setBackground(new Color(30, 30, 30));
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }

        if (e.getSource() == decButton) {
            textField.setText(textField.getText().concat("."));
        }

        if (e.getSource() == addButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '+';
            textField.setText("");
        }
        if (e.getSource() == subButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '-';
            textField.setText("");
        }
        if (e.getSource() == mulButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '*';
            textField.setText("");
        }
        if (e.getSource() == divButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '/';
            textField.setText("");
        }
        if (e.getSource() == powerButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '^';
            textField.setText("");
        }
        if (e.getSource() == sqrtButton) {
            double value = Double.parseDouble(textField.getText());
            textField.setText(String.valueOf(Math.sqrt(value)));
        }
        if (e.getSource() == percentButton) {
            double value = Double.parseDouble(textField.getText());
            textField.setText(String.valueOf(value / 100));
        }

        if (e.getSource() == equButton) {
            num2 = Double.parseDouble(textField.getText());
            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    result = num1 / num2;
                    break;
                case '^':
                    result = Math.pow(num1, num2);
                    break;
            }
            textField.setText(String.valueOf(result));
            num1 = result;
        }

        if (e.getSource() == clrButton) {
            textField.setText("");
        }

        if (e.getSource() == delButton) {
            String text = textField.getText();
            if (text.length() > 0) {
                textField.setText(text.substring(0, text.length() - 1));
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar();
        if (Character.isDigit(key) || key == '.') {
            textField.setText(textField.getText() + key);
        } else if (key == '+' || key == '-' || key == '*' || key == '/' || key == '^') {
            num1 = Double.parseDouble(textField.getText());
            operator = key;
            textField.setText("");
        } else if (key == '\n') {
            num2 = Double.parseDouble(textField.getText());
            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    result = num1 / num2;
                    break;
                case '^':
                    result = Math.pow(num1, num2);
                    break;
            }
            textField.setText(String.valueOf(result));
            num1 = result;
        } else if (key == KeyEvent.VK_BACK_SPACE) {
            String text = textField.getText();
            if (text.length() > 0) {
                textField.setText(text.substring(0, text.length() - 1));
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public static void main(String[] args) {
        new CalculatorGUI();
    }
}

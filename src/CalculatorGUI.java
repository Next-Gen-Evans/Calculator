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
    JPanel panel, topPanel;

    Font myFont = new Font("Poppins", Font.BOLD, 20);

    double num1 = 0, num2 = 0, result = 0;
    char operator;

    public CalculatorGUI() {
        frame = new JFrame("Modern Java Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 600);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(25, 25, 25));

        // Text Field
        textField = new JTextField();
        textField.setBounds(50, 25, 300, 60);
        textField.setFont(new Font("Consolas", Font.BOLD, 26));
        textField.setEditable(false);
        textField.setBackground(new Color(240, 240, 240));
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        textField.setBorder(BorderFactory.createLineBorder(new Color(255, 140, 0), 2));
        textField.addKeyListener(this);

        // Function Buttons
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

        // Style function buttons
        for (int i = 0; i < functionButtons.length; i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setFocusable(false);
            functionButtons[i].setBorder(BorderFactory.createLineBorder(new Color(40, 40, 40)));

            if (i <= 5 || i == 8 || i == 9 || i == 10) {
                functionButtons[i].setBackground(new Color(255, 140, 0)); // Orange
                functionButtons[i].setForeground(Color.WHITE);
            } else {
                functionButtons[i].setBackground(Color.DARK_GRAY);
                functionButtons[i].setForeground(Color.LIGHT_GRAY);
            }
        }

        // Blue buttons for DEL and CLR
        delButton.setBackground(new Color(0, 102, 204));
        clrButton.setBackground(new Color(0, 102, 204));
        delButton.setForeground(Color.WHITE);
        clrButton.setForeground(Color.WHITE);

        // Number Buttons
        for (int i = 0; i < 10; i++) {
            final int index = i;
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
            numberButtons[i].setBackground(new Color(60, 60, 60));
            numberButtons[i].setForeground(Color.WHITE);

            // Hover effect
            numberButtons[i].addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    numberButtons[index].setBackground(new Color(80, 80, 80));
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    numberButtons[index].setBackground(new Color(60, 60, 60));
                }
            });
        }

        // Top Panel for DEL and CLR
        topPanel = new JPanel();
        topPanel.setBounds(50, 95, 300, 50);
        topPanel.setLayout(new GridLayout(1, 2, 10, 10));
        topPanel.setBackground(new Color(25, 25, 25));
        topPanel.add(delButton);
        topPanel.add(clrButton);

        // Main Panel
        panel = new JPanel();
        panel.setBounds(50, 160, 300, 370);
        panel.setLayout(new GridLayout(5, 4, 10, 10));
        panel.setBackground(new Color(25, 25, 25));

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

        frame.add(topPanel);
        frame.add(panel);
        frame.add(textField);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }

        if (e.getSource() == decButton)
            textField.setText(textField.getText().concat("."));
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

        if (e.getSource() == clrButton)
            textField.setText("");
        if (e.getSource() == delButton) {
            String text = textField.getText();
            if (text.length() > 0)
                textField.setText(text.substring(0, text.length() - 1));
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public static void main(String[] args) {
        new CalculatorGUI();
    }
}

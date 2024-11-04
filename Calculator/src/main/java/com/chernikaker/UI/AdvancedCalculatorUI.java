package com.chernikaker.UI;

import com.chernikaker.logic.BigDecimalParser;
import com.chernikaker.logic.Calculator;
import com.chernikaker.logic.Counter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AdvancedCalculatorUI {

    private JFrame frame;
    private JTextField input1;
    private JTextField input2;
    private JTextField input3;
    private JTextField input4;
    private JComboBox<String> operationBox1;
    private JComboBox<String> operationBox2;
    private JComboBox<String> operationBox3;
    private JComboBox<String> roundingComboBox;
    private JButton calculateButton;
    private JTextField resultField;
    private JTextField roundingResultField;

    private final BigDecimalParser p = new BigDecimalParser();
    private final Counter counter = new Counter();

    public AdvancedCalculatorUI() {
        frame = new JFrame("Financial Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1300, 550);
        frame.setResizable(false);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Title label
        JLabel titleLabel = new JLabel("Financial Calculator");
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 48));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setVerticalAlignment(SwingConstants.TOP);
        titleLabel.setForeground(new Color(30, 144, 255));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 18;
        gbc.insets = new Insets(0, 0, 40, 0);
        frame.add(titleLabel, gbc);

        JLabel roundingLabel = new JLabel("Select rounding mode: ");
        roundingLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        roundingLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        roundingLabel.setVerticalAlignment(SwingConstants.TOP);

        JLabel ansLabel = new JLabel("Rounded answer: ");
        ansLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        ansLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        ansLabel.setVerticalAlignment(SwingConstants.TOP);


        // Create input fields
        input1 = createRoundedTextField();
        input2 = createRoundedTextField();
        input3 = createRoundedTextField();
        input4 = createRoundedTextField();

        input1.setPreferredSize(new Dimension(100,40));
        input2.setPreferredSize(new Dimension(100,40));
        input3.setPreferredSize(new Dimension(100,40));
        input4.setPreferredSize(new Dimension(100,40));

        input1.setText("0");
        input2.setText("0");
        input3.setText("0");
        input4.setText("0");

        focusList(input1);
        focusList(input2);
        focusList(input3);
        focusList(input4);

        // Create dropdown for operations
        String[] operations = {"+", "-","*","/"};
        operationBox1 = new JComboBox<>(operations);
        styleComboBox(operationBox1);
        operationBox2 = new JComboBox<>(operations);
        styleComboBox(operationBox2);
        operationBox3 = new JComboBox<>(operations);
        styleComboBox(operationBox3);

        operationBox1.setPreferredSize(new Dimension(50,40));
        operationBox2.setPreferredSize(new Dimension(50,40));
        operationBox3.setPreferredSize(new Dimension(50,40));

        String[] rounding = {"Math", "Bank","Truncation"};
        roundingComboBox = new JComboBox<>(rounding);
        styleComboBox(roundingComboBox);
        roundingComboBox.setPreferredSize(new Dimension(110,40));

        // Create button for calculation
        calculateButton = new JButton("=");
        resultField = createRoundedTextField();
        resultField.setEditable(false); // Make result field non-editable

        roundingResultField = createRoundedTextField();
        roundingResultField.setEditable(false);

        // Set modern button style
        calculateButton.setBackground(new Color(30, 144, 255)); // Dodger Blue
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setFocusPainted(false);


        JLabel openBracketLabel = new JLabel("(");
        JLabel closeBracketLabel = new JLabel(")");
        openBracketLabel.setPreferredSize(new Dimension(10,60));
        openBracketLabel.setFont(new Font("Arial", Font.PLAIN, 48));
        openBracketLabel.setHorizontalAlignment(SwingConstants.CENTER);
        openBracketLabel.setVerticalAlignment(SwingConstants.CENTER);
        openBracketLabel.setForeground(new Color(78, 78, 78));

        closeBracketLabel.setPreferredSize(new Dimension(10,60));
        closeBracketLabel.setFont(new Font("Arial", Font.PLAIN, 48));
        closeBracketLabel.setHorizontalAlignment(SwingConstants.CENTER);
        closeBracketLabel.setVerticalAlignment(SwingConstants.CENTER);
        closeBracketLabel.setForeground(new Color(78, 78, 78));

        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Add components to the frame
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 20, 30, 5);
        frame.add(input1, gbc);

        gbc.insets = new Insets(0, 5, 30, 5);
        gbc.weightx = 0;
        gbc.gridx = 2;
        gbc.gridwidth = 1;
        frame.add(operationBox1, gbc);

        gbc.gridx = 3;
        gbc.gridy = 1;
        frame.add(openBracketLabel, gbc);

        gbc.weightx = 1.0;
        gbc.gridx = 4;
        gbc.gridwidth = 2;
        frame.add(input2, gbc);

        gbc.weightx = 0;
        gbc.gridx = 6;
        gbc.gridwidth = 1;
        frame.add(operationBox2, gbc);

        gbc.weightx = 1.0;
        gbc.gridx = 7;
        gbc.gridwidth = 2;
        frame.add(input3, gbc);

        gbc.weightx = 0.0;
        gbc.gridx = 9;
        gbc.gridwidth = 1;
        frame.add(closeBracketLabel, gbc);

        gbc.gridx = 10;
        frame.add(operationBox3, gbc);

        gbc.weightx = 1.0;
        gbc.gridx = 11;
        gbc.gridwidth = 2;
        frame.add(input4, gbc);

        gbc.weightx = 0.0;
        gbc.gridx = 13;
        gbc.gridwidth = 1;
        frame.add(calculateButton, gbc);

        gbc.insets = new Insets(0, 5, 30, 20);
        gbc.weightx = 1.0;
        gbc.gridx = 14;
        gbc.gridwidth = 3;
        frame.add(resultField, gbc);

        gbc.insets = new Insets(0, 20, 30, 5);
        gbc.weightx = 0;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 6;
        frame.add(roundingLabel, gbc);

        gbc.insets = new Insets(0, 5, 30, 5);
        gbc.weightx = 0.0;
        gbc.gridx = 6;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        frame.add(roundingComboBox, gbc);

        gbc.weightx = 0.0;
        gbc.gridx = 9;
        gbc.gridy = 2;
        gbc.gridwidth = 6;
        frame.add(ansLabel, gbc);

        gbc.insets = new Insets(0, 5, 30, 20);
        gbc.weightx = 1.0;
        gbc.gridx = 15;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        frame.add(roundingResultField, gbc);

        JLabel labelInfo = new JLabel("""
                    <html><div style='text-align: center;'>
                    <b>Allowed number formats</b>:
                    <br>1000.0  &nbsp;1000,0  &nbsp;1 000.0  &nbsp;1 000,0<br>
                    <b>WARNING:</b> no e-notation numbers<br><br>
                     <b>Allowed range of inputs and intermediate calculations</b><br>
                      [-1 000 000 000 000.0000000000; 1 000 000 000 000.0000000000]<br><br>
                      <b>NOTE:</b> Trailing zeros will be removed from the result.<br>
                      Result of every calculation will be rounded up to 10 decimal places</div>
                      </html>""");

        labelInfo.setFont(new Font("Arial", Font.PLAIN, 14));
        labelInfo.setForeground(new Color(163, 163, 163)); // Серый цвет шрифта
        labelInfo.setHorizontalAlignment(JLabel.CENTER);
        labelInfo.setVerticalAlignment(JLabel.BOTTOM);
        // labelInfo.setHorizontalTextPosition(SwingConstants.LEFT);


        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 18;
        gbc.insets = new Insets(30, 10, 0, 0);
        frame.add(labelInfo, gbc);

        JLabel label = new JLabel("Шрубок Анна Владимировна. КТС 3 курс 11 группа, 2024");

        // Изменение стиля JLabel
        label.setFont(new Font("Arial", Font.PLAIN, 18));
        label.setForeground(new Color(150, 150, 150)); // Серый цвет шрифта
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.BOTTOM);
        label.setHorizontalTextPosition(SwingConstants.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 18;
        gbc.insets = new Insets(30, 0, 20, 0);
        frame.add(label, gbc);

        // Add action listener for the button
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<BigDecimal> nums = new ArrayList<>();
                    nums.add(p.parse(input1.getText()));
                    nums.add(p.parse(input2.getText()));
                    nums.add(p.parse(input3.getText()));
                    nums.add(p.parse(input4.getText()));

                    List<String> operations = new ArrayList<>();
                    operations.add((String) operationBox1.getSelectedItem());
                    operations.add((String) operationBox2.getSelectedItem());
                    operations.add((String) operationBox3.getSelectedItem());

                    BigDecimal result = Calculator.calculateExpression(nums, operations);
                    String roundMethod = (String) roundingComboBox.getSelectedItem();
                    BigDecimal roundedResult = Calculator.round(roundMethod, result);
                    // Display the result
                    resultField.setText(p.toString(result));
                    roundingResultField.setText(p.toString(roundedResult));
                }
                catch (ArithmeticException ex){
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Arithmetic Error", JOptionPane.ERROR_MESSAGE);
                }
                catch (IllegalArgumentException ex){
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter valid numbers.\n Allowed formats:\n 1000.0\n1000,0\n1 000.0\n1 000,0", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        roundingComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Получаем выбранное значение
                String selectedValue = (String) roundingComboBox.getSelectedItem();
                String textResult = (String) resultField.getText().trim();
                if(textResult.isEmpty()) return;
                BigDecimal result = p.parse(textResult);
                BigDecimal roundedResult = Calculator.round(selectedValue, result);
                roundingResultField.setText(p.toString(roundedResult));
            }
        });


    }

    public void start(){
        frame.setVisible(true);
    }

    private JTextField createRoundedTextField() {
        JTextField textField = new JTextField(16);
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setBackground(Color.WHITE);
        textField.setForeground(Color.BLACK);
        textField.setPreferredSize(new Dimension(90, 40)); // Increase height
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(255, 255, 255), 3), // Blue border
                BorderFactory.createEmptyBorder(5, 10, 5, 10) // Padding
        ));
        return textField;
    }

    private void styleComboBox(JComboBox<String> comboBox) {
        comboBox.setFont(new Font("Arial", Font.PLAIN, 20)); // Increase font size
        comboBox.setBackground(new Color(30, 144, 255)); // Light blue background
        comboBox.setForeground(Color.WHITE); // White text color
        comboBox.setBorder(BorderFactory.createLineBorder(new Color(30, 144, 255), 2)); // Blue border
        comboBox.setPreferredSize(new Dimension(100, 30));
        comboBox.setRenderer(new DefaultListCellRenderer() {

            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHeight) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHeight);
                label.setForeground(Color.WHITE); // Text color
                if (isSelected) {
                    label.setBackground(new Color(173, 216, 230)); // Light blue for selected
                    label.setOpaque(true); // Make the background visible
                } else {
                    label.setBackground(new Color(30, 144, 255)); // Default background color
                    label.setOpaque(true);
                }
                return label;
            }

        });
        comboBox.setSelectedItem(new Color(30, 144, 255));
    }

    private void focusList(JTextField input){
        input.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }
            @Override
            public void focusLost(FocusEvent e) {
                // Проверяем, пустое ли поле при потере фокуса
                if (input.getText().isEmpty()) {
                    input.setText("0");
                }
            }
        });
    }

}

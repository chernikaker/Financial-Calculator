package com.chernikaker.UI;

import com.chernikaker.logic.BigDecimalParser;
import com.chernikaker.logic.Counter;

import javax.management.InvalidAttributeValueException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

public class CalculatorUI {

        private JFrame frame;
        private JTextField input1;
        private JTextField input2;
        private JComboBox<String> operationBox;
        private JButton calculateButton;
        private JTextField resultField;

        private final BigDecimalParser p = new BigDecimalParser();
        private final Counter counter = new Counter();

        public CalculatorUI() {
            frame = new JFrame("Financial Calculator");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(950, 400);
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
            gbc.gridwidth = 6;
            gbc.insets = new Insets(0, 0, 30, 0);
            frame.add(titleLabel, gbc);

            // Create input fields
            input1 = createRoundedTextField();
            input2 = createRoundedTextField();

            // Create dropdown for operations
            String[] operations = {"+", "-"};
            operationBox = new JComboBox<>(operations);
            styleComboBox(operationBox);

            // Create button for calculation
            calculateButton = new JButton("=");
            resultField = createRoundedTextField();
            resultField.setEditable(false); // Make result field non-editable

            // Set modern button style
            calculateButton.setBackground(new Color(30, 144, 255)); // Dodger Blue
            calculateButton.setForeground(Color.WHITE);
            calculateButton.setFocusPainted(false);

            // Add components to the frame
            gbc.gridwidth = 1;
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.insets = new Insets(5, 10, 5, 10);
            frame.add(input1, gbc);

            gbc.gridx = 1;
            frame.add(operationBox, gbc);

            gbc.gridx = 2;
            frame.add(input2, gbc);

            gbc.gridx = 3;
            gbc.gridwidth = 1;
            frame.add(calculateButton, gbc);

            gbc.gridx = 4;
            gbc.gridwidth = 2;
            frame.add(resultField, gbc);

            JLabel labelInfo = new JLabel("<html>Allowed number formats:<br>1000.0  &nbsp;1000,0  &nbsp;1 000.0  &nbsp;1 000,0</html>");

            labelInfo.setFont(new Font("Arial", Font.PLAIN, 14));
            labelInfo.setForeground(new Color(163, 163, 163)); // Серый цвет шрифта
            labelInfo.setHorizontalAlignment(JLabel.CENTER);
            labelInfo.setVerticalAlignment(JLabel.BOTTOM);
           // labelInfo.setHorizontalTextPosition(SwingConstants.LEFT);

            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth = 6;
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
            gbc.gridy = 3;
            gbc.gridwidth = 6;
            gbc.insets = new Insets(30, 0, 0, 0);
            frame.add(label, gbc);

            // Add action listener for the button
            calculateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        BigDecimal n1 = p.parse(input1.getText());
                        boolean commas1 = p.isComma();
                        boolean spaces1 = p.isSpaces();

                        BigDecimal n2 = p.parse(input2.getText());
                       boolean commas2 = p.isComma();
                       boolean spaces2 = p.isSpaces();
                       if(commas1!=commas2||spaces1!=spaces2){
                           throw new InvalidAttributeValueException();
                       }
                        String operation = (String) operationBox.getSelectedItem();
                        BigDecimal result;
                        // Perform the selected operation
                        if ("+".equals(operation)) {
                            result = counter.add(n1,n2);
                        } else {
                            result = counter.subtract(n1,n2);
                        }

                        // Display the result
                        resultField.setText(p.toString(result));
                    } catch (InvalidAttributeValueException exep){
                        JOptionPane.showMessageDialog(frame, "Numbers must have the same input format", "Input Error", JOptionPane.ERROR_MESSAGE);
                    }
                    catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, "Please enter valid numbers.\n Allowed formats:\n 1000.0\n1000,0\n1 000.0\n1 000,0", "Input Error", JOptionPane.ERROR_MESSAGE);
                    }
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

}

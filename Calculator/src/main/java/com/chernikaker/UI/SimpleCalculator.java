package com.chernikaker.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleCalculator {

    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("Simple Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLayout(new FlowLayout());

        // Create input fields
        JTextField input1 = new JTextField(10);
        JTextField input2 = new JTextField(10);

        // Create dropdown for operations
        String[] operations = {"+", "-"};
        JComboBox<String> operationBox = new JComboBox<>(operations);

        // Create button for calculation
        JButton calculateButton = new JButton("=");
        JTextField resultField = new JTextField(10);
        resultField.setEditable(false); // Make result field non-editable

        // Add components to the frame
        frame.add(input1);
        frame.add(operationBox);
        frame.add(input2);
        frame.add(calculateButton);
        frame.add(new JLabel("="));
        frame.add(resultField);

        // Add action listener for the button
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double num1 = Double.parseDouble(input1.getText());
                    double num2 = Double.parseDouble(input2.getText());
                    String operation = (String) operationBox.getSelectedItem();
                    double result;

                    // Perform the selected operation
                    if ("+".equals(operation)) {
                        result = num1 + num2;
                    } else {
                        result = num1 - num2;
                    }

                    // Display the result
                    resultField.setText(String.valueOf(result));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter valid numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Set frame visibility
        frame.setVisible(true);
    }
}
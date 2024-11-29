package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EmployeeManagementUI extends JFrame {
    private JTextField searchField;
    private JButton searchButton;
    private JButton addButton;
    private JTable employeeTable;
    private DefaultTableModel tableModel;

    public EmployeeManagementUI() {
        setTitle("Employee Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        createGUI();
        setVisible(true);
    }

    private void createGUI() {
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        searchField = new JTextField(20);
        searchButton = new JButton("Search");
        addButton = new JButton("Add");

        topPanel.add(searchField);
        topPanel.add(searchButton);
        topPanel.add(addButton);

        String[] columnNames = {"ID", "Name", "Age", "Position", "Department"};
        Object[][] data = new Object[20][5]; // Placeholder for 20 employees
        for (int i = 0; i < 20; i++) {
            data[i] = new Object[]{i + 1, "Employee " + (i + 1), 25 + i, "Developer", "IT"};
        }

        tableModel = new DefaultTableModel(data, columnNames);
        employeeTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(employeeTable);

        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem editItem = new JMenuItem("Edit");
        JMenuItem deleteItem = new JMenuItem("Delete");
        popupMenu.add(editItem);
        popupMenu.add(deleteItem);

        employeeTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger() && employeeTable.getSelectedRowCount() > 0) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

        editItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = employeeTable.getSelectedRow();
                if (selectedRow != -1) {
                    // Implement your edit logic here
                    JOptionPane.showMessageDialog(EmployeeManagementUI.this, "Edit employee: " + tableModel.getValueAt(selectedRow, 1));
                }
            }
        });

        deleteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = employeeTable.getSelectedRow();
                if (selectedRow != -1) {
                    // Implement your delete logic here
                    // tableModel.removeRow(selectedRow);
                    //get the ID of the selected row
                    int id = (int) tableModel.getValueAt(selectedRow, 0);
                    System.out.println("id = " + id);
                }
            }
        });

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement your add logic here
                JOptionPane.showMessageDialog(EmployeeManagementUI.this, "Add new employee");
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement your search logic here
                String searchText = searchField.getText();
                JOptionPane.showMessageDialog(EmployeeManagementUI.this, "Search for: " + searchText);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EmployeeManagementUI();
            }
        });
    }
}
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class display extends JFrame {
    final int WINDOW_HEIGHT = 800,
            WINDOW_WIDTH = 800;
    final String[] headings = { "ID", "Name", "Manufacturer", "Price PKR" };

    JPanel panel;
    JTable table;
    JScrollPane scroll;
    JTextArea medicine_info;

    BST<medicine> medicines;
    BST<CompositionList> components;
    medicine currentMedicine;
    CompositionList current_component;
    String[][] recommendations;

    display(BST<medicine> medicines, BST<CompositionList> components) {
        super();

        this.medicines = medicines;
        this.components = components;
        current_component = new CompositionList("none");
        recommendations = new String[0][0];

        panel = new JPanel();

        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLayout(null);

        panel.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        panel.setLayout(null);

        show_details();
        add_buttons();

        panel.setVisible(true);
        add(panel);
        setVisible(true);
    }

    void show_details() {
        table = new JTable(recommendations, headings);
        table.setBounds(50, 50, 800, 800);
        table.setRowHeight(50);
        table.setDefaultEditor(Object.class, null);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(230);
        table.getColumnModel().getColumn(2).setPreferredWidth(250);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);

        scroll = new JScrollPane(table);
        scroll.setBounds(35, 450, WINDOW_WIDTH - 100, 300);
        panel.add(scroll);
    }

    void add_buttons() {

        JTextField search_bar = new JTextField();
        search_bar.setBounds(35, 50, 400, 20);
        panel.add(search_bar);
        JButton SearchButton = new JButton("Search");
        SearchButton.setBounds(450, 50, 100, 20);
        panel.add(SearchButton);

        SearchButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                medicine m = medicines.find(new medicine(search_bar.getText()));
                if (m != null) {
                    currentMedicine = m;
                    current_component = components.find(new CompositionList(m.component1 + " " + m.component2));
                    medicine_info.setText(m.toString());
                    ParseRecommendations(current_component.medicines);
                    panel.remove(scroll);
                    show_details();
                    panel.repaint();
                } else {
                    medicine_info.setText("Medicine not Found");
                }
            }

        });

        medicine_info = new JTextArea();
        medicine_info.setBounds(35, 100, WINDOW_WIDTH / 2, 250);
        panel.add(medicine_info);

        JButton buy = new JButton("Purchase Selected Medicine");
        buy.setBounds(500, 225, 200, 30);
        panel.add(buy);
        buy.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (currentMedicine.stock != 0)
                    currentMedicine.stock--;
                medicine_info.setText(currentMedicine.toString());
            }
        });

        JButton getSelected = new JButton("Select Medicine");
        getSelected.setBounds(WINDOW_WIDTH / 2 - 100, 390, 200, 30);
        panel.add(getSelected);

        getSelected.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int medicine_index = table.getSelectedRow();
                medicine m = medicines.find(new medicine(table.getModel().getValueAt(medicine_index, 1) + ""));
                search_bar.setText(m.name);
                medicine_info.setText(m.toString());
                currentMedicine = m;
            }
        });

    }

    void ParseRecommendations(SinglyLL<medicine> list) {
        recommendations = new String[list.size()][4];
        Node<medicine> temp = list.head;
        for (int i = 0; i < list.size(); i++) {
            medicine m = temp.data;
            recommendations[i][0] = m.location;
            recommendations[i][1] = m.name;
            recommendations[i][2] = m.manufacturer;
            recommendations[i][3] = m.price + "";
            temp = temp.next;
        }
    }

}

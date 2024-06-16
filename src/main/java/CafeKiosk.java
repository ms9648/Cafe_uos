package main.java;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class CafeKiosk extends JFrame {
    private DefaultTableModel tableModel;

    public CafeKiosk() {
        setTitle("CAFE UOS");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 800); // 창 크기를 더 크게 설정
        setLayout(new BorderLayout());

        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout());
        JLabel headerLabel = new JLabel("CAFE UOS", JLabel.CENTER);
        headerLabel.setFont(new Font("Serif", Font.BOLD, 24));

        // 상대 경로를 사용하여 이미지 로드
        try {
            ImageIcon originalIcon = new ImageIcon(getClass().getResource("/image/coldbrew.png"));
            Image originalImage = originalIcon.getImage();
            Image resizedImage = originalImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(resizedImage);
            headerLabel.setIcon(resizedIcon);
        } catch (Exception e) {
            e.printStackTrace();
        }

        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Main panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;

        // Tabbed pane for menu
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setUI(new javax.swing.plaf.metal.MetalTabbedPaneUI() {
            @Override
            protected void installDefaults() {
                super.installDefaults();
                tabAreaInsets.left = 10;
            }
        });
        tabbedPane.setBackground(Color.WHITE);
        tabbedPane.setForeground(Color.RED);

        UIManager.put("TabbedPane.selected", Color.RED);

        tabbedPane.addTab("HOT", createMenuPanel(new String[][]{
                {"아메리카노 - 1500원", "/image/hot_americano.jpg"},
                {"카페라떼 - 2500원", "/image/hot_cafelatte.jpeg"},
                {"카푸치노 - 3000원", "/image/hot_cappuccino.jpg"},
                {"카페모카 - 3500원", "/image/hot_cafemocha.png"},
                {"에스프레소 - 2000원", "/image/espresso.jpeg"},
                {"마키아또 - 3000원", "/image/hot_macchiato.jpg"},
                {"플랫 화이트 - 3500원", "/image/hot_flatwhite.jpg"},
                {"다방 커피 - 2000원", "/image/hot_dabangcoffee.jpeg"}
        }));
        tabbedPane.addTab("ICE", createMenuPanel(new String[][]{
                {"아이스 아메리카노 - 2000원", "/image/ice_americano.jpg"},
                {"아이스 카페라떼 - 3000원", "/image/ice_cafelatte.jpg"},
                {"아이스 카푸치노 - 3500원", "/image/ice_cappuccino.png"},
                {"아이스 카페모카 - 4000원", "/image/ice_cafemocha.png"},
                {"아이스 다방 커피 - 2500원", "/image/ice_dabangcoffee.png"},
                {"아이스 카라멜 마끼아또 - 3500원", "/image/ice_macchiato.jpg"},
                {"아이스 플랫 화이트 - 4000원", "/image/ice_flatwhite.jpg"},
                {"콜드브루 - 3500원", "/image/coldbrew.png"}
        }));
        tabbedPane.addTab("NON-COFFEE", createMenuPanel(new String[][]{
                {"핫초코 - 3000원", "/image/hot_choco.jpg"},
                {"딸기 바나나 - 3500원", "/image/strawberry_banana.jpg"},
                {"아이스티 - 2500원", "/image/icetea.png"},
                {"블루베리 스무디 - 3500원", "/image/blueberry_smoothie.jpg"},
                {"레모네이드 - 3500원", "/image/lemonade.jpg"},
                {"요거트 스무디 - 3500원", "/image/yogurt_smoothie.png"},
                {"초코케이크 - 5000원", "/image/choco_cake.png"},
                {"치즈케이크 - 5500원", "/image/cheese_cake.jpg"}
        }));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0.7; // 메뉴 패널을 더 크게 설정
        mainPanel.add(tabbedPane, gbc);

        // Order info panel
        JPanel orderPanel = new JPanel(new BorderLayout());
        String[] columnNames = {"메뉴", "가격", "수량"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable orderTable = new JTable(tableModel);
        orderPanel.add(new JScrollPane(orderTable), BorderLayout.CENTER);

        // Buttons panel
        JPanel buttonsPanel = new JPanel();
        JButton clearButton = new JButton("입력 초기화");
        JButton calculateButton = new JButton("계산하기");

        clearButton.addActionListener(e -> tableModel.setRowCount(0));
        calculateButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "계산 완료"));

        buttonsPanel.add(clearButton);
        buttonsPanel.add(calculateButton);

        orderPanel.add(buttonsPanel, BorderLayout.SOUTH);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0.3; // 오른쪽 정보 패널을 더 작게 설정
        mainPanel.add(orderPanel, gbc);

        add(mainPanel, BorderLayout.CENTER);

        // Footer
        JPanel footerPanel = new JPanel();
        JLabel footerLabel = new JLabel("객체지향프로그래밍및실습 2분반 김민서, 김동민", JLabel.CENTER);
        footerPanel.add(footerLabel);
        add(footerPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel createMenuPanel(String[][] menuItems) {
        JPanel menuPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        for (String[] item : menuItems) {
            JButton menuButton = new JButton("<html>" + item[0].replace(" - ", "<br>") + "</html>");
            menuButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 12)); // 귀여운 글씨체 설정
            menuButton.setBackground(Color.WHITE); // 버튼 배경색을 하얀색으로 설정
            menuButton.setOpaque(true);
            menuButton.setBorder((Border) new RoundedBorder(15)); // 둥근 테두리 설정

            try {
                URL imageUrl = getClass().getResource(item[1]);
                ImageIcon originalIcon = new ImageIcon(imageUrl);
                Image originalImage = originalIcon.getImage();
                Image resizedImage = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                ImageIcon resizedIcon = new ImageIcon(resizedImage);
                menuButton.setIcon(resizedIcon);
                menuButton.setVerticalTextPosition(SwingConstants.BOTTOM);
                menuButton.setHorizontalTextPosition(SwingConstants.CENTER);
                menuButton.setIconTextGap(10); // 이미지와 텍스트 간의 간격 설정
            } catch (Exception e) {
                e.printStackTrace();
            }

            menuButton.addActionListener(new MenuButtonListener(item[0], item[1]));
            menuPanel.add(menuButton);
        }

        return menuPanel;
    }

    private class MenuButtonListener implements ActionListener {
        private String itemName;
        private String itemPrice;
        private String itemImage;

        public MenuButtonListener(String itemName, String itemImage) {
            this.itemName = itemName.split(" - ")[0];
            this.itemPrice = itemName.split(" - ")[1];
            this.itemImage = itemImage;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JDialog dialog = new JDialog(CafeKiosk.this, "메뉴 정보", true);
            dialog.setSize(300, 400);
            dialog.setLayout(new BorderLayout());

            JPanel infoPanel = new JPanel(new BorderLayout());
            JLabel nameLabel = new JLabel(itemName);
            JLabel priceLabel = new JLabel(itemPrice);
            JLabel imageLabel = new JLabel();

            try {
                URL imageUrl = getClass().getResource(itemImage);
                ImageIcon originalIcon = new ImageIcon(imageUrl);
                Image originalImage = originalIcon.getImage();
                Image resizedImage = originalImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                ImageIcon resizedIcon = new ImageIcon(resizedImage);
                imageLabel.setIcon(resizedIcon);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            infoPanel.add(nameLabel, BorderLayout.NORTH);
            infoPanel.add(imageLabel, BorderLayout.CENTER);
            infoPanel.add(priceLabel, BorderLayout.SOUTH);

            JPanel bottomPanel = new JPanel();
            JLabel quantityLabel = new JLabel("수량: ");
            JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
            JButton confirmButton = new JButton("확인");

            confirmButton.addActionListener(ae -> {
                int quantity = (int) quantitySpinner.getValue();
                tableModel.addRow(new Object[]{itemName, itemPrice, quantity});
                dialog.dispose();
            });

            bottomPanel.add(quantityLabel);
            bottomPanel.add(quantitySpinner);
            bottomPanel.add(confirmButton);

            dialog.add(infoPanel, BorderLayout.CENTER);
            dialog.add(bottomPanel, BorderLayout.SOUTH);

            dialog.setVisible(true);
        }
    }

    private class RoundedBorder implements Border {
        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(radius + 1, radius + 1, radius + 2, radius);
        }

        public boolean isBorderOpaque() {
            return true;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CafeKiosk::new);
    }
}

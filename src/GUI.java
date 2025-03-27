import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import Figures.*;

public class GUI {
    private static final int BOARD_SIZE = 8;
    private static final Color LIGHT_COLOR = new Color(235, 235, 208); // #EBEBD0
    private static final Color DARK_COLOR = new Color(119, 148, 85);  // #779455
    private static final Color PRESSED_COLOR_LIGHT = new Color(245, 246, 130);
    private static final Color PRESSED_COLOR_DARK = new Color(185, 202, 67);

    private JFrame frame;
    private JPanel boardPanel;
    private JButton[][] buttons = new JButton[BOARD_SIZE][BOARD_SIZE];
    private Map<String, ImageIcon> pieceImages = new HashMap<>();

    public GUI() {
        frame = new JFrame("Chess Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        loadPieceImages();

        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Chess");
        JMenuItem startGame = new JMenuItem("Начать игру");
        JMenuItem resetGame = new JMenuItem("Сбросить игру");
        JMenuItem changeColor = new JMenuItem("Поменять цвет фигур");

        gameMenu.add(startGame);
        gameMenu.add(resetGame);
        gameMenu.add(changeColor);
        menuBar.add(gameMenu);
        frame.setJMenuBar(menuBar);

        boardPanel = new JPanel(new GridLayout(BOARD_SIZE, BOARD_SIZE)) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(800, 800);
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                revalidate();
                updatePieceIcons();
            }
        };

        initializeBoard();

        frame.add(boardPanel, BorderLayout.CENTER);
        frame.pack();
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void initializeBoard() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                int finalRow = row;
                int finalCol = col;
                JButton button = new JButton();
                button.setOpaque(true);
                button.setBorderPainted(false);
                button.setBackground((row + col) % 2 == 0 ? LIGHT_COLOR : DARK_COLOR);

                button.addActionListener(new ActionListener() {
                    private Color originalColor = button.getBackground();

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        button.setBackground(originalColor == LIGHT_COLOR ? PRESSED_COLOR_LIGHT : PRESSED_COLOR_DARK);
//                        Timer timer = new Timer(200, new ActionListener() {
//                            @Override
//                            public void actionPerformed(ActionEvent evt) {
//                                button.setBackground(originalColor);
//                            }
//                        });
//                        timer.setRepeats(false);
//                        timer.start();
                    }
                });

                buttons[row][col] = button;
                boardPanel.add(button);
            }
        }
    }

    private void loadPieceImages() {
        String[] pieces = {"p", "r", "n", "b", "q", "k"};
        String[] colors = {"w", "b"};
        for (String color : colors) {
            for (String piece : pieces) {
                String path = "images/" + color + piece + ".png";
                pieceImages.put(color + piece, new ImageIcon(path));
            }
        }
    }

    private void updatePieceIcons() {
        int buttonSize = Math.min(boardPanel.getWidth() / BOARD_SIZE, boardPanel.getHeight() / BOARD_SIZE);
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                JButton button = buttons[row][col];
                String pieceKey = getPieceAt(row, col);
                if (pieceImages.containsKey(pieceKey)) {
                    ImageIcon icon = pieceImages.get(pieceKey);
                    Image img = icon.getImage().getScaledInstance(buttonSize, buttonSize, Image.SCALE_AREA_AVERAGING);
                    button.setIcon(new ImageIcon(img));
                } else {
                    button.setIcon(null);
                }
            }
        }
    }

    private String getPieceAt(int row, int col) {
        // Заглушка: Здесь можно вставить логику шахматной партии
        if (row == 1) return "bp";
        if (row == 6) return "wp";
        return "";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUI::new);
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import Figures.*;

public class GUI {
    private static final int BOARD_SIZE = 8;
    private static final Color LIGHT_COLOR = new Color(235, 235, 208); // #EBEBD0
    private static final Color DARK_COLOR = new Color(119, 148, 85);  // #779455
    private static final Color SELECTED_COLOR = new Color(245, 246, 130);
    private static final Dimension MIN_BOARD_SIZE = new Dimension(400, 400);
    private static final Dimension MAX_BOARD_SIZE = new Dimension(800, 800);

    private JFrame frame;
    private JPanel boardPanel;
    private JButton[][] buttons = new JButton[BOARD_SIZE][BOARD_SIZE];
    private Map<String, ImageIcon> pieceImages = new HashMap<>();
    private JButton selectedButton = null;
    int prevX = -1, prevY = -1;    // предыдущие координаты
    int currX = -1, currY = -1;

    public GUI() {
        frame = new JFrame("Chess");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        loadPieceImages();

        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Меню");
        JMenuItem startGame = new JMenuItem("Начать игру");
        JMenuItem resetGame = new JMenuItem("Сбросить игру");
        JMenuItem changeColor = new JMenuItem("Поменять цвет фигур");

        resetGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initializeBoard();
                Board.initializeBoard("White");
            }
        });

        gameMenu.add(startGame);
        gameMenu.add(resetGame);
        gameMenu.add(changeColor);
        menuBar.add(gameMenu);
        frame.setJMenuBar(menuBar);


        frame.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                int size = Math.min(boardPanel.getWidth(), boardPanel.getHeight()) / BOARD_SIZE;
                size = Math.max(size, MIN_BOARD_SIZE.width / BOARD_SIZE);
                size = Math.min(size, MAX_BOARD_SIZE.width / BOARD_SIZE);

                for (int row = 0; row < BOARD_SIZE; row++) {
                    for (int col = 0; col < BOARD_SIZE; col++) {
                        buttons[row][col].setBounds(col * size, row * size, size, size);
                    }
                }
            }

            @Override
            public void componentMoved(ComponentEvent e) {

            }

            @Override
            public void componentShown(ComponentEvent e) {

            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }
        });

        boardPanel = new JPanel(null) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(600, 600);
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                revalidate();
                updatePieceIcons();
            }
        };

        initializeBoard();
        Board.initializeBoard("White");

        frame.add(boardPanel, BorderLayout.CENTER);
        frame.pack();
        frame.setSize(755, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void initializeBoard() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                JButton button = new JButton();
                button.setOpaque(true);
                button.setBorderPainted(false);
                button.setBackground((row + col) % 2 == 0 ? LIGHT_COLOR : DARK_COLOR);
                button.setBounds(col * 150, row * 150, 150, 150);

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (selectedButton != null) {
                            selectedButton.setBackground(
                                    (prevY + prevX) % 2 == 0 ? LIGHT_COLOR : DARK_COLOR
                            );
                        }

                        for (int row = 0; row < 8; row++) {
                            for (int col = 0; col < 8; col++) {
                                if (buttons[row][col] == button) {
                                    currX = row;
                                    currY = col;
                                    break;
                                }
                            }
                        }

                        if (button.getIcon() != null && selectedButton == null) {
                            selectedButton = button;
                            button.setBackground(SELECTED_COLOR);
                            prevX = currX;
                            prevY = currY;
                        }
                        else if (selectedButton != null) {
                            if (Board.move(Board.board[prevX][prevY], currX, currY)) {
                                System.out.println("Фигура перемещена");

                                if (buttons[currX][currY].getIcon() != null) {
                                    System.out.println("Фигура атакована");
                                    buttons[currX][currY].setIcon(null);
                                }

                                buttons[currX][currY].setIcon(selectedButton.getIcon());
                                selectedButton.setIcon(null);

                                buttons[currX][currY].revalidate();
                                buttons[currX][currY].repaint();
                                selectedButton.revalidate();
                                selectedButton.repaint();
                            } else {
                                System.out.println("Ошибка: Board.move() не позволил ход");
                            }

                            selectedButton = null; // Сбрасываем выделение
                        }


                    }
                });

                buttons[row][col] = button;
                boardPanel.add(button);
            }
        }
//        resizeBoard();
    }

//    private void resizeBoard() {
//        int size = Math.min(boardPanel.getWidth(), boardPanel.getHeight()) / BOARD_SIZE;
//        size = Math.max(size, MIN_BOARD_SIZE.width / BOARD_SIZE);
//        size = Math.min(size, MAX_BOARD_SIZE.width / BOARD_SIZE);
//
//        for (int row = 0; row < BOARD_SIZE; row++) {
//            for (int col = 0; col < BOARD_SIZE; col++) {
//                buttons[row][col].setBounds(col * size, row * size, size, size);
//            }
//        }
//    }

    private void loadPieceImages() {
        String[] pieces = {"p", "r", "n", "b", "q", "k"};
        String[] colors = {"w", "b"};
        for (String color : colors) {
            for (String piece : pieces) {
                String path = "images/" + color + piece + ".png";
                ImageIcon icon = new ImageIcon(path);
                Image scaledImage = icon.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
                pieceImages.put(color + piece, new ImageIcon(scaledImage));
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
                    Image img = icon.getImage().getScaledInstance(buttonSize, buttonSize, Image.SCALE_SMOOTH);
                    button.setIcon(new ImageIcon(img));
                } else {
                    button.setIcon(null);
                }
            }
        }
    }

    private String getPieceAt(int row, int col) {
        if (Board.board[row][col] != null) {
            return (Objects.equals(Board.board[row][col].color, "White") ? "w" : "b") +
                    Board.board[row][col].figureLetter.toLowerCase();
        }
        return "";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUI::new);
    }
}

package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sys.function.PngMake;

public class LevelFrame extends JFrame implements ActionListener{
    private Button levelButton[];
    private String levelName[] = {"Easy" , "Normal", "Hard", "Exit"};
    {
        levelButton = new Button[levelName.length];
        for (int i = 0; i < levelButton.length; i++) {
            levelButton[i] = new Button(levelName[i]);
            levelButton[i].setFont(new Font("Arial", Font.PLAIN, 20));
        }
    }

    public LevelFrame(String title) {
        super(title);

        JPanel mainPane = new JPanel();
        mainPane.setLayout(new BorderLayout());
        mainPane.setOpaque(false);

        mainPane.add(setTitle(), BorderLayout.NORTH);
        mainPane.add(new JLabel(PngMake.Make(100, 100, "./lib/BackMines.png")), BorderLayout.CENTER);
        mainPane.add(setButton(),BorderLayout.SOUTH);

        getContentPane().add(mainPane);
        setSize(new Dimension(400,400));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    private JPanel setTitle() {
        JPanel panel = new JPanel();
        JLabel titleLabel = new JLabel("Minesweeper");
        
        titleLabel.setFont(new Font("Arial", Font.BOLD, 25));
        panel.add(titleLabel);
        
        return panel;
    }

    private JPanel setButton() {
        JPanel panel = new JPanel();

        for (int i = 0; i < levelButton.length; i++) {
            levelButton[i].addActionListener(this);
            panel.add(levelButton[i]);
        }

        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == levelButton[0]) {
            new PlayFrame(levelName[0], 12, 20);
        } else if (e.getSource() == levelButton[1]) {
            new PlayFrame(levelName[1], 15, 35);
        } else if (e.getSource() == levelButton[2]) {
            new PlayFrame(levelName[2], 20, 80);
        } else if (e.getSource() == levelButton[3]) {
            System.exit(0);
        } 
        this.dispose();
    }
}
package gui;

import sys.function.BorderMake;
import sys.function.PngMake;
import sys.map.MapManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PlayFrame extends JFrame implements ActionListener {
    private int TIMER = 0;
    protected boolean TimeFlg = true;
    protected JLabel timeLabel = new JLabel();
    private JButton resetBtn = new JButton();
    private sys.map.MapManager manager;
    private final String TIMER_PNG = "./lib/time.png";
    private final String FLG_PNG = "./lib/FLG.png";
    private final String title;
    private int width;
    private int bomb;

    public PlayFrame(String title,int width, int bomb) {
        super(title);
        this.title = title;
        this.width = width;
        this.bomb = bomb;

        manager = new MapManager(width, bomb, this);
    
        JPanel mainPane = new JPanel();
        mainPane.setLayout(new BorderLayout());
        mainPane.add(setOrder(), BorderLayout.NORTH);
        mainPane.add(manager.makeMap(), BorderLayout.CENTER);
        
        getContentPane().add(mainPane);
        setSize(new Dimension(600,600));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        play();
    }

    private void play() {
        
        new Thread(new Runnable() {
            public void run() {
                boolean flg = false;
                while (TimeFlg) {
                    timeLabel.setText(String.valueOf(TIMER));
                    try {
                        if (manager.isClear()) {
                            flg = true;
                            break;
                        }
                        TIMER++;
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                resetBtn.setEnabled(false);
                if (flg == true) {
                    flg = GameSet("GameClear", "GameClear!\nClear Time: " + TIMER + "s\nGo to Home?");
                } else {
                    flg = GameSet("GameOver", "Game Over...\nGo to Home?");
                }

                if (flg == true) {
                    ReSet();
                } else {
                    System.exit(0);
                }
            }
        }).start(); 
    }

    private JPanel setOrder() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel timePane = new JPanel();
        {
            JLabel timePnglabel = new JLabel();
            timePnglabel.setIcon(PngMake.Make(50, 50, TIMER_PNG));
            timeLabel.setFont(new Font("Arial", Font.BOLD, 25));
            timePane.add(timePnglabel);
            timePane.add(timeLabel);
        }

        JPanel resetPane = new JPanel();
        resetBtn.setIcon(PngMake.Make(50, 50, "./lib/Reset.png"));
        resetBtn.setPreferredSize(new Dimension(50, 50));
        resetBtn.setBackground(Color.lightGray);
        resetBtn.addActionListener(this);
        resetBtn.setBorder(BorderMake.RaisedBorder());
        resetPane.add(resetBtn);
            
        JPanel flgPane = new JPanel();
        {
            JLabel flgPngLabel = new JLabel();
            flgPngLabel.setIcon(PngMake.Make(50, 50, FLG_PNG));
            JLabel flgLabel = manager.getFlgLabel();
            flgLabel.setFont(new Font("Arial", Font.BOLD, 25));
            flgPane.add(flgPngLabel);
            flgPane.add(flgLabel);
        }

        panel.add(flgPane, BorderLayout.WEST);
        panel.add(resetPane, BorderLayout.CENTER);
        panel.add(timePane, BorderLayout.EAST);
        panel.setBorder(BorderMake.lowBorder());
        
        return panel;
    }

    public boolean GameSet(String title, String msg) {
        TimeFlg = false;
        int res = JOptionPane.showConfirmDialog(null, msg, title, JOptionPane.YES_NO_OPTION);
        if (res == JOptionPane.YES_OPTION) {
            return true;
        } else {
            return false;
        }
    }

    public void setTimeFlg(boolean outFlg) {
        this.TimeFlg = outFlg;
    }

    private void Restart() {
        new PlayFrame(title, width, bomb);
        this.dispose();
    }

    private void ReSet() {
        new LevelFrame("Minesweeper");
        this.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetBtn) {
            Restart();
        }
    }
}


import javax.swing.*;
import java.awt.event.*;

public class Window implements Runnable{

    JFrame frame;
    Box[][] boxes;

    @Override
    public void run()
    {
        initFrame();
        initBoxes();
        initTimer();
    }

    private void initTimer() {
        TimerListener t1 = new TimerListener();
        Timer timer = new Timer(Config.SLEEP, t1);
        timer.start();
    }

    private void initBoxes() {
        boxes = new Box[Config.WIDTH][Config.HEIGHT];
        for (int x = 0; x < Config.WIDTH; x++) {
            for (int y = 0; y < Config.HEIGHT; y++) {
                boxes[x][y] = new Box(x,y);
                frame.add(boxes[x][y]);
            }
        }
        for (int x = 0; x < Config.WIDTH; x++)
            for (int y = 0; y < Config.HEIGHT; y++)
                for(int sx = -1 ; sx <= +1 ; sx++)
                    for (int sy = -1; sy <= +1 ; sy++)
                        if ((sx == 0 && Math.abs(sy) == 1) || (sy == 0 && Math.abs(sx) == 1))
                            boxes[x][y].cell.nearAdd(boxes[(x +  sx + Config.WIDTH) % Config.WIDTH][(y + sy + Config.HEIGHT) % Config.HEIGHT].cell); // может упасть все если придем к координатам 0


        for (int i = 0; i < 5; i++) {
            boxes[i][0].cell.status = CellStatus.Live;
            boxes[i][0].setColor();
        }

    }

    private void initFrame() {
        frame = new JFrame("Cellular Automaton");
        frame.getContentPane().setLayout(null);
        frame.setSize(Config.SIZE * Config.WIDTH, Config.SIZE * Config.HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private class TimerListener implements ActionListener{
        boolean flop = false;
        @Override
        public void actionPerformed(ActionEvent e) {
            flop = !flop;

            for (int x = 0; x < Config.WIDTH; x++) {
                for (int y = 0; y < Config.HEIGHT; y++) {
                    if(flop)
                        boxes[x][y].step1();
                    else
                        boxes[x][y].step2();
                }
            }
        }
    }
}

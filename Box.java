import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//
public class Box extends JPanel {
    Cell cell;
    public Box(int x, int y){
        super();// call parent constructor
        cell = new Cell();
        setBounds(x * Config.SIZE, y * Config.SIZE, Config.SIZE, Config.SIZE);
        setBackground(Color.BLACK);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                cell.turn();
            }
        });
    }

    public void setColor(){
        setBackground(Config.getColor(cell.status));
    }
    public void step1(){
        cell.preparatoryStep1();
        setColor();
    }
    public void step2(){
        cell.preparatoryStep2();
        setColor();
    }

}

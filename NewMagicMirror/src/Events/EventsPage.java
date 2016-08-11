package Events;

import com.jfoenix.controls.JFXButton;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import static com.jfoenix.controls.JFXButton.ButtonType.RAISED;
import static java.awt.Color.WHITE;
import static java.awt.SystemColor.text;
import static javafx.scene.paint.Color.rgb;

/**
 * Created by Burhan N on 7/17/2016.
 */
public class EventsPage {
    private JScrollPane scrollPane;
    private JList jList;
    private JPanel jPanels = new JPanel();

    public EventsPage(){
        ParsingJson parsed = new ParsingJson();
        Events.Event[] list = parsed.getGson();
        System.out.println(list.length);

        jPanels.setLayout(new GridLayout(0,1));
        for(int i = 0; i < list.length; i++) {
            JPanel jPanel = new JPanel();
            jPanel.setLayout(new GridLayout(4,1));
            jPanel.add(new JLabel(list[i].getTitle()));
            jPanel.add(new JLabel(list[i].getLocation()));
            jPanel.add(new JLabel(list[i].getFormattedStart()));
            jPanel.add(new JLabel(list[i].getDescription()));
            jPanel.setBorder(BorderFactory.createLineBorder(Color.black));
            jPanels.add(jPanel);
        }


//        Adding Events to one of the views
//        jList = new JList(list);
//        scrollPane = new JScrollPane(jList);
        scrollPane = new JScrollPane(jPanels);
        jPanels.setAutoscrolls(true);

        scrollPane.setBounds(5,5,30,100);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
        scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0,0));
    }

    public JScrollPane getScrollPane(){
        return scrollPane;
    }

    public JList getList(){
        return jList;
    }

    public JPanel getjPanels(){
        return jPanels;
    }
}

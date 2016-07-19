package Events;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Burhan N on 7/17/2016.
 */
public class EventsPage {
    private Events.Event[] list;
    private JScrollPane scrollPane;
    private JList jList;
    private JPanel jPanel;

    public EventsPage(){
        jPanel = new JPanel();
        ParsingJson parsed = new ParsingJson();
        list = parsed.getGson();

        // Adding Events to one of the views
        jList = new JList(list);
        scrollPane = new JScrollPane(jList);
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
}


import Events.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

public class Driver {
	public static void main(String args[]) throws Exception {
		//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		final ScrollingPage datePage = new ScrollingPage();
		EventsPage eventsPage = new EventsPage();


		DateFormat dateFormat = new SimpleDateFormat("EEEEE, MMMMM dd yyyy");
		DateFormat timeFormat = new SimpleDateFormat("h:mm a");
		Date date = new Date();
		System.out.println(dateFormat.format(date) + "\n" + timeFormat.format(date));

		datePage.setText("<html>" + dateFormat.format(date) +  "<br>" + timeFormat.format(date) + "</html>");
		datePage.setColor(Color.BLUE);
		SwingUtilities.invokeAndWait(new Runnable() {
			@Override
			public void run() {
				final JFrame jFrame = new JFrame() {
					{
						final PanelSlider<JFrame> slider = new PanelSlider<JFrame>(this);
						final JPanel jPanel = slider.getBasePanel();

						slider.addComponent(datePage);
						slider.addComponent(eventsPage.getScrollPane()); // Events Page
						slider.addComponent(new ScrollingPage());
						slider.addComponent(new ScrollingPage());

						MouseListener mouseListener = new MouseAdapter() {
							public void mouseClicked(MouseEvent mouseEvent) {
								JList theList = (JList) mouseEvent.getSource();
								if (mouseEvent.getClickCount() == 2) {
									int index = theList.locationToIndex(mouseEvent.getPoint());
									if (index >= 0) {
										Events.Event o = (Events.Event) theList.getModel().getElementAt(index);
										System.out.println("Double-clicked on: " + o.getFormattedStart() + "\nLocation: "
										+ o.getLocation() + "\nDescription: " + o.getDescription());
									}
								}
							}
						};
						eventsPage.getList().addMouseListener(mouseListener);

						getContentPane().add(jPanel);
						setSize(500, 500);
						setLocationRelativeTo(null);
						setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						setVisible(true);
					}
				};
			}
		});
	}
}

import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Driver {

	public static void main(String args[]) throws Exception {
		final ScrollingPage datePage = new ScrollingPage();

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
						slider.addComponent(new ScrollingPage());
						slider.addComponent(new ScrollingPage());
						slider.addComponent(new ScrollingPage());

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
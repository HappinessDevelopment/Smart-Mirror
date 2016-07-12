import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.SwingConstants;

public class ScrollingPage extends JPanel {
	JLabel label;

	public ScrollingPage() {
		super(true);
		label = new JLabel();
		label.setFont(new Font("Helvetica", Font.BOLD, 20));
		setLayout(new BorderLayout());

		// vertical scroll bar
		JScrollBar vbar = new JScrollBar(JScrollBar.VERTICAL, 30, 70, 0, 100);

		// moves this many unites when button is clicked
		vbar.setUnitIncrement(2);

		// moves this many unites when track is clicked
		vbar.setBlockIncrement(4);

		vbar.addAdjustmentListener(new MyAdjustmentListener());

		add(vbar, BorderLayout.EAST);
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		add(label, BorderLayout.CENTER);
		//add(new JLabel("TESTING"));
	}
	
	public void setColor(Color color) {
		label.setForeground(color);
	}
	
	public void setText(String text) {
		label.setText(text);
	}

	class MyAdjustmentListener implements AdjustmentListener {
		public void adjustmentValueChanged(AdjustmentEvent e) {
			//label.setText("    New Value is " + e.getValue() + "      ");
			//label.setText(date.getDate());
			repaint();
		}
	}
}
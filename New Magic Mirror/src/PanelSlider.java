import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.*;

public class PanelSlider<ParentType extends Container> {

	private static final int RIGHT = 0x01;
	private static final int LEFT = 0x02;
	private final JPanel basePanel = new JPanel();
	private final ParentType parent;
	private final Object lock = new Object();
	private final ArrayList<Component> jPanels = new ArrayList<Component>();
	private final boolean useSlideButton = true;
	private boolean isSlideInProgress = false;

	private final JPanel glassPane;

	{
		glassPane = new JPanel();
		glassPane.setOpaque(false);
		glassPane.addMouseListener(new MouseAdapter() {
		});
		glassPane.addMouseMotionListener(new MouseMotionAdapter() {
		});
		glassPane.addKeyListener(new KeyAdapter() {
		});
	}

	public PanelSlider(final ParentType parent) {
		if (parent == null) {
			throw new RuntimeException("ProgramCheck: Parent can not be null.");
		}
		if ((parent instanceof JFrame) || (parent instanceof JDialog) || (parent instanceof JWindow)
				|| (parent instanceof JPanel)) {
		} else {
			throw new RuntimeException("ProgramCheck: Parent type not supported. " + parent.getClass().getSimpleName());
		}
		this.parent = parent;
		attach();
		basePanel.setSize(parent.getSize());
		basePanel.setLayout(new BorderLayout());
		if (useSlideButton) {
			final JPanel statusPanel = new JPanel();
			basePanel.add(statusPanel, BorderLayout.SOUTH);
			statusPanel.add(new JButton("Slide Left") {
				private static final long serialVersionUID = 9204819004142223529L;

				{
					setMargin(new Insets(0, 0, 0, 0));
				}

				{
					addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(final ActionEvent e) {
							slideLeft();
						}
					});
				}
			});
			statusPanel.add(new JButton("Slide Right") {
				{
					setMargin(new Insets(0, 0, 0, 0));
				}

				private static final long serialVersionUID = 9204819004142223529L;

				{
					addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(final ActionEvent e) {
							slideRight();
						}
					});
				}
			});
		}
	}

	public JPanel getBasePanel() {
		return basePanel;
	}

	private void attach() {
		final ParentType w = this.parent;
		if (w instanceof JFrame) {
			final JFrame j = (JFrame) w;
			if (j.getContentPane().getComponents().length > 0) {
				throw new RuntimeException("ProgramCheck: Parent already contains content.");
			}
			j.getContentPane().add(basePanel);
		}
		if (w instanceof JDialog) {
			final JDialog j = (JDialog) w;
			if (j.getContentPane().getComponents().length > 0) {
				throw new RuntimeException("ProgramCheck: Parent already contains content.");
			}
			j.getContentPane().add(basePanel);
		}
		if (w instanceof JWindow) {
			final JWindow j = (JWindow) w;
			if (j.getContentPane().getComponents().length > 0) {
				throw new RuntimeException("ProgramCheck: Parent already contains content.");
			}
			j.getContentPane().add(basePanel);
		}
		if (w instanceof JPanel) {
			final JPanel j = (JPanel) w;
			if (j.getComponents().length > 0) {
				throw new RuntimeException("ProgramCheck: Parent already contains content.");
			}
			j.add(basePanel);
		}
	}

	public void addComponent(final Component component) {
		if (jPanels.contains(component)) {
		} else {
			jPanels.add(component);
			if (jPanels.size() == 1) {
				basePanel.add(component);
			}
			component.setSize(basePanel.getSize());
			component.setLocation(0, 0);
		}
	}

	public void removeComponent(final Component component) {
		if (jPanels.contains(component)) {
			jPanels.remove(component);
		}
	}

	public void slideLeft() {
		slide(LEFT);
	}

	public void slideRight() {
		slide(RIGHT);
	}

	private void enableUserInput(final ParentType w) {
		if (w instanceof JFrame) {
			((JFrame) w).getGlassPane().setVisible(false);
		}
		if (w instanceof JDialog) {
			((JDialog) w).getGlassPane().setVisible(false);
		}
		if (w instanceof JWindow) {
			((JWindow) w).getGlassPane().setVisible(false);
		}
	}

	private void disableUserInput(final ParentType w) {
		if (w instanceof JFrame) {
			((JFrame) w).setGlassPane(glassPane);
		}
		if (w instanceof JDialog) {
			((JDialog) w).setGlassPane(glassPane);
		}
		if (w instanceof JWindow) {
			((JWindow) w).setGlassPane(glassPane);
		}
		glassPane.setVisible(true);
	}

	private void enableTransparentOverylay() {
		if (parent instanceof JFrame) {
			((JFrame) parent).getContentPane().setBackground(jPanels.get(0).getBackground());
			parent.remove(basePanel);
			parent.validate();
		}
		if (parent instanceof JDialog) {
			((JDialog) parent).getContentPane().setBackground(jPanels.get(0).getBackground());
			parent.remove(basePanel);
			parent.validate();
		}
		if (parent instanceof JWindow) {
			((JWindow) parent).getContentPane().setBackground(jPanels.get(0).getBackground());
			parent.remove(basePanel);
			parent.validate();
		}
	}

	private void slide(final int slideType) {
		if (!isSlideInProgress) {
			isSlideInProgress = true;
			final Thread t0 = new Thread(new Runnable() {
				@Override
				public void run() {
					parent.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					disableUserInput(parent);
					slide(true, slideType);
					enableUserInput(parent);
					parent.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					isSlideInProgress = false;
				}
			});
			t0.setDaemon(true);
			t0.start();
		} else {
			Toolkit.getDefaultToolkit().beep();
		}
	}

	private void slide(final boolean useLoop, final int slideType) {
		if (jPanels.size() < 2) {
			System.err.println("Not enough panels");
			return;
		}
		synchronized (lock) {
			Component componentOld = null;
			Component componentNew = null;
			if ((slideType == LEFT)) {
				componentNew = jPanels.remove(jPanels.size() - 1);
				componentOld = jPanels.get(0);
				jPanels.add(0, componentNew);
			}
			if ((slideType == RIGHT)) {
				componentOld = jPanels.remove(0);
				jPanels.add(componentOld);
				componentNew = jPanels.get(0);
			}
			final int w = componentOld.getWidth();
			final int h = componentOld.getHeight();
			final Point p1 = componentOld.getLocation();
			final Point p2 = new Point(0, 0);
			if (slideType == LEFT) {
				p2.x += w;
			}
			if (slideType == RIGHT) {
				p2.x -= w;
			}
			componentNew.setLocation(p2);
			int step = 0;
			if ((slideType == LEFT) || (slideType == RIGHT)) {
				step = (int) (((float) parent.getWidth() / (float) Toolkit.getDefaultToolkit().getScreenSize().width)
						* 40.f);
			} else {
				step = (int) (((float) parent.getHeight() / (float) Toolkit.getDefaultToolkit().getScreenSize().height)
						* 20.f);
			}
			step = step < 5 ? 5 : step;
			basePanel.add(componentNew);
			basePanel.revalidate();
			if (useLoop) {
				final int max = (slideType == LEFT) || (slideType == RIGHT) ? w : h;
				final long t0 = System.currentTimeMillis();
				for (int i = 0; i != (max / step); i++) {
					switch (slideType) {
					case LEFT: {
						p1.x -= step;
						componentOld.setLocation(p1);
						p2.x -= step;
						componentNew.setLocation(p2);
						break;
					}
					case RIGHT: {
						p1.x += step;
						componentOld.setLocation(p1);
						p2.x += step;
						componentNew.setLocation(p2);
						break;
					}
					default:
						new RuntimeException("ProgramCheck").printStackTrace();
						break;
					}

					try {
						Thread.sleep(500 / (max / step));
					} catch (final Exception e) {
						e.printStackTrace();
					}
				}
				final long t1 = System.currentTimeMillis();
			}
			componentOld.setLocation(-10000, -10000);
			componentNew.setLocation(0, 0);
		}
	}
}
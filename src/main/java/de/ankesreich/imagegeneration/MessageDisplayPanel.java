package de.ankesreich.imagegeneration;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class MessageDisplayPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private static final int EPD_WIDTH = 400;
	private static final int EPD_HEIGHT = 300;
	private static final String FILENAME_BACKGROUND = "background.bmp";
	
	
	public MessageDisplayPanel(String text) throws IOException
	{
		setFixedSize(this,new Dimension(EPD_WIDTH, EPD_HEIGHT));
		setLayout(new GridBagLayout());
		if(text == null)
		{
			text = "jibt nüscht zu sagen..";
		}
		if(text.length() > 60)
		{
			text = text.substring(0, 60) + "..";
		}
		JLabel label = createLabelText(text);
		GridBagConstraints gridbagconstraint = createGridbagconstraint(new Insets(0,40,150,105));
		add(label, gridbagconstraint);
		
		BufferedImage backgroundImage = createBackgroundImage();
		JLabel wIcon = new JLabel(new ImageIcon(backgroundImage));
		add(wIcon, createGridbagconstraint(new Insets(0,0,0,0) ));
	}

	private BufferedImage createBackgroundImage() throws IOException {
		InputStream input = this.getClass().getClassLoader().getResourceAsStream(FILENAME_BACKGROUND);
		BufferedImage backgroundImage = null;
		try
		{
			backgroundImage = ImageIO.read(input);
		}
		finally
		{
			if (input != null)
			{
				input.close();
			}
		}
		return backgroundImage;
	}

	private JLabel createLabelText(String text) {
		int sizeFont = getSizefont(text);
		JLabel label = new JLabel(
				"<HTML> <span style='font-size:" + sizeFont + "px'>" + text + 
					"</span></HTML>", SwingConstants.CENTER);
		return label;
	}

	private int getSizefont(String text) {
		int sizeFont = 40;
		if(text.length() > 20)
		{
			sizeFont = 15;
		}
		else if(text.length() > 12)
		{
			sizeFont = 20;
		}
		else if (text.length() > 9)
		{
			sizeFont = 30;
		}
		return sizeFont;
	}
	
	void setFixedSize(Component comp, Dimension size) {
		comp.setPreferredSize(size);
		comp.setMinimumSize(size);
		comp.setMaximumSize(size);
	}
	
	GridBagConstraints createGridbagconstraint(Insets insets ) {
		GridBagConstraints constr = new GridBagConstraints();
		constr.gridx = 0;
		constr.gridy = 0;
		constr.weightx = .0;
		constr.anchor = GridBagConstraints.CENTER;
		constr.ipadx = 0;
		constr.ipady = 0;
		constr.fill = GridBagConstraints.BOTH;
		constr.insets = insets;
		return constr;
	}
	
	
	  
}
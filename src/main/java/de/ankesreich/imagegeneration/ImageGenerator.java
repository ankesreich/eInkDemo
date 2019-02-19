package de.ankesreich.imagegeneration;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ImageGenerator {

	private static Logger log = Logger.getLogger(ImageGenerator.class.getName());

	public static File writeImageToFile(String fileName, String message) throws IOException {
		MessageDisplayPanel panel = new MessageDisplayPanel(message);
		addToJFrame(panel);
		return createImageAndWrite(fileName, panel);
	}

	private static void addToJFrame(JPanel panel) throws IOException {
		JFrame frame = new JFrame("MyPanel");
		frame.add(panel);
		frame.pack();
	}

	private static File createImageAndWrite(String file, MessageDisplayPanel panel) {
		BufferedImage image = createImage(panel);
		File output = new File(file + ".bmp");
		try {
			ImageIO.write(image, "bmp", output);
		} catch (IOException e) {
			log.log(Level.WARNING, "IOException writeImageToFile", e);
		}
		return output;
	}

	private static BufferedImage createImage(MessageDisplayPanel panel) {
		BufferedImage image = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
		Graphics2D g = image.createGraphics();
		panel.printAll(g);
		g.dispose();
		return image;
	}

}

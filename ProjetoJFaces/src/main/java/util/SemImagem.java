package util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class SemImagem {
	public static byte[] gerar() {
		BufferedImage bi = new BufferedImage(200, 200,

				BufferedImage.TYPE_INT_RGB);

		Graphics g = bi.createGraphics();
		g.setColor(new Color(230, 230, 255));
		g.fillRect(0, 0, 200, 200);
		g.setColor(Color.BLACK);
		Font font = new Font("Courier", Font.BOLD, 30);
		g.setFont(font);
		g.drawString("Sem", 70, 70);
		g.drawString("Imagem", 40, 120);
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		try {
			ImageIO.write(bi, "png", buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer.toByteArray();
	}
}
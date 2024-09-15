import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BarcodeGenerator {

    public static void main(String[] args) {
        String barcodeData = "Hello, World!";
        String filePath = "output.png";
        generateBarcode(barcodeData, filePath, BarcodeFormat.QR_CODE, 200, 200);
        System.out.println("Barcode generated successfully.");
    }

    private static void generateBarcode(String data, String filePath, BarcodeFormat format, int width, int height) {
        try {
            BitMatrix bitMatrix;
            if (format == BarcodeFormat.QR_CODE) {
                bitMatrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, width, height);
            } else {
                bitMatrix = new QRCodeWriter().encode(data, BarcodeFormat.CODE_128, width, height);
            }

            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    image.setRGB(x, y, bitMatrix.get(x, y) ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
                }
            }

            ImageIO.write(image, "png", new File(filePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
// for scanner
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BarcodeScanner {

    public static void main(String[] args) {
        String filePath = "output.png";
        String barcodeData = readBarcode(filePath);
        System.out.println("Scanned barcode data: " + barcodeData);
    }

    private static String readBarcode(String filePath) {
        try {
            BufferedImage image = ImageIO.read(new File(filePath));
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
            Result result = new MultiFormatReader().decode(binaryBitmap);
            return result.getText();
        } catch (IOException | NotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

package com.warez.utils;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class BarCodeUtil {

    private static Logger log = LoggerFactory.getLogger(BarCodeUtil.class);

    /**
     * 条码
     *
     * @param contents
     * @param qrCode   [true:二维码, false:条形码]
     * @param width
     * @param height
     * @return
     */
    public static OutputStream encode(String contents, boolean qrCode, int width, int height) {
        try {

            Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            BitMatrix bitMatrix = null;
            if (qrCode) { // 二维码
                // 指定边距
                hints.put(EncodeHintType.MARGIN, 0);
                // 指定纠错等级
                hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.Q);
                bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height, hints);
            } else { // 条形码
                int codeWidth = 3 + // start guard
                        (7 * 6) + // left bars
                        5 + // middle guard
                        (7 * 6) + // right bars
                        3; // end guard
                codeWidth = Math.max(codeWidth, width);
                bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.EAN_13, codeWidth, height, hints);
            }
            // 查看条码效果
            // MatrixToImageWriter.writeToStream(bitMatrix, "png", new FileOutputStream(new File("D:/x.png")));
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "png", outputStream);
            return outputStream;
        } catch (Exception e) {
            log.error("生成条码异常,对应条码:{},对应异常:{}", contents, e.getMessage());
        }
        return null;
    }

    /**
     * base64编码
     *
     * @param contents
     * @param qrCode   [true:二维码, false:条形码]
     * @param width
     * @param height
     * @return
     */
    public static String encode2Base64String(String contents, boolean qrCode, int width, int height) {
        ByteArrayOutputStream outputStream = (ByteArrayOutputStream) encode(contents, qrCode, width, height);
        if (null == outputStream) {
            return null;
        }
        return String.format("data:image/.png;base64,%s", Base64.encodeBase64String(outputStream.toByteArray()));
    }

    /**
     * 解析条形码
     *
     * @param imgPath
     * @return
     */
    public static String decode(String imgPath) {
        try {
            BufferedImage image = ImageIO.read(new File(imgPath));
            if (image == null) {
                log.error("条码位置可能不存在:{}", imgPath);
                return null;
            }
            return decode(image);
        } catch (Exception e) {
            log.error("解析条码异常,对应条码位置:{},对应异常:{}", imgPath, e.getMessage());
        }
        return null;
    }

    /**
     * 解析条形码
     *
     * @param imgPath
     * @return
     */
    public static String decode(InputStream inputStream) {
        try {
            BufferedImage image = ImageIO.read(inputStream);
            return decode(image);
        } catch (Exception e) {
            log.error("解析条码异常,对应异常:{}", e.getMessage());
        }
        return null;
    }

    /**
     * 解析条形码
     *
     * @param image
     * @return
     * @throws NotFoundException
     */
    private static String decode(BufferedImage image) throws NotFoundException {
        LuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();
        hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
        Result result = new MultiFormatReader().decode(bitmap, hints);
        return result.getText();
    }

//    public static void main(String[] args) {
//        String contents = "6923450657713";
//        int width = 105, height = 105;
//        String str = BarCodeUtil.encode2Base64String(contents, false, width, height);
//        System.out.println(str);
//
//        System.out.println(decode("D:/x.png"));
//    }
}

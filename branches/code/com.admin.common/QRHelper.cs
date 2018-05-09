using System.Drawing;
using System.Drawing.Imaging;
using System.IO;
using System.Text;
using ThoughtWorks.QRCode.Codec;

namespace com.admin.common
{
    public class QRHelper
    {
        /// <summary>
        /// 
        /// </summary>
        /// <param name="txt"></param>
        /// <param name="qrcodeEncodeMode">enum:BYTE、NUMERIC、ALPHA_NUMERIC、</param>
        /// <param name="qrcodeErrorCorrect">enum:L、M、Q、H</param>
        /// <param name="qrCodeScale">int</param>
        /// <param name="qrcodeVersion">int</param>
        /// <returns></returns>
        public static byte[] CreateQr(string txt, string qrcodeEncodeMode = "BYTE", string qrcodeErrorCorrect = "M", int qrCodeScale = 4, int qrcodeVersion = 7)
        {
            QRCodeEncoder qr = new QRCodeEncoder();
            #region 设置
            switch (qrcodeEncodeMode.ToUpper())
            {
                case "BYTE":
                    qr.QRCodeEncodeMode = QRCodeEncoder.ENCODE_MODE.BYTE;
                    break;
                case "NUMERIC":
                    qr.QRCodeEncodeMode = QRCodeEncoder.ENCODE_MODE.NUMERIC;
                    break;
                case "ALPHA_NUMERIC":
                    qr.QRCodeEncodeMode = QRCodeEncoder.ENCODE_MODE.ALPHA_NUMERIC;
                    break;
                default:
                    qr.QRCodeEncodeMode = QRCodeEncoder.ENCODE_MODE.BYTE;
                    break;
            }
            switch (qrcodeErrorCorrect.ToUpper())
            {
                case "M":
                    qr.QRCodeErrorCorrect = QRCodeEncoder.ERROR_CORRECTION.M;
                    break;
                case "L":
                    qr.QRCodeErrorCorrect = QRCodeEncoder.ERROR_CORRECTION.L;
                    break;
                case "Q":
                    qr.QRCodeErrorCorrect = QRCodeEncoder.ERROR_CORRECTION.Q;
                    break;
                case "H":
                    qr.QRCodeErrorCorrect = QRCodeEncoder.ERROR_CORRECTION.H;
                    break;
                default:
                    qr.QRCodeErrorCorrect = QRCodeEncoder.ERROR_CORRECTION.M;
                    break;
            }
            qr.QRCodeScale = qrCodeScale;
            qr.QRCodeVersion = qrcodeVersion; 
            #endregion
            Bitmap bitmap = qr.Encode(txt, Encoding.UTF8);
            using (MemoryStream ms = new MemoryStream())
            {
                bitmap.Save(ms, ImageFormat.Png);
                return ms.ToArray();
            }
        }
    }
}

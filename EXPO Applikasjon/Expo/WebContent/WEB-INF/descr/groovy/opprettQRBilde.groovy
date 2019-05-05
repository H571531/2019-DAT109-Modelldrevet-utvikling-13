#input String standId


import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import com.webratio.rtx.RTXConstants
 
import com.webratio.struts.WRGlobals;
 
import com.webratio.struts.HttpRequestHelper
 
import com.webratio.rtx.core.BeanHelper


import java.io.IOException;
import java.lang.Exception;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.io.File;
import javax.imageio.ImageIO;


import java.awt.image.BufferedImage;
import org.apache.commons.io.IOUtils
 
 
 
/*gets the request from the localContext*/
 
def request = localContext.get(RTXConstants.HTTP_SERVLET_REQUEST_KEY)
 
 
 
/*gets the session from the request*/
 
def session = request.getSession().getServletContext();
 
def requestHelper = (HttpRequestHelper) session.getAttribute(WRGlobals.HTTP_REQUEST_HELPER_KEY);
 
 
 
/*gets the URL of the application*/
 
def url = requestHelper.getAbsoluteURLContext(false, request)
 


def imgPath = url + "/img/standQR/" + standId + ".jpg"
def localImgPath = "../webapps/Expo/img/standQR/" + standId + ".jpg"
def standURL = url + "/stand/" + standId


try{

	QRCodeWriter qrCodeWriter = new QRCodeWriter();
    BitMatrix bitMatrix = qrCodeWriter.encode(standURL, BarcodeFormat.QR_CODE, 512, 512);
    BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
	File outputfile = new File(localImgPath);
    //Path path = FileSystems.getDefault().getPath(imgPath);
    //MatrixToImageWriter.writeToPath(bitMatrix, "PNG", outputfile.toPath());
    
    
	ImageIO.write(image, "jpg", outputfile);
        
       } catch (IOException e){
       	e.printStackTrace();
       }
       
return imgPath
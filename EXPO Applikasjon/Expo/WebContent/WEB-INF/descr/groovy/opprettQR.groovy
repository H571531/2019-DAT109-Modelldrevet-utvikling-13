#input String standId


import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.webratio.rtx.RTXConstants
import com.webratio.struts.WRGlobals;
import com.webratio.struts.HttpRequestHelper
import com.webratio.rtx.core.BeanHelper
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import com.webratio.rtx.RTXBLOBData;
import com.webratio.rtx.blob.BLOBData;
import com.webratio.rtx.blob.ByteArrayBLOBData;

 
def ext = StringUtils.substringAfterLast(thumbnail, ".")
 
File tempFile = File.createTempFile("image", "." + ext)
 
tempFile.deleteOnExit()
 
/*gets the request from the localContext*/
 
def request = localContext.get(RTXConstants.HTTP_SERVLET_REQUEST_KEY)
 
 
 
/*gets the session from the request*/
 
def session = request.getSession().getServletContext();
 
def requestHelper = (HttpRequestHelper) session.getAttribute(WRGlobals.HTTP_REQUEST_HELPER_KEY);
 
 
 
/*gets the URL of the application*/
 
def url = requestHelper.getAbsoluteURLContext(false, request)

def qrURL = url + "/stand/" + standId
 
QRCodeWriter writer = new QRCodeWriter();
BufferedImage image = null;

/*
File tempFile = File.createTempFile("image", ".png" )
 
tempFile.deleteOnExit()
*/

/*
OutputStream out = null
RTXBLOBData newQR = null
def imageName = "img/standQR/" + standId + ".png";

try{
	
    BitMatrix bitMatrix = writer.encode(qrURL, BarcodeFormat.QR_CODE,800,800);
    image = MatrixToImageWriter.toBufferedImage(bitMatrix);
    ByteArrayOutputStream os = new ByteArrayOutputStream();   
	ImageIO.write(image, "png", os)

	

	newQR = new ByteArrayBLOBData(imageName, os.toByteArray());
    
    }catch (Exception e){
            e.printStackTrace();
    } finally {
    	
    }
        

*/
 
 
       
        /*
ByteArrayOutputStream os = new ByteArrayOutputStream(); 
try{
	ImageIO.write(image,"png", os);
} catch (IOException e) {
 
	// TODO Auto-generated catch block
 
	e.printStackTrace();
 
	}
	
RTXBLOBData newThumbnail = new ByteArrayBLOBData(imageName, os.toByteArray());
*/

return 1
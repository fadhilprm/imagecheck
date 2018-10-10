package xyz.fadhilprm.app.imagecheck;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.bytesource.ByteSourceFile;
import org.apache.commons.imaging.formats.jpeg.JpegImageParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author fadhilprm
 *
 */
@Component
public class ImageChecker {
	
	private static final Logger LOGGER =LoggerFactory.getLogger(ImageChecker.class);
	/**
	 * @param image
	 * @return boolean 
	 * @throws IOException
	 */
	public final boolean isImageCorrupt(File image) throws IOException {
		LOGGER.debug("reading image {}", image.getName());
	    JpegImageParser parser = new JpegImageParser();
	    ByteSourceFile bs = new ByteSourceFile(image);
	    try {
	        BufferedImage bi = parser.getBufferedImage(bs, null);
	        bi.flush();
	        return false;
	    } catch (ImageReadException e) {
	        LOGGER.error("an problem while reading image {}, caused by : {}" , image.getName(), e);
	    	return true;
	    }
	}
}

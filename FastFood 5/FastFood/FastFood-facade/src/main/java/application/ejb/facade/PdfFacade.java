package application.ejb.facade;

import application.ejb.entity.Orderr;
import application.ejb.facade.interfaces.PdfFacadeLocal;
import application.ejb.pdf.PdfCreator;
import com.itextpdf.text.DocumentException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author Łukasz
 */
@Stateless
public class PdfFacade implements PdfFacadeLocal {

    @Override
    public byte[] createPdf(List<Orderr> order) {
        ByteArrayOutputStream outStream;
        try {
            outStream = PdfCreator.createPdf(order);
            return outStream.toByteArray();
        } catch (DocumentException | IOException ex) {
            Logger.getLogger(PdfFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}

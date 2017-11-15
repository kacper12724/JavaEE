package application.ejb.pdf;

import application.ejb.entity.Orderr;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Łukasz
 */
public class PdfCreator {

    private static Font helvetica12;

    private static final String FACTURE = "Faktura";
    private static final String FAST_FOOD = "Fast Food";
    private static final String MSG_HEAD = "Faktura wygenerowana przez Fastfood";
    private static final String MSG_PART1 = "Część pierwsza";
    private static final String ORDER = "Zamówienie:";
    private static final String MEAL_NAME = "Nazwa dania";
    private static final String MEAL_PRICE = "Cena";
    private static final String MEAL_SUM = "Suma";

    public static ByteArrayOutputStream createPdf(List<Orderr> order) throws FileNotFoundException, DocumentException, IOException {
        BaseFont helvetica = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1250, BaseFont.EMBEDDED);
        helvetica12 = new Font(helvetica, 12);

        Document document = new Document();

        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, byteStream);

        document.open();
        addMetaData(document);
        addFirstPage(document, order);
        document.close();

        return byteStream;
    }

    private static void addMetaData(Document document) {
        document.addTitle(FACTURE);
        document.addSubject(FACTURE);
        document.addKeywords(FACTURE);
        document.addAuthor(FAST_FOOD);
        document.addCreator(FAST_FOOD);
    }

    private static void addFirstPage(Document document, List<Orderr> order) throws DocumentException {
        Paragraph preface = new Paragraph();
        addEmptyLine(preface, 1);

        preface.add(new Paragraph(MSG_HEAD));

        Anchor anchor = new Anchor(MSG_PART1, helvetica12);
        anchor.setName(MSG_PART1);

        Chapter chapter = new Chapter(new Paragraph(anchor), 1);
        Paragraph subPara = new Paragraph(ORDER, helvetica12);
        Section section = chapter.addSection(subPara);

        addEmptyLine(subPara, 2);

        addTable(section, order);

        document.add(preface);
        document.add(subPara);
        document.add(section);
    }

    private static void addTable(Section section, List<Orderr> order) {
        PdfPTable table = new PdfPTable(3);

        PdfPCell c1 = new PdfPCell(new Phrase(MEAL_NAME, helvetica12));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        PdfPCell c2 = new PdfPCell(new Phrase(MEAL_PRICE, helvetica12));
        c2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c2);

        PdfPCell c3 = new PdfPCell(new Phrase(MEAL_SUM, helvetica12));
        c3.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c3);

        table.setHeaderRows(1);

        double sum = 0;

        for (Orderr o : order) {
            table.addCell(new Phrase(o.getIdFood().getName(), helvetica12));
            table.addCell(new Phrase(o.getIdFood().getPrice().toString(), helvetica12));
            sum = sum + o.getIdFood().getPrice().doubleValue();
            table.addCell("");
        }

        table.addCell("");
        table.addCell("");
        table.addCell(new Phrase(String.valueOf(sum), helvetica12));

        section.add(table);
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

}

package corendon.utilities;

import com.itextpdf.text.Chunk;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import corendon.queries.StatsMath;
import java.util.Date;

/**
 * This class takes the data from the database and presents it in an easy to
 * read pdf export
 *
 * @author huipvandenende
 */
public class PDFExport {

    Date date = new Date();
    //Uncomment the right one for your operating system!
    File file = new File("report/Report " + date.toString() + ".pdf"); //For Mac OS
//    File file = new File("../../../../report//Report " + date.toString()); //For Windows Not working ATM
    StatsMath totalDays = new StatsMath();

    /**
     * Puts the data in the file
     *
     * @throws com.itextpdf.text.DocumentException
     * @throws java.io.IOException
     */
    public void toPDF() throws DocumentException, IOException {

        // makes the directory that holds the file
        file.getParentFile().mkdirs();

        Document report = new Document();

        // makes the actual file in the directory
        // date,toString() added for automated dating of filenames
        //Uncomment the right one for your operating system!
        PdfWriter.getInstance(report, new FileOutputStream("report/Report " + 
                date.toString())); //For Mac OS
//        PdfWriter.getInstance(report, new FileOutputStream("../../../../report//Report "
//                + date.toString())); //For Windows Not working ATM

        // puts data into the pdf file
        report.open();
        report.add(new Paragraph("IRREGULARITY REPORT"));
        report.add(Chunk.NEWLINE);
        report.add(new Paragraph("Luggage returned in the last 3 days: "
                + StatsMath.threeDaysResults() + totalDays.getTotalDays(3)));

        report.add(new Paragraph("Luggage returned in the last 21 days: "
                + StatsMath.twentyOneDaysResults() + totalDays.getTotalDays(21)));

        report.add(new Paragraph("Luggage returned in the last month: "
                + StatsMath.oneMonthResults() + totalDays.getTotalDays(31)));
        
        report.add(new Paragraph("Luggage returned in the last year: "
                + StatsMath.oneYearResults()));
        
        report.add(new Paragraph("Luggage unresolved in the last year: "
                + StatsMath.oneYearUnsolvedResults() + totalDays.getTotalDays(365)));

        report.close();
    }
}

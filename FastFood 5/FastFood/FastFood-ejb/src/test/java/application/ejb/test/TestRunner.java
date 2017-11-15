package application.ejb.test;

import application.ejb.pdf.PdfCreatorTest;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 *
 * @author Łukasz
 */
public class TestRunner {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(PdfCreatorTest.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println(result.wasSuccessful());
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.ejb.pdf;

import application.ejb.entity.Orderr;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Łukasz
 */
public class PdfCreatorTest {

    public PdfCreatorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of createPdf method, of class PdfCreator.
     *
     * @throws java.lang.Exception
     */
    public void testCreatePdf() throws Exception {
        System.out.println("createPdf");
        List<Orderr> order = null;
        String expResult = "pdf//facture.pdf";
//        String result = PdfCreator.createPdf(order);
//        assertEquals(expResult, result);
    }

}

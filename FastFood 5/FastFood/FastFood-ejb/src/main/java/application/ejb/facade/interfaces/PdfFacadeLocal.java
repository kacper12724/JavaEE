/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.ejb.facade.interfaces;

import application.ejb.entity.Orderr;
import java.util.List;

/**
 *
 * @author Łukasz
 */
public interface PdfFacadeLocal {

    byte[] createPdf(List<Orderr> order);

}

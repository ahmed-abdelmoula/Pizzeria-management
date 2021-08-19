/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Yossra
 */
public class ModeleRecette {
    java.util.Date date;
	int qte,codeProd,codePV;
	public ModeleRecette(java.util.Date d, int qte, int codeProd, int codePV) {
		super();
		this.date = d;
		this.qte = qte;
		this.codeProd = codeProd;
		this.codePV = codePV;
	}
}

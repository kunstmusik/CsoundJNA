/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunstmusik.csoundjna;

/**
 *
 * @author stevenyi
 */
public class CsoundJNA {

    public static void main(String args[]){
        Csound csound = new Csound();
        System.out.println("Csound Version: " + csound.getVersion());
    }
}

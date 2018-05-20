/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunstmusik.csoundjna;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author stevenyi
 */
public class CsoundJNA {

    public static class CSOUND extends Structure {

        @Override
        protected List<String> getFieldOrder() {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

            return new ArrayList<String>();
        }
        
    }

    public interface LibCsound extends Library {
        LibCsound INSTANCE = (LibCsound)Native.loadLibrary("csound64", LibCsound.class);

        Pointer csoundCreate(long l); 
        int csoundGetVersion(Pointer p);
    }

    public static void main(String args[]){
        Pointer csound = LibCsound.INSTANCE.csoundCreate(0);
        System.out.println("Csound Version: " + LibCsound.INSTANCE.csoundGetVersion(csound));
    }
}

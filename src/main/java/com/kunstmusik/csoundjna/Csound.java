/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunstmusik.csoundjna;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

/**
 *
 * @author stevenyi
 */
public class Csound {
    
    public interface LibCsound extends Library {
        LibCsound INSTANCE = (LibCsound)Native.loadLibrary("csound64", LibCsound.class);

        Pointer csoundCreate(long l); 
        int csoundGetVersion(Pointer p);
    }

    private final Pointer csoundPtr;

    public Csound(){
        csoundPtr = LibCsound.INSTANCE.csoundCreate(0);
    }

    public int getVersion() {
        return LibCsound.INSTANCE.csoundGetVersion(csoundPtr);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunstmusik.csoundjna;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stevenyi
 */
public class CsoundJNA {

    public static void main(String args[]) {
        // This pretty much needs to always happen when in a JVM app;
        // maybe should just do this once within the static initializer block
        // in Csound class...
        Csound.initialize(Csound.CSOUNDINIT_NO_ATEXIT
                | Csound.CSOUNDINIT_NO_SIGNAL_HANDLER);

        Csound csound = new Csound();
        System.out.println("Csound Version: " + csound.getVersion());

        csound.setOption("-odac");
        csound.setOption("-b1024");
        csound.setOption("-b4096");
        csound.compileOrc("sr=48000\nksmps=64\nnchnls=2\n0dbfs=1\n"
                + "instr 1\n"
                + "asig = vco2(0.25, p4)\n"
                + "asig = diode_ladder(asig, expon(10000, p3, 100), 4)\n"
                + "outc(asig, asig)\n"
                + "endin\n"
                + "schedule(1,0,10, 440)\n"
                + "schedule(1,0,10, 880)\n"
                + "event_i(\"e\",11,11)\n"
        );
        csound.start();

        Thread t = new Thread(() -> {

            while (csound.performKsmps() == 0) {
            }
            csound.stop();

            csound.cleanup();

            csound.reset();
        });
        t.start();

        try {
            t.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(CsoundJNA.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

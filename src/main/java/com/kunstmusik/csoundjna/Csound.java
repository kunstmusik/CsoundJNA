/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunstmusik.csoundjna;

import com.sun.jna.Callback;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

/**
 *
 * @author stevenyi
 */
public class Csound {

    public static final int CSOUNDINIT_NO_SIGNAL_HANDLER = 1;
    public static final int CSOUNDINIT_NO_ATEXIT = 2;

    private static final CsoundLib INSTANCE;

    static {
        // not sure this is best as it doesn't account for if libcsound64 
        // is not found
        INSTANCE = (CsoundLib) Native.loadLibrary("csound64", CsoundLib.class);
    }

    public static int initialize(int flags) {
        return INSTANCE.csoundInitialize(flags);
    }

    private final Pointer csoundPtr;

    public Csound() {
        csoundPtr = INSTANCE.csoundCreate(0);
    }

    public int getVersion() {
        return INSTANCE.csoundGetVersion(csoundPtr);
    }

    public int setOption(String option) {
        return INSTANCE.csoundSetOption(csoundPtr, option);
    }

    public int compileOrc(String s) {
        return INSTANCE.csoundCompileOrc(csoundPtr, s);
    }

    public int compileOrcAsync(String s) {
        return INSTANCE.csoundCompileOrcAsync(csoundPtr, s);
    }

    public int compileCsdText(String csdText) {
        return INSTANCE.csoundCompileCsdText(csoundPtr, csdText);
    }

    public void start() {
        INSTANCE.csoundStart(csoundPtr);
    }

    public void stop() {
        INSTANCE.csoundStop(csoundPtr);
    }

    public int performKsmps() {
        return INSTANCE.csoundPerformKsmps(csoundPtr);
    }

    public int cleanup() {
        return INSTANCE.csoundCleanup(csoundPtr);
    }

    public void reset() {
        INSTANCE.csoundReset(csoundPtr);
    }

    public void setMessageCallback(MessageCallback cb) {
        INSTANCE.csoundSetMessageStringCallback(csoundPtr, cb);
    }

    interface CsoundLib extends Library {

        Pointer csoundCreate(long l);

        int csoundGetVersion(Pointer p);

        int csoundInitialize(int flags);

        int csoundSetOption(Pointer p, String option);

        int csoundCompileOrc(Pointer p, String str);

        int csoundCompileOrcAsync(Pointer p, String str);

        int csoundCompileCsdText(Pointer p, String csd_text);

        int csoundPerformKsmps(Pointer p);

        int csoundStart(Pointer p);

        void csoundStop(Pointer p);

        int csoundCleanup(Pointer p);

        void csoundReset(Pointer p);

        void csoundSetMessageStringCallback(Pointer p, 
                MessageCallback cb);
    }

    public static interface MessageCallback extends Callback {
        void invoke(Pointer p, int attr, String msg);
    }
}

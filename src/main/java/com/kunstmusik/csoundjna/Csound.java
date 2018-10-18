/*
 * Csound.java 
 * Copyright (c) 2018 Steven Yi (stevenyi@gmail.com)
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published
 * by  the Free Software Foundation; either version 2 of the License or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; see the file COPYING.LIB.  If not, write to
 * the Free Software Foundation Inc., 59 Temple Place - Suite 330,
 * Boston, MA  02111-1307 USA
 */
package com.kunstmusik.csoundjna;

import static com.kunstmusik.csoundjna.CsoundLib.*;
import com.sun.jna.Pointer;

/**
 *
 * @author stevenyi
 */
public class Csound {

    private final Pointer csoundPtr;

    public Csound() {
        csoundPtr = csoundCreate(0);
    }

    public int getVersion() {
        return csoundGetVersion(csoundPtr);
    }

    public int setOption(String option) {
        return csoundSetOption(csoundPtr, option);
    }

    public int compileOrc(String s) {
        return csoundCompileOrc(csoundPtr, s);
    }

    public int compileOrcAsync(String s) {
        return csoundCompileOrcAsync(csoundPtr, s);
    }

    public int compileCsdText(String csdText) {
        return csoundCompileCsdText(csoundPtr, csdText);
    }

    public void start() {
        csoundStart(csoundPtr);
    }

    public void stop() {
        csoundStop(csoundPtr);
    }

    public int performKsmps() {
        return csoundPerformKsmps(csoundPtr);
    }

    public int cleanup() {
        return csoundCleanup(csoundPtr);
    }

    public void reset() {
        csoundReset(csoundPtr);
    }

    public void setMessageCallback(MessageCallback cb) {
        csoundSetMessageStringCallback(csoundPtr, cb);
    }

}

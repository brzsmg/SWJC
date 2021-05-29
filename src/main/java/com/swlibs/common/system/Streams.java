package com.swlibs.common.system;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class Streams {
    public static String toString(InputStream inputStream) {

        final int bufferSize = 1024;
        final char[] buffer = new char[bufferSize];
        final StringBuilder out = new StringBuilder();
        try(Reader in = new InputStreamReader(inputStream, "UTF-8")) {
            for (; ; ) {
                int rsz = in.read(buffer, 0, buffer.length);
                if (rsz < 0)
                    break;
                out.append(buffer, 0, rsz);
            }
            return out.toString();
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String toString(Reader reader) {
        try {
            StringBuilder builder = new StringBuilder();
            int charsRead = -1;
            char[] chars = new char[100];
            do{
                charsRead = reader.read(chars,0,chars.length);
                //if we have valid chars, append them to end of string.
                if(charsRead>0)
                    builder.append(chars,0,charsRead);
            }while(charsRead>0);
            return builder.toString();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}

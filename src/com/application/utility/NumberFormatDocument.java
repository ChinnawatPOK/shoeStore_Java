package com.application.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

/**
 * Valid input format before insert into text box
 * Charector set is { 0-9, '-', ',' }
 * 
 * @author dt74695
 * @version 1.0
 */
public class NumberFormatDocument extends LimitLengthDocument {//??????????????????????????????????
    Pattern p = null;
    Matcher m = null;
    
    /**
     * Creates a new instance of StatememtSeqDocument
     */
    public NumberFormatDocument(int maxChars) {//????????????????????????
        super(maxChars);
        p = Pattern.compile("\\d{0,}"); // Create Regular Expression, Ex 1-20,25,30-34
    }    
    
    /**
     * Insert String
     * @param offs the starting offset >= 0
     * @param str the string to insert; does nothing with null/empty strings
     * @param a the attributes for the inserted content
     * @exception BadLocationException  the given insert position is not a valid
     *   position within the document
     * 
     * @throws BadLocationException
     */
    @Override
    public void insertString(int offset, String string, AttributeSet attr) 
            throws BadLocationException {
        m = p.matcher(string);  
        if (m.matches()){   // check format is correct or is not?
            super.insertString(offset, string.toUpperCase(), attr);
        }
    }
}

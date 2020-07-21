package com.application.utility;

import java.awt.Toolkit;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;

/**
 *
 * @author admin
 */
public class LimitLengthDocument extends DefaultStyledDocument {
    private int maxChars;
    /** Creates a new instance of LimitLengthDocument */
    public LimitLengthDocument(int maxChars) {
        this.maxChars = maxChars;
    }
    
    public void insertString(int offset, String string,
            AttributeSet attr) throws BadLocationException {
        
        // Limit characters to the maxChars.
        if ((getLength() + string.length()) > maxChars) {
            // Throw a beep at the user.
            Toolkit.getDefaultToolkit().beep();
            return;
        }
        super.insertString(offset, string, attr);
    }
}

/*
 * UserPasswordDocument.java
 *
 * Created on March 16, 2006, 3:37 PM
 *
 * Unpublished Copyright (c) 2000-2005 DST Output, Inc.
 * All rights reserved.
 */

package com.application.utility;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

/**
 *
 * @author adkinstl
 * @SourceSafe $Revision: 2 $
 */
public class UserPasswordDocument extends LimitLengthDocument {
    
    /**
     * Creates a new instance of UserPasswordDocument
     */
    public UserPasswordDocument(int maxChars) {
        super(maxChars);
    }
    
    public void insertString(int offset, String string,
            AttributeSet attr) throws BadLocationException {
        super.insertString(offset, string.toUpperCase(), attr);
    }
}

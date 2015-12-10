package com.bea.medrec.utils;

import java.io.File;
import java.io.FilenameFilter;

/**
 * <p>Filters XML files.</p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public class XMLFilter implements FilenameFilter {
    public boolean accept(File dir, String name) {
        return (name.endsWith(".xml"));
    }
}

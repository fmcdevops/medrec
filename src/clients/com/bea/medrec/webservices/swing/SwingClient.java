package com.bea.medrec.webservices.swing;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;


/**
 * <p>Java Swing Client to demonstrate java
 * client to web service communication.</p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public class SwingClient extends JComponent {
  private static int width = 600;
  private static int height = 300;

  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) { }

    EditProfileFrame frame = new EditProfileFrame();
    BoxLayout box = new javax.swing.BoxLayout(frame.getContentPane(),
        BoxLayout.Y_AXIS);
    frame.getContentPane().setLayout(box);

    //setDefaultCloseOperation

    frame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) { System.exit(0); }
    });
  }
}

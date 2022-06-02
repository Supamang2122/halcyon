package project.connections.resource;

import java.io.*;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class ResourceDistributor {
  public ImageIcon getFromAsImageIcon(String path) {
    try {
      return new ImageIcon(java.util.Objects.requireNonNull(getClass().getResource(path)));
    } catch (NullPointerException e) {
      return new ImageIcon(path);
    }
  }
}
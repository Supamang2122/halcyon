package com.jackmeng.halcyon.app.components.info;

import javax.swing.JComponent;

public interface InformationTab {
  String getName();

  String getToolTip();

  JComponent getComponent();
}
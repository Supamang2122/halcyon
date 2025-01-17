/*
 *  Copyright: (C) 2022 name of Jack Meng
 * Halcyon MP4J is music-playing software.
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program; If not, see <http://www.gnu.org/licenses/>.
 */

package com.jackmeng.cosmos.components.bbloc.buttons;

import com.jackmeng.cosmos.components.bbloc.BBlocButton;
import com.jackmeng.cosmos.components.settings.SettingsPane;
import com.jackmeng.halcyon.constant.Global;
import com.jackmeng.halcyon.constant.Manager;
import com.jackmeng.halcyon.utils.DeImage;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * A representation of a deprecated SettingsView class
 * which used to be in the bottom tabs view pane.
 *
 * @author Jack Meng
 * @since 3.0
 */
public class Settings extends JButton implements BBlocButton {
  private SettingsPane e = new SettingsPane();
  public Settings() {
    setRolloverIcon(DeImage.resizeImage(Global.rd.getFromAsImageIcon(Manager.SETTINGS_BUTTON_PRESSED_ICON), 16, 16));
    setIcon(DeImage.resizeImage(Global.rd.getFromAsImageIcon(Manager.SETTINGS_BUTTON_DEFAULT_ICON), 16, 16));
    setToolTipText(Manager.SETTINGS_BUTTON_TOOLTIP);
    setOpaque(true);
    setBorder(null);
    setDoubleBuffered(true);
    setBackground(null);
    addActionListener(this);
    setContentAreaFilled(false);

  }


  /**
   * @return JComponent
   */
  @Override
  public JComponent getComponent() {
    return this;
  }


  /**
   * @param e
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    this.e.run();
  }
}

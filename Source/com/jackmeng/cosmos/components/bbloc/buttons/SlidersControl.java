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

import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.*;

import com.jackmeng.cosmos.components.bbloc.BBlocButton;
import com.jackmeng.cosmos.components.dialog.SlidersDialog;
import com.jackmeng.halcyon.constant.Global;
import com.jackmeng.halcyon.constant.Manager;
import com.jackmeng.halcyon.utils.DeImage;

public class SlidersControl extends JButton implements BBlocButton {
  private boolean isPressed;
  private SlidersDialog dialog;

  public SlidersControl() {
    setIcon(DeImage.resizeImage(Global.rd.getFromAsImageIcon(Manager.SLIDERS_BUTTON_DEFAULT_ICON), 16, 16));
    setToolTipText("Open a window to display sliders that can alter certain things");
    setOpaque(true);
    setBorder(null);
    setDoubleBuffered(true);
    addActionListener(this);
    setBackground(null);
    setContentAreaFilled(false);
    isPressed = false;
    dialog = new SlidersDialog();
    dialog.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        isPressed = false;
      }
    });
  }

  @Override
  public JComponent getComponent() {
    return this;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if(!isPressed) {
      dialog.run();
    }
  }

}

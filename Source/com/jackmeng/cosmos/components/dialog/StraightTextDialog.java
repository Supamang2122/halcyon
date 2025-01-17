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

package com.jackmeng.cosmos.components.dialog;

import java.awt.Dimension;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import com.jackmeng.halcyon.Halcyon;
import com.jackmeng.halcyon.constant.Global;
import com.jackmeng.halcyon.constant.Manager;

public class StraightTextDialog extends JFrame implements Runnable {
  private JScrollPane pane;
  private JEditorPane text;

  public StraightTextDialog(String content) {
    setIconImage(Global.rd.getFromAsImageIcon(Manager.PROGRAM_ICON_LOGO).getImage());
    setTitle("Information");
    setPreferredSize(new Dimension(250, 100));
    setAutoRequestFocus(true);

    text = new JEditorPane("text/html", content);
    text.setEditable(false);
    text.setFocusable(false);

    pane = new JScrollPane(text);
    pane.setPreferredSize(new Dimension(250, 100));
    pane.getViewport().setPreferredSize(new Dimension(250, 100));

    setLocationRelativeTo(Halcyon.bgt.getFrame());

    setContentPane(pane);
  }

  @Override
  public void run() {
    SwingUtilities.invokeLater(() -> {
      pack();
      setVisible(true);
    });
  }
}

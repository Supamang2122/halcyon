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

package com.jackmeng.cosmos.components.bottompane;

import com.jackmeng.cosmos.events.FVRightClick;
import com.jackmeng.cosmos.events.FVRightClick.RightClickHideItemListener;
import com.jackmeng.halcyon.constant.Global;
import com.jackmeng.halcyon.constant.Manager;
import com.jackmeng.halcyon.filesystem.PhysicalFolder;
import com.jackmeng.halcyon.filesystem.VirtualFolder;
import com.jackmeng.halcyon.utils.DeImage;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a Pane containing a list of files for only
 * one directory. It will not contain any sub-directories.
 *
 * This file list can contain any file type, but it will be decided
 * beforehand.
 *
 * This mechanism suggested by FEATURES#8 and deprecated
 * the original tabs mechanism of 3.0.
 *
 * @author Jack Meng
 * @since 3.1
 */
public class FileList extends JScrollPane implements TabTree {
  private JTree tree;

  /**
   * Represents a list of collected files throughout the
   * current selected folder for this instance of a FileList.
   *
   * Parameter 1: {@link java.io.File} A file object representing a file in the
   * folder.
   * Parameter 2: {@link javax.swing.tree.DefaultMutableTreeNode} The node
   * instance of the file as represented on the JTree.
   */
  private Map<File, DefaultMutableTreeNode> fileMap;

  private transient PhysicalFolder info;

  private DefaultMutableTreeNode root;

  public boolean isVirtual;

  public FileList(PhysicalFolder info, Icon closed, Icon open, Icon leaf, String rightClickHideString, RightClickHideItemListener hideStringTask) {
    super();
    this.info = info;
    fileMap = new HashMap<>();
    root = new DefaultMutableTreeNode(info.getName());
    isVirtual = info instanceof VirtualFolder;
    setAutoscrolls(true);
    setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    setPreferredSize(new Dimension(Manager.FILEVIEW_MIN_WIDTH, Manager.FILEVIEW_MIN_HEIGHT));
    setMinimumSize(new Dimension(Manager.FILEVIEW_MIN_WIDTH, Manager.FILEVIEW_MIN_HEIGHT));
    setBorder(null);
    for (File f : info.getFiles(Manager.ALLOWED_FORMATS)) {
      if (f != null) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(f.getName());
        fileMap.put(f, node);
        root.add(node);
      }
    }

    tree = new JTree(root);
    tree.setRootVisible(true);
    tree.setShowsRootHandles(true);
    tree.setExpandsSelectedPaths(true);
    tree.setEditable(false);
    tree.setRequestFocusEnabled(false);
    tree.setScrollsOnExpand(true);
    tree.setAutoscrolls(true);
    tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

    DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) tree.getCellRenderer();
    renderer.setClosedIcon(DeImage.resizeImage((ImageIcon) closed, 16, 16));
    renderer.setOpenIcon(DeImage.resizeImage((ImageIcon) open, 16, 16));
    renderer.setLeafIcon(DeImage.resizeImage((ImageIcon) leaf, 16, 16));

    tree.addMouseListener(new FVRightClick(this, rightClickHideString, hideStringTask));
    tree.setCellRenderer(renderer);

    getViewport().add(tree);
    getViewport().setBorder(null);
  }

  public FileList(PhysicalFolder info) {
    this.info = info;
    fileMap = new HashMap<>();
    root = new DefaultMutableTreeNode(info.getName());
    isVirtual = info instanceof VirtualFolder;
    setAutoscrolls(true);
    setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    setPreferredSize(new Dimension(Manager.FILEVIEW_MIN_WIDTH, Manager.FILEVIEW_MIN_HEIGHT));
    setMinimumSize(new Dimension(Manager.FILEVIEW_MIN_WIDTH, Manager.FILEVIEW_MIN_HEIGHT));

    for (File f : info.getFiles(Manager.ALLOWED_FORMATS)) {
      if (f != null) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(f.getName());
        fileMap.put(f, node);
        root.add(node);
      }
    }

    tree = new JTree(root);
    tree.setRootVisible(true);
    tree.setShowsRootHandles(true);
    tree.setExpandsSelectedPaths(true);
    tree.setScrollsOnExpand(true);
    tree.setEditable(false);
    tree.setRequestFocusEnabled(false);
    tree.setScrollsOnExpand(true);
    tree.setAutoscrolls(true);
    tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
    setBorder(null);
    DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) tree.getCellRenderer();
    ImageIcon closedIcon = Global.rd.getFromAsImageIcon(Manager.FILEVIEW_ICON_FOLDER_CLOSED);
    ImageIcon openIcon = Global.rd.getFromAsImageIcon(Manager.FILEVIEW_ICON_FOLDER_OPEN);
    ImageIcon leafIcon = Global.rd.getFromAsImageIcon(Manager.FILEVIEW_ICON_FILE);
    renderer.setClosedIcon(DeImage.resizeImage(closedIcon, 16, 16));
    renderer.setOpenIcon(DeImage.resizeImage(openIcon, 16, 16));
    renderer.setLeafIcon(DeImage.resizeImage(leafIcon, 16, 16));

    tree.addMouseListener(new FVRightClick(this));
    tree.setCellRenderer(renderer);

    getViewport().add(tree);
    getViewport().setBorder(null);
  }

  /**
   * @return The JTree representing this viewport.
   */
  public JTree getTree() {
    return tree;
  }

  /**
   * @return A FolderInfo object representing this FileList
   */
  public PhysicalFolder getFolderInfo() {
    return info;
  }

  /**
   * @return A Node that represents the root node.
   */
  public DefaultMutableTreeNode getRoot() {
    return root;
  }

  /**
   * @return Returns the default file map with each File object having a node.
   */
  public Map<File, DefaultMutableTreeNode> getFileMap() {
    return fileMap;
  }

  /**
   * This function facilitates reloading the current
   * folder:
   *
   * 1. If a file doesn't exist anymore, it will be removed
   * 2. If a new file has been added, it will be added into the Tree
   *
   * The detection on if a folder exists or not is up to the parent
   * BottomPane {@link com.jackmeng.cosmos.components.bottompane.BottomPane}.
   */
  public void revalidateFiles() {
    for (File f : info.getFiles(Manager.ALLOWED_FORMATS)) {
      if (f != null && !fileMap.containsKey(f)) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(f.getName());
        fileMap.put(f, node);
        root.add(node);
        ((DefaultTreeModel) tree.getModel()).reload();
      }
    }
    List<File> toRemove = new ArrayList<>();
    for (File f : fileMap.keySet()) {
      if (!f.exists() || !f.isFile()) {
        ((DefaultTreeModel) tree.getModel()).removeNodeFromParent(fileMap.get(f));
        toRemove.add(f);
      }
    }
    for (File f : toRemove) {
      fileMap.remove(f);
    }
  }


  /**
   * @param nodeName
   */
  @Override
  public void remove(String nodeName) {
    try {
      for (File f : fileMap.keySet()) {
        if (f.getName().equals(nodeName)) {
          DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
          model.removeNodeFromParent(fileMap.get(f));
          model.reload();
          fileMap.remove(f);
        }
      }
    } catch (IllegalArgumentException e) {
      // IGNORE
    }
  }


  /**
   * @param node
   * @return String
   */
  @Override
  public String getSelectedNode(DefaultMutableTreeNode node) {
    for (File f : fileMap.keySet()) {
      if (fileMap.get(f).equals(node)) {
        return f.getAbsolutePath();
      }
    }
    return "";
  }

  @Override
  public String getPath() {
    return info.getAbsolutePath();
  }
}

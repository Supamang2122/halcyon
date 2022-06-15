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

package com.jackmeng.constant;

import com.jackmeng.app.connections.properties.ResourceFolder;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * A constant defined class that holds
 * values for any external resources, such as
 * the properties file for the program config.
 *
 * @author Jack Meng
 * @since 3.0
 * @see com.jackmeng.app.connection.properties.ResourceFolder
 */
public class ProgramResourceManager {

  private ProgramResourceManager() {
  }

  public static final String FILE_SLASH = "/";
  public static final String PROGRAM_RESOURCE_FOLDER = "halcyon";
  public static final String PROGRAM_RESOURCE_FILE_PROPERTIES = "halcyon.properties";
  public static final String KEY_USER_DEFAULT_FOLDER = "user.default.folder";
  public static final String VALUE_USER_DEFAULT_FOLDER = ".";
  public static final String KEY_USE_MEDIA_TITLE_AS_INFOVIEW_HEADER = "audio.info.media_title_as_header";
  public static final String VALUE_USE_MEDIA_TITLE_AS_INFOVIEW_HEADER = "true";
  public static final String[] RESOURCE_SUBFOLDERS = { "log", "bin" };
  public static final String DEFAULT_ARTWORK_FILE_NAME = "artwork_cache.png";

  /**
   * @return The Map of default properties
   */
  public static Map<String, String> getProgramDefaultProperties() {
    Map<String, String> properties = new HashMap<>();
    properties.put(KEY_USER_DEFAULT_FOLDER, VALUE_USER_DEFAULT_FOLDER);
    properties.put(KEY_USE_MEDIA_TITLE_AS_INFOVIEW_HEADER,
        VALUE_USE_MEDIA_TITLE_AS_INFOVIEW_HEADER);
    return properties;
  }

  /**
   * @return The map of the allowed properties
   */
  public static Map<String, String[]> getAllowedProperties() {
    Map<String, String[]> properties = new HashMap<>();
    properties.put(KEY_USER_DEFAULT_FOLDER, new String[] {});
    properties.put(KEY_USE_MEDIA_TITLE_AS_INFOVIEW_HEADER,
        new String[] { "true", "false" });
    return properties;
  }

  /**
   * Writes a bufferedimage to the resource folder.
   * 
   * @param img An image to write; a BufferedImage instance
   * @return The string representing the location of the image (ABSOLUTE PATH)
   */
  public static String writeBufferedImageToBin(BufferedImage img) {
    return ResourceFolder.writeBufferedImageCacheFile(
        img,
        RESOURCE_SUBFOLDERS[1],
        DEFAULT_ARTWORK_FILE_NAME);
  }
}

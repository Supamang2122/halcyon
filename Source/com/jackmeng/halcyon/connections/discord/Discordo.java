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

package com.jackmeng.halcyon.connections.discord;

import com.jackmeng.cosmos.components.toppane.layout.InfoViewTP.InfoViewUpdateListener;
import com.jackmeng.halcyon.debug.Debugger;
import com.jackmeng.halcyon.utils.TextParser;
import com.jackmeng.tailwind.AudioInfo;
import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;

/**
 * Represents the Discord Rich Presence Interfacing.
 *
 * This class was originally found in 2.0 but I later
 * decided to change this and it is what it is now.
 *
 * It should not be called by any external processes, and
 * should remain independent. Due to this, this class is completely
 * "lonely" and must rely completely on listeners and process calls
 * to function and/or update it's own state.
 *
 * @author Jack Meng
 * @since 2.0
 */
public class Discordo implements InfoViewUpdateListener {

  protected static DiscordRichPresence rpc;
  protected static final String PROJECT_ID = "989355331761086475";
  private static final String STATE = "Listening to\n ", NOTHING_MUSIC = "Nothing";

  /**
   * Starts the dispatch of the RPC
   */
  public void start() {
    DiscordEventHandlers handlers = new DiscordEventHandlers.Builder()
        .setReadyEventHandler(
            user -> Debugger.good("Connected to Discord user: " + user.username + "#" + user.discriminator))
        .build();
    DiscordRPC.discordInitialize(PROJECT_ID, handlers, true);

    rpc = new DiscordRichPresence.Builder(STATE
        + NOTHING_MUSIC)
        .setBigImage("logo", "Halcyon")
        .build();
    DiscordRPC.discordUpdatePresence(rpc);
  }


  /**
   * @param title A title to dispatch as
   */
  public void set(String title) {
    DiscordEventHandlers handlers = new DiscordEventHandlers.Builder()
        .setReadyEventHandler(
            user -> Debugger.log("Connected to Discord user: " + user.username + "#" + user.discriminator))
        .build();
    DiscordRPC.discordInitialize(PROJECT_ID, handlers, true);
    String b = STATE + TextParser.parseAsPure(title);
    rpc = new DiscordRichPresence.Builder(b)
        .setBigImage("logo", "Halcyon")
        .build();
    DiscordRPC.discordUpdatePresence(rpc);
  }

  @Override
  public void infoView(AudioInfo info) {
    set(info.getTag(AudioInfo.KEY_MEDIA_TITLE));
  }

}

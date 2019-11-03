package com.gmail.chickenpowerrr.ranksync.server.reward;

import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * This class contains the settings for a reward system
 *
 * @author Chickenpowerrr
 * @since 1.4.0
 */
@Getter
@AllArgsConstructor
public class RewardSettings implements com.gmail.chickenpowerrr.ranksync.api.reward.RewardSettings {

  private final com.gmail.chickenpowerrr.ranksync.api.reward.RewardSettings.RewardAction syncAction;
  private final com.gmail.chickenpowerrr.ranksync.api.reward.RewardSettings.RewardAction unsyncAction;

  /**
   * This class contains all settings for a reward action
   *
   * @author Chickenpowerrr
   * @since 1.4.0
   */
  @Getter
  @AllArgsConstructor
  public static class RewardAction implements
      com.gmail.chickenpowerrr.ranksync.api.reward.RewardSettings.RewardAction {

    private final int maxEnabled;
    private final boolean enabled;
    private final List<String> commands;

    /**
     * Returns the commands that should be executed when someone syncs their account, %player%
     * is the placeholder for the player who synced their account
     */
    public List<String> getCommands() {
      return Collections.unmodifiableList(this.commands);
    }
  }
}

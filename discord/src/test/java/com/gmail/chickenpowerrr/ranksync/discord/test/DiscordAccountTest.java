package com.gmail.chickenpowerrr.ranksync.discord.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.gmail.chickenpowerrr.ranksync.core.user.User;
import com.gmail.chickenpowerrr.ranksync.core.user.UserLink;
import com.gmail.chickenpowerrr.ranksync.discord.DiscordAccount;
import com.gmail.chickenpowerrr.ranksync.discord.DiscordPlatform;
import com.gmail.chickenpowerrr.ranksync.discord.RoleRankResource;
import java.util.ArrayList;
import java.util.List;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@SuppressWarnings("unchecked")
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
public class DiscordAccountTest {

  private static final String MEMBER_NAME = "name";
  private static final String PLATFORM_FORMAT = "Platform: %name%";
  private static final String RANK_1_FORMAT = "Rank 1: %name%";
  private static final String RANK_2_FORMAT = "Rank 2: %name%";
  private static final String RANK_3_FORMAT = "Rank 3: %name%";

  private User user;
  private DiscordAccount account;
  private RoleRankResource rankResource;
  private DiscordPlatform discordPlatform;
  private UserLink<DiscordPlatform> userLink;

  @Mock
  private Member member;

  @Mock
  private Role role1, role2, role3;

  @BeforeEach
  public void setUp() {
    this.discordPlatform = new DiscordPlatform(PLATFORM_FORMAT);

    List<UserLink<DiscordPlatform>> userLinks = new ArrayList<>();
    List<Role> roles = new ArrayList<>();
    roles.add(this.role1);
    roles.add(this.role2);
    roles.add(this.role3);

    this.user = new User((List<UserLink<?>>) (List<?>) userLinks);
    this.account = new DiscordAccount(this.member, this.discordPlatform, userLinks);
    this.discordPlatform.addRankResource(this.rankResource);
    this.userLink = new UserLink<>(this.account, this.user);

    userLinks.add(this.userLink);

    when(this.member.getEffectiveName()).thenReturn(MEMBER_NAME);
    when(this.member.getRoles()).thenReturn(roles);
    when(this.role1.getName()).thenReturn("Role 1");
    when(this.role1.getId()).thenReturn("1");
    when(this.role2.getName()).thenReturn("Role 2");
    when(this.role2.getId()).thenReturn("2");
    when(this.role3.getName()).thenReturn("Role 3");
    when(this.role3.getId()).thenReturn("3");
  }

  @Test
  public void testName() {
    assertThat(this.account.getName()).isEqualTo(MEMBER_NAME);
  }

  @Test
  public void testFormat() throws Exception {
    String name = this.account.formatName().get();
  }
}

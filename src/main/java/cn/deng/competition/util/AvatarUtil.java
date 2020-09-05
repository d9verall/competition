package cn.deng.competition.util;

import java.util.UUID;

/**
 * @author verall
 */
public class AvatarUtil {

  private AvatarUtil() {
  }

  public static String generate() {
    return "http://identicon.relucks.org/" + UUID.randomUUID() + "?size=500";
  }
}

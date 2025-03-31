package com.axonivy.connectivity.rest;

import java.util.Locale;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.user.NewUser;

public class UserCreator {

  public static void createUsers(String prefix, int amount) {
    var security = Ivy.security();
    for (int i = 0; i < amount; i++) {
      if (i % 100 == 0) {
        System.out.println("/" + i);
      }
      String userN = prefix + i;
      IUser user = security.users().find(userN);
      if (user == null) {
        var newUser = NewUser.create(userN)
            .fullName(userN)
            .password(userN)
            .language(Locale.GERMAN)
            .mailAddress("nomail@junit.com")
            .externalName(userN).toNewUser();
        user = security.users().create(newUser);
      }
    }
  }
}

package db.demos.persistence;

import ch.ivyteam.ivy.jsf.primefaces.model.LazyRepositoryDataModel;

public class PlayerDataModel extends LazyRepositoryDataModel<Player> {

  private static final long serialVersionUID = 1L;

  public PlayerDataModel() {
    super(Player.repository());
  }
}

package se481.project.transmatter.tmuser.dao;

import se481.project.transmatter.tmuser.entity.TmUser;

public interface TmUserDao {
    TmUser markRecipe(TmUser user);
    TmUser unMarkRecipe(TmUser user);
}

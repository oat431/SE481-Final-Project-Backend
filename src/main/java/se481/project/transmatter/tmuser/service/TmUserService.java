package se481.project.transmatter.tmuser.service;

import se481.project.transmatter.recipe.entity.Recipe;
import se481.project.transmatter.tmuser.entity.TmUser;

public interface TmUserService {
    TmUser markRecipe(TmUser user, Recipe recipe);
    TmUser unMarkRecipe(TmUser user, Recipe recipe);
}

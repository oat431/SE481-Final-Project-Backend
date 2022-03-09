package se481.project.transmatter.tmuser.service;

import org.springframework.beans.factory.annotation.Autowired;
import se481.project.transmatter.recipe.entity.Recipe;
import se481.project.transmatter.tmuser.dao.TmUserDao;
import se481.project.transmatter.tmuser.entity.TmUser;

public class TmUserServiceImpl implements TmUserService{
    @Autowired
    TmUserDao tmUserDao;

    @Override
    public TmUser markRecipe(TmUser user, Recipe recipe) {
        user.getMark().add(recipe);
        return tmUserDao.markRecipe(user);
    }

    @Override
    public TmUser unMarkRecipe(TmUser user, Recipe recipe) {
        int index = 0;
        for(Recipe i : user.getMark()){
            if(recipe.equals(i)){
                user.getMark().remove(i);
                break;
            }
        }
        return tmUserDao.unMarkRecipe(user);
    }
}

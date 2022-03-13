package se481.project.transmatter.tmuser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se481.project.transmatter.recipe.dao.RecipeDao;
import se481.project.transmatter.recipe.entity.Recipe;
import se481.project.transmatter.tmuser.dao.TmUserDao;
import se481.project.transmatter.tmuser.entity.TmUser;

@Service
public class TmUserServiceImpl implements TmUserService{
    @Autowired
    TmUserDao tmUserDao;

    @Autowired
    RecipeDao recipeDao;

    @Override
    public TmUser markRecipe(TmUser user, Recipe recipe) {
        recipeDao.addRecipe(recipe);
        user.getMark().add(recipe);
        return tmUserDao.markRecipe(user);
    }

    @Override
    public TmUser unMarkRecipe(TmUser user, Recipe recipe) {
        for(Recipe i : user.getMark()){
            if(recipe.getTitle().equals(i.getTitle())){
                System.out.println(i.getTitle());
                user.getMark().remove(i);
                break;
            }
        }
        return tmUserDao.unMarkRecipe(user);
    }

    @Override
    public TmUser getUser(Long id) {
        return tmUserDao.getUser(id);
    }
}

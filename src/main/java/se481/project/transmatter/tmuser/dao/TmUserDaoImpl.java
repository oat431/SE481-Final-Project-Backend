package se481.project.transmatter.tmuser.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import se481.project.transmatter.tmuser.entity.TmUser;
import se481.project.transmatter.tmuser.repository.TmUserRepository;

@Repository
public class TmUserDaoImpl implements TmUserDao{

    @Autowired
    TmUserRepository tmUserRepository;

    @Override
    public TmUser markRecipe(TmUser user) {
        return tmUserRepository.save(user);
    }

    @Override
    public TmUser unMarkRecipe(TmUser user) {
        return tmUserRepository.save(user);
    }
}

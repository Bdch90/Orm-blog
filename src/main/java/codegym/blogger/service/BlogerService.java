package codegym.blogger.service;

import codegym.blogger.model.Blogger;

import java.util.List;

public interface BlogerService
{
    List<Blogger> findAll();

    Blogger findById(long id);

    void save(Blogger blogger);

    void remove(long id);
}

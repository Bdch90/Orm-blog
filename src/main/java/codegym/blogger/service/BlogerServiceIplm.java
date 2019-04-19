package codegym.blogger.service;

import codegym.blogger.model.Blogger;
import codegym.blogger.repository.BloggerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BlogerServiceIplm implements BlogerService
{
    @Autowired
    private BloggerRepository bloggerRepository;

    @Override
    public List<Blogger> findAll() {
        return bloggerRepository.findAll();
    }

    @Override
    public Blogger findById(long id)
    {
        return bloggerRepository.findById(id);
    }

    @Override
    public void save(Blogger bloger)
    {
        bloggerRepository.save(bloger);
    }

    @Override
    public void remove(long id)
    {
        bloggerRepository.remove(id);
    }
}

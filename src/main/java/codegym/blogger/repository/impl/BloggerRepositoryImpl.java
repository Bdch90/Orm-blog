package codegym.blogger.repository.impl;

import codegym.blogger.model.Blogger;
import codegym.blogger.repository.BloggerRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class BloggerRepositoryImpl implements BloggerRepository
{
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Blogger> findAll()
    {
        TypedQuery<Blogger> query = em.createQuery("select c from Blogger c", Blogger.class);
        return query.getResultList();
    }

    @Override
    public Blogger findById(long id)
    {
        TypedQuery<Blogger> query = em.createQuery("select c from Blogger c where c.id=:id", Blogger.class);
        query.setParameter("id",id);
        return query.getSingleResult();
    }

    @Override
    public void save(Blogger model)
    {
        if (model.getId() != null)
        {
            em.merge(model);
        }
        else
        {
            em.persist(model);
        }
    }

    @Override
    public void remove(long id)
    {
        Blogger blogger = findById(id);
        if (blogger != null)
        {
            em.remove(blogger);
        }
    }
}

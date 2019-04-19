package codegym.blogger.controller;

import codegym.blogger.model.Blogger;
import codegym.blogger.service.BlogerService;
import codegym.blogger.service.BlogerServiceIplm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BloggerController
{
    @Autowired
    BlogerService blogerService = new BlogerServiceIplm();

    @GetMapping("/home")
    public ModelAndView showBlogList()
    {
        List<Blogger> bloggers = blogerService.findAll();

        ModelAndView modelAndView = new ModelAndView("/blog/home");
        modelAndView.addObject("blogs", bloggers);
        return modelAndView;
    }

    @GetMapping("/create-blog")
    public ModelAndView showCreateForm()
    {
        ModelAndView modelAndView = new ModelAndView("/blog/create");
        modelAndView.addObject("blog", new Blogger());
        return modelAndView;
    }

    @PostMapping("/create-blog")
    public ModelAndView createBlog(@ModelAttribute("blog") Blogger blogger)
    {
        blogerService.save(blogger);
        ModelAndView modelAndView = new ModelAndView("/blog/create");
        modelAndView.addObject("blog", blogger);
        modelAndView.addObject("message","Add successfully");
        return modelAndView;
    }

    @GetMapping("/edit-blog/{id}")
    public ModelAndView showUpdateForm(@PathVariable Long id)
    {
        Blogger blogger = blogerService.findById(id);
        if(blogger != null)
        {
            ModelAndView modelAndView = new ModelAndView("/blog/edit");
            modelAndView.addObject("blog",blogger);
            return modelAndView;
        }
        else
        {
            ModelAndView modelAndView = new ModelAndView("/error404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-blog")
    public ModelAndView updateBlog(@ModelAttribute("blog") Blogger blogger)
    {
        blogerService.save(blogger);

        ModelAndView modelAndView = new ModelAndView("/blog/edit");
        modelAndView.addObject("blog",blogger);
        modelAndView.addObject("message","Update successfull");
        return modelAndView;
    }

    @GetMapping("/view-blog/{id}")
    public ModelAndView viewBlog(@PathVariable Long id)
    {
        Blogger blogger = blogerService.findById(id);
        ModelAndView modelAndView;
        if(blogger != null)
        {
            modelAndView = new ModelAndView("/blog/view");
            modelAndView.addObject("blog",blogger);
        }
        else
        {
            modelAndView = new ModelAndView("/error404");
        }
        return modelAndView;
    }

    @GetMapping("/delete-blog/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id)
    {
        Blogger blogger = blogerService.findById(id);
        ModelAndView modelAndView;
        if(blogger != null)
        {
            modelAndView = new ModelAndView("/blog/delete");
            modelAndView.addObject("blog",blogger);
        }
        else
        {
            modelAndView = new ModelAndView("/error404");
        }
        return modelAndView;
    }

    @PostMapping("/delete-blog")
    public String deleteBlog(@ModelAttribute("blog") Blogger blogger)
    {
        blogerService.remove(blogger.getId());
        return "redirect:home";
    }
}

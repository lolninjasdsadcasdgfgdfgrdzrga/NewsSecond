package Root.Cntroller;

import Root.Model.News;
import Root.Model.Role;
import Root.Model.Teg;
import Root.Model.User;

import Root.Repository.NewRepository;
import Root.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class MainController {
    @Autowired
    NewRepository newRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/news")
    public String jj(Model model) {
        List<News> list = newRepository.findAll();
        model.addAttribute("f", list);

        return "main";


    }

    @PostMapping("/addnews")
    public String dd(@RequestParam("Title") String title,
                     @RequestParam("text") String text,
                     @RequestParam("category") String category,
                     @AuthenticationPrincipal User user
    ) {
        News news = new News();
        news.setTitle(title);
        news.setContent(text);
        List<Teg> listOfTegs = new ArrayList<>();
        Teg teg = Teg.valueOf(category);
        listOfTegs.add(teg);
        Date date = new Date();
        news.setTegs(listOfTegs);
        news.setDate(date);
        news.setUser(user);
        newRepository.save(news);
        System.out.println(user);

        return "redirect:/news";

    }

    @PostMapping("filterbycategory")
    public String filter(@RequestParam(value = "POLITIC", required = false) String politic, @RequestParam(value = "INNOVATION", required = false) String inovation, @RequestParam(value = "FOREST", required = false) String forest, @RequestParam(value = "SPORT", required = false) String sport, Model model) {
        List<Teg> tegs = new ArrayList<>();
        if (politic != null) {

            tegs.add(Teg.POLITIC);
        }
        if (inovation != null) {

            tegs.add(Teg.INNOVATION);
        }
        if (forest != null) {

            tegs.add(Teg.FOREST);
        }
        if (sport != null) {

            tegs.add(Teg.SPORT);
        }
        if (sport == null & forest == null & inovation == null & politic == null) {
            tegs.add(Teg.POLITIC);
            tegs.add(Teg.INNOVATION);
            tegs.add(Teg.FOREST);
            tegs.add(Teg.FOREST);
        }

        List<News> h = newRepository.findAll();
        List<News> f = new ArrayList<>();

        for (int i = 0; i < h.size(); i = i + 1) {
            ArrayList<Teg> g = new ArrayList<>(tegs);
            g.retainAll(h.get(i).getTeg());
            if (g.size() > 0) {
                f.add(h.get(i));
            }
        }
        model.addAttribute("f", f);
        return "main";
    }

    @PostMapping("filterbydate")
    public String FindDate(@RequestParam(value = "date", required = false)
                           @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date, Model model) {
        List<News> dates = newRepository.findByDateAfter(date);
        List<News> allNews = newRepository.findAll();
        if (date == null) {
            model.addAttribute("f", allNews);
        } else {
            model.addAttribute("f", dates);
        }

        return "main";
    }

    @GetMapping("/login")
    public String Login() {

        return "login";

    }

    @PostMapping("/login")

    public String login(@RequestParam(name = "username") String name, @RequestParam(name = "password") String password, @RequestParam(name = "passwordConfirm") String passwordConfirm, Model model) {
        if (userRepository.existsUserByUsername(name)) {

            model.addAttribute("errorMessage", "Choose a different username");
            return "login";
        }

        if (password.equals(passwordConfirm)) {
            User user = new User();
            user.setPassword(password);
            user.setUsername(name);
            Set<Role> role = new HashSet<>();
            role.add(Role.ADMIN);
            user.setActive(true);
            user.setRoles(role);
            userRepository.save(user);

        }


        return "login";

    }

    @GetMapping("/registration")
    public String d() {
        return "registration";
    }

    @PostMapping("registration")
    public String Registration( @RequestParam (name="username")String name,@RequestParam (name = "password")String password,@RequestParam (name="passwordOne")String passwordOne) {

     if (!password.equals(passwordOne)){
         return "registration";
     }
     List<User>h=userRepository.findAll();
     for (int i=0;i<h.size();i=i+1) {
         if (h.get(i).equals(name)) {
             return "registration";
         }
     }
     User user=new User();
         user.setUsername(name);
         user.setPassword(password);
     user.setActive(true);
Set<Role>roles=new HashSet<>();
roles.add(Role.USER);
user.setRoles(roles);

userRepository.save(user);
      return "registration";

    }

}



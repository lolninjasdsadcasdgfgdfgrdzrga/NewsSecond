package Root.Cntroller;

import Root.Model.News;
import Root.Model.Role;
import Root.Model.User;
import Root.Repository.NewRepository;
import Root.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class AdminController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    NewRepository newsRepository;

    @GetMapping("admin")
    public String open(Model model) {
        List<User> f = userRepository.findAll();
        model.addAttribute("userList", f);


        return "admin";
    }

    @GetMapping("/user/ban/{id}")
    public String ban(@PathVariable Long id) {
        User g = userRepository.findById(id).get();
        g.setActive(false);
        userRepository.save(g);
        return "redirect:/admin";
    }

    @GetMapping("user/change/{id}")
    public String update(@PathVariable Long id,Model model) {
        User user = userRepository.findById(id).get();



        model.addAttribute("past", user.getId() );
model.addAttribute("name",user.getUsername());
model.addAttribute("password",user.getPassword());
        return "editUser";
    }
 @PostMapping("/change")
    public String change (@RequestParam (name="username")String userName,@RequestParam(name = "password")
         String password,@RequestParam (name="admin",required = false)String admin,@RequestParam (name = "user",required = false)String user,@RequestParam(name = "ID")Long id){
Set<Role> roles=new HashSet<>();
if (admin.equals("on")) {
    roles.add(Role.ADMIN);

}
if (user.equals("on")){
roles.add(Role.USER);
}
User user1=new User();
    user1.setUsername(userName);
    user1.setPassword(password);
   user1.setRoles(roles);
    user1.setId(id);
    user1.setActive(true);
    userRepository.save(user1);

    return "redirect:/admin";
    }
}


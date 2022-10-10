package Root.Repository;

import Root.Model.News;
import Root.Model.User;
import net.bytebuddy.utility.dispatcher.JavaDispatcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository

public interface UserRepository extends JpaRepository<User, Long> {
public boolean existsUserByUsername(String username);
public User findByUsername(String username);



}

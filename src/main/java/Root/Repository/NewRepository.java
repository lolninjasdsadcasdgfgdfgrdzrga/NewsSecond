package Root.Repository;

import Root.Model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public interface NewRepository extends JpaRepository<News, Long> {
List<News>findByDateAfter(Date date);
}

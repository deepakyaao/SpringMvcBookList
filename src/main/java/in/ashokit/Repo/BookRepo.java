package in.ashokit.Repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.ashokit.Entity.Book;

@Repository
public interface BookRepo  extends JpaRepository<Book, Integer>{

	//use for SOFT DELETE
	public List<Book> findByActiveSW(String status);
}

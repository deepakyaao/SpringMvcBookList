package in.ashokit.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import in.ashokit.Entity.Book;
import in.ashokit.Repo.BookRepo;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	private BookRepo bookRepo;
	
	/* use below method when Not Implementing SOFT DELETE
	@Override
	public List<Book> getAllBooks() {
		
		List<Book> book = bookRepo.findAll();
		
		return book;
	}*/
	
	@Override
	public List<Book> getAllBooks() {
		
		return bookRepo.findByActiveSW("Y");
		
	}
	
	@Override
	public boolean saveBook(Book book) {
		
		 book.setActiveSW("Y");//for soft delete,setting Switch to YES
		 Book savedEntity=bookRepo.save(book);
		return savedEntity.getBookId()!=null;
	}

	
    /* use below method for HARD DELETE
	public void deleteBook(Integer bookId) {
		bookRepo.deleteById(bookId);
	}*/
	
	
	//for SOFT DELETE
	public void deleteBook(Integer bookId) {
		
		Optional<Book> book=bookRepo.findById(bookId);
		if(book.isPresent()) {
		   Book b=book.get();
		   b.setActiveSW("N");
		   bookRepo.save(b);
		}
	}
	
	public Book getBookId(Integer bookId) {
		Optional<Book> book=bookRepo.findById(bookId);
		if(book.isPresent()) {
			Book b=book.get();
		
		return b;
		}
		return null;
	}
	
}

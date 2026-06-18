package in.ashokit.Service;

import java.util.List;

import in.ashokit.Entity.Book;

public interface BookService {

	public List<Book> getAllBooks();
	
	public boolean saveBook(Book book);
	
	public void deleteBook(Integer bookId);
	
	public Book getBookId(Integer bookId);
	
}

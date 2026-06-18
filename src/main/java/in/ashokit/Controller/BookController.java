package in.ashokit.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import in.ashokit.Entity.Book;
import in.ashokit.Entity.CartDto;
import in.ashokit.Entity.Users;
import in.ashokit.Repo.BookRepo;
import in.ashokit.Repo.UserRepo;
import in.ashokit.Service.BookService;
import in.ashokit.Service.BookServiceImpl;
import in.ashokit.Service.CartServiceImpl;

@Controller
public class BookController {
	
	@Autowired
	private BookServiceImpl bookService;
	
	@Autowired
	private CartServiceImpl cartService;
	
	@Autowired
	private UserRepo userRepo;
	
//we can send data from Controller to UI in 3 ways:
//1. ModelAndView class 2. Model interface 3. @ModelAttribute annotation 
	
  //1.using ModelAndView class	
	//method to display book form to insert new book 
	@GetMapping("/addBook")
	public ModelAndView addBook() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("book", new Book());
		mav.setViewName("addBook");
		
		return mav;
	}
	
/*2. using Model Interface
 *   //method to display book form to insert new book
 
		@GetMapping("/index")
		public String index(@ModelAttribute("book") Book book) {
			
			return "index";
		}
	
//3. using @ModelAttribute 
    //method to display book form to insert new book
    
		@GetMapping("/index")
		public String index(Model model) {
			model.addAttribute("book", new Book());
			return "index";
		}
*/
	
	//method to save new book inserted 
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/saveBook") 
	public ModelAndView saveBook( Book book) {
		
		
		ModelAndView mav = new ModelAndView();
		boolean b=bookService.saveBook(book);
		if(b==true)
		mav.addObject("successMsg","Record Saved");
		else
		mav.addObject("errMsg","Failed to Save");
		
		mav.setViewName("addBook");
		return mav;
	}
	
	//method to display book list
	@GetMapping("/viewBooks")
	public ModelAndView getAllBook() {
		ModelAndView mav = new ModelAndView();
		List<Book> book = bookService.getAllBooks();
		mav.addObject("book",book);//key is string, and value can be ANYTHING
		mav.setViewName("bookView");
		return mav;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/delete")//in below @RequestParam("bookId"-This name should be same as mentioned in addBook.html)
	public ModelAndView deleteBook(@RequestParam("bookId") Integer bookId){
		
		
		bookService.deleteBook(bookId);
		List<Book> allBooks=bookService.getAllBooks();
		ModelAndView mav = new ModelAndView();
		mav.addObject("book",allBooks);
		mav.setViewName("bookView");
		return mav;
		
		
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/edit")
	public ModelAndView editBook(@RequestParam("bookId") Integer bookId) {
		  
		Book book=bookService.getBookId(bookId);
		ModelAndView mav = new ModelAndView();
		mav.addObject("book",book);
		mav.setViewName("addBook");
		return mav;
		
	}
	
	
	@GetMapping("/test")
	public String test() {
		return "bookView";
	}
	
	@GetMapping("/buyNow")
	public String buyNow(Integer bookId) {

	    System.out.println("Buy Now with BookId : " + bookId);

	    return "redirect:/viewBooks";
	}
	
	@GetMapping("/cart/add")
	public String addToCart(@RequestParam Integer bookId) {

	    Integer userId = 1; // temporary

	    cartService.addToCart(userId, bookId);

	    return "redirect:/viewBooks";
	}
	
	@GetMapping("/cart")
	public ModelAndView viewCart(Principal principal) {

	    String email = principal.getName();
	    Users user = userRepo.findByEmail(email);

	    List<CartDto> cartItems =
	            cartService.getCartItems(user.getUserId());

	    ModelAndView mav = new ModelAndView();

	    mav.addObject("cartItems", cartItems);

	    mav.setViewName("cartView");

	    return mav;
	}
}

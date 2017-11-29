package pkgEmpty;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import pkgLibrary.Book;
import pkgLibrary.BookException;

public class BookTest {

	@Test
	public void testGetBook() throws BookException {
		Book b = Book.getBook("bk107");
		
		assertEquals(b.getTitle(), "Splish Splash");
		assertEquals(b.getAuthor(), "Thurman, Paula");
		assertEquals(b.getGenre(), "Romance");
	}
	@Test
	public void testAddBook() throws BookException, ParseException {
		
		Book b = new Book();
		b.setAuthor("XXX XXX");
		b.setTitle("XXXX XXXX");
		b.setGenre("XX XX XX");
		b.setDescription("XXXXXXXXXXXXXXXXXXX");
		b.setId("bk218");
		

		String inputString = "11-25-2017";
		DateFormat dateFormat = new SimpleDateFormat("MM-DD-yyyy");
		Date inputDate = dateFormat.parse(inputString);
		
		b.setPublish_date(inputDate);
		
		b.setPrice(5.55);
		
		Book.addBook("catID", b);
		
		Book b1 = Book.getBook("bk218");
		
		
		assertEquals(b1.getTitle(), "XXXX XXXX");
		assertEquals(b1.getAuthor(), "XXX XXX");
		assertEquals(b1.getGenre(), "XX XX XX");
		
	}
	

}

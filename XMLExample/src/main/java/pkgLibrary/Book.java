package pkgLibrary;

import java.io.File;
import java.util.ArrayList; 
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

public class Book {

	private String id;
	private String author;
	private String title;
	private String genre;
	private double price;
	private Date publish_date;
	private String description;
	private double cost;

	public Book() {

	}
	
	public static Book getBook(String ID) throws BookException {

		Catalog cat = ReadXMLFile();

		ArrayList<Book> books = cat.getBooks();

		Book rightBook = null;

		for (Book b : books) {

			if (b.getId().equals(ID)) {

				rightBook = b;

			}
		}
		if (rightBook != null) {
			return rightBook;
		}
		else{
			
			throw new BookException("Book not found!");}

	}
	
	public Book(String id) {
		id = id;
		try {
			author = getBook(id).getAuthor();
			title = getBook(id).getTitle();
			genre = getBook(id).getGenre();
			price = getBook(id).getPrice();
			publish_date = getBook(id).getPublish_date();
			description = getBook(id).getDescription();
			cost = price * .8;
		} catch (BookException e) {
			System.out.println(e.getMessage());
		}

	}

	
	private static void WriteXMLFile(Catalog cat) {
		try {

			String basePath = new File("").getAbsolutePath();
			basePath = basePath + "\\src\\main\\resources\\XMLFiles\\Books.xml";
			File file = new File(basePath);

			JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(cat, file);
			jaxbMarshaller.marshal(cat, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	

	public static void addBook(String catID, Book newBook) throws BookException {
		Catalog cat = ReadXMLFile();

		ArrayList<Book> books = cat.getBooks();

		Book rightBook = null;

		for (Book b : books) {

			if (b.getId().equals(newBook.getId())) {

				rightBook = b;

			}
		}
		if (rightBook == null) {

			ArrayList<Book> catBooks = cat.getBooks();

			catBooks.add(newBook);

			cat.setBooks(catBooks);
			WriteXMLFile(cat);
		}
		else{
			
			throw new BookException("Book already exists!");}

		


		}

	private static Catalog ReadXMLFile() {

		Catalog cat = null;

		String basePath = new File("").getAbsolutePath();
		basePath = basePath + "\\src\\main\\resources\\XMLFiles\\Books.xml";
		File file = new File(basePath);

		System.out.println(file.getAbsolutePath());
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			cat = (Catalog) jaxbUnmarshaller.unmarshal(file);
			System.out.println(cat);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return cat;

	}
	public Book(String id, String author, String title, String genre, double price, Date publish_date, String description)
	{
		super();
		this.id = id;
		this.author = author;
		this.title = title;
		this.genre = genre;		
		this.price = price;
		this.publish_date = publish_date;
		this.description = description;
	}
	
 

	public String getId() {
		return id;
	}

	@XmlAttribute
	public void setId(String id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	@XmlElement
	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	@XmlElement
	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	@XmlElement
	public void setGenre(String genre) {
		this.genre = genre;
	}

	public double getPrice() {
		return price;
	}

	@XmlElement
	public void setPrice(double price) {
		this.price = price;
	}

	public Date getPublish_date() {
		return publish_date;
	}

	@XmlElement
	public void setPublish_date(Date publish_date) {
		this.publish_date = publish_date;
	}

	public String getDescription() {
		return description;
	}

	@XmlElement
	public void setDescription(String description) {
		this.description = description;
	}

	
	

}

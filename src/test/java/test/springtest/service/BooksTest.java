package test.springtest.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.springtest.config.AppConfig;
import com.springtest.config.PersistenceConfig;
import com.springtest.data.Book;
import com.springtest.service.BookService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AppConfig.class, PersistenceConfig.class})
public class BooksTest {

	@Autowired
	private BookService books;
	
	@Test
	public void test() throws InterruptedException {
		final Book book1 = new Book(1, "A");
		books.createBook(book1);
		final Book book2 = new Book(2, "B");
		books.createBook(book2);

		final Book book3 = new Book(3, "C");
		books.createBookWithoutTx(book3);
		final Book bookAfterCommit = new Book(55, "AFTER COMMIT");
		books.triggerCreatePostCommit(bookAfterCommit);
		final Book book4 = new Book(4, "D");
		books.createBook(book4);
		
		assertEquals(book1, books.lookupBookById(1));
		assertEquals(book2, books.lookupBookById(2));
		assertEquals(book4, books.lookupBookById(4));
		assertEquals(book3, books.lookupBookById(3));
		
		Thread thread = new Thread() {
			@Override
			public void run() {
				System.out.println("Starting Thread");
				assertEquals(book3, books.lookupBookById(3));
				assertEquals(book4, books.lookupBookById(4));
				System.out.println("Lookup in Thread ok");
			}
		};
		
		thread.start();
		thread.join();
		
	}
}

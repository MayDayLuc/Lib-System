package service;

import model.Book;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class BookInfoServiceTest {

    private BookInfoService bookInfoService;

    @Before
    public void setUp() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        bookInfoService = (BookInfoService) applicationContext.getBean("bookInfoService");
    }

    @Test
    public void getAllBooks() {
        for (Book book: bookInfoService.getAllBooks()){
            System.out.println(book.getCategory().getName() + " " + book.getLastBorrow().getBorrowDate());
            assertEquals(book.getName(), "微积分");
        }
    }
}
package servlet;

import dao.BookCategoryDao;
import dao.BookDao;
import entity.BookCategory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "BookAddServlet")
public class BookAddServlet extends HttpServlet {
    BookCategoryDao bookCategoryDao = new BookCategoryDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String BookNumber = (String) request.getParameter("BookNumber");
        String Name = (String) request.getParameter("name");
        String Press = (String) request.getParameter("press");
        double Price = Double.parseDouble(request.getParameter("price"));
        String Category = request.getParameter("category");
        String Author = (String) request.getParameter("author");
        String Description = request.getParameter("description");
        //数据类型可能不对
        String AmountString = (String) request.getParameter("amount");
        int Amount = Integer.parseInt(AmountString);
        String Floor = (String) request.getParameter("floor");
        String Shelf = (String) request.getParameter("shelf");
        String AreaCode = (String) request.getParameter("areaCode");
        BookDao bookDao = new BookDao();
        boolean exit = bookDao.isExitInDB(BookNumber);
        if (exit) {
            response.sendRedirect("book_add.jsp?info=error");
        } else {
            bookDao.addBook(BookNumber, Name, Press, Price, Author, Category
                    , Amount, Floor, Shelf, AreaCode, Description);
            response.sendRedirect("book_add.jsp?info=success");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<BookCategory> bookCategories = bookCategoryDao.getCategories();
        request.setAttribute("bookCategories", bookCategories);
        request.getRequestDispatcher("book_add.jsp").forward(request, response);
    }
}

package servlet;

import dao.BookDao;
import org.springframework.expression.spel.ast.BooleanLiteral;
import view.MainView;
import vo.Book;
import vo.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookServlet {
    private List<Book> bookList = new ArrayList<>();
    private Scanner in = new Scanner(System.in);
    private MainView mainView=new MainView();
    private String []title={"增加图书","删除图书","修改图书","按照书名查询图书","所有图书","返回"};
    private BookDao bookDao=new BookDao();
    public void manageBooks(User user, String path) {
        bookList=bookDao.listAllBooks(path);
        while (true){
            System.out.println("欢迎"+user.getName()+"进入图书管理系统");
            mainView.show(title);
            System.out.println("请输入要进行的操作:");
            int i = in.nextInt();
            if (i == 1){
               add(path);
            } else if (i == 2){
              remove(path);
            } else if (i == 3){
                update(path);
            } else if (i == 4){
                search(path);
            } else if (i == 5){
                listAllBooks(path);
            } else {
                break;
            }
        }
    }

    private void search(String path) {
        System.out.println("请输入要查找的书名:");
        String name = in.next();
        for (Book book : bookList) {
            if (name.equals(book.getName())){
                System.out.println("您找的书如下");
                System.out.println("书名\t作者\t图书编号");
                System.out.println(book.getName()+"\t"+book.getAuthor()+"\t"+book.getISBN());
            }
        }


    }

    public void update(String path) {
        List<Book> bookList1 = listAllBooks(path);
        System.out.println("输入所要修改图书的编号");
        int i = in.nextInt();
        if (bookList.size()!=0){
            if (i>=1 && i<=bookList.size()){

                Book book = bookList.get(i-1);
                System.out.println("请输入新的内容:");
                System.out.println("1.书名");
                String name = in.next();
                System.out.println("2.作者");
                String author=in.next();
                System.out.println("3.ISBN");
                long isbn= in.nextLong();
                // 删除对象
                book.setName(name);
                book.setAuthor(author);
                book.setISBN(isbn);
                bookDao.saveData(path,bookList);
                System.out.println("修改成功");
            } else {
                System.out.println("输入无效!");
            }
        }


    }

    public void remove(String path) {
        List<Book> bookList1 = listAllBooks(path);
        System.out.println("输入所要删除图书的编号");
        int i = in.nextInt();
        if (bookList.size()!=0){
            if (i>=1 && i<=bookList.size()){

                Book book = bookList.get(i-1);

                // 删除对象
                bookList.remove(book);
                System.out.println("删除成功");
                bookDao.saveData(path,bookList);
            } else {
                System.out.println("输入无效!");
            }
        }

    }

    public List<Book> listAllBooks(String path) {
        System.out.println("书名\t作者\t图书编号");

        for (int i = 0; i < bookList.size(); i++) {
            System.out.println(i+1+"\t"+bookList.get(i).getName()+"\t"+bookList.get(i).getAuthor()+"\t"+bookList.get(i).getISBN());

        }
        return bookList;
    }

    public void add(String path) {
        System.out.println("请输入书名:");
        String name = in.next();
        System.out.println("请输入作者:");
        String author = in.next();

        long ISBN = System.currentTimeMillis();

        Book book = new Book();
        book.setName(name);
        book.setAuthor(author);
        book.setISBN(ISBN);

        bookList.add(book);

        System.out.println("添加成功!");
        bookDao.saveData(path,bookList);
    }
}

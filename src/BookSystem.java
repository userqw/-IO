import dao.BookDao;
import dao.UserDao;
import servlet.BookServlet;
import servlet.UserServlet;
import view.MainView;
import vo.Book;
import vo.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookSystem {
    private UserDao userDao=new UserDao();
    private BookDao bookDao=new BookDao();


    private List<User> userList = new ArrayList<>();
    private final static String BOOK_PATH = "./booklist";
    private final static String USER_PATH = "./userlist";
    private UserServlet userServlet = new UserServlet();
    private BookServlet bookServlet=new BookServlet();
    private Scanner in = new Scanner(System.in);
    private MainView mainView = new MainView();

    public void start() {
        String[] title = {"注册", "登录", "退出"};
        while (true) {
            mainView.show(title);
            System.out.println("请输入要进行的操作:");
            try {
                int i = in.nextInt();
                if (i == 1) {
                    userServlet.register(userList, USER_PATH);
                } else if (i == 2) {
                    User user = userServlet.login(USER_PATH);
                    if (user!=null){
                    // 正常运行登录成功之后的方法
                        bookServlet.manageBooks(user,BOOK_PATH);
                    }
                } else {
                    System.out.println("输入有误, 请重新输入");
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("输入有误, 请重新输入");
            }
        }

    }
}

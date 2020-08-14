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
        String[] title = {"ע��", "��¼", "�˳�"};
        while (true) {
            mainView.show(title);
            System.out.println("������Ҫ���еĲ���:");
            try {
                int i = in.nextInt();
                if (i == 1) {
                    userServlet.register(userList, USER_PATH);
                } else if (i == 2) {
                    User user = userServlet.login(USER_PATH);
                    if (user!=null){
                    // �������е�¼�ɹ�֮��ķ���
                        bookServlet.manageBooks(user,BOOK_PATH);
                    }
                } else {
                    System.out.println("��������, ����������");
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("��������, ����������");
            }
        }

    }
}

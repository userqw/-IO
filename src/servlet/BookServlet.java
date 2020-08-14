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
    private String []title={"����ͼ��","ɾ��ͼ��","�޸�ͼ��","����������ѯͼ��","����ͼ��","����"};
    private BookDao bookDao=new BookDao();
    public void manageBooks(User user, String path) {
        bookList=bookDao.listAllBooks(path);
        while (true){
            System.out.println("��ӭ"+user.getName()+"����ͼ�����ϵͳ");
            mainView.show(title);
            System.out.println("������Ҫ���еĲ���:");
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
        System.out.println("������Ҫ���ҵ�����:");
        String name = in.next();
        for (Book book : bookList) {
            if (name.equals(book.getName())){
                System.out.println("���ҵ�������");
                System.out.println("����\t����\tͼ����");
                System.out.println(book.getName()+"\t"+book.getAuthor()+"\t"+book.getISBN());
            }
        }


    }

    public void update(String path) {
        List<Book> bookList1 = listAllBooks(path);
        System.out.println("������Ҫ�޸�ͼ��ı��");
        int i = in.nextInt();
        if (bookList.size()!=0){
            if (i>=1 && i<=bookList.size()){

                Book book = bookList.get(i-1);
                System.out.println("�������µ�����:");
                System.out.println("1.����");
                String name = in.next();
                System.out.println("2.����");
                String author=in.next();
                System.out.println("3.ISBN");
                long isbn= in.nextLong();
                // ɾ������
                book.setName(name);
                book.setAuthor(author);
                book.setISBN(isbn);
                bookDao.saveData(path,bookList);
                System.out.println("�޸ĳɹ�");
            } else {
                System.out.println("������Ч!");
            }
        }


    }

    public void remove(String path) {
        List<Book> bookList1 = listAllBooks(path);
        System.out.println("������Ҫɾ��ͼ��ı��");
        int i = in.nextInt();
        if (bookList.size()!=0){
            if (i>=1 && i<=bookList.size()){

                Book book = bookList.get(i-1);

                // ɾ������
                bookList.remove(book);
                System.out.println("ɾ���ɹ�");
                bookDao.saveData(path,bookList);
            } else {
                System.out.println("������Ч!");
            }
        }

    }

    public List<Book> listAllBooks(String path) {
        System.out.println("����\t����\tͼ����");

        for (int i = 0; i < bookList.size(); i++) {
            System.out.println(i+1+"\t"+bookList.get(i).getName()+"\t"+bookList.get(i).getAuthor()+"\t"+bookList.get(i).getISBN());

        }
        return bookList;
    }

    public void add(String path) {
        System.out.println("����������:");
        String name = in.next();
        System.out.println("����������:");
        String author = in.next();

        long ISBN = System.currentTimeMillis();

        Book book = new Book();
        book.setName(name);
        book.setAuthor(author);
        book.setISBN(ISBN);

        bookList.add(book);

        System.out.println("��ӳɹ�!");
        bookDao.saveData(path,bookList);
    }
}

package servlet;

import dao.UserDao;
import vo.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.Scanner;

public class UserServlet {

    private UserDao userDao=new UserDao();
    private Scanner in = new Scanner(System.in);

    public void register(List<User> userList, String path) {
        // ע��

        System.out.println("�������û���:");
        String username = in.next();
        System.out.println("����������:");
        String password = in.next();

        // �ж�list���Ƿ���ڸ��û���
        for (User user : userList) {
            if (username.equals(user.getName())) {
                System.out.println("���û����Ѿ���ռ��.");
                return;
            }
        }

        // ֻҪִ�е����λ��,
        // һ���Ǳ�����ʱ��û���κ�username�����������ƥ��

        User user = new User();
        user.setName(username);
        user.setPassword(password);

        userList.add(user);

        userDao.saveData(path, userList);
        System.out.println("ע��ɹ�!");
    }

    public User login(String path) {
        // ��¼
        System.out.println("�������û���:");
        String username = in.next();
        System.out.println("����������:");
        String password = in.next();

        // �����ж��Ƿ��¼�ɹ�
        ObjectInputStream ois=null;
        try {
            ois=new ObjectInputStream(new FileInputStream(path));
            List<User> userList = (List<User>) ois.readObject();
            for (User user : userList) {
                if (username.equals(user.getName())&&password.equals(user.getPassword())){
                    System.out.println("��¼�ɹ�");
                    return user;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

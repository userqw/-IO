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
        // 注册

        System.out.println("请输入用户名:");
        String username = in.next();
        System.out.println("请输入密码:");
        String password = in.next();

        // 判断list中是否存在该用户名
        for (User user : userList) {
            if (username.equals(user.getName())) {
                System.out.println("该用户名已经被占用.");
                return;
            }
        }

        // 只要执行到这个位置,
        // 一定是遍历的时候没有任何username和输入的内容匹配

        User user = new User();
        user.setName(username);
        user.setPassword(password);

        userList.add(user);

        userDao.saveData(path, userList);
        System.out.println("注册成功!");
    }

    public User login(String path) {
        // 登录
        System.out.println("请输入用户名:");
        String username = in.next();
        System.out.println("请输入密码:");
        String password = in.next();

        // 用于判断是否登录成功
        ObjectInputStream ois=null;
        try {
            ois=new ObjectInputStream(new FileInputStream(path));
            List<User> userList = (List<User>) ois.readObject();
            for (User user : userList) {
                if (username.equals(user.getName())&&password.equals(user.getPassword())){
                    System.out.println("登录成功");
                    return user;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

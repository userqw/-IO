package dao;

import vo.User;

import java.io.*;
import java.util.List;

public class
UserDao {

    public void saveData(String path, List<User> userList) {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(path));
            oos.writeObject(userList);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package view;

public class MainView {
    //��ӡ�б�
    public void show(String []str) {
        for(int i=0;i<str.length;i++) {
            System.out.printf("%d %s\n",i+1,str[i]);
        }
    }
}

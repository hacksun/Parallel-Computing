package Java_Study;

class BookOffice extends Thread{
    private static int tickets=30;
    /*
    public BookOffice(String str){
        super(str);
    }
    */
    public void run(){
            while(tickets>0){
                synchronized (this) {
                    if (tickets > 0) {

                        System.out.println(Thread.currentThread().getName() + "卖掉第" + tickets-- + "张票");//这里我是可以直接用this来代替currentThread的，因为是继承Thread的方法实现多线程。

                    }
                }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {

                    }


            }
    }
}

public class BookTicket {
    public static void main(String[] args){

        BookOffice m = new BookOffice();

        Thread t1 = new Thread(m,"窗口A");//这里调用给的是Thread的默认构造器，给构造器命名，老师在上课时用的是在继承类中重写了super构造器。
        Thread t2 = new Thread(m,"窗口B");
        Thread t3 = new Thread(m,"窗口C");
        Thread t4 = new Thread(m,"窗口D");

        /*
        BookOffice t1 =new BookOffice("A");
        BookOffice t2 =new BookOffice("B");
        BookOffice t3 =new BookOffice("C");
        BookOffice t4 =new BookOffice("D");
        */
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}

package Java_Study;

class  Bank{
    private String bankid;
    private double balance;
    public Bank(){

    }
    public Bank(String bankid, double balance){
        this.bankid=bankid;
        this.balance=balance;
    }
    public String getBankid(){
        return bankid;
    }
    public void setBankid(String bankid){
        this.bankid=bankid;
    }
    public double getBalance(){
        return balance;
    }
    public  void setBalance(double balance){
        this.balance=balance;
    }
}

class ZhangSan extends Thread{
    private Bank bank;
    private double drawmoney;
    public ZhangSan(Bank bank,double drawmoney){
        this.bank=bank;
        this.drawmoney=drawmoney;
    }
    public void run(){
        synchronized (bank){
            if(bank.getBalance()>=drawmoney){
                System.out.println("张三"+"在柜台取出"+drawmoney+"元");
                try{
                    Thread.sleep(200);
                }catch(InterruptedException e){}
                double a=bank.getBalance()-drawmoney;
                bank.setBalance(a);
                System.out.println("当前账户余额为："+bank.getBalance());

            }
            else{
                System.out.println("余额不足，不能取钱！");
            }
        }
    }
}

class LiSi extends Thread{
    private Bank bank;
    private double drawmoney;
    public LiSi(Bank bank,double drawmoney){
        this.bank=bank;
        this.drawmoney=drawmoney;
    }
    public void run(){
        synchronized (bank){
            if(bank.getBalance()>=drawmoney){
                System.out.println("李四"+"在ATM取出"+drawmoney+"元");
                try{
                    Thread.sleep(200);
                }catch(InterruptedException e){}
                double a=bank.getBalance()-drawmoney;
                bank.setBalance(a);
                System.out.println("当前账户余额为："+bank.getBalance());

            }
            else{
                System.out.println("余额不足，不能取钱！");
            }
        }
    }
}

public class WithdrawMoney {
    public static void main(String[] args){
        Bank b=new Bank("15130120141",10000);
        ZhangSan z= new ZhangSan(b,10000);
        LiSi l=new LiSi(b,1);
        z.start();
        l.start();
    }
}


# -*- coding: utf-8 -*-  
import threading,time
#tab和空格之间的排版问题导致出错真的是一件很难查找出来的问题

class Bank(threading.Thread):
    def __init__(self,money):
        threading.Thread.__init__(self)
        self.money=money

    def withdraw(self,num):
        if lock.acquire():
            temp=self.money
            temp -= num
            self.money = temp
            lock.release()

    def deposit(self,num):
        self.money += num


    def get_money(self):
        return self.money


class ZhangSan(threading.Thread):
    def __init__(self,account):
        threading.Thread.__init__(self)
        self.account = account

    def run(self):
        self.account.withdraw(100)
        print('张三取了100元')


class LiSi(threading.Thread):
    def __init__(self,account):
        threading.Thread.__init__(self)
        self.account = account

    def run(self):
        self.account.deposit(100)
        print('李四取了100元')

if __name__ == '__main__':
    lock=threading.Lock()
    account=Bank(1000)
	#存了1000
    zhangsan = ZhangSan(account)
    lisi = LiSi(account)

    zhangsan.start()
    lisi.start()
	
    print("剩余%s元"%(account.get_money()))
				

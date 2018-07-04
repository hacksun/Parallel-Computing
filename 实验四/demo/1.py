# -*- coding: utf-8 -*-  
import threading
import time
import random

class BoxOffice(threading.Thread):
    #继承这个类
	
    '''售票窗口，初始化，为了模拟人是不停地来，
	这里其实可以直接把等待人数设置成30，但是还是不要这样了。'''
    def __init__(self, wait_num=10, index=0):
        super().__init__()
        self.wait_num = wait_num          
        self.setName('窗口' + str(index))
		
		
    #重写run方法，while的条件就是如果没票或者没人两者任意一个满足就不卖了		
    def run(self):
        global counter, mutex
        
        while counter and self.wait_num:
            time.sleep(random.randrange(0,1))
            mutex.acquire()
			#这里的作用就是上锁了
            if counter == 0:        
                mutex.release()
			#这里的作用是解锁
                print(self.getName(), '：票已售完')
                break
                
            counter = counter - 1
            print('%s：剩余 %s 张票'%(self.getName(), counter))
			#其实用format也ok
            mutex.release()
            self.wait_num -= 1        
            self.wait_num += random.randrange(0,2)

    
if __name__ == '__main__':
    # 剩余车票数
    counter = 30
    
    # 创建锁
    mutex = threading.Lock()
    
    windows = []
    for i in range(4):
	#这个线程等待时间不能设置太小啊
        wait_num = random.randrange(0,10) 
        windows.append(BoxOffice(wait_num, i+1)) 
    
    # start
    for w in windows:
        w.start()
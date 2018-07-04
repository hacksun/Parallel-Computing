#coding=utf-8
import re
import collections

txt1 = open("file_1.txt", "r").read()

txt2 = open("file_2.txt", "r").read()

str1 = "there are %d books in file_1 \n" %(len(re.findall("book", txt1)))
str2 = "there are %d books in file_2\n" %(len(re.findall("book", txt2)))
str3 = "there are %d books in all files\n" %(len(re.findall("book", txt2))+len(re.findall("book", txt1)))

print (str1)
print (str2)
print (str3)

a = open('D:\\大三下学期\\并行计算\\实验三\\file.txt','a')
a.write(str1)
a.write(str2)
a.write(str3)
a.close()
'''
#python 2.x
patt = re.compile("\w+")
counter = collections.Counter(patt.findall(
    open('file_1.py','rt').read()
    ))
 
# top 10
for word, times in counter.most_common(10):
    print word, times
 
# find word
counter_dict = dict(counter.most_common(0))
tobefind = 'book'
print tobefind, counter_dict.get(tobefind, 0)
'''
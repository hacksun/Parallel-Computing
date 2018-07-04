#coding=utf-8
 
def QuickSort(arr,firstIndex,lastIndex):
    if firstIndex<lastIndex:
        divIndex=Partition(arr,firstIndex,lastIndex)
 
        QuickSort(arr,firstIndex,divIndex)       
        QuickSort(arr,divIndex+1,lastIndex)
    else:
        return
 
 
def Partition(arr,firstIndex,lastIndex):
    i=firstIndex-1
    for j in range(firstIndex,lastIndex):
        if arr[j]<=arr[lastIndex]:
            i=i+1
            arr[i],arr[j]=arr[j],arr[i]
    arr[i+1],arr[lastIndex]=arr[lastIndex],arr[i+1]
    return i
 
 
arr=[3,7,12,5,3,10,11,9,4,2,4]
 
QuickSort(arr,0,len(arr)-1)
b = open('D:\\大三下学期\\并行计算\\实验三\\quicksort.txt','a')
print(arr,file=b)
print(arr)
b.close

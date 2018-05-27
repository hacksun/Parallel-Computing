package Java_Study;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

class Max extends RecursiveTask<Integer>{
    private int threshold;
    private  List<String> list;
    //private  List<String> list1;

    //private  List<String> list2;

    public Max(List<String> list,int threshold){
        this.list = list;

        this.threshold = threshold;
    }
    protected Integer compute(){

        Integer number=0;
        if (list.size() < threshold) {
            for(int i=0;i<list.size();i++){
                if((list.get(i)!=null&&list.get(i).matches("^[0-9]+$"))){
                    number += Integer.parseInt(list.get(i));
                }

            }

        }else {
            int middle = list.size() / 2;
            List<String> leftList = list.subList(0, middle);
            List<String> rightList = list.subList(middle, list.size());
            Max left = new Max(leftList, threshold);
            Max right = new Max(rightList, threshold);
            left.fork();
            right.fork();
            Integer join1 = left.join();
            Integer join2 = right.join();
            number = join1 + join2;



        }

        return number;
    }
}

public class FindMax {
    public  static void main(String args[]){


        String[] list_1 = {"*", "%", "3", "#", "6", "~", "!", "2"};
        String[] list_2 = {"&", "￥", "@", "1", "4", ":", "2", "1"};

        List<String> stringList1 = new ArrayList<>(Arrays.asList(list_1));
        List<String> stringList2 = new ArrayList<>(Arrays.asList(list_2));
        ForkJoinPool a= new ForkJoinPool();
        Max task1 = new Max(stringList1,3);
        Max task2 = new Max(stringList2,3);
        Future<Integer> result1 = a.submit(task1);
        Future<Integer> result2 = a.submit(task2);
        try{

                int x = result1.get();
                int y = result2.get();
                if(x>y){
                    System.out.println(x+">"+y+" 即 list_1>list_2");
                }else{
                    System.out.println(y+">"+x+" 即 list_1<list_2");
                }

        }catch (Exception e){}

    }



}

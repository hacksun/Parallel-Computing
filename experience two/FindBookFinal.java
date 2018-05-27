package Java_Study;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;
/*
class Findtimes{
    public static int FindtimesFunction(File file){
        try {
            FileInputStream fim = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fim));
            String str = "";//初始化
            String result = "";
            while ((str = br.readLine()) != null) {
                result += str;
            }
            System.out.println("文件内容：" + result);
            String[] arr = result.split(",");
            int num=0;
            for(int i=0;i<arr.length;i++){
                if(arr[i].equals("book")){
                    num++;
                }
            }
            System.out.println("文件含有"+num+"个book。");
        }catch(FileNotFoundException e){}
        catch(IOException e){}
        return 0;
    }
}
*/
class PFindTimes extends RecursiveTask<Integer> {
    private int threshold;
    String[] arr;
    int start,end;
    PFindTimes(int start,int end,int threshold,String[] arr){
        this.start = start;
        this.end = end;
        this.threshold = threshold;
        this.arr=arr;
    }

    protected Integer compute(){
        int num=0;
        List<String> ex = Arrays.asList(arr);
        if (ex.size()<threshold){
            for(int i=0;i<ex.size();i++){
                if(arr[i].equals("book")){
                    num++;
                }
            }
        }else{
            int middle = arr.length / 2;
            String[] leftString=new String[5];
            String[] rightString=new String[5];

            for(int i=0;i<arr.length;i++){
                if(i<middle){

                    leftString[i]=arr[i];

                }else{
                    rightString[i-middle]=arr[i];

                }
            }

            PFindTimes left = new PFindTimes(0,middle, threshold,leftString);
            PFindTimes right = new PFindTimes(middle,arr.length,threshold,rightString);

            left.fork();
            right.fork();
            Integer join1 = left.join();
            Integer join2 = right.join();
            num = join1 + join2;
        }
        return num;
    }
}

public class FindBookFinal {
    public static void main(String[] args){
        File file_1 = new File("file_1.txt");
        File file_2 = new File("file_2.txt");
        try {
            FileInputStream fim1 = new FileInputStream(file_1);
            BufferedReader br1 = new BufferedReader(new InputStreamReader(fim1));
            String result1 = "";//初始化
            String str1 = "";
            while ((str1 = br1.readLine()) != null) {
                result1 += str1;
            }
            System.out.println("文件1内容：" + result1);
            String[] arr1 = result1.split(",");

            FileInputStream fim2 = new FileInputStream(file_2);
            BufferedReader br2 = new BufferedReader(new InputStreamReader(fim2));
            String result2 = "";//初始化
            String str2 = "";
            while ((str2 = br2.readLine()) != null) {
                result2 += str2;
            }
            System.out.println("文件2内容：" + result2);
            String[] arr2 = result2.split(",");

            ForkJoinPool a= new ForkJoinPool();
            PFindTimes task1 = new PFindTimes(0,9,6,arr1);
            PFindTimes task2 = new PFindTimes(0,7,6,arr2);

            Future<Integer> r1 = a.submit(task1);
            Future<Integer> r2 = a.submit(task2);
            try{
                int x = r1.get();
                int y = r2.get();
                System.out.println("文件1中含有："+x+"个book");
                System.out.println("文件2中含有："+y+"个book");
            }catch (Exception e){}
        }catch (FileNotFoundException e){}
        catch(IOException e){}
    }
}


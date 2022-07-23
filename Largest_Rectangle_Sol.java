//Stack based Solution
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {
     static List<Integer> pos=new ArrayList<>();
     static List<Integer> height=new ArrayList<>();
    public static long peek()
        {
            return height.get(height.size()-1);
        }
    public static long largestRectangle(List<Integer> h) {
        //two stacks
        //function to get the top of the stack
        long max=-1;
        int i=0;
        while(i<h.size())
        {
            //continue adding if the height is increasing
            if(height.size()==0||peek()<h.get(i))
            {
                height.add(h.get(i));
                pos.add(i);
            }
            else if(h.get(i)<peek())//if the height decreases
            {
                int tempp=0,temph=0;
                //finding the position of the block with height just greater than the current block
                while(height.size()!=0&&h.get(i)<peek())
                {
                    tempp=pos.get(height.size()-1);
                    pos.remove(height.size()-1);
                    //temph=height.get(height.size()-1);
                    height.remove(height.size()-1);
                    //max=Math.max(max,(i-tempp)*temph);
                }
                //key step
                pos.add(tempp);//move to the location which has height  just greater than the current building
                height.add(h.get(i));// add the minimum height
            }
            i++;
        }
        //calculate the maximum
        //pop out all remaining elements from the stack
        while(height.size()!=0)
                {
                    long tempp=pos.get(height.size()-1);
                    pos.remove(height.size()-1);
                    long temph=height.get(height.size()-1);
                    height.remove(height.size()-1);
                    max=Math.max(max,(h.size()-tempp)*temph);
                }
        return max;
}
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> h = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        long result = Result.largestRectangle(h);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class SeatGenerator {
    
    private String filename;
    private int row,col;
    private ArrayList<String> ar_name;
    private ArrayList<Integer> ar_id;
    private int name_size=0;
    
    public SeatGenerator(String f,int r,int c)throws IOException{

        filename=f;
        row=r;
        col=c;
        // Read file and input student name
        FileReader fr=new FileReader(filename);
        BufferedReader br=new BufferedReader(fr);
        ar_name=new ArrayList<String>();
        while(br.ready()){
        	String[] temp=(br.readLine()).split(",");
        	for(String e:temp){
        		ar_name.add(e);
        	};
        }
        fr.close();
        
        // Generate random seat ID
        name_size=ar_name.size();
        ar_id=new ArrayList<Integer>();
        while(ar_id.size()!=ar_name.size()){
            int id=(int)(Math.random()*ar_name.size());
            if(!ar_id.contains(id)){
                ar_id.add(id);
            }
        }
        
        // Padding
        int rest=ar_name.size();
        while(ar_name.size()!=row*col){
        	ar_name.add("   ");
        	ar_id.add(rest);
        	rest++;
        }
        
    }
    
    public ArrayList<String> getNameList(){
        return ar_name;
    }
    
    public ArrayList<Integer> getIdList(){
        return ar_id;
    }
    
    public int getNameSize(){
    	return name_size;
    }
    
}

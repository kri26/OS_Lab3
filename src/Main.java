import java.util.Scanner;

public class Main {

    public static void main(String[] args) {   	
        Operation operation = new Operation(2048, 512);
        int pages[] = {3, 0, 4, 2, 5, 7, 6};        
        for(int i = 0; i < pages.length; i++){
		      if (pages[i] >= 0 && pages[i] < operation.getTableVirtualPagesSize()){
		    	  System.out.println("¬ведЄн индекс страницы в таблице страниц: " + pages[i]);
		    	  operation.insertIntoPhysicalMemory(pages[i]);
		      }
	     }
    }
}
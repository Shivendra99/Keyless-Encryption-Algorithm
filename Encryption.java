import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Encryption {
	
	static private int quanta=3,val=0;
	static byte count=0;
	public static void encryptLogic(int c[])
	{ 
		String[] s=new String[quanta];
		int[] l=new int[quanta];
		if(count%3==0)
		{
			for(int i=0;i<quanta;i++)
				s[i]=Integer.toBinaryString(c[i]);
			for(int i=0;i<c.length;i++)
				l[i]=s[i].length();
			
			val=0;
			for(int i=l[1]-1;i>=l[1]-quanta&&i>=0;i--)
				val=val+(int)(Math.pow(2,l[1]-i-1)*((int)s[1].charAt(i)-48));
			
		    c[0]=c[0]<<val;
		    c[2]=c[2]<<val;

		    int num=100000000;
		    
		    long[] cn={c[0],c[1],c[2]};
		    long ex=0;
		    cn[0]=Long.parseLong(Long.toBinaryString(cn[0]));
		    cn[2]=Long.parseLong(Long.toBinaryString(cn[2]));
		    
		    ex=cn[0]+cn[2]/num;
		    cn[0]=(cn[0]+cn[2]/num)%num;
		    cn[2]=(cn[2]+ex/num)%num;
		    int i=0;
		    c[0]=0;
		    c[2]=0;
		    while(cn[0]!=0||cn[2]!=0)
		    {
		    	c[0]=c[0]+(int)(Math.pow(2, i)*(cn[0]%10));
		    	c[2]=c[2]+(int)(Math.pow(2, i)*(cn[2]%10));
		    	cn[0]=cn[0]/10;
		    	cn[2]=cn[2]/10;
		    	i++;
		    }
		    
		}
		
		else if(count%3==1||count%3==-1)
		{
			for(int i=0;i<quanta;i++)
				s[i]=Integer.toBinaryString(c[i]);
			for(int i=0;i<c.length;i++)
				l[i]=s[i].length();
			
			val=0;
			for(int i=l[2]-1;i>=l[2]-quanta&&i>=0;i--)
				val=val+(int)(Math.pow(2,l[2]-i-1)*((int)s[2].charAt(i)-48));
			
		    c[0]=c[0]<<val;
		    c[1]=c[1]<<val;

		    int num=100000000;
		    
		    long[] cn={c[0],c[1],c[2]};
		    long ex=0;
		    cn[0]=Long.parseLong(Long.toBinaryString(cn[0]));
		    cn[1]=Long.parseLong(Long.toBinaryString(cn[1]));
		    
		    ex=cn[0]+cn[1]/num;
		    cn[0]=(cn[0]+cn[1]/num)%num;
		    cn[1]=(cn[1]+ex/num)%num;
		    int i=0;
		    c[0]=0;
		    c[1]=0;
		    while(cn[0]!=0||cn[1]!=0)
		    {
		    	c[0]=c[0]+(int)(Math.pow(2, i)*(cn[0]%10));
		    	c[1]=c[1]+(int)(Math.pow(2, i)*(cn[1]%10));
		    	cn[0]=cn[0]/10;
		    	cn[1]=cn[1]/10;
		    	i++;
		    }
		    
		}
		
		else if(count%3==2||count%3==-2)
		{
			for(int i=0;i<quanta;i++)
				s[i]=Integer.toBinaryString(c[i]);
			for(int i=0;i<c.length;i++)
				l[i]=s[i].length();
			
			val=0;
			for(int i=l[0]-1;i>=l[0]-quanta&&i>=0;i--)
				val=val+(int)(Math.pow(2,l[0]-i-1)*((int)s[0].charAt(i)-48));
			
		    c[2]=c[2]<<val;
		    c[1]=c[1]<<val;

		    int num=100000000;
		    
		    long[] cn={c[0],c[1],c[2]};
		    long ex=0;
		    cn[2]=Long.parseLong(Long.toBinaryString(cn[2]));
		    cn[1]=Long.parseLong(Long.toBinaryString(cn[1]));
		    
		    ex=cn[1]+cn[2]/num;
		    cn[1]=(cn[1]+cn[2]/num)%num;
		    cn[2]=(cn[2]+ex/num)%num;
		    int i=0;
		    c[1]=0;
		    c[2]=0;
		    while(cn[1]!=0||cn[2]!=0)
		    {
		    	c[1]=c[1]+(int)(Math.pow(2, i)*(cn[1]%10));
		    	c[2]=c[2]+(int)(Math.pow(2, i)*(cn[2]%10));
		    	cn[1]=cn[1]/10;
		    	cn[2]=cn[2]/10;
		    	i++;
		    }   
		}
		count++;
	}
	
	
	public static void  encrypt(String ip,String op) throws IOException
	{
        FileInputStream in = null;
        FileOutputStream out = null;
		
        try
		{ 
			in = new FileInputStream(ip);         
            out = new FileOutputStream(op);
		}catch(IOException e)
		{
			System.out.println("Problem in loading file(s) Or Invalid path !!");
			throw e;
		}
		
        int c[]=new int[quanta];
        
        try    
        {
        	for(int i=0;i<quanta;i++)
        		c[i]=in.read();
        	while (true)
        	{
        	   long p2=in.getChannel().size();
               System.out.print("Progress : "+((p2-in.available())*100)/p2+"% \r");
        	   //System.out.println("size "+sizeOf(in.read()));
               encryptLogic(c);
               for(int i=0;i<quanta;i++)
           		out.write(c[i]);
           	   for(int i=0;i<quanta;i++)
        	      c[i]=in.read();
           	   if(c[0]==-1||c[1]==-1||c[2]==-1)
           	   {
           		   for(int i=0;i<quanta;i++)
           			   if(c[i]!=-1)
           			      out.write(c[i]);
           		   break;
           	   }
            }
        	System.out.println(" !! File successfully encrypted !!");
        }catch(IOException e)
        {
        	System.out.println("Error in encrypting the file : "+e);
        }
        finally 
        {
           if (in != null) 
             in.close();
           if (out != null) 
             out.close();
    	}
        
	}
	
	
	/*public boolean login()
	{
		String uname,pswrd;
		 
        FileInputStream in = new FileInputStream("passcode.txt");
        System.out.print("Enter username :  ");
        uname=sc.nextLine();
        System.out.print("Enter password :  ");
        pswrd=sc.nextLine();
        
	}*/
	
    
	public static void main(String[] args) throws IOException {

    	Scanner sc=new Scanner (System.in);
  
        //F:/Education/Software engineering/Project/test//copy1.pdf
    	//F:/Education/Software engineering/Project/test//ABC.txt
    	//F:/Education/Software engineering/Project/test//Linkin Park - In The End.mp3
    	//F:/Education/Software engineering/Project/test//hun_tetho_mera_door__rdb_medium.mp4
        String op,ip;        
        
        System.out.println("Enter the path of the file to be encrypted : ");
        ip=sc.nextLine();
        sc.close();
        op=ip+"encrypted_copy";
        
        try
        { encrypt(ip,op);
        }catch(IOException e)
        {
        	System.out.println("!! Error !!");
        }
    } 
}
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Decryption {
	
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
			
			long c0=Long.parseLong(Long.toBinaryString(c[0])),c2=Long.parseLong(Long.toBinaryString(c[2]));;
			
			int sub0=(int)(c0%Math.pow(10, val)),sub2=(int)(c2%(int)Math.pow(10, val));
			
			c0=sub2*(int)Math.pow(10,8-val)+c0/(int)Math.pow(10,val);
			c2=sub0*(int)Math.pow(10,8-val)+c2/(int)Math.pow(10,val);
			
			int i=0;
			c[0]=0;
			c[2]=0;
			while(c0!=0||c2!=0)
		    {
		    	c[0]=c[0]+(int)(Math.pow(2, i)*(c0%10));
		    	c[2]=c[2]+(int)(Math.pow(2, i)*(c2%10));
		    	c0=c0/10;
		    	c2=c2/10;
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
			
			long c0=Long.parseLong(Long.toBinaryString(c[0])),c1=Long.parseLong(Long.toBinaryString(c[1]));;
			
			int sub0=(int)(c0%Math.pow(10, val)),sub1=(int)(c1%(int)Math.pow(10, val));
			
			c0=sub1*(int)Math.pow(10,8-val)+c0/(int)Math.pow(10,val);
			c1=sub0*(int)Math.pow(10,8-val)+c1/(int)Math.pow(10,val);
			
			int i=0;
			c[0]=0;
			c[1]=0;
			while(c0!=0||c1!=0)
		    {
		    	c[0]=c[0]+(int)(Math.pow(2, i)*(c0%10));
		    	c[1]=c[1]+(int)(Math.pow(2, i)*(c1%10));
		    	c0=c0/10;
		    	c1=c1/10;
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
			
			long c1=Long.parseLong(Long.toBinaryString(c[1])),c2=Long.parseLong(Long.toBinaryString(c[2]));;
			
			int sub1=(int)(c1%Math.pow(10, val)),sub2=(int)(c2%(int)Math.pow(10, val));
			
			c1=sub2*(int)Math.pow(10,8-val)+c1/(int)Math.pow(10,val);
			c2=sub1*(int)Math.pow(10,8-val)+c2/(int)Math.pow(10,val);
			
			int i=0;
			c[1]=0;
			c[2]=0;
			while(c1!=0||c2!=0)
		    {
		    	c[1]=c[1]+(int)(Math.pow(2, i)*(c1%10));
		    	c[2]=c[2]+(int)(Math.pow(2, i)*(c2%10));
		    	c1=c1/10;
		    	c2=c2/10;
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
        	   // System.out.println("size "+sizeOf(in.read()));
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
        	System.out.println(" !! File successfully decrypted !!");
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
  
        //F:/Education/Software engineering/Project/test//copy1.pdfencrypted_copy
    	//F:/Education/Software engineering/Project/test//Linkin Park - In The End.mp3decrypted_copy
    	//F:/Education/Software engineering/Project/test//ABC.txtdecrypted_copy
        //F:/Education/Software engineering/Project/test//hun_tetho_mera_door__rdb_medium.mp4encrypted_copy
    	String op,ip;
        
        System.out.println("Enter the path of the file to be decrypted : ");
        ip=sc.nextLine();
        sc.close();
        op=ip+"decrypted_copy";
        
        try
        { encrypt(ip,op);
        }catch(IOException e)
        {
        	System.out.println("!! Error !!");
        }
    } 
}
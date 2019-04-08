/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finite_field_calculator;

/**
 *
 * @author Ahmed_Martin
 */
public class Finite_field_calculator {

    
    // irreducable f(x) = x^8+x^4+x^3+x+1  GF(2^8)
    
    public static void main(String[] args) {
        // TODO code application logic here
        
         
        String bits[] = new String[3];
        bits[0]=convert_int_to_binary(50);
        bits[1]=convert_int_to_binary(100);
        bits[2]=convert_int_to_binary(150);
        
        
        System.out.println("first  = "+bits[0] );
        System.out.println("second = "+bits[1]);
        System.out.println("therd  = "+bits[2]);
        System.out.println("add    = "+add(bits));
        System.out.println("pro_arr= "+product(bits));
        System.out.println("pro_bin= "+product("01010111", "10000011"));
        System.out.println("add_num= "+convert_binary_to_int(add(bits)));
        System.out.println("pro_num= "+convert_binary_to_int(product("01010111", "10000011")));
        
    }
    
    // convert from binary to decimal
    static  String convert_int_to_binary(int number){
        String bits = Integer.toBinaryString(number);
        if(bits.length()<8){
            String temp = "";
           for(int i=0;i<8-bits.length();i++)
              temp+="0";
           bits=temp+bits;
        }else if (bits.length()>8){
            System.out.println("number "+number+" it's binary is greater than 8 bits");
            System.exit(0);
        }   
        return bits;
    }
    
    // convert number from decimal to binary
    static  int convert_binary_to_int(String bits){
        int sum =0;
        for(int i=0,j=7;i<8;i++,j--){
           if(bits.charAt(j)=='1'){
             sum+=(int) Math.pow(2, i);
           }
        }
        return sum;
    }
    
    // add multible number
    static String add (String[] bits){
          String result="";
          for(int column=0;column<8;column++){
             char temp = bits[0].charAt(column);
              for(int row=1;row<bits.length;row++){
                 if(temp==bits[row].charAt(column))
                    temp='0';
                 else
                    temp='1';
              }
              result+=temp;
          }
          return result;
    }
    
    // product multible number
    static String product(String [] bits){
             String first = bits[0];
             String second = bits[1];
             String result = product(first, second);
             for(int i=2;i<bits.length;i++){
                 first = result;
                 second= bits[i];
                 result = product(first, second);
             }
             return result;
    }
    
    //product 2 numbers
    static String product(String first , String second){
           String irreducable = "00011011";      // irreducable function for GF(2^8) 
           String []result = new String[8];      // array contain all result for product x in f(Xi)
           result[0] =first;
           // algorethm to product x in f(X)
           for(int i=1;i<8;i++){
               // sheft 1 character and put 0 at the end
               String sheft = result[i-1].substring(1, 8)+"0";
               result[i]="";
               // check if first character =0
               if(result[i-1].charAt(0)=='0')
                   result[i]=sheft;  // result i = sheft 
               else{
                  // add irreducable with sheft and put result in result i
                 String [] t ={sheft,irreducable};
                 result[i]= add(t);
               }
           }
           
           String temp ="";
           // if i found 1 in index i  put result[j] at temp and separate it by ","
           for(int i=0,j=7;i<8;i++,j--){
              if(second.charAt(i)=='1'){
                 temp+=result[j]+",";
              }
           }
           return add(temp.split(","));  // split data in add method to add all value for result [i]
    }
    
}

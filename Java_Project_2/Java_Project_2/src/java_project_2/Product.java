package java_project_2;
public class Product {
     private int id;
    private String name;
    private float price;
   private byte[] picture;
    
    public Product(int pid, String pname, float pprice,byte[] pimg){
        this.id      = pid;
        this.name    = pname;
        this.price   = pprice;
        this.picture = pimg;      
    }
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public float getPrice(){
        return price;
    }
    public byte[] getPicture(){
        return picture;
    }

    byte[] getImage() {
        throw new UnsupportedOperationException("daha yüklenmedi"); 
    }
}

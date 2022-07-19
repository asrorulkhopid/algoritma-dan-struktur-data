package Algodat;
import java.util.*; //import java uti untuk menggunakan Class-calss dalam library java
class Tanki{ //deklarasi class tanki -class tanki akan menyimpan data berupa nama tanki dan isinya
    static ArrayList [] tanki= {new ArrayList(),new ArrayList(),new ArrayList(),new ArrayList(),
                                    new ArrayList(),new ArrayList(),new ArrayList()}; //array yang menyimpan arraylist, tiap arraylist mewakili satu tanki
    static String [] namaTanki ={"Tanki 1","Tanki 2","Tanki 3","Tanki 4","Tanki 5","Tanki 6","Tanki 7"};
    //method isi tanki berfungsi untuk menampilkan nama dan isi tanki
    static void IsiTanki(){
        for(int i=0;i<7 && tanki[i].size()!=0;i++){
            System.out.print("\n"+namaTanki[i]+"\t  :");
            for(int j=0;j<tanki[i].size() ;j++){
                System.out.print(tanki[i].get(j)+" ");
            }
        }
    }
}//class ikan adalah class yang menyimpan informasi tentang ikan yaitu nama, tetangga, tanki yang di tempati dan status visit
class Ikan{
    String name; //deklarasi variabel nama
    int tanki; //deklarasi variabel tanki
    boolean visited=false; //deklarasi variabel visited
    ArrayList <Ikan> tetangga=new ArrayList(); //deklarasi arraylist yang nnti menyimpan data tetangga
    public Ikan(String name){   //constructor- akan memberikan nama pada si objek ikan
        this.name=name;
    }
    //menambahkan tetangga dengan 1 parameter(satu tetangga)
    void setTetangga(Ikan ikan1){
        this.tetangga.add(ikan1); //menambah ikan 1 sebagai tetangga
    }
    //menambahkan tetangga dengan 2 parameter(dua tetangga)
    void setTetangga(Ikan ikan1,Ikan ikan2){
        this.tetangga.add(ikan1); //menambah ikan 1 sebagai tetangga
        this.tetangga.add(ikan2); //menambah ikan 2 sebagai tetangga
    }
    //menambahkan tetangga dengan 3 parameter(tiga tetangga)
    void setTetangga(Ikan ikan1,Ikan ikan2,Ikan ikan3){
        this.tetangga.add(ikan1); //menambah ikan 1 sebagai tetangga
        this.tetangga.add(ikan2); //menambah ikan 2 sebagai tetangga
        this.tetangga.add(ikan3); //menambah ikan 3 sebagai tetangga
    }
    //menambahkan tetangga dengan 4 parameter(empat tetangga)
    void setTetangga(Ikan ikan1,Ikan ikan2,Ikan ikan3,Ikan ikan4){
        this.tetangga.add(ikan1); //menambah ikan 1 sebagai tetangga
        this.tetangga.add(ikan2); //menambah ikan 2 sebagai tetangga
        this.tetangga.add(ikan3); //menambah ikan 3 sebagai tetangga
        this.tetangga.add(ikan4); //menambah ikan 4 sebagai tetangga
    }
    //method untuk menampilkan semua tetangga dari si ikan
    void Daftartetangga(){
        for(int i=0; i<tetangga.size();i++){ //perulangan sebanyak tetangga 
            System.out.print(tetangga.get(i).name+" "); //menampilkan nama tiap tetangga
        }
    }    
}
//class graph menyimpan data ikan sebagai vertex dan terdapat method-method lain
class Graph{
    Ikan temp; //deklarasi variabel temp untuk menyimpan data ikan ketika sewaktu-waktu di butuhkan
    ArrayList <Ikan> ikan= new ArrayList(); //list yang berisi Ikan(vertex)
    void DaftarIkan(){ //method untuk menampilkan posisi tanki (warna)tempat si ikan
        System.out.println("Hasil Graph coloring :");
        for(int i=0;i<ikan.size();i++){ //perulangan sebanyak isi list
            System.out.println(ikan.get(i).name+" : "+ikan.get(i).tanki);   //menampilkan nama si ikan dan nomor tanki-nya
        }
    }
    void addIkan(Ikan ikan){//method untuk menambahkan ikan ke dalam list ikan(Vertex)
        this.ikan.add(ikan);
    }
    //mengatur status visited ke false untuk semua vertex
    void setVisitStatus(){
        for(int i=0;i<ikan.size();i++){ //perulangan sebanyak ikan
            ikan.get(i).visited=false; //set visited ke false
        }
    }
    //method di bawah untuk memberikan warna atau memposisikan ikan ke tanki tertentu
    void setWarna(){
        setVisitStatus();//mengatur semua status visited ikan ke false
//        perulangan berikut untuk mengurutkan ikan berdasarkan banyak tetangga
        for(int i=1;i<ikan.size();i++){ //perulangan sebanyak ikan
            for(int j=i;j>0;j--){//perulangan iindeks yang di tinjau sampai indeks 0
                if(ikan.get(j).tetangga.size()>ikan.get(j-1).tetangga.size()){ //yang lebih banyak tetangganya akan di posisikan lebih awal dalam list
                    temp=ikan.remove(j);
                    ikan.add(j-1,temp);
                }else{
                    j=0;
                }
            }
        }
        int tanki=0; //tanki diawali dari indeks 0
        for(int i=0;i<ikan.size(); i++){    //perulangan sebanyak data ikan
            if(!ikan.get(i).visited){   //jika ikan belum dikunjungi maka akan di beri warna baru
                ikan.get(i).tanki=tanki;    //pemberian nomer warna(tanki)
                Tanki.tanki[tanki].add(ikan.get(i).name); //Menambah ikan ke tanki tertentu
                ikan.get(i).visited=true;   //ikan yang di masukkan di set sebagai sudah dikunjungi
                for(int k=0;k<ikan.size(); k++){ //perulangan sebanyak ikan untuk hubungan ketetanggaan
                    int bantu=0; //nomor tanki
                    for(int j=0;j<ikan.get(i).tetangga.size();j++){ //perulangan sebanyak tetangga si ikan
                        if(!ikan.get(k).visited && !ikan.get(k).name.equalsIgnoreCase(ikan.get(i).tetangga.get(j).name)){
                            bantu++;
                        }
                    }
                    //menambah ikan ke dalam tanki
                    if(bantu==ikan.get(i).tetangga.size()){
                        ikan.get(k).tanki=ikan.get(i).tanki;
                        Tanki.tanki[tanki].add(ikan.get(k).name);
                        ikan.get(k).visited=true;
                    }                
                }
                tanki++;
            }
        }
    }
    //method untuk menampilkan daftar tetangga si ikan
    void AdjList(){
        for(int i=0;i<ikan.size();i++){   //perulangan sebanyak vertex digunakn untuk mengunjungi vertex 
            System.out.print("\n"+ikan.get(i).name+ " -->");
            ikan.get(i).Daftartetangga(); //Setiap vertex yang dikunjungi akan menampilkan tetangganya dengan method daftarTetangga
        }
    }
}
public class Modul5Jurnal1 {
    public static void main(String [] args){
        //objek ikan
        Ikan Alpha =new Ikan ("Alphas");
        Ikan Betas =new Ikan ("Betas");
        Ikan Certas =new Ikan ("Certas");
        Ikan Deltas =new Ikan ("Deltas");
        Ikan Epsas =new Ikan ("Epsas");
        Ikan Fetas =new Ikan ("Fetas");
        //objek graph
        Graph graph =new Graph();
        //menambah ikan ke dalam graph
        graph.addIkan(Alpha);Alpha.setTetangga(Betas,Certas);
        graph.addIkan(Betas);Betas.setTetangga(Alpha,Certas,Epsas);
        graph.addIkan(Certas);Certas.setTetangga(Alpha,Betas,Deltas,Epsas);
        graph.addIkan(Deltas);Deltas.setTetangga(Certas,Fetas);
        graph.addIkan(Epsas);Epsas.setTetangga(Betas,Certas,Fetas);
        graph.addIkan(Fetas);Fetas.setTetangga(Deltas,Epsas);
        //menampilkan daftar tetangga
        graph.AdjList();
        //mengatur warna(tanki) untuk setiap ikan
        graph.setWarna();
        System.out.println("\n");
        graph.DaftarIkan();
        Tanki.IsiTanki();
    }
}

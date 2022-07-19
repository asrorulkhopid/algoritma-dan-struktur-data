package Algodat;
import java.util.ArrayList;
import java.util.Scanner;
//class daerah untuk menyimpan Data daerag
class Daerah{//deklarasi class
    String name;//deklarasi variabel nama
    boolean visited=false; //deklarasi variabel visited
    ArrayList <Daerah>tetangga=new ArrayList<Daerah>();//menyimpan data tetangga
    ArrayList <Integer>bobot=new ArrayList <Integer>();//menyimpan bobot ke tetangga sesuai indeks tetangga 
    public Daerah(String name){//constructor dan memberikan nama pada objek daerah
        this.name=name;
    }
    //mengatur tetangga dan bobotnya dengan satu parameter daerah dan bobot
    void setTetangga(Daerah D1, int j1){
        tetangga.add(D1);bobot.add(j1);
    }
    //mengatur tetangga dan bobotnya dengan dua parameter daerah dan bobot
    void setTetangga(Daerah D1,int j1,Daerah D2,int j2){
        tetangga.add(D1);bobot.add(j1);
        tetangga.add(D2);bobot.add(j2);
    }
    //mengatur tetangga dan bobotnya dengan tiga parameter daerah dan bobot
    void setTetangga(Daerah D1,int j1,Daerah D2,int j2,Daerah D3,  int j3){
        tetangga.add(D1);bobot.add(j1);
        tetangga.add(D2);bobot.add(j2);
        tetangga.add(D3);bobot.add(j3);
    }
    //mengatur tetangga dan bobotnya dengan empat parameter daerah dan bob
    void setTetangga(Daerah D1,int j1,Daerah D2,int j2,Daerah D3, int j3,Daerah D4, int j4){
        tetangga.add(D1);bobot.add(j1);
        tetangga.add(D2);bobot.add(j2);
        tetangga.add(D3);bobot.add(j3);
        tetangga.add(D4);bobot.add(j4);
    }
    //menampilkan daftar tetangga untuk si ikan
    void Daftartetangga(){
        for(int i=0; i<tetangga.size();i++){ //perulangan sebanyak tetangga 
            System.out.println("\t"+tetangga.get(i).name+" : "+bobot.get(i)); //menampilkan nama tiap tetangga
        }
    }
}
//class graph untuk menyimpan data ikan dan tetangga nya serta bobot
class Graph1{
    Daerah temp; //deklarasi variabel temp
    ArrayList <Daerah> daerah= new ArrayList(); //list untuk menyimpan vertex
    void DaftarDaerah(){ //menampilkan daerah-daerah yang ada dalam graph
        System.out.println("Daftar tempat :");
        for(int i=0;i<daerah.size();i++){//perulangan sebanyak daerah yang ada dalam list
            System.out.println(daerah.get(i).name);
        }
    }
    //method untuk menambah daerah ke dalam list
    void addDaerah(Daerah daerah){
        this.daerah.add(daerah);
    }
    //methiod untuk mengatur visitd semua daerah ke false
    void setVisitStatus(){
        for(int i=0;i<daerah.size();i++){//perulangan sebanyak 
            daerah.get(i).visited=false;
        }
    }
    //menampilkan daerah dan tetangga-nya
    void AdjList(){
        for(int i=0;i<daerah.size();i++){   //perulangan sebanyak vertex digunakn untuk mengunjungi vertex 
            System.out.print("\n"+daerah.get(i).name+ " -->");
            daerah.get(i).Daftartetangga(); //Setiap vertex yang dikunjungi akan menampilkan tetangganya dengan method daftarTetangga
        }
    }
    
    //method untuk menentukan jarak terpendek dari suatu daerah ke daerah lain
    void dijkstra(Daerah asal, Daerah tujuan){
        int sv=0;
        Daerah kunjungi=asal;
        int [][] jarak = new int[daerah.size()][daerah.size()];
//        LinkedL [][] rute = new LinkedL[daerah.size()][daerah.size()];
        int bantu=0;
        boolean visit []=new boolean[daerah.size()]; //array untuk menyimpan status visited
        visit[0]=true; //visit untuk daerah asal akan di set ke true
        int indeksSmallest=0;//indeksSmallest=0 artinya indeks pertama(asal) sebagai jarak terpendek
        while(kunjungi!=tujuan){
            int smallest=1000000000;//smallest 100000- infinity
            for(int j=0;j<daerah.size();j++){//perulangan sebanyak 
                for(int i=0;i<kunjungi.tetangga.size();i++){//perulangan sebanyak tetangga dari daerah yang di kunjungi
                    if(kunjungi.tetangga.get(i).name.equals(daerah.get(j).name)){//jika Daerah yang ada di to adalah tetangga dari yang dikunjungi maka update jarak terpendek
                        if(bantu>0 && (jarak[bantu-1][indeksSmallest]+kunjungi.bobot.get(i))<= jarak[bantu-1][j]){
                            jarak[bantu][j]=jarak[bantu-1][indeksSmallest]+kunjungi.bobot.get(i);
                        }else if(bantu>0 && (jarak[bantu-1][indeksSmallest]+kunjungi.bobot.get(i))> jarak[bantu-1][j]){
                            jarak[bantu][j]=jarak[bantu-1][j];
                        }else{
                            jarak[bantu][j]=kunjungi.bobot.get(i);
                        }
                        i=kunjungi.tetangga.size();
                    }else if(daerah.get(j).name.equals(kunjungi.name)){
                        if(visit[j] && bantu>0){
                            jarak[bantu][j]=jarak[bantu-1][j];
                        }else{
                            jarak[bantu][j]=0;
                        }
                        i=kunjungi.tetangga.size();
                    }else{//jika tidak ada jalur ke suatu daerah maka jarak adalah infinity(100000 sebagai infinty)
                        if(visit[j] && bantu>0){
                            jarak[bantu][j]=jarak[bantu-1][j];
                        }else if(bantu>0 && jarak[bantu-1][j]<1000000000){
                            jarak[bantu][j]=jarak[bantu-1][j];
                        }else{
                            jarak[bantu][j]=1000000000;   
                        }
                    }
                }
            }
            //mengambil indeks dari jarak terpendek
            for(int j=0;j<daerah.size();j++){//perulangan sebanyak isi list to
                if(jarak[bantu][j]<=smallest && !visit[j] ){
                    smallest=jarak[bantu][j];
                    indeksSmallest=j;
                }
            }
            visit[indeksSmallest]=true;//atur visit ke true untuk jarak yang terkecil saat ini
            kunjungi=daerah.get(indeksSmallest);
            if(kunjungi==tujuan){
                sv=indeksSmallest;
            }
            bantu++;//update nilai bantu
        }
        System.out.print(jarak[bantu-1][sv]+" Jam perjalanan dari "+asal.name+" ke "+tujuan.name+" pada hari ");
    }
    //method untuk menemukan daerah tertentu(return daerah) berdasarkan nama
    Daerah findDaerah(String cari){
        boolean find=false;
        for(int i=0;i<daerah.size() && !find ;i++){
            if(cari.equalsIgnoreCase(daerah.get(i).name)){
                temp=daerah.get(i);
                find=true;
            }
        }
        return temp;
    }
}
public class Modul5Nomer2 {
    public static void main(String [] args){
        Scanner input=new Scanner(System.in);//deklarasi Scanner
        Graph1 dijkstra=new Graph1();//membuat objek graph
        //Membuat objek daerah dengan nama yang sudah di tentukan
        Daerah Nigata=new Daerah("Nigata");
        Daerah Miyagi=new Daerah("Miyagi");
        Daerah Kyoto=new Daerah("Kyoto");
        Daerah Shiga=new Daerah("Shiga");
        Daerah Chiba=new Daerah("Chiba");
        Daerah Hyogo=new Daerah("Hyogo");
        Daerah Osaka=new Daerah("Osaka");
        Daerah Saitama=new Daerah("Saitama");
        Daerah Fukushima=new Daerah("Fukushima");
        Daerah Fukuoka=new Daerah("Fukuoka");
        Daerah Nagano=new Daerah("Nagano");
        Daerah Gunma=new Daerah("Gunma");
        Daerah Miyagawa=new Daerah("Miyagawa");
        //menambahkan daerah ke dalam graph
        dijkstra.addDaerah(Nigata);
        dijkstra.addDaerah(Miyagi);
        dijkstra.addDaerah(Kyoto);
        dijkstra.addDaerah(Shiga);
        dijkstra.addDaerah(Chiba);
        dijkstra.addDaerah(Hyogo);
        dijkstra.addDaerah(Osaka);
        dijkstra.addDaerah(Saitama);
        dijkstra.addDaerah(Fukushima);
        dijkstra.addDaerah(Fukuoka);
        dijkstra.addDaerah(Nagano); 
        dijkstra.addDaerah(Gunma);
        dijkstra.addDaerah(Miyagawa);
        //mengatur tetangga dan bobot untuk tiap-tiap Daerah
        Nigata.setTetangga(Fukuoka, 8, Miyagi, 15, Shiga, 10);
        Miyagi.setTetangga(Nigata, 15, Chiba, 4, Fukushima, 25);
        Kyoto.setTetangga(Shiga, 7, Hyogo, 5);
        Shiga.setTetangga(Kyoto, 7, Nigata, 10, Chiba, 15,Osaka,5);
        Chiba.setTetangga(Miyagi, 4,Shiga, 15);
        Hyogo.setTetangga(Kyoto, 5, Osaka, 6);
        Osaka.setTetangga(Shiga, 5, Hyogo, 6, Fukuoka, 10, Saitama,10);
        Osaka.setTetangga(Nagano,12);
        Saitama.setTetangga(Osaka, 10, Nagano, 6, Miyagawa, 9, Fukushima,12);
        Fukushima.setTetangga(Miyagi, 25, Saitama, 12, Miyagawa, 3);
        Fukuoka.setTetangga(Osaka, 10, Nigata, 8);
        Nagano.setTetangga(Osaka, 12, Saitama, 6, Gunma, 8);
        Gunma.setTetangga(Nagano, 8, Miyagawa, 10);
        Miyagawa.setTetangga(Gunma, 10, Saitama, 9, Fukushima, 3);
        
        System.out.print("Asal   :");
        String asal=input.nextLine();
        System.out.print("Tujuan :");
        String tujuan=input.nextLine();
        System.out.print("Hari   :");
        String hari=input.nextLine();
        
        //menutup jalan berdasarkan hari
        if(hari.equalsIgnoreCase("senin")){
            Shiga.bobot.set(1, 100000);
            Nigata.bobot.set(2, 100000);
        }else if(hari.equalsIgnoreCase("selasa")){
            Miyagi.bobot.set(1, 100000);
            Chiba.bobot.set(0, 100000);            
        }else if(hari.equalsIgnoreCase("rabu")){
            Nagano.bobot.set(2, 100000);
            Gunma.bobot.set(0, 100000);            
        }else if(hari.equalsIgnoreCase("kamis")){
            Miyagi.bobot.set(2,100000);
            Fukushima.bobot.set(0, 100000);            
        }else if(hari.equalsIgnoreCase("jumat")){
            Miyagawa.bobot.set(2, 100000);
            Fukushima.bobot.set(2, 100000);            
        }else if(hari.equalsIgnoreCase("sabtu" )||hari.equalsIgnoreCase("Minggu")){
            Osaka.bobot.set(3, 100000);
            Saitama.bobot.set(0, 100000);            
            Fukuoka.bobot.set(1, 100000);
            Nigata.bobot.set(0, 100000);            
        }
        //mencari daerah yang dimasukkan dan simpan dalam variabel a dan b
        Daerah a=dijkstra.findDaerah(asal),b=dijkstra.findDaerah(tujuan);
        dijkstra.dijkstra(a,b);//memanggil method untuk menentukan jarak/waktu terpendek
        System.out.println(hari);
    }
}

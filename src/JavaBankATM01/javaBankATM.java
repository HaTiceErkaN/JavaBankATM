package JavaBankATM01;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class javaBankATM {
     /*      ATM
        Kullaniciya giriş için kart numarasi ve şifresini isteyin, eger herhangi birini yanlis girerse tekrar isteyin.
        Kart numarasi aralarda boşluk ile girerse de eger doğruysa kabul edin.
        Kart numarasi ve sifre dogrulanirsa kullanicinin yapabileceği işlemleri ekrana yazdirin,

        Bakiye sorgula, para yatirma, para çekme, para gönderme, sifre değiştirme ve cikis.

        Para çekme ve para gonderme işleminde mevcut bakiyeden buyuk para çekilemez,
        Para gönderme işleminde istenen iban TR ile baslamali ve toplam 26 karakterli olmali, eger değilse menü ekranina geri donsun.
        Sifre değiştirme işleminde mevcut şifreyi teyit ettikten sonra, sifre değişiklik işlemini yapmali,

      */

    static double bakiye;
    static String kartNo="1234123412341234";
    static int sifrem=1234;

    static  Scanner scan= new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("***** JAVA BANK'A HOŞGELDİNİZ *****");
        int sifre;
        String kartno;
        do {
            System.out.println("Lutfen kart numaranizi giriniz");
            kartno=scan.nextLine();
            System.out.println("Lutfen sifrenizi giriniz");
            sifre= scan.nextInt();

            if (sifre!=sifrem || !kartno.replace(" ","").equals(kartNo)){
                System.out.println("Islem basarisiz");
                System.out.println("Kart no veya sifre hatali");
            }else{
                atmPanel();
            }

        }while(sifre!=sifrem || !kartno.replace(" ","").equals(kartNo));
    }

    private static void atmPanel() {

        char islem= ' ';

        do {
            System.out.println("Lutfen yapmak istediginiz islemi giriniz\n1-Bakiye sorgulama\n2-Para yatirma" +
                    "\n3-Para Cekme\n4-Para gonderme\n5-Sifre degistime\n6-Cikis");
           islem= scan.next().charAt(0);

           switch (islem){
               case '1':
                   bakiyeSorgula(); break;
               case '2':
                   paraYatirma(); break;
               case '3':
                   paraCekme();break;
               case '4':
                   paraGonder();break;
               case '5':
                   sifreDegistir();break;
               case '6':
                   System.out.println("Cikis yapiyorsunuz, Bilgi fisi almak ister misiniz?\nEvet-->1\nHayir-->2");
                   char fis=scan.next().charAt(0);
                   if (fis=='1'){
                       fatura();
                   }else{
                       System.out.println("Cikis yapiliyor....\nJavaBank'i tercih ettiginiz icin tesekkür ediyoruz!");
                   }
                   break;
           }
        }while(islem!='6');

    }

    public static void fatura() {
        LocalDateTime lcd=LocalDateTime.now();
        DateTimeFormatter dtf= DateTimeFormatter.ofPattern("dd/MMM/yyyy"+" HH.mm");
        lcd.format(dtf);
        System.out.println(lcd.format(dtf));
    }

    public static void sifreDegistir() {
        int eskisifre;
        System.out.println("Lutfen sifrenizi girin");
        int haksayisi=3;
            for (int i = 1; i <=haksayisi ; i++) {
                eskisifre= scan.nextInt();
                if (eskisifre==sifrem){
                    System.out.println("Sifreniz dogru Lutfen yenisifrenizi giriniz");
                    int yenisifre= scan.nextInt();
                    break;
                }else{
                    System.out.println("Lutfen tekrar deneyiniz");
                }
                if (i==haksayisi){
                    System.out.println(haksayisi+" kez hatali giris yaptiniz, isleminize devam edemezsiniz.");
                }
            }
    }

    public static void paraGonder() {
        System.out.println("Lutfen gondermek istediginiz kisinin hesap numarasini(IBAN) giriniz");
        String Iban= scan.nextLine();

        if (Iban.startsWith("TR") && Iban.replaceAll("\\s", "").length()==26){
            System.out.println("IBAN kontrol ediliyor....\nIBAN dogrulandi.");
            System.out.println("Lutfen gondermek istediginiz tutari giriniz");
            double tutar= scan.nextDouble();

            if (tutar>bakiye){
                System.out.println("Bakiye tutariniz yetersizdir, islem yapilamiyor.");
            }else{
                bakiye-=tutar;
                System.out.println("Isleminiz gerceklesti");
                System.out.println("Yatirilan tutar: "+tutar);
                System.out.println("Kalan bakiye: "+bakiye);
            }
        }else{
            System.out.println("IBAN numarasi gecersizdir.Lutfen tekrar deneyin");
        }

    }

    public static void paraCekme() {
        System.out.println("Lutfen cekmek istediginiz tutari giriniz");
        double cekilecekTuar= scan.nextDouble();

        if (cekilecekTuar>bakiye){
            System.out.println("Bakiyeniz yetersizdir. İslem devam edemiyor");
        }else {
            System.out.println("İsleminiz basari ile gerceklesti");
            bakiye-=cekilecekTuar;
            System.out.println("Cekilen tutar: "+cekilecekTuar);
            System.out.println("Kalan bakiye: "+bakiye);
        }
    }


    public static void paraYatirma() {
        System.out.println("Lutfen yatirmak istediginiz tutari giriniz");
        double yatirilacakTuar= scan.nextDouble();
        bakiye+=yatirilacakTuar;
        System.out.println("İsleminiz basarili bir sekilde gerceklesti.");
        System.out.println("Bakiyeniz: "+bakiye);

    }

    public static void bakiyeSorgula() {
        System.out.println("Bakiyeniz: "+bakiye+" TL");

    }
}

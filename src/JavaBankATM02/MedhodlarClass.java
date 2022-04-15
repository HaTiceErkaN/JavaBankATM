package JavaBankATM02;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MedhodlarClass {
    static Scanner scan=new Scanner(System.in);


    public static void girisEkraninaGit() {
        System.out.println("***** JAVA BANK'A HOŞGELDİNİZ *****");
        System.out.println("Lutfen kart numaranizi giriniz: ");
        String girilenKartNo= scan.nextLine().replaceAll(" ","");
        System.out.println("Lutfen sifrenizi giriniz: ");
        String girilenSifre= scan.nextLine();

        if (girilenSifre.equals(MusteriBilgileri.getSifrem()) && girilenKartNo.equals(MusteriBilgileri.getKartNo())){
            MedhodlarClass.secimYap();
        }else{
            System.out.println("Kart numaraniz veya sifreniz hatali. Lutfen tekrar deneyin");
            girisEkraninaGit();
        }

    }

    public static void secimYap() {
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
                   cikis();
                    break;
            }
        }while(islem!='6');
    }

    public static void cikis() {
        System.out.println("Cikis yapiliyor, bankamizi tercih ettiginiz icin tesekkur ederiz...");
    }

    public static void sifreDegistir() {
        System.out.println("Lutfen sifrenizi girin");
        int haksayisi=3;
        for (int i = 1; i <=haksayisi ; i++) {
            String eskisifre= scan.nextLine();
            if (eskisifre.equals(MusteriBilgileri.getSifrem())){
                System.out.println("Sifreniz dogru Lutfen yeni sifrenizi giriniz");
                String yenisifre= scan.nextLine();
                System.out.println("Sifrenizbasarili bir sekilde degistirildi");
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
        scan.next();
        String Iban= scan.nextLine();


        if (!Iban.startsWith("TR") && (Iban.replaceAll("\\s", "").length()==26)) {
            System.out.println("IBAN numarasi gecersizdir.Lutfen tekrar deneyin");
            secimYap();
        }else{
            System.out.println("IBAN kontrol ediliyor....\nIBAN dogrulandi.");
            System.out.println("Lutfen gondermek istediginiz tutari giriniz");
            double tutar= scan.nextDouble();

            if (tutar>MusteriBilgileri.bakiye){
                System.out.println("Bakiye tutariniz yetersizdir, islem yapilamiyor.");
            }else{
                MusteriBilgileri.bakiye-=tutar;
                System.out.println("Isleminiz gerceklesti");
                System.out.println("Yatirilan tutar: "+tutar);
                System.out.println("Kalan bakiye: "+MusteriBilgileri.bakiye);
            }
        }
    }

    public static void paraCekme() {

        System.out.println("Lutfen cekmek istediginiz tutari giriniz");
        double cekilecekTuar= scan.nextDouble();

        if (cekilecekTuar>MusteriBilgileri.bakiye){
            System.out.println("Bakiyeniz yetersizdir. İslem devam edemiyor");
        }else {
            System.out.println("İsleminiz basari ile gerceklesti");
            MusteriBilgileri.bakiye-=cekilecekTuar;
            System.out.println("Cekilen tutar: "+cekilecekTuar);
            System.out.println("Kalan bakiye: "+MusteriBilgileri.bakiye);
        }
    }


    public static void paraYatirma() {

        System.out.println("Lutfen yatirmak istediginiz tutari giriniz");
        double yatirilacakTuar= scan.nextDouble();
        MusteriBilgileri.bakiye+=yatirilacakTuar;
        System.out.println("İsleminiz basarili bir sekilde gerceklesti.");
        System.out.println("Bakiyeniz: "+MusteriBilgileri.bakiye);

    }

    public static void bakiyeSorgula() {
        System.out.println("Bakiyeniz: "+MusteriBilgileri.bakiye+" TL");

    }
    }


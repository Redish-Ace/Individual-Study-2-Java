package Institutie;
/*Problema 16:Personal.
Elaboraţi un program prin intermediul căruia se va efectua evidenţa personalului dintr-o instituţie. În instituţia dată sunt următoarele tipuri de personal:
Angajat – despre care se cunosc datele: numele, prenumele, codul fiscal, numărul de ore lucrate, plata pentru o oră.
În calitate de metode vor fi: citirea datelor de la tastatură, afişarea datelor la ecran, determinarea salariului conform formulei: salariu=ore_lucrate*plata_ora.

Scrieți și o clasă Test în care efectuați operaţii de adăugare a persoanelor,
afişare a tuturor persoanelor din instituţie,
determinarea bugetului lunar de salarizare a personalului instituţiei,
afișați lista elevilor cu bursă,
lista elevilor fărăr bursă,
datele angajatului cu salariul maxim.*/

import java.io.*;
import java.util.*;
import javax.swing.*;
public class Angajat extends Persoana {
    protected int[] oreLucrate = new int[1000];
    protected double[] plataOra = new double[1000], salariu = new double[1000];
    public int entries = 0;
    public double max = -9999;

    // Constructor care actualizeaza informatia din vectori
    public Angajat(){
        super.file = new File("C:\\Users\\Lilian\\Downloads\\POO Individuala\\StudiuIndividual2\\src\\Institutie\\Angajat.txt");
        try {
            Scanner scan = new Scanner(file);
            int index = 0;
            while (scan.hasNext()){
                super.nume[index] = scan.next();
                super.prenume[index] = scan.next();
                super.codFiscal[index] = scan.next();
                oreLucrate[index] = scan.nextInt();
                plataOra[index] = scan.nextDouble();
                salariu[index] = scan.nextDouble();
                index++;
                entries++;
            }
        }
        catch(FileNotFoundException fnfe){
            System.out.println("File Not Found");
        }
    }

    // Actualizeaza fisierul cu informatie din vectori
    protected void updateFile(){
        try{
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(int index = 0; index < entries; index++){
                if(nume[index] == null) break;

                bufferedWriter.write(nume[index]+" "+prenume[index]+" "+codFiscal[index]+" "+oreLucrate[index]+" "+plataOra[index]+" "+salariu[index]+"\n");
            }
            bufferedWriter.close();
            fileWriter.close();
        }catch(IOException ioe){
            System.out.println("IOException");
        }
    }

    // Actualizeaza vectorii cu informatie din fisier
    protected void updateArrays(){
        try {
            Scanner scan = new Scanner(file);
            int index = 0;
            while (scan.hasNext()){
                super.nume[index] = scan.next();
                super.prenume[index] = scan.next();
                super.codFiscal[index] = scan.next();
                oreLucrate[index] = scan.nextInt();
                plataOra[index] = scan.nextDouble();
                salariu[index] = scan.nextDouble();
                index++;
                entries++;
            }
        }
        catch(FileNotFoundException fnfe){
            System.out.println("File Not Found");
        }
    }

    // Introducerea informatiei despre un numar de angajati de baza
    protected void setAngajat(){
        int add = Integer.parseInt(JOptionPane.showInputDialog("Cati angajati doriti sa adaugati"));
        try{
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(int index = 0; index<add; index++){
               setPersAngajat(index, "C:\\Users\\Lilian\\Downloads\\POO Individuala\\StudiuIndividual2\\src\\Institutie\\Angajat.txt");

                oreLucrate[index] = Integer.parseInt(JOptionPane.showInputDialog("Ore lucrate "+super.nume[index]));

                plataOra[index] = Double.parseDouble(JOptionPane.showInputDialog("Plata pe ora  "+super.nume[index]));
                bufferedWriter.write(oreLucrate[index]+" "+plataOra[index]+" "+salariu[index]+"\n");
            }
            bufferedWriter.close();
            fileWriter.close();
        }catch(IOException ioe){
            System.out.println("IOException");
        }
        menu();
    }

    // Returneaza lista angajatilor
    protected void getAngajatList(){
        updateArrays();
        if(super.nume[0] == null){
            System.out.println("Nu sunt angajati in lista"); }
        else {
            String[] stringList = new String[1000];
            for (int index = 0; index < entries; index++) {
                if(super.nume[index] == null) break;
                stringList[index] = nume[index] + " " + prenume[index] + " " + codFiscal[index] + " " + oreLucrate[index] + " " + plataOra[index] + " " + salariu[index];
            }
            JList jList = new JList(stringList);

            JPanel jPanel = new JPanel();
            jPanel.add(jList);

            JFrame jFrame = new JFrame("Angajati");
            jFrame.add(jPanel);
            jFrame.setSize(350, 1080);
            jFrame.show();
        }
        super.setTimer();
    }

    // Cacluleaza salariu fiecarui angajat
    protected void salariu(){
        updateArrays();
        for(int index = 0; index<entries; index++){
            if(super.nume[index] == null) break;
            salariu[index] = plataOra[index]*oreLucrate[index];
            if(salariu[index]>max) max = salariu[index];
        }
        updateFile();
    }

    // Returneaza lista angajatilor cu salariu maxim
    protected void maxSalariu(){
        salariu();
        updateArrays();
        if(super.nume[0] == null){
            System.out.println("Nu sunt angajati in lista"); }
        else {
            String[] stringList = new String[1000];
            for (int index = 0; index < entries; index++) {
                if(super.nume[index] == null) break;
                if (salariu[index] == max) {
                    stringList[index] = nume[index] + " " + prenume[index] + " " + codFiscal[index] + " " + oreLucrate[index] + " " + plataOra[index] + " " + salariu[index];
                }
            }
            JList jList = new JList(stringList);

            JPanel jPanel = new JPanel();
            jPanel.add(jList);

            JFrame jFrame = new JFrame("Angajati su salariu maxim");
            jFrame.add(jPanel);
            jFrame.setSize(350, 1080);
            jFrame.show();
        }
        super.setTimer();
    }

    public void menu(){
        byte switchIndex = Byte.parseByte(JOptionPane.showInputDialog("1.Adauga angajati\n2.Arata lista angajatilor\n3.Arata lista angajatilor cu salariu maxim"));

        switch(switchIndex){
            case 1 -> setAngajat();
            case 2 -> getAngajatList();
            case 3 -> maxSalariu();
            default -> System.exit(0);
        }
    }

    public void setSalariu(double salariu, int index) {
        this.salariu[index] = salariu;
    }

    public double getSalariu(int index) {
        return salariu[index];
    }
}

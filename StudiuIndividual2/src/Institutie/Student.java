package Institutie;

/*Student – despre care se cunosc datele: numele, prenumele, codul fiscal, grupa, media.
În calitate de metode vor fi: citirea datelor de la tastatură, afişarea datelor la ecran, determinarea bursei.
Bursa studentului o vom determina astfel:
Bursa= 0 lei, dacă media <7.5; 300 lei, dacă 7.5 ≤media <8.5; 400 lei, dacă 8.5 ≤media <9.5; 500 lei, dacă media ≥9.5 */

import java.io.*;
import java.util.*;
import javax.swing.*;
public class Student extends Persoana {
    protected  JFrame jFrame;
    protected String[] grupa = new String[1000];
    protected double[] media = new double[1000];
    protected int[] bursa = new int[1000];
    public int entries = 0;

    // Constructor care actualizeaza vectorii
    public Student(){
        super.file = new File("C:\\Users\\Lilian\\Downloads\\POO Individuala\\StudiuIndividual2\\src\\Institutie\\Student.txt");
        try {
            Scanner scan = new Scanner(file);
            int index = 0;
            while (scan.hasNext()){
                super.nume[index] = scan.next();
                super.prenume[index] = scan.next();
                super.codFiscal[index] = scan.next();
                grupa[index] = scan.next();
                media[index] = scan.nextDouble();
                bursa[index] = scan.nextInt();
                index++;
                entries++;
            }
        }
        catch(FileNotFoundException fnfe){
            System.out.println("File Not Found");
        }
    }

    // Actualizeaza fisierul cu informatii din vectori
    protected void updateFile(){
        try{
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(int index = 0; index < entries; index++){
                if(super.nume[index] == null) break;
                else{
                    bufferedWriter.write(super.nume[index] + " "+super.prenume[index] + " "+super.codFiscal[index] + " "+grupa[index] + " "+media[index] + " "+bursa[index] + "\n");
                }
            }
            bufferedWriter.close();
        }catch(IOException ioe){
            System.out.println("IOException");
        }
    }

    // Actualizarea vectoriilor cu informatie din fisier
    protected void updateArrays(){
        try {
            Scanner scan = new Scanner(file);
            int index = 0;
            while (scan.hasNext()){
                super.nume[index] = scan.next();
                super.prenume[index] = scan.next();
                super.codFiscal[index] = scan.next();
                grupa[index] = scan.next();
                media[index] = scan.nextDouble();
                bursa[index] = scan.nextInt();
                index++;
                entries++;
            }
        }
        catch(FileNotFoundException fnfe){
            System.out.println("File Not Found");
        }
    }

    // Introducerea informatiei unui numar de studenti
    protected void setStudent() {
        int add = Integer.parseInt(JOptionPane.showInputDialog("Cati angajati doriti sa adaugati"));
        try{
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(int index = 0; index<add; index++){
                setPersStudent(index);

                grupa[index] = JOptionPane.showInputDialog("Grupa studentului "+super.nume[index]);
                bufferedWriter.write(grupa[index]+" ");

                media[index] = Double.parseDouble(JOptionPane.showInputDialog("media studentului "+super.nume[index]));
                bufferedWriter.write(media[index]+" ");

                bursa[index] = 0;
                bufferedWriter.write(bursa[index]+"\n");
            }
            bufferedWriter.close();
            fileWriter.close();
        }catch(IOException ioe){
            System.out.println("IOException");
        }
        super.setTimer();
    }

    // Returneaza lista studentilor
    protected void getStudentList() {
        updateArrays();
        if(super.nume[0] == null){
            System.out.println("Nu sunt studenti in lista"); }
        else {
            String[] stringList = new String[1000];
            for (int index = 0; index < entries; index++) {
                if(nume[index] == null) break;

                stringList[index] = super.nume[index] + " " + super.prenume[index] + " " + super.codFiscal[index] + " " +
                        grupa[index] + " " + media[index] + " " + bursa[index];
            }
            JList jList = new JList(stringList);

            JPanel jPanel = new JPanel();
            jPanel.add(jList);

            JFrame jFrame = new JFrame("Studenti");
            jFrame.add(jPanel);
            jFrame.setSize(350, 1080);
            jFrame.show();
        }
        super.setTimer();
    }

    /*Bursa studentului o vom determina astfel:
    Bursa= 0 lei, dacă media <7.5; 300 lei, dacă 7.5 ≤media <8.5; 400 lei, dacă 8.5 ≤media <9.5; 500 lei, dacă media ≥9.5*/
    protected void setBursa(){
        updateArrays();
        for(int index = 0; index<entries; index++){
            if(media[index]>=9.5) bursa[index] = 500;
            else if(media[index]<9.5 && media[index]>=8.5) bursa[index] = 400;
            else if(media[index]<8.5 && media[index]>=7.5) bursa[index] = 300;
            else bursa[index] = 0;
        }
        updateFile();
        menu();
    }

    // Returneaza informatia studentilor cu bursa
    protected void studentBursa(){
        updateArrays();
        if (super.nume[0] == null) {
            System.out.println("Nu sunt studenti in lista"); }
        else {
            String[] list = new String[1000];
            int index1 = 0;
            for (int index = 0; index < entries; index++) {
                if (bursa[index] > 0) {
                    list[index1] = super.nume[index] + " " + super.prenume[index] + " " + super.codFiscal[index] + " " + grupa[index] + " " + media[index] + " " + bursa[index];
                    index1++;
                }
            }

            JList jList = new JList(list);

            JPanel jPanel = new JPanel();
            jPanel.add(jList);

            JFrame jFrame = new JFrame("Studenti cu bursa");
            jFrame.add(jPanel);
            jFrame.setSize(350, 1080);
            jFrame.show();
        }
        super.setTimer();
    }

    // Returneaza informatia studentilor fara bursa
    protected void studentNoBursa(){
        updateArrays();
        if (super.nume[0] == null) {
            System.out.println("Nu sunt studenti in lista"); }
        else {
            String[] list = new String[1000];
            int index1 = 0;
            for (int index = 0; index < entries; index++) {
                if(nume[index] == null) break;
                if (bursa[index] == 0) {
                    list[index1] = super.nume[index] + " " + super.prenume[index] + " " + super.codFiscal[index] + " " + grupa[index] + " " + media[index] + " " + bursa[index];
                    index1++;
                }
            }

            JList jList = new JList(list);

            JPanel jPanel = new JPanel();
            jPanel.add(jList);

            jFrame = new JFrame("Studenti fara bursa");
            jFrame.add(jPanel);
            jFrame.setSize(350, 1080);
            jFrame.show();
        }
        super.setTimer();
    }

    @Override
    public void menu(){
        byte switchIndex = Byte.parseByte(JOptionPane.showInputDialog("1.Adauga studenti\n2.Arata lista studentilor\n3.Determinati bursa\n4.Arata lista studentilor cu bursa\n5.Arata lista studentilor fara bursa"));

        switch(switchIndex){
            case 1 -> setStudent();
            case 2 -> getStudentList();
            case 3 -> setBursa();
            case 4 ->studentBursa();
            case 5 ->studentNoBursa();
            default -> System.exit(0);
        }
    }
}

package Institutie;

/*Angajat de bază – despre care se cunosc datele: numele, prenumele, codul fiscal, numărul de ore lucrate, plata pentru o oră, gradul (0,1,2,3), anul angajării.
În calitate de metode vor fi: citirea datelor de la tastatură, afişarea datelor la ecran,
determinarea salariului conform formulei: salariu=ore_lucrate*plata_ora.
Angajatul va beneficia şi de un adaos la salariu. Salariaţii cu gradul 0 vor avea un adaos de 50%, gradul 1 – 40%, gradul 2 – 30%.
În dependenţă de stagiu adaosul va fi de 35% pentru cei cu un stagiu ≥ 10 ani, 25% pentru cei cu un stagiu ≥ 3 ani şi <10 ani şi 10% pentru toţi ceilalţi.*/

import java.io.*;
import java.util.*;
import javax.swing.*;
public class AngajatDeBaza extends Angajat {
    private final File fileBaza = new File("C:\\Users\\Lilian\\Downloads\\POO Individuala\\StudiuIndividual2\\src\\Institutie\\AngajatDeBaza.txt");
    private final byte[] grad = new byte[1000];
    private final int[] anulAngajarii = new int[1000];
    private boolean isSet = false;
    final int year = 2023;

    // Constructor care actualizeaza informatia din vectori
    public AngajatDeBaza(boolean set) {
        if (set){
            isSet = set;
            try {
                Scanner scan = new Scanner(fileBaza);
                int index = 0;
                while (scan.hasNext()){
                    super.nume[index] = scan.next();
                    super.prenume[index] = scan.next();
                    super.codFiscal[index] = scan.next();
                    super.oreLucrate[index] = scan.nextInt();
                    super.plataOra[index] = scan.nextDouble();
                    grad[index] = scan.nextByte();
                    anulAngajarii[index] = scan.nextInt();
                    super.salariu[index] = scan.nextDouble();
                    index++;
                    super.entries++;
                }
            }
            catch(FileNotFoundException fnfe){
                System.out.println("File Not Found");
            }
        }
        else {
            try {
                Scanner scan = new Scanner(super.file);
                int index = 0;
                while (scan.hasNext()) {
                    super.nume[index] = scan.next();
                    super.prenume[index] = scan.next();
                    super.codFiscal[index] = scan.next();
                    super.oreLucrate[index] = scan.nextInt();
                    super.plataOra[index] = scan.nextDouble();
                    super.salariu[index] = scan.nextDouble();
                    index++;
                    super.entries++;
                }
            } catch (FileNotFoundException fnfe) {
                System.out.println("File Not Found");
            }
        }
    }

    // Introducerea gradului si anului angajat angajatilor
    public void setGradAndYear(){
        for(int index = 0; index<super.entries; index++){
            if(super.nume[index]==null) break;
            grad[index] = Byte.parseByte(JOptionPane.showInputDialog("Gradul angajatului "+super.nume[index]));
            anulAngajarii[index] = Integer.parseInt(JOptionPane.showInputDialog("Anul angajarii "+super.nume[index]));
        }
        isSet = true;
        salariuBoost();
        menu();
    }

    // Actualizeaza suma salariului dupa gradul angajatului si diferenta anului curent si anul de angajare
    public void salariuBoost(){
        updateArrays();

        for(int index=0; index<super.entries; index++){
            if(super.nume[index] == null) break;
            switch(grad[index]){
                case 0 -> setSalariu(getSalariu(index)+(getSalariu(index)*0.5), index);
                case 1 -> setSalariu(getSalariu(index)+(getSalariu(index)*0.4), index);
                case 2 -> setSalariu(getSalariu(index)+(getSalariu(index)*0.3), index);
                default -> setSalariu(getSalariu(index), index);
            }
            if(year-anulAngajarii[index]>=10) setSalariu(getSalariu(index)+(getSalariu(index)*0.35), index);
            else if(year-anulAngajarii[index]>=3) setSalariu(getSalariu(index)+(getSalariu(index)*0.25), index);
            else setSalariu(getSalariu(index)+(getSalariu(index)*0.1), index);
            if(super.salariu[index]>super.max) super.max = super.salariu[index];
        }

        updateFile();
    }

    // Actualizeaza vectorii cu informatie din fisier
    protected void updateArrays(){
        try {
            Scanner scan = new Scanner(fileBaza);
            int index = 0;
            while (scan.hasNext()){
                super.nume[index] = scan.next();
                super.prenume[index] = scan.next();
                super.codFiscal[index] = scan.next();
                super.oreLucrate[index] = scan.nextInt();
                super.plataOra[index] = scan.nextDouble();
                grad[index] = scan.nextByte();
                anulAngajarii[index] = scan.nextInt();
                super.salariu[index] = scan.nextDouble();
                index++;
                entries++;
            }
        }
        catch(FileNotFoundException fnfe){
            System.out.println("File Not Found");
        }
    }

    // Actualizeaza fisierul cu informatie din vectori
    @Override protected void updateFile(){
        try{
            FileWriter fileWriter = new FileWriter(fileBaza);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(int index = 0; index < super.entries; index++){
                if(nume[index] == null) break;

                bufferedWriter.write(super.nume[index]+" "+super.prenume[index]+" "+super.codFiscal[index]+" "+super.oreLucrate[index]+" "+super.plataOra[index]+" "+
                        grad[index]+" "+anulAngajarii[index]+" "+super.salariu[index]+"\n");
            }
            bufferedWriter.close();
            fileWriter.close();
        }catch(IOException ioe){
            System.out.println("IOException");
        }
    }

    // Introducerea informatiei despre un numar de angajati de baza
    @Override protected void setAngajat(){
        int add = Integer.parseInt(JOptionPane.showInputDialog("Cati angajati doriti sa adaugati"));
        try{
            FileWriter fileWriter = new FileWriter(fileBaza, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(int index = 0; index<add; index++){
                setPersAngajat(index, "C:\\Users\\Lilian\\Downloads\\POO Individuala\\StudiuIndividual2\\src\\Institutie\\AngajatDeBaza.txt");
                super.oreLucrate[index] = Integer.parseInt(JOptionPane.showInputDialog("Ore lucrate "+super.nume[index]));
                super.plataOra[index] = Double.parseDouble(JOptionPane.showInputDialog("Plata pe ora "+super.nume[index]));
                grad[index] = Byte.parseByte(JOptionPane.showInputDialog("Gradul angajatului "+super.nume[index]));
                anulAngajarii[index] = Integer.parseInt(JOptionPane.showInputDialog("Anul angajarii "+super.nume[index]));
                bufferedWriter.write(super.oreLucrate[index]+" "+super.plataOra[index]+" "+grad[index]+" "+anulAngajarii[index]+" "+salariu[index]+"\n");
            }
            bufferedWriter.close();
            fileWriter.close();
        }catch(IOException ioe){
            System.out.println("IOException");
        }
        menu();
    }

    // Returneaza lista angajatilor
    @Override protected void getAngajatList(){
        updateArrays();
        if(super.nume[0] == null){
            System.out.println("Nu sunt angajati in lista"); }
        else {
            String[] stringList = new String[1000];
            for (int index = 0; index < super.entries; index++) {
                if(super.nume[index] == null) break;
                stringList[index] = super.nume[index] + " " + super.prenume[index] + " " + super.codFiscal[index] + " " +
                        super.oreLucrate[index] + " " + super.plataOra[index] + " " + super.salariu[index] + " " + grad[index]
                        + " " + anulAngajarii[index];
            }
            JList jList = new JList(stringList);

            JPanel jPanel = new JPanel();
            jPanel.add(jList);

            JFrame jFrame = new JFrame("Angajati de baza");
            jFrame.add(jPanel);
            jFrame.setSize(400, 1080);
            jFrame.show();
        }
        super.setTimer();
    }

    // Returneaza lista angajatilor cu salariu maxim
    protected void maxSalariu(){
        salariu();
        salariuBoost();
        if(super.nume[0] == null){
            System.out.println("Nu sunt angajati in lista"); }
        else {
            String[] stringList = new String[1000];
            for (int index = 0; index < entries; index++) {
                if(super.nume[index] == null) break;
                if (salariu[index] == max) {
                    stringList[index] = super.nume[index] + " " + super.prenume[index] + " " + super.codFiscal[index] + " " +
                            super.oreLucrate[index] + " " + super.plataOra[index] + " " + super.salariu[index] + " " + grad[index]
                            + " " + anulAngajarii[index];
                    System.out.println(stringList[index]);
                }
            }
            JList jList = new JList(stringList);

            JPanel jPanel = new JPanel();
            jPanel.add(jList);

            JFrame jFrame = new JFrame("Angajati cu salariu maxim");
            jFrame.add(jPanel);
            jFrame.setSize(350, 1080);
            jFrame.show();
        }
        super.setTimer();
    }

    @Override public void menu(){
        byte switchIndex = Byte.parseByte(JOptionPane.showInputDialog("1.Adauga angajati\n2.Arata lista angajatilor\n3.Adaugati gradul si anul angajarii angajatilor de baza\n4.Arata lista angajatilor cu salariu maxim"));

        if(isSet){
            switch (switchIndex) {
                case 1 -> setAngajat();
                case 2 -> getAngajatList();
                case 3 -> setGradAndYear();
                case 4 -> maxSalariu();
                default -> System.exit(0);
            }
        }
        else {
            switch (switchIndex) {
                case 1 -> super.setAngajat();
                case 2 -> super.getAngajatList();
                case 3 -> setGradAndYear();
                case 4 -> maxSalariu();
                default -> System.exit(0);
            }
        }
    }
}

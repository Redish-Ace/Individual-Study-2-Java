package Institutie;

import java.io.*;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

public abstract class Persoana {
    protected File file;
    protected String[] nume = new String[1000], prenume = new String[1000], codFiscal = new String[1000];

    // Introducerea informatiei unui angajat dupa index
    protected void setPersAngajat(int index, String filePath){
        file = new File(filePath);
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            nume[index] = JOptionPane.showInputDialog("Numele angajatului " + index);
            prenume[index] = JOptionPane.showInputDialog("Prenumele angajatului " + nume[index]);
            codFiscal[index] = JOptionPane.showInputDialog("Codul fiscal al angajatului " + nume[index]);
            bufferedWriter.write(nume[index] + " "+prenume[index] + " "+codFiscal[index] + " ");

            bufferedWriter.close();
            fileWriter.close();
        }catch(IOException ioe){
            System.out.println("IOException");
        }
    }

    // Introducerea informatiei unui student dupa index
    protected void setPersStudent(int index){
        file = new File("C:\\Users\\Lilian\\Downloads\\POO Individuala\\StudiuIndividual2\\src\\Institutie\\Student.txt");
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            nume[index] = JOptionPane.showInputDialog("Numele studentului " + index);
            prenume[index] = JOptionPane.showInputDialog("Prenumele studentului " + nume[index]);
            codFiscal[index] = JOptionPane.showInputDialog("Codul fiscal al studentului " + nume[index]);
            bufferedWriter.write(nume[index] + " "+prenume[index] + " "+codFiscal[index] + " ");

            bufferedWriter.close();
            fileWriter.close();
        }catch(IOException ioe){
            System.out.println("IOException");
        }
    }

    public void menu(){}

    // Seteaza un timer care va apela metoda menu dupa 5 secunde
    protected void setTimer() {
        java.util.Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                menu();
            }
        }, 5000);
    }
}

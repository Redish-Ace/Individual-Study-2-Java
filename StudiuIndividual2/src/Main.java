import Institutie.*;

import javax.swing.*;
//Scrieți și o clasă Test în care efectuați operaţii de adăugare a persoanelor, afişare a tuturor persoanelor din instituţie, determinarea bugetului lunar de salarizare a personalului instituţiei, afișați lista elevilor cu bursă, lista elevilor fărăr bursă, datele angajatului cu salariul maxim.
public class Main {
    public static void main(String[] args) {
        byte switchIndex = Byte.parseByte(JOptionPane.showInputDialog("1.Angajat\n2.Angajat de baza\n3.Student"));

        switch(switchIndex){
            case 1 -> {
                Angajat angajat = new Angajat();
                angajat.menu();
            }
            case 2 -> {
                AngajatDeBaza angajatDeBaza = new AngajatDeBaza(true);
                angajatDeBaza.menu();
            }
            case 3 -> {
                Student student = new Student();
                student.menu();
            }
            default -> System.out.println();
        }
    }
}
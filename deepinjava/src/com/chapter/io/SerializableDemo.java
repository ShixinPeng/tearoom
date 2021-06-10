package com.chapter.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SerializableDemo {


    public static void main(String[] args) {

        PrintStream out = System.out;

        out.println("ahah");
    }

    static void testOutput(){

        CarWheel obj = new CarWheel(88);

        File file = new File("CarWheel.data");
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));

            objectOutputStream.writeObject(obj);
            objectOutputStream.flush();

            ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
            Object object = objectInputStream.readObject();
            if (object instanceof CarWheel){
                CarWheel carWheel = (CarWheel) object;
                System.out.println( carWheel.toString());
            }

        }catch (IOException | ClassNotFoundException ioException){
            ioException.printStackTrace();
        }

        System.out.println();

    }

    static void instanceMustBeSerializable(){
        Cat cat = new Cat();

        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("Cat.data"))));

            objectOutputStream.writeObject(cat);
            objectOutputStream.flush();

        }catch (IOException ioException){
            ioException.printStackTrace();
        }

    }

    /**
     * [Chimpanzee{name='null'type='B', age=0}, Chimpanzee{name='null'type='B', age=0}]
     */
    static void writeAndReadChimpanzee(){
        ArrayList<Chimpanzee> Chimpanzees = new ArrayList<>();
        Chimpanzees.add(new Chimpanzee("Ham", 2, 'A'));
        Chimpanzees.add(new Chimpanzee("Enos", 4, 'B'));
        File file = new File("Chimpanzee.data");

        saveToFile2(Chimpanzees, file);

        List<Chimpanzee> list = readByFile2(file);
        System.out.println(list);
    }

    static void writeAndReadObject(){

        ArrayList<Gorilla> gorillas = new ArrayList<>();
        gorillas.add(new Gorilla("Grodd", 5, false));
        gorillas.add(new Gorilla("Ishmael", 8, true));
        File file = new File("gorilla.data");

        saveToFile(gorillas, file);

        List<Gorilla> list = readByFile(file);
        System.out.println(list);
    }

    static List<Chimpanzee> readByFile2(File dataFile) {
        ArrayList<Chimpanzee> chimpanzeeArrayList = new ArrayList<>();

        try {

            ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(dataFile)));

            while (true){
                Object object = objectInputStream.readObject();
                if (object instanceof Chimpanzee){
                    Chimpanzee target = (Chimpanzee) object;
                    chimpanzeeArrayList.add(target);
                }
            }

        } catch (IOException | ClassNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        return chimpanzeeArrayList;
    }

    static void saveToFile2(List<Chimpanzee> gorillas, File dataFile) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(dataFile)));

            for (Chimpanzee chimpanzee : gorillas) {
                objectOutputStream.writeObject(chimpanzee);
                objectOutputStream.flush();
            }
//            objectOutputStream.close();
        } catch (IOException ioException) {

            ioException.printStackTrace();
        }

    }

    static List<Gorilla> readByFile(File dataFile) {
        ArrayList<Gorilla> gorillas = new ArrayList<>();

        try {

            ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(dataFile)));

            while (true){
                Object object = objectInputStream.readObject();
                if (object instanceof Gorilla){
                    Gorilla gorilla = (Gorilla) object;
                    gorillas.add(gorilla);
                }
            }

        } catch (IOException | ClassNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        return gorillas;
    }

    static void saveToFile(List<Gorilla> gorillas, File dataFile) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(dataFile)));

            for (Gorilla gorilla : gorillas) {
                objectOutputStream.writeObject(gorilla);
                objectOutputStream.flush();
            }
//            objectOutputStream.close();
        } catch (IOException ioException) {

            ioException.printStackTrace();
        }

    }
}

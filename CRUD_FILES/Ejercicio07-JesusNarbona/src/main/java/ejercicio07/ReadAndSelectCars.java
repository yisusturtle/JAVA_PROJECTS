package ejercicio07;

import ejercicio07.vehiculos.Deportivo;
import ejercicio07.vehiculos.Furgoneta;
import ejercicio07.vehiculos.Turismo;
import ejercicio07.vehiculos.Vehiculo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadAndSelectCars {
    public static void main(String[] args) {
        ArrayList<Vehiculo> vehiculos = new ArrayList<>();

        //Ruta
        String ruta = "vehiculos.txt";

        //Lectura
        try(Scanner sc = new Scanner(new FileReader(ruta))){
            while(sc.hasNextLine()){
                String linea = sc.nextLine();
                String[] parts = linea.substring(2,linea.length()-1).split(":");

                if(linea.charAt(0)==0){
                    Turismo turismo = new Turismo();
                    turismo.setMatricula(parts[0]);
                    turismo.setKilometros(Double.parseDouble(parts[1]));
                    turismo.setNumeroBastidor(parts[2]);
                    turismo.setMarca(parts[3]);
                    turismo.setPuertas(Integer.parseInt(parts[4]));
                    turismo.setMarchaAutomatica(Boolean.parseBoolean(parts[5]));
                    turismo.setColor(parts[6]);
                    vehiculos.add(turismo);

                }else if(linea.charAt(0)==1){
                    Deportivo deportivo = new Deportivo();
                    deportivo.setMatricula(parts[0]);
                    deportivo.setKilometros(Double.parseDouble(parts[1]));
                    deportivo.setNumeroBastidor(parts[2]);
                    deportivo.setMarca(parts[3]);
                    deportivo.setPuertas(Integer.parseInt(parts[4]));
                    deportivo.setMarchaAutomatica(Boolean.parseBoolean(parts[5]));
                    deportivo.setNeumaticos(parts[6]!=null?parts[6]:null);
                    vehiculos.add(deportivo);
                }else{
                    Furgoneta furgoneta = new Furgoneta();
                    furgoneta.setMatricula(parts[0]);
                    furgoneta.setKilometros(Double.parseDouble(parts[1]));
                    furgoneta.setNumeroBastidor(parts[2]);
                    furgoneta.setMarca(parts[3]);
                    furgoneta.setPuertas(Integer.parseInt(parts[4]));
                    furgoneta.setMarchaAutomatica(Boolean.parseBoolean(parts[5]));
                    vehiculos.add(furgoneta);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        vehiculos.forEach(coche->{
            System.out.println(coche);
        });
    }
}

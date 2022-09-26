/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordenación;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import edu.princeton.cs.algs4.StdRandom;

/**
 *
 * @author Jennifer
 */
public class Taller3 {
    public static void main(String[] args) throws ParseException {
        // TODO code application logic here
        Pelicula[] peliculas = Pelicula.LeerCSV("C:\\Users\\Jennifer\\OneDrive - UPB\\Documentos\\SEMESTRE IV\\ESTRUCTURAS DE DATOS\\IMDb movies.csv");
        
        //System.out.println(Pelicula.MedirTiempoInsertion(peliculas,Pelicula.ComparadorPeliculas.compTitulo));        
        //System.out.println(Pelicula.MedirTiempoMergeX(peliculas,Pelicula.ComparadorPeliculas.compTitulo));
        //System.out.println(Pelicula.MedirTiempoArrays(peliculas,Pelicula.ComparadorPeliculas.compTitulo));
        
        double cont = 0;
        List<Double> Insertion = new ArrayList<Double>();
        List<Double> MergeX = new ArrayList<Double>();
        List<Double> Arrays = new ArrayList<Double>();
        
        for(int i = 0; i < 20; i++){
            StdRandom.shuffle(peliculas);
            System.out.println(Pelicula.MedirTiempoInsertion(peliculas,Pelicula.ComparadorPeliculas.compTitulo));
            
            
        }
        /*for(int j = 0; j < 20; j++){
            StdRandom.shuffle(peliculas);
            cont =+ Pelicula.MedirTiempoMergeX(peliculas,Pelicula.ComparadorPeliculas.compTitulo);
        }*/
       // System.out.println(cont);
        /*for(int k = 0; k < 20; k++){
            Arrays.add(Pelicula.MedirTiempoMergeX(peliculas,Pelicula.ComparadorPeliculas.compTitulo));
        }*/
        //Insertion.toString();
        /*for (double x: MergeX){
            System.out.println(x);
        }*/
        //MergeX.toString();
        //Arrays.toString();
        
    }
    
}

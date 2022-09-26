/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordenación;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.MergeX;
import edu.princeton.cs.algs4.Selection;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
/**
 *
 * @author Jennifer
 */
public class Pelicula implements Comparable<Pelicula> {
    private String imdb_title_id;
    private String title;
    private String original_title;
    private int year;
    private Date date_published;
    private String genre;
    private int duration;
    private String country;
    private String language;
    private String director;
    private String writer;
    private String production_company;
    private String actors;
    private String description;
    private float avg_vote;
    private int votes;
    private String budget;
    private String usa_gross_income;
    private String worlwide_gross_income;
    private String metascore;
    private float reviews_from_users;
    private String reviews_from_critics;
    
   
    
    public static Pelicula[] LeerCSV(String ruta) throws ParseException{
        
        List<Pelicula> listaP = new ArrayList<Pelicula>();
        In in = new In(ruta);
        in.readLine(); // ignore first line
        int counter = 0;
        while (!in.isEmpty()) {
            counter++;
            String line = in.readLine();
            // StdOut.println(line);
            try {
            String[] fields = null;
            
                // Solucion para no separar el campo de actores encerrado en comillas:
                // Tomada de: https://stackoverflow.com/questions/1757065/java-splitting-a-comma-separated-string-but-ignoring-commas-in-quotes
                fields = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                // StdOut.println(fields.length);
                Pelicula p = new Pelicula();
                p.imdb_title_id = fields[0];
                p.title = fields[1];
                p.original_title = fields[2];
                p.year = Integer.valueOf(fields[3]);
                p.date_published = parseDate(fields[4]);
                p.genre = fields[5];
                p.duration = Integer.valueOf(fields[6]);
                p.country = fields[7];
                p.language = fields[8];
                p.director = fields[9];
                p.writer = fields[10];
                p.production_company = fields[11];
                p.actors = fields[12];
                p.description = fields[13];
                p.avg_vote = Float.valueOf(fields[14]);
                p.votes = Integer.valueOf(fields[15]);
                p.budget = fields[16];
                p.usa_gross_income = fields[17];
                p.worlwide_gross_income = fields[18];
                p.metascore = fields[19];
                p.reviews_from_users = parseFloat(fields[20]);
                p.reviews_from_critics = (fields.length>21) ? fields[21] : null;
                listaP.add(p); //añadimos a la lista
            }
            catch(NumberFormatException e) {
                StdOut.println("ERROR: Linea "+counter);
                StdOut.println(line);
                StdOut.println();
                return null;
            }
        }
        Pelicula[] vectorP = new Pelicula[listaP.size()]; //llenamos el vector 
        for(int i = 0; i < listaP.size(); i++){
            vectorP[i] = listaP.get(i);
        }
        return vectorP;
    }
    private static float parseFloat(String s) {
        try {
            return Float.valueOf(s);
        }
        catch(NumberFormatException e) {
            return 0.0f;
        }
    }

    private static Date parseDate(String s) throws ParseException {
        if (s.indexOf(' ')>=0) return sdfz.parse(s);
        else if (s.indexOf('-')<0) return sdfy.parse(s);
        return sdf.parse(s);
    }
    
    final static SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
    final static SimpleDateFormat sdfy = new SimpleDateFormat("yyyy");
    final static SimpleDateFormat sdfz = new SimpleDateFormat("'TV Movie 'yyyy");

    @Override
    public int compareTo(Pelicula o) {
        return Float.compare(this.avg_vote, o.avg_vote);//si es 0 son iguales, si es positivo this es mayor
        //si es negativo o es mayor 
    }
    
    public void ListarPorRating(List<Pelicula> listaP){
        Pelicula[] p = listaP.toArray(new Pelicula[] {});
        Comparator<Pelicula> compRating = (p1, p2) -> { return  Float.compare(p2.avg_vote, p1.avg_vote); };
        Insertion.sort(p, compRating);
        for(Pelicula x: p) StdOut.println(x.toString());
    }
    @Override
    public String toString(){
        String cadena = "";
        cadena = "imdb_title_id: " + this.imdb_title_id + 
                "\ntitle: " + this.title + 
                "\noriginal_title: " + this.original_title +
                "\nyear: " + this.year + 
                "\ndate_published: " + this.date_published + 
                "\ngenre: " + this.genre + 
                "\nduration: " + this.duration + 
                "\ncountry: " +  this.country +
                "\nlanguage: " + this.language + 
                "\ndirector: " + this.director + 
                "\nwriter: " + this.writer + 
                "\nproduction_company: " + this.production_company +
                "\nactors: " +  this.actors +
                "\ndescription: " + this.description + 
                "\navg_vote: " + this.avg_vote + 
                "\nvotes: " + this.votes + 
                "\nbudget: " + this.budget + 
                "\nusa_gross_income: " + this.usa_gross_income + 
                "\nworlwide_gross_income: " + this.worlwide_gross_income + 
                "\nmetascore: " + this.metascore + 
                "\nreviews_from_users: " + this.reviews_from_users + 
                "\nreviews_from_critics: " + this.reviews_from_critics;
                        
        return cadena;
    }
    
    public void ListarPorComparador(Pelicula vec[], Comparator<Pelicula> c){
        Insertion.sort(vec, c);
        for(Pelicula x: vec) StdOut.println(x.toString());
    }
    
    public static class ComparadorPeliculas{
        static Comparator<Pelicula> compTitulo = (p1, p2) -> { return  p1.title.compareTo(p2.title); };
        static Comparator<Pelicula> compFechaPub = (p1, p2) -> { return (p1.date_published.compareTo(p2.date_published)); };
        
        public Comparator<Pelicula> getComp(){
            return compTitulo;
        }
        
    }
    
    public static double MedirTiempoInsertion(Pelicula vec[], Comparator c ){
        double t1 = 0;
        StdRandom.shuffle(vec);

        Stopwatch s1 = new Stopwatch();
        Insertion.sort(vec,c);
        return t1 = s1.elapsedTime();
    }
    
    public static double MedirTiempoMergeX(Pelicula vec[], Comparator c ){
        double t2 = 0;
        StdRandom.shuffle(vec);
        Stopwatch s2 = new Stopwatch();
        MergeX.sort(vec, c);
        return t2 = s2.elapsedTime();
    }
    
    public static double MedirTiempoArrays(Pelicula vec[], Comparator c ){
        double t3 = 0;
        StdRandom.shuffle(vec);
        Stopwatch s3 = new Stopwatch();
        Arrays.sort(vec, c);
        return t3 = s3.elapsedTime();
    }
    
    
    
    
}





package Pruebas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.SwingConstants.CENTER;

class Panel extends JPanel{

    /*----------------------------------------------------------------------------------------------------------------*/
    //Creo variables de forma estatica para poder llamarlos en cualquier parte de la clase.

    private static JPanel buttonPanel;

    private static JLabel background;

    private static JPanel infoPokemon;

    private static JPanel equipo;

    private static List<Pokemon> listaPokemon = new ArrayList<>(Output_Input.Input());

    /*----------------------------------------------------------------------------------------------------------------*/


    Panel(){
        background = crearBackground();

        buttonPanel = crearBotones();

        this.add(background);
        background.setLayout(new FlowLayout());
        background.add(buttonPanel);
    }

    private static JLabel crearBackground(){

        ImageIcon pika = new ImageIcon("iconos\\pokemon-backgrounds-8.jpg");
        Image pikachu = pika.getImage().getScaledInstance(700, 656, Image.SCALE_SMOOTH);

        background = new JLabel(new ImageIcon(pikachu));
        background.setBounds(0,0, 500, 700);

        return background;

    }

    private static JPanel crearBotones(){

        buttonPanel = new JPanel( new GridLayout(7, 2, 6, 5) );
        buttonPanel.setBorder( new EmptyBorder(0, 0, 0, 0) );

        buttonPanel.setOpaque( false );

        for (int i=0;   i<13;    i++) {

            Boton boton = new Boton(listaPokemon.get(i));
            boton.setPreferredSize( new Dimension(300, 75) );

            if (i==12){
                boton.setVisible(false);
            }

            buttonPanel.add(boton);

        }



        ImageIcon pokeball = new ImageIcon("iconos\\pokemon\\13.png");
        Boton boton = new Boton(pokeball);

        if (equipo != null) boton.setVisible(false);

        buttonPanel.add(boton);

        return buttonPanel;

    }

    static void borrarBotones(String nombre){
        if (buttonPanel.isVisible()){
            buttonPanel.setVisible(false);
            infoPokemon(nombre);
        }else {
            infoPokemon.setVisible(false);
            background.remove(infoPokemon);
            buttonPanel.setVisible(true);
        }
    }

    static void borrarBotones(){
        if (buttonPanel.isVisible()) {
            buttonPanel.setVisible(false);
        }else {
            background.remove(equipo);
            buttonPanel.setVisible(true);
            /*if (Boton.equipoVisible) {
                buttonPanel.remove(buttonPanel.getComponent(13));
            }*/
        }
    }

    private static void infoPokemon(String nombre) {

        infoPokemon = new JPanel(new GridLayout(4,2,6,5));
        infoPokemon.setBorder( new EmptyBorder(0, 0, 0, 0));

        //Agregar La imagen del pokemon
        ImageIcon pokemon = new ImageIcon("iconos\\pokemonGif\\" + nombre + ".gif");
        JLabel pokegif = new JLabel(pokemon);
        pokegif.setSize(300, 75);
        infoPokemon.add(pokegif);

        int tipoPrinc = -1;
        int tipoSec = -1;
        String nomb = "";
        int nroPokedex = 0;
        double altura = 0;
        double peso = 0;
        String descripcion = "";
        //Busco el pokemon en la lista cuyo nombre coincida con el dado en esta clase
        for (Pokemon poke : listaPokemon) {
            if (poke.getName().equals(nombre)) {
                tipoPrinc = poke.getTipoPrinc();
                tipoSec = poke.getTipoSec();
                nomb = poke.getName();
                nroPokedex = poke.getNroPokedex();
                altura = poke.getAltura();
                peso = poke.getPeso();
                descripcion = poke.getDescripcion();
            }
        }

        JLabel nom = new JLabel("Nombre: "+nomb);
        nom.setHorizontalAlignment(CENTER);
        nom.setSize(300,75);
        nom.setFont(new Font("Arial", Font.BOLD, 24));
        infoPokemon.add(nom);

        if (tipoSec==-1){

            JLabel text = new JLabel("Tipo:");
            text.setFont(new Font("Arial", Font.BOLD, 24));
            text.setHorizontalAlignment(CENTER);
            text.setSize(300,75);
            infoPokemon.add(text);

            ImageIcon tipo1 = new ImageIcon("iconos\\Tipos\\"+tipoPrinc+".png");
            Image pokem = tipo1.getImage().getScaledInstance(100,45, Image.SCALE_SMOOTH);
            JLabel tipoP = new JLabel(new ImageIcon(pokem));
            tipoP.setHorizontalAlignment(CENTER);
            tipoP.setSize(300, 75);
            infoPokemon.add(tipoP);

        }else {
            ImageIcon tipo1 = new ImageIcon("iconos\\Tipos\\"+tipoPrinc+".png");
            Image pokem = tipo1.getImage().getScaledInstance(100,45, Image.SCALE_SMOOTH);
            JLabel tipoP = new JLabel(new ImageIcon(pokem));
            tipoP.setHorizontalAlignment(CENTER);
            tipoP.setSize(300, 75);
            infoPokemon.add(tipoP);

            ImageIcon tipo2 = new ImageIcon("iconos\\Tipos\\"+tipoSec+".png");
            Image pokemo = tipo2.getImage().getScaledInstance(100,45, Image.SCALE_SMOOTH);
            JLabel tipoS = new JLabel(new ImageIcon(pokemo));
            tipoS.setHorizontalAlignment(CENTER);
            tipoS.setSize(300, 75);
            infoPokemon.add(tipoS);
        }

        JLabel nro = new JLabel("Nro Pokedex: #"+nroPokedex);
        nro.setFont(new Font("Arial", Font.BOLD, 24));
        nro.setHorizontalAlignment(CENTER);
        nro.setSize(300,75);
        infoPokemon.add(nro);

        JLabel alt = new JLabel("Altura: "+altura+" m");
        alt.setFont(new Font("Arial", Font.BOLD, 24));
        alt.setHorizontalAlignment(CENTER);
        alt.setSize(300,75);
        infoPokemon.add(alt);

        JLabel pes = new JLabel("Peso: "+peso+" Kg");
        pes.setFont(new Font("Arial", Font.BOLD, 24));
        pes.setHorizontalAlignment(CENTER);
        pes.setSize(300,75);
        infoPokemon.add(pes);

        JLabel desc = new JLabel("Descripcion: "+descripcion);
        desc.setFont(new Font("Arial", Font.BOLD, 24));
        desc.setHorizontalAlignment(CENTER);
        desc.setSize(300,75);
        //infoPokemon.add(desc);

        //Agregar el boton de volver a la pantalla anterior
        Boton boton = new Boton("Volver atras");
        boton.setSize(300, 75);
        infoPokemon.add(boton);


        infoPokemon.setOpaque(false);
        background.setLayout(new FlowLayout());
        background.add(infoPokemon);
    }

    static void crearEquipo(){
        JLabel texto = new JLabel("Elija 3 pokemon");
        texto.setHorizontalAlignment(CENTER);
        texto.setFont(new Font("Arial", Font.BOLD, 24));
        texto.setBackground(Color.orange);
        texto.setOpaque(true);

        equipo = new JPanel( new GridLayout(2, 3, 6, 5) );
        equipo.setBorder( new EmptyBorder(0, 0, 0, 0) );
        equipo.add(texto);

        for (int i = 0; i < 5; i++) {
            Boton boton = new Boton(i + 1, null);
            boton.setPreferredSize(new Dimension(200, 200));
            equipo.add(boton);
        }
        equipo.getComponent(4).setVisible(false);
        equipo.getComponent(5).setVisible(false);

        equipo.setOpaque( false );
        borrarBotones();
        buttonPanel.setVisible(false);
        background.add(equipo);

    }

    static void crearEquipo(int n, String nombre){

        equipo.remove(equipo.getComponent(n));

        boolean flag = true;
        for (int i = 0; i < 3; i++) {
            if (i==n-1 ) {
                for (Pokemon poke : listaPokemon) {
                    if (poke.getName().equals(nombre)) {
                        Boton boton = new Boton(i + 1, poke);
                        boton.setPreferredSize(new Dimension(100, 75));
                        boton.setName(nombre);
                        equipo.add(boton,n);
                    }
                }
            }
            if (equipo.getComponent(n).getName()==null){
                flag=false;
            }
        }

        if (!flag){
            equipo.getComponent(4).setVisible(true);
            equipo.getComponent(5).setVisible(true);

        }


        equipo.setOpaque( false );
        borrarBotones();
        buttonPanel.setVisible(false);
        background.add(equipo);
    }

    static void valorarEquipo(){

        List<Integer> debilidades = new ArrayList<>();
        List<Integer> fortalezas = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            String n = equipo.getComponent(i+1).getName();

            for (Pokemon pokemon : listaPokemon) {
                if (pokemon.getName().equals(n)) {
                    int tipoPr = pokemon.getTipoPrinc();
                    int tipoSe = pokemon.getTipoSec();

                    TablaTipos.buscarDebilidades(tipoPr, debilidades);
                    TablaTipos.buscarFortalezas(tipoPr, fortalezas);
                    if (tipoSe > -1) {
                        TablaTipos.buscarDebilidades(tipoSe, debilidades);
                        TablaTipos.buscarFortalezas(tipoSe, fortalezas);
                    }
                }
            }

        }

        TablaTipos.comparar(debilidades,fortalezas);

        /*System.out.print("\nDebilidades): ");
        TablaTipos.imprimir(debilidades);
        System.out.print("\nFortalezas: ");
        TablaTipos.imprimir(fortalezas);*/
        equipo.setVisible(false);

        JPanel valoracion = new JPanel(new GridLayout(3, 1, 6, 5));
        valoracion.setBorder( new EmptyBorder(0, 0, 0, 0));
        valoracion.setBackground(Color.orange);
        valoracion.setOpaque(true);

        JLabel fortale = new JLabel("Fortalezas:" +TablaTipos.imprimir(fortalezas));
        fortale.setFont(new Font("Arial", Font.BOLD, 24));
        fortale.setHorizontalAlignment(CENTER);
        fortale.setSize(300,75);
        valoracion.add(fortale);

        JLabel debilida = new JLabel("Debilidades: " +TablaTipos.imprimir(debilidades));
        debilida.setFont(new Font("Arial", Font.BOLD, 24));
        debilida.setHorizontalAlignment(CENTER);
        debilida.setSize(300,75);
        valoracion.add(debilida);

        valoracion.setVisible(true);
        //background.setLayout(new FlowLayout());
        background.add(valoracion);

    }


}

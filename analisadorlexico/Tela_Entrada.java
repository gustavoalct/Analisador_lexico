
package analisadorlexico;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;



/**
 *
 * @author Gustavo
 */
// aqui comeca o codigo
public class Tela_Entrada extends javax.swing.JFrame {

    // VARIAVEISx'
    // Palavras reservadas de saida
    private ArrayList<String> PalReservadas = new ArrayList <>() ;
    private ArrayList<String> Pal_Entre_Aspa = new ArrayList <>() ;
    private ArrayList<String> num_pal_cada_aspa = new ArrayList <>() ;
    private ArrayList<String> temp5 = new ArrayList <>() ;
      
    // tokens de saida
    private ArrayList<String> token = new ArrayList <>();
    private ArrayList<String> Possiveis_Ids = new ArrayList <>();
    private ArrayList<String> Ids = new ArrayList <>();
    private ArrayList<String> Ids_erro = new ArrayList <>();
    private ArrayList<String> token_saida = new ArrayList <>();
    // vetor armazena todas palavras reservadas
    private ArrayList<String> palavras = new ArrayList <>();
    // pega palavra por palavra do que foi escrito
    private ArrayList<String> palavra = new ArrayList <>() ;
    private ArrayList<String> palavra2 = new ArrayList <>() ;
     ArrayList<Character> numeros_char = new ArrayList<Character>();
    // Letra para o AFD
    private ArrayList<String> letras = new ArrayList <>();
    private ArrayList<String> simbolos = new ArrayList <>();
    private ArrayList<String> numeros = new ArrayList <>();
    private ArrayList<String> quebra_linha = new ArrayList <>() ;
    private ArrayList<String> index_pal = new ArrayList <>() ;
    private ArrayList<String> num_linha  = new ArrayList <>();
    //private int index_pal [] = null;
    private int estado_inicial=0;
    private int estado_final= 1;
    private int [][] tabela_transicao = new int[3][2];
    //private ArrayList<String> tabela_transicao= new ArrayList <>();
   // codigo da tela de entrada
    public Tela_Entrada() {
        initComponents();
        
        // codigo da tela de entrada
       
        // Palavras reservadas
        palavras.add("programa");       token.add("INICIO_PROG"); 
        palavras.add("utilize");        token.add("UTIL_BIB");
        palavras.add("bibio");          token.add("BIB_IO");
        palavras.add("const");          token.add("CONSTANTE");   
        palavras.add("inicio");         token.add("INICIO_PROG");
        palavras.add("fim");            token.add("FIM_PROG");  
        palavras.add("inteiro");        token.add("NUM_INT");   
        palavras.add("real");           token.add("NUM_REAL");
        palavras.add("caractere");      token.add("CARACTER");
        palavras.add("palavra");        token.add("PALAVRA");
        palavras.add("logico");         token.add("VALOR_LOG");
        palavras.add("var");            token.add("VARIAVEL");  
        palavras.add("arranjo");        token.add("ARRANJO");
        palavras.add("leia");           token.add("LEIA");
        palavras.add("leialn");         token.add("leia_PULA_LINHA");
        palavras.add("escreva");        token.add("ESCREVA");
        palavras.add("escrevaln");      token.add("ESCREVA_LN");
        palavras.add("se");             token.add("CONDICAO_SE");
        palavras.add("entao");          token.add("CONDICAO_ENTAO");
        palavras.add("senao");          token.add("CONDICAO_SENAO");  
        palavras.add("para");           token.add("LACO_PARA");
        palavras.add("de");             token.add("LACO_DE");  
        palavras.add("ate");            token.add("LACO_ATE");
        palavras.add("faca");           token.add("FACA");
        palavras.add("enquanto");       token.add("ENQUANTO");
        palavras.add("repita");         token.add("REPITA");
        palavras.add("procedimento");   token.add("PROCEDIMENTO");
        palavras.add("funcao");         token.add("FUNCAO");
       /*palavra que nao ta reservada*/// token.add("ID");
        
        // operadores               
        palavras.add("=");              token.add("ATRIBUI_IGUAL");
        palavras.add("==");             token.add("IGUAL");
        palavras.add("<");             token.add("MAIOR");
        palavras.add(">");             token.add("MENOR");
        palavras.add("<");              token.add("MENOR");
        palavras.add("<=");             token.add("MENOR_IGUAL");
        palavras.add("||");             token.add("OU");
        palavras.add("&&");             token.add("AND");
        palavras.add("!");              token.add("DIFERENTE");
        palavras.add("+");              token.add("SOMA");
        palavras.add("-");              token.add("SUBTRAI");
        palavras.add("*");              token.add("MULTIPLICA");
        palavras.add("/");              token.add("DIV");
        palavras.add("%");              token.add("PORC");
        palavras.add("^");              token.add("EXP");
        palavras.add("(");              token.add("ABRE_PARENTESE");
        palavras.add(")");              token.add("FECHA_PARENTESE");
        palavras.add("'");              token.add("ABRE_ASPA_SIMP");
       
        
        
        String branco = "";
        String aux = "\""+branco;              
        palavras.add(aux);              token.add("ABRE_ASPA_DUPLA");
       // palavras.add()
        palavras.add(",");              token.add("VIRGULA");
        palavras.add(".");              token.add("PONTO");
        palavras.add("{");              token.add("ABRE_CHAVES");
        palavras.add("}");              token.add("FEHCA_CHAVES");
        palavras.add("[");              token.add("ABRE_COLCHETES");
        palavras.add("]");              token.add("FECHA_COLCHETES");
        palavras.add(";");              token.add("PONTO_VIRGULA");
        palavras.add(":");              token.add("DOIS_PONTO");

        // pegar linha por linha que for digitada
     //   JOptionPane.showMessageDialog(null, "ENTREI");
        
        
     // CONTEUDO PARA ANALISAR O AFD
     letras.add("a");letras.add("b"); letras.add("c");letras.add("d");letras.add("e");letras.add("f");letras.add("g");letras.add("h");letras.add("i");letras.add("j");letras.add("k");
     letras.add("l");letras.add("m");letras.add("n");letras.add("o");letras.add("p");letras.add("q");letras.add("r");letras.add("s");letras.add("t");letras.add("u");letras.add("v");
     letras.add("w");letras.add("x");letras.add("y");letras.add("z");
     
     // simbolos que nao podem ter no af
     simbolos.add("@");simbolos.add("#");simbolos.add("$");
     
     // numeros para o AFD
     numeros.add("0");numeros.add("1");numeros.add("2");numeros.add("3");numeros.add("4");numeros.add("5");numeros.add("6");numeros.add("7");numeros.add("8");numeros.add("9");
     
    
     
     // Preenche tabela de transicao
     tabela_transicao[0][0]=1;
     tabela_transicao[0][1]=2;
     tabela_transicao[1][0]=1;
     tabela_transicao[1][1]=2;
     tabela_transicao[2][0]=2;
     tabela_transicao[2][1]=2;
     // estado inicial recebe 0
     
     char a='0';char v='1';char b='2';char t='3';char u='4';char i='5';char h='6';char g='7';char s='8';char z='9';
     numeros_char.add(a);numeros_char.add(g);numeros_char.add(h);numeros_char.add(i);numeros_char.add(u);numeros_char.add(t);numeros_char.add(b);numeros_char.add(v);
     numeros_char.add(s);numeros_char.add(z);
     
     
     
    }
  
    
 
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Btn_Executar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Texto_Codigo = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabela_saida = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 51, 51));

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        Btn_Executar.setText("Executar");
        Btn_Executar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_ExecutarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ANALISADOR LÉXICO");
        jLabel1.setToolTipText("");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Digiteo código");

        Texto_Codigo.setColumns(20);
        Texto_Codigo.setRows(5);
        jScrollPane1.setViewportView(Texto_Codigo);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Aluno: Gustavo Antonio C. de Alcântara");

        tabela_saida.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "LEXEMA", "TOKEN", "VALOR INICIAL", "ESCOPO", "LINHAS", "COLUNAS", "LINHA"
            }
        ));
        jScrollPane2.setViewportView(tabela_saida);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(212, 212, 212)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 395, Short.MAX_VALUE))
                            .addComponent(jScrollPane1)
                            .addComponent(Btn_Executar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 722, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Btn_Executar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(99, 99, 99))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(91, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Btn_ExecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_ExecutarActionPerformed
        //Mostra();
        
        // quebra linha
        Quebra_Linha(); // chamar esse aqui pro programa executa
         
       
         
    }//GEN-LAST:event_Btn_ExecutarActionPerformed
       
    // quebra linha do text field e coloca cada linha em uma linha do array list
  
    
    
  
    
  public void Quebra_Linha(){
        String branco = "";
        String aux2 = "\""+branco;
        char aspa2 = aux2.charAt(0);
      
        // Colocar o texte fild em um string e quebrar essa string
        String teste2 = Texto_Codigo.getText();      
        String teste3 = teste2.replaceAll("\\n", "@");  
        
        //DESIMENDA OS CARACETRES
        String teste4 = teste3.replace(";", " ; ");
        String teste5 = teste4.replace("'", " ' ");      
        String teste6 = teste5.replace("\"", " \" ");
        
        String teste61 = teste6.replace("(", " ( ");
        String teste62 = teste61.replace(")", " ) ");
        String teste63 = teste62.replace("[", " [ ");
        String teste64 = teste63.replace("]", " ] ");
        String teste65 = teste64.replace("{", " { ");
        String teste66 = teste65.replace("}", " } ");
        String teste67 = teste66.replace("/", " / ");
        String teste68 = teste67.replace("+", " + ");
        String teste69 = teste68.replace("-", " - ");
        String teste70 = teste69.replace("=", " = ");
        String teste71 = teste70.replace("%", " % ");
        String teste72 = teste71.replace("||", " || ");
        String teste73 = teste72.replace("&&", " && ");
        String teste74 = teste73.replace("!", " ! ");
        String teste75 = teste74.replace("==", " == ");
        String teste76 = teste75.replace("<", " < ");        
        String teste77 = teste76.replace("^", " ^ ");
        String teste78 = teste77.replace(">=", " >= ");
        String teste79 = teste78.replace("<=", " <= ");
        String teste80 = teste79.replace(">", " > ");
        String teste81 = teste80.replace(".", " . ");
        String teste82 = teste81.replace(",", " , ");
        String teste83 = teste82.replace("*", " * ");
        String teste84 = teste83.replace("?", " ? ");
        String teste85 = teste84.replace(":", " : ");
        // TIRA EXCESSO DE ESPAÇO
        String teste7 = teste85.replace("  ", " ");      
        String teste8 = teste7.replace("   ", " ");      
        String teste9 = teste8.replace("     ", " ");      
        String teste10 = teste9.replace("      ", " ");
        String teste11 = teste10.replace("	", " ");
        String teste12 = teste11.replace("	 ", " ");
        String teste13 = teste12.replace("         ", " ");
        String teste = teste13.replace("  ", " ");
        
             
        String[] aux = teste.split("@");
        String temp;
        for (int i=0;i<aux.length;i++){
            
            temp=aux[i];
            //System.out.println(temp);
            quebra_linha.add(temp);
            temp="";
        }
        
        /*for(int i=0;i<quebra_linha.size();i++){
            System.out.println("AQUI: "+ quebra_linha.get(i)); 
            
        }*/
        
       //quebra_linha.add(teste.split(";"));
        //Mostra();
        ReconhecePalavraReservada();
        //teste();
    }
    
    public void ReconhecePalavraReservada (){
        // separa palavra por palavra, ou seja pegar o array e quebrar em slipsts
        
        String branco = "";
        String aux2 = "\""+branco; // abre aspas duplas 
        String aux3="'";
        int cont_pal=0;
//        String aspa1_1 = " s " + aspa1 +" s";
        char aspa2 = aux2.charAt(0);
        char aspa3 = aux3.charAt(0) ;
        
        // guarda a posiçao da linha, nao sei se vai ficar desse jeito
        int cont=0;
        
        for(int i=0;i<quebra_linha.size();i++){
            // pega linha do texto 
            String temp = quebra_linha.get(i);
           
            String aux = "";
            String n_linha="";
            // percorre a linha e pega palavra por palavra
            for(int j=0; j < temp.length(); j++){
                 // pega letra por da string e compara se tem espaço
                if((temp.charAt(j) != ' ')){
                    //se nao tiver espaço pega a letra e forma a palavra
                    char p;
                    p = temp.charAt(j);                    
                    //  forma a palavra 
                    aux += p;
                   
                }else{
                    if(!aux.isEmpty()){
                        String temp2 = aux.toLowerCase();
                        palavra.add(temp2);
                        
                    }                   
                    n_linha="";
                    n_linha = Integer.toString(i+1);  // pega a linha da palavra
                    aux= ""; // zera a palavra 
                    num_linha.add(n_linha); // pega linha da palavra */
                }          
            }
           
          
        }
        
     // tira o texto entre as aspas duplas
        for(int i=0; i< palavra.size();i++){
             //System.out.println(palavra.get(i));
            if(palavra.get(i).contains("\"")){
                cont++;
                
                for (int j = i+1; j < palavra.size(); j++) {
                    // aqui vai entrar so quando for par
                    if(palavra.get(j).contains("\"")){
                        cont++;
                        i=j;
                        break;
                    }
                    if(cont % 2 == 1){
                        // aqui ta o texto entre as aspas
                        Pal_Entre_Aspa.add(palavra.get(j));
                        palavra.remove(j);
                        palavra.add(j,"£");
                        cont_pal++;
                    }
                }
  
            }
             num_pal_cada_aspa.add(Integer.toString(cont_pal));
            // quando for montar a saida, comparar onde tem aspas simples ou duplas quando achar incorporar na frente o numero de palavras na frente da aspa e na linha 
             cont_pal=0;
        }
        
        
        // tira o texto na aspa simples, para nao contar como palavra reservadas etc
        for(int i=0; i< palavra.size();i++){
             //System.out.println(palavra.get(i));
            if(palavra.get(i).contains("'")){
                cont=1;
                
                for (int j = i+1 ; j < palavra.size(); j++) {
                    // aqui vai entrar so quando for par
                    if(palavra.get(j).contains("'")){
                        cont=2;
                        i=j+1;
                        break;
                    }else{
                        String sub="";
                        cont_pal++;
                       // aqui ta o texto entre as aspas
                        Pal_Entre_Aspa.add(palavra.get(j));
                        palavra.remove(j);
                        palavra.add(j,"£");
                       
                    }
                        
                        
                }
                cont=0;
            }
            
            num_pal_cada_aspa.add(Integer.toString(cont_pal));
            // quando for montar a saida, comparar onde tem aspas simples ou duplas quando achar incorporar na frente o numero de palavras na frente da aspa e na linha 
            cont_pal=0;
        }
        
        /*for(int i=0;i<Pal_Entre_Aspa.size();i++){
           System.out.println("Palavras " +Pal_Entre_Aspa.get(i));
       }*/
          
       
       // Ver se a palavra esta contida nas palavras reservadas
       int cont2 = 0;
       int cont3=0;
       // VERIFICA AS PALAVRAS RESERVADAS
       for(int i = 0; i < palavra.size();i++ ){ // palavra por palavra
          
           String temp = palavra.get(i);
           String convert="";
           
           // pegar a linha de cada string
            
           // pego o index da palavra
           for(int j = 0; j < palavras.size();j++){ // palavras reservadas   
                String pal=temp.toLowerCase();
                String num="";
                int cont4=0;
                for(int w=0; w<pal.length();w++){
                    char c = pal.charAt(w);
                    if(numeros_char.contains(c)){
                       num +=c;
                        
                       cont4=1;
                    }else{
                        cont4=0;
                        break;
                    }
                }
                if((palavras.get(j).equals(pal))||(cont4==1)){// verifica palavra com letra maiuscula
                    temp=pal;                      
                    cont=1;  // atribui 1 se a a palavra tiver contida
                    // pegar o index do array onde a palavra ta
                    convert= Integer.toString(j);
                }          
           }
           
     
           
           // atribui se a palavra tiver contida
           if(cont == 1){
               
               //System.out.println(temp);
              
               PalReservadas.add(temp);
               index_pal.add(convert);
               
           }else{
               // palavras que nao sao reservadas
               if(temp!="£"){
                  Possiveis_Ids.add(temp); 
               }
               
               // chamar funcao para verificar se possui algum id como palavra reservada
                             
           }
           temp="";
           cont=0;   
       }
       
       System.out.println(palavra);
       //System.out.println(Possiveis_Ids);
       
      Verifica_ID();
      token();
       //Lexema_Token();
       Escopo();
      Valor_Inicial(); // pega o valor de uma variavel e coloca na coluna valor inicial e na ordem certa
      Linha_Coluna(); 
      
     saida();
    }
  		
   public void token(){
       
       for(int i=0; i < palavra.size();i++){
           token_saida.add("ID");
       }
       for(int i=0; i<palavra.size();i++){
           for(int j=0; j<PalReservadas.size();j++){
               if(palavra.get(i).equals(PalReservadas.get(j))){
                   // qual posicao encontra essa palavra reservada no array palavra reservadas
                  for(int w=0; w <palavras.size();w++){
                      if( PalReservadas.get(j).equals(palavras.get(w)) ){
                          // w e onde se encontra a posicao do 
                          token_saida.add(i,token.get(w));
                          break;
                      }
                  } 
                  break;
               }
           }
       }
       
       // Trata as aspas abertura e fechamento
           for(int i=0;i<token_saida.size();i++){
            if (token_saida.get(i) == "ABRE_ASPA_DUPLA"){
                for(int j=i+1; j<token_saida.size();j++){
                      
                    if(token_saida.get(j)=="ABRE_ASPA_DUPLA"){
                      String sub ="FECHA_ASPAS_DUPLAS";
                      token_saida.remove(j);
                      token_saida.add(j, sub);
                      break;
                    }else{
                       token_saida.remove(j);
                       token_saida.add(j, " - "); 
                    }
                    
                }
            }
            
             if (token_saida.get(i) == "ABRE_ASPA_SIMP"){
                for(int j=i+1; j<token_saida.size();j++){
                       
                    if(token_saida.get(j)=="ABRE_ASPA_SIMP"){
                      String sub ="FECHA_ASPAS_SIMPLES";
                      token_saida.remove(j);
                      token_saida.add(j, sub);
                      break;
                    }else{
                      token_saida.remove(j);
                      token_saida.add(j, " - "); 
                    }
                    
                }
            }
                
        }
       
   }
    
    
    // verifica se o id digita pode ser uma palavra reservada
    
    public void Verifica_ID(){
        String erro ="erro lexico";
       // System.out.println(Possiveis_Ids);
        int cont=0;
        for(int i=0; i<Possiveis_Ids.size();i++){
            int pos=0;
            int estado = estado_inicial;
            String pal=Possiveis_Ids.get(i);
            //System.out.println(pal.length());
             
            for(int j=0;j<pal.length();j++){     
                 char letra = pal.charAt(j); // pega a letra a letra da palavra 
                 String temp = Character.toString(letra); // transforma o char em string
                // System.out.println("entrei "+ temp);
                 int elementoInteiro = 0 ;
                 
                 if(pal.contains("ç")){ //ve se o id tem ç
                     // " Ç " caso encontra, nao pode 
                     // parar aqui a palavra, nao vai  pro estado final
                     pos=Possiveis_Ids.get(i).length(); // nao percorre mais a palavra
                     elementoInteiro=1;
                     
                 }else if((j==0)&&((simbolos.contains(temp))||(numeros.contains(temp)))){ // aqui se primeira letra do id possui algum numero ou simbolo
                     //caiu aqui ta errado, nao vai pro estado final
                     elementoInteiro=1;
                     
                 }else{                     
                       elementoInteiro=0;// o primeiro caracter e uma letra
                       
                 }
                     
                        
                                 
                 letra=' ';
                 temp=""; 
                 estado = tabela_transicao[estado][elementoInteiro];
                 
                   
            }    
                                  
                 // pegar a primeira letra da palavra e comparar se possui algum numero  
         
             if(estado_final == estado){
                 // return 1, aqui a palavra é aceita 
                 int cont2=0;
                 for(int j=1;j<pal.length();j++){
                     char h = pal.charAt(j);
                     String temp2 = Character.toString(h);
                     
                     if(simbolos.contains(temp2)){
                         cont2=1;
                         System.out.println("letra2: "+cont2);
                     }
                         
                         //break;
                 }
                 
                 if(cont2==1){
                    
                     Ids.add(pal +" "+ erro);
                     
                     // reportar erro
                 }else{
                    Ids.add(pal);
             }
                 
             }else{
                // return 0 , aqui a palavra nao é aceita
                 Ids.add(pal+" "+erro);
                // reportar erro 
             }            
    }
        

  }    
    
   private ArrayList<String> var_escopo = new ArrayList <>() ;
   private ArrayList<String> escopo = new ArrayList <>() ;
   // escopo da variavel 
    public void  Escopo(){
   
        ArrayList<String> ids_escopo = new ArrayList <>() ;
        String escopo_nome = null;
        // percorrer palavras reservadas e junto com os ids
        for(int i=0; i< palavra.size();i++){
            int index = 0,cont=0;
            
            if(palavra.get(i).equals("funcao")){
                // pegar o index 
                cont=1;
                int j=i+1;  
                while(!palavra.get(j).equals(";")){
                     var_escopo.add(palavra.get(j));
                     j++;
                }
            }     
        }
        
        escopo_nome=var_escopo.get(0); // pega o nome do escopo
        
        // pegar os palavras que sao ids
        for(int i=1;i<var_escopo.size();i++){
            for(int j=0; j < Ids.size();j++){
                if(var_escopo.get(i).equals(Ids.get(j))){
                    // pegar esse id
                    ids_escopo.add(var_escopo.get(i));
                }
            }
        }
        //System.out.println(ids_escopo);
        
       ArrayList<String> index = new ArrayList <>() ;
        // pegar a posicao desses ids nas palavras
        for(int i=0;i<ids_escopo.size();i++){
            for(int j=0;j<palavra.size();j++){
                if(ids_escopo.get(i).equals(palavra.get(j))){
                    index.add(Integer.toString(j)); // pego a posicao onde o id ta na palavra 
                }               
            }       
        }
        
        //System.out.println(index);
        // deixa o array escopo do tamanho do array palavra
        for(int i=0;i<palavra.size();i++){
            escopo.add("-");
        }
        
        for(int i=0; i<index.size();i++){
            int temp = Integer.valueOf(index.get(i));
            escopo.add(temp,escopo_nome);
        }
        
       // System.out.println("nome do escopo: "+ escopo_nome);
        
        /*for(int i=0;i<escopo.size();i++){
            System.out.println(escopo.get(i));
        }    */
    }
    
    private ArrayList<String> valor_inicial = new ArrayList <>() ;
      // pegar o valor de atribuição
    public void Valor_Inicial(){
        ArrayList<String> index = new ArrayList <>() ;
        // percorrer palavra e encontrar = de atribuição
        for(int i =0; i< palavra.size();i++){
            if(palavra.get(i).equals("=")){
              int aux = i+1; // to pegando valor da frente do =
              index.add(Integer.toString(aux));   // pegamos a  posiçao onde encontra o igual
            }
        }
        
        // preencher o array de saida com os valores certos
        int cont=0;
       // int aux=Integer.valueOf(index.get(0));//
       // System.out.println("aux vale; "+aux);
       for(int i =0;i<palavra.size();i++){
           valor_inicial.add("-");
       }
        for(int i =0; i<index.size();i++){ 
            int aux=Integer.valueOf(index.get(i));//
            aux = aux-2;
            int add= Integer.valueOf(index.get(i));//- 2 por causa de ta o id ta duas casas passadas
            valor_inicial.add(aux,palavra.get(add)); // valor inicial adiciona na ordem do id atribuido, aux posiçao do id, 
           
        }
        // pega caso for numero 
        for(int i =0; i< palavra.size();i++){
            for(int j=0; j<numeros.size();j++){
                if(palavra.get(i).contains(numeros.get(j))){
                   valor_inicial.add(i,palavra.get(i));
                }
            }          
        }
        
        
    }
    
    // pega arranjos e matrizes, linhas e colunas
     private ArrayList<String> coluna = new ArrayList <>() ;
      private ArrayList<String> linha = new ArrayList <>() ;
    void Linha_Coluna(){
           ArrayList<String> colchetes = new ArrayList <>() ;
           ArrayList<String> linhas = new ArrayList <>() ;
           ArrayList<String> colunas= new ArrayList <>() ;
           ArrayList<String> index= new ArrayList <>() ;
        
        // pegar todos os valores dentro das colchetes
        for(int i =0; i<palavra.size();i++){
           
           if(palavra.get(i).equals("arranjo")){
               int j=i+2;
               if(palavra.get(i+1).equals("[")){
               
                while(!palavra.get(j).equals("]")){
                    colchetes.add(palavra.get(j));
                    j++;
                }
                i=j;
                colchetes.add("£" );
            } 
           }
           
        }
        
        
        
        
        
        // forma de tratar todos colchetes
        String teste = "";
        for(int i=0; i<colchetes.size();i++){
             teste+=colchetes.get(i);
        }
        colchetes.clear();
        
       String[] array = teste.split("£");
       
       for(int i=0;i<array.length;i++){
            colchetes.add(array[i]);
        }
      
      
       
        // pegar as linhas e colunas
       
       for(int i=0; i<colchetes.size();i++){
           if(colchetes.get(i).contains(",")){ // pega vetores de duas dimensoes
               // cada linha e uma string
               String temp=colchetes.get(i);
               for(int j=0; j<temp.length();j++){
                   int tam = temp.length();
                   if(temp.charAt(j)==','){ // encontrar a virgula que separa as dimensoes                 
                       int aux=j;
                       aux--;
                       String t="";
                       String g="";
                       // forma de pegar o valor a esquerda da virgula ou seja pegar a coluna
                       while(temp.charAt(aux)!='.'){
                            char a= temp.charAt(aux);                       
                            t+=Character.toString(a);                                            
                            aux--;
                       } 
                       String k = new StringBuilder(t).reverse().toString();                    
                       linhas.add(k);
                       
                       //colunas
                       int cont=j+4; // pega a posicao da virgula
                       while(cont!=-1){
                           char a= temp.charAt(cont);  
                          
                           g+=Character.toString(a);                                            
                           cont++;
                           if(cont==temp.length()){
                               cont=-1;
                           }
                       }
                       colunas.add(g);                       
                   }                      
               }
               
           }else{
               
               String temp=colchetes.get(i);
               linhas.add("1");
               String g="";
               System.out.println(temp);
               int cont=3;
               while(cont!=-1){
                   char a=temp.charAt(cont);
                   g+=Character.toString(a);
                   cont++;
                   if(cont==temp.length()){
                       cont=-1;
                   }
               }
               colunas.add(g);
               
               
           }
       }
       
        //System.out.println("linhas"+linhas);
        //System.out.println("colunas: "+colunas);
      
     // agr colocar em ordem de apresentaçao 
       for(int i=0;i<palavra.size();i++){ // inicia os array para ficar do tam do array palavra
           coluna.add("-");
           linha.add("-");
       }
       
       for(int i=0;i<palavra.size();i++){          
           // achar palavras arranjo
           if(palavra.get(i).equals("arranjo")){
                // pegar o index aqui para preencher na ordem correta
                String pos =  Integer.toString(i-2);
                index.add(pos);
           }
       }
        //System.out.println("INDEX: "+index);
       
       for(int i=0;i<index.size();i++){
           String aux=index.get(i);
           int po= Integer.parseInt(aux);
           String aux2 = linhas.get(i);
           linha.remove(po);
           coluna.remove(po);
           
           linha.add(po,aux2);
           coluna.add(po,colunas.get(i));
       }
       // System.out.println(coluna);
       
       /* for(int i=0;i<palavra.size();i++){
            System.out.println(palavra.get(i)+" "+linha.get(i)+" "+coluna.get(i));
        }*/
        
    }

 private ArrayList<String> palavra_saida = new ArrayList <>() ;       
  public void saida(){
      // lexema, Token, valor inicial, escopo, linhas, colunas, linha
     /* for(int i=0 ;i < Pal_Entre_Aspa.size();i++){
          System.out.println("pal entre aspas : "+ Pal_Entre_Aspa.get(i));
                  
      }*/
      // Montar saida das paalvras , verificar quando a palavra e reservada, e quando nao for colocar o id 
      
      
      for(int i=0; i< palavra.size();i++){
         int cont=0;
          for(int j =0; j< PalReservadas.size();j++){
             if((palavra.get(i).equals(PalReservadas.get(j)))  || (palavra.get(i).equals("£"))){
                cont=1;               
              }
          }
          
          if(cont==1){
              palavra_saida.add(palavra.get(i));
          }else{
               palavra_saida.add("@");
          }
      }
      
      for(int i=0; i< Ids.size();i++){
          for(int j=0;j<palavra_saida.size();j++){
              if(palavra_saida.get(j).equals("@")){
                palavra_saida.remove(j);
                palavra_saida.add(j,Ids.get(i));
                break;
              }
          }
      }
     
      
      
         for(int i=0; i< Pal_Entre_Aspa.size();i++){
          for(int j=0;j<palavra_saida.size();j++){
              if(palavra_saida.get(j).equals("£")){
                palavra_saida.remove(j);
                palavra_saida.add(j,Pal_Entre_Aspa.get(i));
                break;
              }
          }
      }
      
      //for(int i=0;i<palavra_saida.size();i++){
        
          System.out.println("Lexema: "+palavra_saida.size());              
     // } 
          
    
     //// for(int i=0; i<token_saida.size();i++){
          System.out.println("Token: "+ token_saida.size());
      //}
     
      //
     // for(int i=0;i<valor_inicial.size();i++){
          System.out.println("Valor inicia: "+valor_inicial.size());
     // }
      
     // for(int i=0; i<escopo.size();i++){
          System.out.println("escopo: "+escopo.size());
     // }
      
     // for(int i=0; i<coluna.size();i++){
          System.out.println("coluna: "+coluna.size());
     // }
      
     // for(int i=0; i<linha.size();i++){
          System.out.println("linha: "+linha.size());
          System.out.println("Num linha: "+ num_linha.size());
      ////}
      for(int i=0; i<num_linha.size();i++){
          System.out.println("linha: "+ num_linha.get(i));
      }
     
  }
    

  
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Tela_Entrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tela_Entrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tela_Entrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tela_Entrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tela_Entrada().setVisible(true);
            
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_Executar;
    private javax.swing.JTextArea Texto_Codigo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabela_saida;
    // End of variables declaration//GEN-END:variables

    private void JOptionPane(String entrei) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

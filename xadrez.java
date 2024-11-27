//Atenção, esse código não está finalizado ainda

package outros;
import static java.lang.Math.abs;
import javax.swing.JOptionPane;
public class xadrez {
    public static void main(String[] args) {
        int i, j, k=0, rodadas=0, y=0, x=0, y2=0, x2=0, a, b;
        String comando, aviso="";
        char peca, destino, letrax, letrax2;
        int tabuleiro[][] = {
            {-2,-3,-4,-5,-6,-4,-3,-2}, 
            {-1,-1,-1,-1,-1,-1,-1,-1}, 
            {0,0,0,0,0,0,0,0}, 
            {0,0,0,0,0,0,0,0}, 
            {0,0,0,0,0,0,0,0}, 
            {0,0,0,0,0,0,0,0}, 
            {1,1,1,1,1,1,1,1}, 
            {2,3,4,5,6,4,3,2}
        };
        boolean game = true;
        
        while (game == true) { //Inicia o jogo e o deixa em looping até que alguém ganhe
            String text=""; //Reinicia o tabuleiro
            rodadas++; //Determina número de rodadas

            //Desenha o tabuleiro
            text += "  A B C D E F G H \n"; //Escreve as letras de A à H em cima do tabuleiro
            for (i=0; i<8; i++) {
                text += ((8-i)); //Escreve os números de 1 à 8 do lado do tabuleiro
                for (j=0; j<8; j++) {
                    //Preenche os espaços do tabuleiro com suas peças
                    switch (tabuleiro[i][j]) { 
                        case 0 -> { 
                            if ((i+j)%2 == 0) {
                                text += (" O");
                            }
                            else{
                                text += (" O");
                            }
                        }
                        case 1 -> text += (" P");
                        case 2 -> text += (" R");
                        case 3 -> text += (" N");
                        case 4 -> text += (" B");
                        case 5 -> text += (" Q");
                        case 6 -> text += (" K");
                        case -1 -> text += (" P");
                        case -2 -> text += (" R");
                        case -3 -> text += (" N");
                        case -4 -> text += (" B");
                        case -5 -> text += (" Q");
                        case -6 -> text += (" K");
                    }
                }
                text +=("\n");
            }
            
            System.out.println(text);
            boolean rodada = false;
            
            while (rodada == false){ //A rodada não irá acabar até que um movimento permitido seja feito
                comando = JOptionPane.showInputDialog(null,aviso + "Escreva um movimento"); //Recebe um comando
                rodada = true;
                aviso = "";
                if (comando.charAt(3) == ('x')){
                    comando = comando.replace("x", "");
                }
                peca = comando.charAt(0); //Define a peça do comando
                letrax = comando.charAt(1); //Define o "x" da peça
                switch (letrax) {
                    case 'A','a' -> x = 0;
                    case 'B','b' -> x = 1;
                    case 'C','c' -> x = 2;
                    case 'D','d' -> x = 3;
                    case 'E','e' -> x = 4;
                    case 'F','f' -> x = 5;
                    case 'G','g' -> x = 6;
                    case 'H','h' -> x = 7;
                }
                y = 8-Character.getNumericValue(comando.charAt(2)); //Define o "y" da peça

                letrax2 = comando.charAt(3); //Define o destino "x" da peça
                switch (letrax2) {
                    case 'A','a' -> x2 = 0;
                    case 'B','b' -> x2 = 1;
                    case 'C','c' -> x2 = 2;
                    case 'D','d' -> x2 = 3;
                    case 'E','e' -> x2 = 4;
                    case 'F','f' -> x2 = 5;
                    case 'G','g' -> x2 = 6;
                    case 'H','h' -> x2 = 7;
                }
                y2 = 8-Character.getNumericValue(comando.charAt(4)); //Define o destino "y" da peça

                System.out.print("x = "+x+"\ny = "+y+"\nx2 ="+x2+"\ny2 ="+y2+"\nk= "+k+"\n"+abs(x2-x)+"\n"+abs(y2-y)); //Debug
                
                if (tabuleiro[y][x] != 0){ //Guarante que apenas peças nos lugares solicitados serão mexidas, previnindo multiplicação
                    switch (peca) {
                        
                        //Peão
                        case 'P', 'p' -> {
                            if ((y-1)==y2 && x==x2 && tabuleiro[y2][x]==0 || (y-2)==y2 && tabuleiro[(y-1)][x]==0 && y==6 && x==x2 && tabuleiro[y2][x2]==0 || (y-1)==y2 && (x+1)==x2 && tabuleiro[y2][x2]<0 || (y-1)==y2 && (x-1)==x2 && tabuleiro[y2][x2]<0) {
                                tabuleiro[y][x] = 0;
                                tabuleiro[y2][x2] = 1;
                            }
                            else {
                                rodada = false;
                                aviso = "Movimento inválido!\n";
                            }
                        }
                        
                        //Torre
                        case 'R', 'r' -> {
                            if (x < x2){
                                a = x+1;
                                b = x2;
                            }
                            else {
                                if (x2 < x){
                                    a = x2+1;
                                    b = x;
                                }
                                else {
                                    if (y < y2){
                                        a = y+1;
                                        b = y2;
                                    }
                                    else {
                                        a = y2+1;
                                        b = y;
                                    }
                                }
                            }
                            for (k=a; k<b; k++){
                                if (x==x2 && y!=y2 && tabuleiro[k][x]==0) {
                                }
                                else {
                                    if (y==y2 && x!=x2 && tabuleiro[y][k]==0){
                                    }
                                    else {
                                        rodada = false;
                                        aviso = "Movimento inválido!\n";
                                    }
                                }
                            }
                            if (rodada == true && tabuleiro[y2][x2]<=0){
                                tabuleiro[y][x] = 0;
                                tabuleiro[y2][x2] = 2;
                            }
                            else {
                                rodada = false;
                                aviso = "Movimento inválido!\n";
                            }
                        }
                        
                        //Cavaleiro
                        case 'N', 'n', 'C', 'c' -> {
                            if (tabuleiro[y2][x2]<=0){
                                if ((y-1)==y2 && (x-2)==x2 || (y-2)==y2 && (x-1)==x2 || (y-2)==y2 && (x+1)==x2 || (y-1)==y2 && (x+2)==x2 || (y+1)==y2 && (x+2)==x2 || (y+2)==y2 && (x+1)==x2 || (y+2)==y2 && (x-1)==x2 || (y+1)==y2 && (x-2)==x2) {
                                    tabuleiro[y][x] = 0; 
                                    tabuleiro[y2][x2] = 3;
                                } 
                                else {
                                    rodada = false;
                                    aviso = "Movimento inválido!\n";
                                }
                            }
                            else {
                                rodada = false;
                                aviso = "Movimento inválido!\n";
                            }
                        }
                        
                        //Bispo
                        case 'B', 'b' -> {
                            if (abs(x2-x) == abs(y2-y)){
                            for (k=1; k<abs(x2-x); k++){
                                if ( y < y2 && x < x2){
                                    if (tabuleiro[y-k][x-k]==0){
                                        
                                    }
                                }
                                else {
                                    if (y < y2 && x2 < x){
                                        if (tabuleiro[y+k][x-k]==0){
                                            
                                        }
                                    }
                                    else {
                                        if (y2 < y && x2 < x){
                                            if (tabuleiro[y+k][x+k]==0){
                                                
                                            }
                                        }
                                        else {
                                            if (y2 < y && x < x2){
                                                if (tabuleiro[y-k][x+k]==0){
                                                    
                                                }
                                            }
                                            else {
                                                rodada = false;
                                                aviso = "Movimento inválido!\n";
                                            }
                                        }
                                    }
                                }
                            }
                            if (rodada == true && abs(x2-x) == abs(y2-y) ){
                                tabuleiro[y][x] = 0;
                                tabuleiro[y2][x2] = 4;
                            }
                            else {
                                rodada = false;
                                aviso = "Movimento inválido!\n";
                            }
                            }
                            else {
                                rodada = false;
                                aviso = "Movimento inválido!\n";
                            }
                        }
                        
                        //Rainha
                        case 'Q', 'q' -> {
                        }
                        
                        //Rei
                        case 'K', 'k' -> {
                            if (tabuleiro[y2][x2]<=0){
                                if ((y-1)==y2 && (x-1)==x2 || (y-1)==y2 && x==x2 || (y-1)==y2 && (x+1)==x2 || y==y2 && (x+1)==x2 || (y+1)==y2 && (x+1)==x2 || (y+1)==y2 && x==x2 || (y+1)==y2 && (x-1)==x2 || y==y2 && (x-1)==x2){
                                    tabuleiro[y][x] = 0; 
                                    tabuleiro[y2][x2] = 6;
                                }
                                else {
                                    rodada = false;
                                    aviso = "Movimento inválido!\n";
                                }
                            }
                            else {
                                rodada = false;
                                aviso = "Movimento inválido!\n";
                            }
                        }
                    }
                }
                else {
                    rodada = false;
                    aviso = "Movimento inválido!\nEspaço vazio!\n";
                }
            }
            
            System.out.print("\n\n");
        }
    }
}

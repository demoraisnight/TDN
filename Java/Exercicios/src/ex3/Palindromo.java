package ex3;

public class Palindromo {
	

	public static void main(String[] args) {
		
		String palavra_1 = "Rosa"; 
		String palavra_2 = "anilina"; 
	

        System.out.println(palavra_1 + " é um palidromo: " + isPalindromo(palavra_1));
        System.out.println(palavra_2 + " é um palidromo: " + isPalindromo(palavra_2));
	}
	
	
	//Método que retorna se uma String é um palíndromo
	public static boolean isPalindromo(String texto) {
		
		//Para aceitar frases como palíndromo devemos excluir os espaços
		String texto_sem_espaco = texto.replace(" ", "");
		
		
	    String reverse = new StringBuilder(texto_sem_espaco).reverse().toString();
	    return texto_sem_espaco.equalsIgnoreCase(reverse);
	}

}

package ex4;

public class MatrizIndentidade {
	

	public static void main(String[] args) {
		
		int numero_1 = 3; 
		int numero_2 = 7;
	

        System.out.println("A matriz identidade de "+ numero_1 + " é: ");
        MatrizIdentidade(numero_1);
        System.out.println("A matriz identidade de "+ numero_2 + " é: ");
        MatrizIdentidade(numero_2);
	}
	
	
	public static void MatrizIdentidade(int numero) {
	    for (int i = 0; i < numero; i++) {
	        for (int j = 0; j < numero; j++) {
	            if (i == j) {
	                System.out.print("1 ");
	            } else {
	                System.out.print("0 ");
	            }
	        }
	        System.out.println();
	    }
	}
}

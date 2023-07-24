package ex2;

public class Primos {

    public static void main(String[] args) {

        System.out.println("Os números primos entre 0 e 1000 são: ");
        for (int i = 2; i <= 1000; i++) {
            if (isPrime(i)) {
                System.out.print(i + " ");
            }
        }
    }
    
    
    
    // Função que retorna se um número é primo
	public static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }

       
        //Se um número n não for primo, ele pode ser fatorado em dois fatores a e b:
        //n = a * b
        //Agora a e b não podem ser ambos maiores que a raiz quadrada de n, pois então o produto a * b
        //seria maior que sqrt(n) * sqrt(n) = n. Portanto, em qualquer fatoração de n,
        //pelo menos um dos fatores deve ser menor ou igual à raiz quadrada de n e, 
        //se não conseguirmos encontrar nenhum fator menor ou igual à raiz quadrada, n deve ser primo.
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }

	
}

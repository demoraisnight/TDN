package ex5;



public class Troco {
	

	public static void main(String[] args) {
		
		
		calcularTroco(250.57, 290); // Vai retornar o troco em menor quantidade de notas e moedas possíveis
		//calcularTroco(280,220); // O pagamento deve ser maior que o custo
		//calcularTroco(280,280);// Não tem troco

    }
	
    
	public static void calcularTroco(double custo, double pagamento) {
		
		
		if(custo > pagamento) {
			System.out.println("O pagamento deve ser maior que o custo" );
			
		} else if(custo == pagamento) {
			System.out.println("Não tem troco");
		}else {
			
		    double troco = pagamento - custo;
		    System.out.println("Troco: R$ " + troco);
		    
		    
		    
		    Double[] valores = { 100.0, 50.0, 20.0, 10.0, 5.0, 2.0, 1.0, .50, .25, .1, .05, .01 };
		    String[] nomeNotasEMoedas = { "nota de 100", "nota de 50", "nota de 20", "nota de 10", "nota de 5", "nota de 2",
		            "nota de 1", "moeda de 50", "moeda de 25", "moeda de 10", "moeda de 5", "moeda de 1" };
		    
		    

		    for (int i = 0; i < valores.length; i++) {
		    	
		        int quantidade = (int) (troco / valores[i]);
		        troco = troco % valores[i];
		        
		        if (quantidade > 0) {
		            System.out.println(quantidade + " - " + nomeNotasEMoedas[i]);
		        	
		        }
		    }
			
		}
		
	
	}
	
	
	
	
}

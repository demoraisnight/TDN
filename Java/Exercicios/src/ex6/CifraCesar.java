package ex6;

public class CifraCesar {
	public static String cifrar(String texto, int chave) {
		
        StringBuilder textoCriptografado = new StringBuilder();

        for (char letra : texto.toCharArray()) {
            if (Character.isLetter(letra)) {
            	
            	//charMaiúsculoEncriptado = ((charOriginal – 65 + chave) mod 26) + 65
            	//charMinúsculoEncriptado = ((charOriginal – 97 + chave) mod 26) + 97
                char base = Character.isUpperCase(letra) ? 'A' : 'a';
                char letraCriptografada = (char) ((letra - base + chave) % 26 + base);
                
                textoCriptografado.append(letraCriptografada);
            } else {
            	
                textoCriptografado.append(letra);
            }
        }

        return textoCriptografado.toString();
    }

    public static String decifrar(String texto, int chave) {
        return cifrar(texto, 26 - chave);// 26 letras - o tanto percorrido(chave)
    }

    public static void main(String[] args) {
        String mensagem = "Hello, world!";
        int chave = 10;

        String mensagemCriptografada = cifrar(mensagem, chave);
        String mensagemDescriptografada = decifrar(mensagemCriptografada, chave);

        System.out.println("Mensagem original: " + mensagem);
        System.out.println("Mensagem criptografada: " + mensagemCriptografada);
        System.out.println("Mensagem descriptografada: " + mensagemDescriptografada);
    }

}

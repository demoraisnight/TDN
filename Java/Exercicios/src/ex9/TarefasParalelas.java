package ex9;

import java.util.Random;

public class TarefasParalelas {
	public static void main(String[] args) {
        Thread tarefa1 = new Thread(new Tarefa("tarefa_a"));
        Thread tarefa2 = new Thread(new Tarefa("tarefa_b"));
        Thread tarefa3 = new Thread(new Tarefa("tarefa_c"));
        Thread tarefaFim = new Thread(new TarefaFim());

        tarefa1.start();
        tarefa2.start();
        tarefa3.start();

        try {
            tarefa1.join();
            tarefa2.join();
            tarefa3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        tarefaFim.start();
    }

    public static class Tarefa implements Runnable {
        private final String nome;
        private final Random random = new Random();

        public Tarefa(String nome) {
            this.nome = nome;
        }

        @Override
        public void run() {
            int tempo = random.nextInt(5000) + 1000;
            try {
                Thread.sleep(tempo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Tarefa " + nome + " finalizada em " + tempo + " ms");
        }
    }

    public static class TarefaFim implements Runnable {
        @Override
        public void run() {
            System.out.println("Todas as tarefas foram concluídas!");
        }
    }

}

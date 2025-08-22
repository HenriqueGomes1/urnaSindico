import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VotacaoUrna {

    public static void main(String[] args) {
        
        //Armazena candidados
        List<String> nomesCandidatos = new ArrayList<>();
        List<Integer> numerosCandidatos = new ArrayList<>();
        List<Integer> votosCandidatos = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String escolha;

        do {
            System.out.println("\n--- Sistema de Votação para Síndico ---");
            System.out.println("1. Cadastrar candidato.");
            System.out.println("2. Realizar votação.");
            System.out.println("3. Ver resultados");
            System.out.println("4. Sair.");
            System.out.print("Escolha uma opção: ");
            escolha = scanner.nextLine();

            switch (escolha) {
                case "1":
                    //Cadastramento candidatos
                    System.out.println("\n--- Cadastro de Candidato ---");
                    System.out.print("Digite o nome do seu candidato: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite o número do seu candidato: ");
                    int numero = Integer.parseInt(scanner.nextLine());

                    if (numerosCandidatos.contains(numero)) {
                        System.out.println("Erro: Já existe um candidato com este número. Tente novamente.");
                    } else {
                        nomesCandidatos.add(nome);
                        numerosCandidatos.add(numero);
                        votosCandidatos.add(0);
                        System.out.println("Candidato " + nome + " cadastrado com sucesso!");
                    }
                    break;

                case "2":
                    //Votação
                    System.out.println("\n--- Votação ---");
                    if (nomesCandidatos.isEmpty()) {
                        System.out.println("Nenhum candidato cadastrado. Por favor, cadastre um candidato primeiro.");
                        break;
                    }

                    System.out.println("Candidatos disponíveis:");
                    for (int i = 0; i < nomesCandidatos.size(); i++) {
                        System.out.println("Número: " + numerosCandidatos.get(i) + " - Nome: " + nomesCandidatos.get(i));
                    }

                    System.out.print("Digite o número do seu candidato: ");
                    int voto = Integer.parseInt(scanner.nextLine());
                    int index = numerosCandidatos.indexOf(voto);
                    
                    if (index != -1) {
                        int votosAtuais = votosCandidatos.get(index);
                        votosCandidatos.set(index, votosAtuais + 1);
                        System.out.println("Voto registrado com sucesso!");
                    } else {
                        System.out.println("Número de candidato inválido. Por favor, vote em um número da lista.");
                    }
                    break;

                case "3":
                    //Resultado
                    System.out.println("\n--- Resultado da Votação ---");
                    if (nomesCandidatos.isEmpty()) {
                        System.out.println("Nenhum candidato cadastrado para mostrar resultados.");
                        break;
                    }

                    int totalVotos = votosCandidatos.stream().mapToInt(Integer::intValue).sum();

                    if (totalVotos == 0) {
                        System.out.println("Ainda não há votos registrados.");
                        break;
                    }

                    //Loop de organizar essa bagaça
                    for (int i = 0; i < votosCandidatos.size() - 1; i++) {
                        for (int j = i + 1; j < votosCandidatos.size(); j++) {
                            if (votosCandidatos.get(j) > votosCandidatos.get(i)) {
                                // Troca votos
                                int tempVotos = votosCandidatos.get(i);
                                votosCandidatos.set(i, votosCandidatos.get(j));
                                votosCandidatos.set(j, tempVotos);

                                // Troca nomes
                                String tempNome = nomesCandidatos.get(i);
                                nomesCandidatos.set(i, nomesCandidatos.get(j));
                                nomesCandidatos.set(j, tempNome);

                                // Troca números
                                int tempNumero = numerosCandidatos.get(i);
                                numerosCandidatos.set(i, numerosCandidatos.get(j));
                                numerosCandidatos.set(j, tempNumero);
                            }
                        }
                    }
                    // -----------------------------------------------------

                    int maiorVoto = votosCandidatos.get(0);
                    String vencedor = nomesCandidatos.get(0);
                    
                    System.out.println("----------------------------------------");
                    System.out.println("Candidatos e Votos:");
                    System.out.println("----------------------------------------");
                    
                    for (int i = 0; i < nomesCandidatos.size(); i++) {
                        String nomeCandidato = nomesCandidatos.get(i);
                        int votos = votosCandidatos.get(i);
                        double porcentagem = (double) votos / totalVotos * 100;

                        System.out.printf("Nome: %s | Votos: %d | Porcentagem: %.2f%%%n", 
                                          nomeCandidato, votos, porcentagem);
                    }
                    
                    System.out.println("----------------------------------------");
                    System.out.println("Vencedor dos votos é: " + vencedor + ", parabéns!!");
                    System.out.println("----------------------------------------");
                    break;

                case "4":
                    System.out.println("Saindo do sistema...");
                    break;

                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção de 1 a 4.");
            }
        } while (!escolha.equals("4"));

        scanner.close();
    }
}

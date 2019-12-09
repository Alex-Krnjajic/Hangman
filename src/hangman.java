import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.Random;

public class hangman {
	static String[] words = { "storm", "hunter", "current", "tender", "sofa", "finished", "peak", "distort", "concede",
			"high", "sour" };
	static String[] hangmanStates = { "", "      \n     |\n     |\n     |\n     |\n     |",
			" +---+\n     |\n     |\n     |\n     |\n     |", " +---+\n |   |\n     |\n     |\n     |\n     |",
			" +---+\n |   |\n O   |\n     |\n     |\n     |", " +---+\n |   |\n O   |\n/|\\  |\n     |\n     |",
			" +---+\n |   |\n O   |\n/|\\  |\n/ \\  |\n     |" };


	public static void main(String[] args) {

		String currentLetter = "";
		String currentWord = "";
		String guessedWord = "";
		String menu = "";
		int start = 0;
		int rightLetter = 0;
		int fails = 0;
		//ArrayList<Character> currentWordChar = new ArrayList<Character>();
		ArrayList<Character> guessedWordChar = new ArrayList<Character>();
		char currentLetterChar = ' ';

		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		Random random = new Random();

		while (true) {

			if (start == 0) {

				System.out.println("Hang Man: The Videogame");
				System.out.println();
				System.out.println("press Enter to start");

				reader.nextLine();
				start = 1;
				fails = 0;
				guessedWordChar.clear();
				currentWord = words[random.nextInt(words.length)];
				
				for (int i = 0; i < currentWord.length(); i++) {

					guessedWordChar.add('_');
				}

			}
			guessedWord = guessedWordChar.stream().map(Object::toString)
                    .collect(Collectors.joining(" "));
			System.out.println(guessedWord);
			System.out.println();
			while (true) {
				System.out.println("choose (type the number to proceed)");
				System.out.println("1: guess letter");
				System.out.println("2: guess word");

				menu = reader.nextLine();
				if (menu.equals("1")) {
					
					
					rightLetter = 0;
					System.out.println("write one letter");
					currentLetter = reader.nextLine();
					
					if (currentLetter.isEmpty()) {
						System.out.println("shits empty");
						System.out.println();
						break;
					}

					currentLetterChar = currentLetter.charAt(0);

					for (int i = 0; i < currentWord.length(); i++) {

						if (currentLetterChar == currentWord.charAt(i)) {
							if (currentLetterChar == guessedWordChar.get(i)) {
								System.out.println("already guessed letter");
							}
							else {
							guessedWordChar.set(i, currentLetterChar);
							System.out.println();
							System.out.println("correct guess");
							rightLetter = 1;
							}
						} else if (guessedWordChar.get(i) != '_') {
							continue;
						}

					}
					if (rightLetter != 1) {
						fails++;
						System.out.println(hangmanStates[fails]);
					}
				
					guessedWord = guessedWordChar.stream().map(Object::toString)
	                        .collect(Collectors.joining(" "));
					System.out.println(guessedWord);
					if (currentWord.equals(guessedWord)) {
						System.out.println("You Winner!");
						start=0;
						break;
					}

				} else if (menu.equals("2")) {
					System.out.println("guess word");
					String currentGuessedWord = reader.nextLine();
					if (currentGuessedWord.equals(currentWord)) {
						System.out.println("You Winner!");
						System.out.println(currentWord);
						start=0;
						break;
					}
					else {
						fails++;
						System.out.println(hangmanStates[fails]);
					}
					
				} else {
					System.out.println("Error: input not valid");
				}
			}
		}
	}
}

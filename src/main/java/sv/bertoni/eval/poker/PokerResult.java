package sv.bertoni.eval.poker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sv.bertoni.eval.auxiliar.PokerHandEvaluator;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PokerResult {

    private static final Logger logger = LoggerFactory.getLogger(PokerResult.class);

    public void writeResults(){
        Path path;
        long countPlayerOneWins;
        long countPlayerTwoWins;
        long countPlayerNeitherWins;
        try (FileWriter fileWriter = new FileWriter("result.txt");
             BufferedWriter writer = new BufferedWriter(fileWriter);

        ){


//            logger.info(Paths.get(".").toAbsolutePath().normalize().toString());
            path = new File(Paths.get(".").toAbsolutePath().normalize().toString()+ "/pokerdata.txt").toPath();

            countPlayerOneWins = Files
                    .lines(path)
                    .parallel()
                    .filter(s -> new PokerHandEvaluator.Hand(s, 0).score > new PokerHandEvaluator.Hand(s, 3 * 5).score)
                    .count();

            countPlayerTwoWins = Files
                    .lines(path)
                    .parallel()
                    .filter(s -> new PokerHandEvaluator.Hand(s, 0).score < new PokerHandEvaluator.Hand(s, 3 * 5).score)
                    .count();

            countPlayerNeitherWins = Files
                    .lines(path)
                    .parallel()
                    .filter(s -> new PokerHandEvaluator.Hand(s, 0).score == new PokerHandEvaluator.Hand(s, 3 * 5).score)
                    .count();
            writer.append("1. "+countPlayerOneWins + System.lineSeparator());
            writer.append("2. "+countPlayerTwoWins + System.lineSeparator());
            writer.append("3. "+countPlayerNeitherWins);

        } catch (IOException e) {
            logger.error(e.getMessage(),e);
        } catch (Exception e){
            logger.error(e.getMessage(),e);
            /*catch (URISyntaxException e) {
            logger.error(e.getMessage());
        }*/
        }
    }

    public static void main(String[] args) {
        PokerResult pokerResult = new PokerResult();
        pokerResult.writeResults();
    }


}

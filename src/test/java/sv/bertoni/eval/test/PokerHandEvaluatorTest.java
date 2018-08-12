package sv.bertoni.eval.test;


import org.junit.Test;
import sv.bertoni.eval.auxiliar.PokerHandEvaluator;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class PokerHandEvaluatorTest {

    private static final Logger logger = LogManager.getLogger(PokerHandEvaluatorTest.class);

    @Test
    public void getPlayerOneWins() throws IOException, URISyntaxException {
        logger.info(Files
                .lines(new File(PokerHandEvaluator.class.getResource("/pokerdata.txt").toURI()).toPath())
                .parallel()
                .filter(s -> new PokerHandEvaluator.Hand(s, 0).score > new PokerHandEvaluator.Hand(s, 3 * 5).score)
                .count());

    }

    @Test
    public void getPlayerTwoWins() throws IOException, URISyntaxException {
        logger.info(Files
                .lines(new File(PokerHandEvaluator.class.getResource("/pokerdata.txt").toURI()).toPath())
                .parallel()
                .filter(s -> new PokerHandEvaluator.Hand(s, 0).score < new PokerHandEvaluator.Hand(s, 3 * 5).score)
                .count());

    }
    @Test
    public void getPlayerNeitherWins() throws IOException, URISyntaxException {

        Path path = new File(PokerHandEvaluator.class.getResource("/pokerdata.txt").toURI()).toPath();


        logger.info(Files
                .lines(path)
                .parallel()
                .filter(s -> new PokerHandEvaluator.Hand(s, 0).score == new PokerHandEvaluator.Hand(s, 3 * 5).score)
                .count());
/*        Files
                .lines(path)
                .parallel().
                forEach(
                        s -> logger.info("\r\n"+
                                new PokerHandEvaluator.Hand(s, 0).toString()+"\r\n"+
                                   new PokerHandEvaluator.Hand(s, 3 * 5).toString()
                        ));*/



    }



}

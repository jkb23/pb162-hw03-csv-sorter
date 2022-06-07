package cz.muni.fi.pb162.hw03.impl;

import cz.muni.fi.pb162.hw02.HasLabels;
import cz.muni.fi.pb162.hw02.LabelMatcher;
import cz.muni.fi.pb162.hw02.impl.LabeledOperations;
import cz.muni.fi.pb162.hw03.cmd.CommandLine;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;

/**
 * Application Runtime
 */
final class Application {

    private final ApplicationOptions options;

    Application(ApplicationOptions options, CommandLine cli) {
        Objects.requireNonNull(options);
        Objects.requireNonNull(cli);

        this.options = options;
    }

    /**
     *
     * @param inputList head from input file
     * @return position of labels equivalent
     */
    public int getPosition(List<String> inputList){
        for (int i = 0; i < inputList.size(); i++){
            if (inputList.get(i).equals(options.getLabelColumn())){
                return i;
            }
        }
        return 0;
    }

    /**
     * this function iterates through input file, tries to match it with expression from filter file,
     * creates and writes to a file
     * I wanted to separate the run function to make it more readable than 2 while loops and million variables,
     * hope it's better :|
     * @param matcher
     * @param fileName
     * @throws IOException
     */
    public void iterateInput(LabelMatcher matcher, String fileName) throws IOException{
        BufferedReader inputReader = Files.newBufferedReader(options.getInput(), options.getCharset());
        String inputHeader = inputReader.readLine();
        List<String> inputList = List.of(inputHeader.split(options.getDelimiter()));
        int position = getPosition(inputList);
        String inputLine;
        boolean written = false;

        CsvWriter myWriter = new CsvWriter(fileName, options.getCharset());
        myWriter.write(inputHeader + System.getProperty("line.separator"));

        while ((inputLine = inputReader.readLine()) != null) {
            inputList = List.of(inputLine.split(options.getDelimiter()));
            HasLabels hasLabels = new LabelSet(inputList.get(position));

            if (matcher.matches(hasLabels)) {
                myWriter.write(inputLine + System.getProperty("line.separator"));
                written = true;
            }
        }
        myWriter.close(written);
    }

    /**
     * Note:    This method represents the runtime logic.
     * However, you should still use proper decomposition.
     * <p>
     * Application runtime logic
     */
    void run() throws IOException {
        BufferedReader filterReader = Files.newBufferedReader(options.getFilters(), options.getCharset());
        filterReader.readLine();
        String filterLine;

        while ((filterLine = filterReader.readLine()) != null) {
            List<String> filterList = List.of(filterLine.split(options.getDelimiter()));
            String fileName = options.getOutput().resolve(filterList.get(0) + ".csv").toString();
            LabelMatcher matcher = LabeledOperations.expressionMatcher(filterList.get(1).trim());
            iterateInput(matcher, fileName);
        }
    }
}

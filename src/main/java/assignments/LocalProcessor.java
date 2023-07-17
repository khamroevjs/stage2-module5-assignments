package assignments;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import assignments.annotations.FullNameProcessorGeneratorAnnotation;
import assignments.annotations.ListIteratorAnnotation;
import assignments.annotations.ReadFullProcessorNameAnnotation;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
@Setter
public class LocalProcessor {
    private String processorName;
    private long period = 10000000000000L;
    protected String processorVersion;
    private int valueOfCheap;
    private Scanner informationScanner;
    private List<String> stringList = new LinkedList<>();
    private static Logger logger = LoggerFactory.getLogger(LocalProcessor.class);
    private StringBuilder builder;

    public LocalProcessor(String processorName, long period, String processorVersion, int valueOfCheap,
                          Scanner informationScanner, List<String> stringList) {
        this.processorName = processorName;
        this.period = period;
        this.processorVersion = processorVersion;
        this.valueOfCheap = valueOfCheap;
        this.informationScanner = informationScanner;
        this.stringList = stringList;
    }

    public LocalProcessor() {
    }

    @ListIteratorAnnotation
    public void listIterator(List<String> stringList) {
        this.stringList = new LinkedList<>(stringList);
        for (String s : this.stringList) {
            logger.info(String.valueOf(s.hashCode()));
        }
    }

    @FullNameProcessorGeneratorAnnotation
    public String fullNameProcessorGenerator(List<String> stringList) {
        builder = new StringBuilder();
        for (int i = 0; i < this.stringList.size(); i++) {
            builder.append(stringList.get(i));
            builder.append(' ');
        }
        processorName = builder.toString();
        return processorName;
    }

    @ReadFullProcessorNameAnnotation
    public void readFullProcessorName(File file) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(file)) {
            informationScanner = scanner;
            builder = new StringBuilder();
            while (informationScanner.hasNext()) {
                builder.append(informationScanner.nextLine());
            }
            processorName = builder.toString();
        }
    }
}

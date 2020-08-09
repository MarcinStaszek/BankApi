package pl.staszek.bank.app.validator;

import java.time.LocalDate;

public class PeselUtil {

    private int[] pesel;

    public PeselUtil(String peselStr) {
        pesel = peselStr.chars()
                .map(ch -> Character.getNumericValue(ch))
                .toArray();
    }

    public LocalDate getDateOfBirth() {
        int year = extractTensAndOnes(pesel[0], pesel[1]);
        int month = extractTensAndOnes(pesel[2], pesel[3]);
        int dayOfMonth = extractTensAndOnes(pesel[4], pesel[5]);

        if (month > 80 && month < 93) {
            year += 1800;
            month -= 80;
        }
        else if (month > 0 && month < 13) {
            year += 1900;
        }
        else if (month > 20 && month < 33) {
            year += 2000;
            month -= 20;
        }
        else if (month > 40 && month < 53) {
            year += 2100;
            month -= 40;
        }
        else if (month > 60 && month < 73) {
            year += 2200;
            month -= 60;
        }

        return LocalDate.of(year, month, dayOfMonth);
    }

    private int extractTensAndOnes(int tens, int ones) {
        return 10 * tens + ones;
    }
}

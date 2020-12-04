import lombok.*;

import java.util.regex.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Passport {

    private String birthYear;

    private String issueYear;

    private String expirationYear;

    private String height;

    private String hairColor;

    private String eyeColor;

    private String passportID;

    private String countryID;

    public boolean isValid(){
        return validateBirthYear() && validateIssueYear() && validateExpirationYear() && valiHeight() && validateHairColor() && validateEyesColor() && validatePassportId();
    }

    private boolean validatePassportId() {
        final Pattern pattern = Pattern.compile("^[0-9]{9}$");
        return pattern.matcher(this.passportID).matches();
    }

    private boolean validateHairColor() {
        final Pattern pattern = Pattern.compile("^(#[0-9a-f]{6})$");
        return pattern.matcher(this.hairColor).matches();
    }

    private boolean validateEyesColor() {
        final Pattern pattern = Pattern.compile("^(amb|blu|brn|gry|grn|hzl|oth)$");
        return pattern.matcher(this.eyeColor).matches();
    }

    private boolean valiHeight() {
        final Pattern pattern = Pattern.compile("^((1([5-8][0-9]|9[0-3])cm)|((59|6[0-9]|7[0-6])in))$");
        return pattern.matcher(this.height).matches();
    }

    private boolean validateExpirationYear(){
        final Pattern pattern = Pattern.compile("^(2030|202[0-9])$");
        return pattern.matcher(this.expirationYear).matches();
    }

    private boolean validateIssueYear(){
        final Pattern pattern = Pattern.compile("^(2020|201[0-9])$");
        return pattern.matcher(this.issueYear).matches();
    }

    private boolean validateBirthYear(){
        final Pattern pattern = Pattern.compile("^(200[0-2]|19[2-9][0-9])$");
        return pattern.matcher(this.birthYear).matches();
    }
}